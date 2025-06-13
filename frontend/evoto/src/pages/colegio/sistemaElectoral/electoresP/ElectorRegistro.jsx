import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscar from './ModalBuscarPersona';
import { Modal, Button} from 'react-bootstrap';

const ElectorRegistro = () => {
  const today = new Date().toISOString().split("T")[0];
  const [formData, setFormData] = useState({
    idDePersona: "",
    entidadFederativaId: "",
    municipioId: "",
    localidadId: "",
    coloniaId: "",
    codigoPostalId: "",
    calle: "",
    numeroExterior: "",
    numeroInterior: "",
    fechaDeInicio: "",
    fechaDeInicioElector: "",
  });

  const [showModal, setShowModal] = useState(false); // Controla la visibilidad del modal



  const handleSelectPersona = (id) => {
    setFormData((prev) => ({ ...prev, idDePersona: id }));
    setShowModal(false); // Cierra el modal al seleccionar
  };

  const handleCloseModal = () => {
    setShowModal(false);
    console.log("Modal cerrado");
  };

  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [colonias, setColonias] = useState([]);
  const [codigosPostales, setCodigosPostales] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();


  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0];

  useEffect(() => {
    fetchEntidades();
  }, []);

  const fetchEntidades = async () => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/entidad-federativa`);
      const data = await response.json();
      setEntidades(data);
    } catch (error) {
      console.error("Error al cargar entidades federativas:", error);
    }
  };

  const fetchMunicipios = async (entidadId) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/localidad/municipio/${municipioId}`);
      const data = await response.json();
      setLocalidades(data);
    } catch (error) {
      console.error("Error al cargar localidades:", error);
    }
  };

  const fetchColonias = async (municipioId) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/colonia/municipio/${municipioId}`);
      const data = await response.json();
      setColonias(data);
    } catch (error) {
      console.error("Error al cargar colonias:", error);
    }
  };

  const fetchCodigosPostales = async (coloniaId) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/postal/colonia/${coloniaId}`);
      const data = await response.json();
      setCodigosPostales(data);
    } catch (error) {
      console.error("Error al cargar códigos postales:", error);
    }
  };

  const handleRegresar = () => {
    navigate("/colegio/sistema/ele"); // Regresa al menú anterior
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    // Cargar datos dependientes
    if (name === "entidadFederativaId") fetchMunicipios(value);
    if (name === "municipioId") {
      fetchLocalidades(value);
      fetchColonias(value);
    }
    if (name === "coloniaId") fetchCodigosPostales(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    if (!formData.idDePersona || isNaN(formData.idDePersona)) {
      alert("Debe seleccionar un ID de Persona válido.");
      return;
    }
  
    setLoading(true);
    setError("");
  
    // Extraer parámetros de la solicitud
    const { idDePersona, fechaDeInicioElector, ...domicilioDTO } = formData;
  
    try {
      const queryParams = new URLSearchParams({
        idDePersona: idDePersona,
        fechaDeInicioElector: fechaDeInicioElector || new Date().toISOString().split("T")[0], // Fecha por defecto
      });
  
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/elector/registrar?${queryParams.toString()}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(domicilioDTO), // Solo el objeto DomicilioDTO en el cuerpo
        }
      );
  
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
  
      alert("Elector registrado exitosamente.");
      navigate("/colegio/sistema/ele");
    } catch (error) {
      alert(`${error}`);
      console.error("Error al registrar elector:", error);
      setError("Error al registrar el elector. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Elector</h1>
      <button onClick={handleRegresar} style={{ marginBottom: "20px" }}>
  Regresar
</button>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
      <div>
          <label>Persona ID:</label>
          <input
            type="text"
            name="idDePersona"
            value={formData.idDePersona}
            onChange={(e) => setFormData({ ...formData, idDePersona: e.target.value })}
            required
            pattern="^[0-9]+$"
            title="El ID de la persona debe ser un número válido."
            disabled
          />
      {/* Button to open the modal */}
      <button variant="primary" onClick={() => setShowModal(true)}>
        Buscar Persona
      </button>
        </div>

        <div>
          <label>Entidad Federativa:</label>
          <select
            name="entidadFederativaId"
            value={formData.entidadFederativaId}
            onChange={handleChange}
            required
          >
            <option value="">Seleccione Entidad Federativa</option>
            {Array.isArray(entidades)&&entidades.map((entidad) => (
              <option key={entidad.id} value={entidad.id}>
                {entidad.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Municipio:</label>
          <select
            name="municipioId"
            value={formData.municipioId}
            onChange={handleChange}
            required
            disabled={!municipios.length}
          >
            <option value="">Seleccione Municipio</option>
            {Array.isArray(municipios)&&municipios.map((municipio) => (
              <option key={municipio.id} value={municipio.id}>
                {municipio.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Localidad:</label>
          <select
            name="localidadId"
            value={formData.localidadId}
            onChange={handleChange}
            required
            disabled={!localidades.length}
          >
            <option value="">Seleccione Localidad</option>
            {Array.isArray(localidades)&&localidades.map((localidad) => (
              <option key={localidad.id} value={localidad.id}>
                {localidad.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Colonia:</label>
          <select
            name="coloniaId"
            value={formData.coloniaId}
            onChange={handleChange}
            required
            disabled={!colonias.length}
          >
            <option value="">Seleccione Colonia</option>
            {Array.isArray(colonias)&&colonias.map((colonia) => (
              <option key={colonia.id} value={colonia.id}>
                {colonia.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Codigo postal:</label>
          <select
            name="codigoPostalId"
            value={formData.codigoPostalId}
            onChange={handleChange}
            required
            disabled={!codigosPostales.length}
          >
            <option value="">Seleccione Colonia</option>
            {Array.isArray(codigosPostales)&&codigosPostales.map((codigoPostal) => (
              <option key={codigoPostal.id} value={codigoPostal.id}>
                {codigoPostal.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Calle:</label>
          <input
            type="text"
            name="calle"
            value={formData.calle}
            onChange={handleChange}
            required
            pattern="^[A-Z]+( [A-Z]+)*$"
            title="La calle debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales."
          />
        </div>
        <div>
          <label>Número Exterior:</label>
          <input
            type="number"
            name="numeroExterior"
            value={formData.numeroExterior || ""}
            onChange={handleChange}
            min="1"
          />
        </div>
        <div>
          <label>Número Interior:</label>
          <input
            type="number"
            name="numeroInterior"
            value={formData.numeroInterior || ""}
            onChange={handleChange}
            min="1"
          />
        </div>
        <div>
          <label>Fecha de Inicio:</label>
          <input
            type="date"
            name="fechaDeInicio"
            value={formData.fechaDeInicio}
            onChange={handleChange}
            min={oneHundredYearsAgo}
            max={today}
            required
          />
        </div>
        <div>
          <label>Fecha de Registro:</label>
          <input
            type="date"
            name="fechaDeInicioElector"
            value={today}
            onChange={handleChange}
            min={oneHundredYearsAgo}
            max={today}
            disabled
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/colegio/sistema/ele")}>
          Cancelar
        </button>
      </form>

      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>Buscar Persona</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ModalBuscar onSelect={handleSelectPersona} />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cerrar
          </Button>
        </Modal.Footer>
      </Modal>
      
    </div>
  );
};

export default ElectorRegistro;
