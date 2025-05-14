import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const ElectorEditar = () => {
  const { id } = useParams(); // Obtiene el ID del elector desde la URL
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    idElector: "",
    idPersona: "",
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

  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [colonias, setColonias] = useState([]);
  const [codigosPostales, setCodigosPostales] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
    
  
  
  // Fetch inicial para cargar los datos del elector
  useEffect(() => {
    fetchElectorData();
    fetchEntidades();
  }, []);

  // Fechas para validaciones
  const today = new Date().toISOString().split("T")[0]; // Fecha actual
  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0]; // Hace 100 años


  const fetchElectorData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/elector/${id}/editar`);
      if (!response.ok) {
        throw new Error("Error al obtener los datos del elector.");
      }
      const data = await response.json();
      setFormData({
        idElector: data.idElector || "",
        idPersona: data.idPersona || "",
        entidadFederativaId: data.entidadFederativaId || "",
        municipioId: data.municipioId || "",
        localidadId: data.localidadId || "",
        coloniaId: data.coloniaId || "",
        codigoPostalId: data.codigoPostalId || "",
        calle: data.calle || "",
        numeroExterior: data.numeroExterior || "",
        numeroInterior: data.numeroInterior || "",
        fechaDeInicio: data.fechaDeInicio || "",
        fechaDeInicioElector: data.fechaDeInicioElector || "",
      });
      if (data.entidadFederativaId) fetchMunicipios(data.entidadFederativaId);
      if (data.municipioId) fetchLocalidades(data.municipioId);
      if (data.municipioId) fetchColonias(data.municipioId);
      if (data.coloniaId) fetchCodigosPostales(data.coloniaId);
    } catch (error) {
      console.error("Error al cargar datos del elector:", error);
      setError("No se pudo cargar la información del elector.");
    } finally {
      setLoading(false);
    }
  };

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
    setLoading(true);
    setError("");
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/elector/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
      alert("Elector actualizado exitosamente.");
      navigate("/colegio/sistema/ele");
    } catch (error) {
      console.error("Error al actualizar elector:", error);
      setError("No se pudo actualizar la información del elector.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Editar Elector</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID Elector:</label>
          <input type="text" name="idElector" value={formData.idElector} disabled />
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

        <button type="submit" disabled={loading}>
          {loading ? "Guardando..." : "Guardar Cambios"}
        </button>
        <button type="button" onClick={() => navigate("/colegio/sistema/ele")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default ElectorEditar;
