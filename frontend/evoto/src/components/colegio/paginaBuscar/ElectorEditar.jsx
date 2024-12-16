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

  // Fechas para validaciones
  const today = new Date().toISOString().split("T")[0]; // Fecha actual
  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0]; // Hace 100 años

  // Fetch inicial para cargar los datos del elector
  useEffect(() => {
    fetchElectorData();
    fetchEntidades();
  }, []);

  const fetchElectorData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`http://localhost:8080/api/elector/${id}/editar`);
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
      if (data.localidadId) fetchColonias(data.localidadId);
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
      const response = await fetch("http://localhost:8080/api/entidad-federativa");
      const data = await response.json();
      setEntidades(data);
    } catch (error) {
      console.error("Error al cargar entidades federativas:", error);
    }
  };

  const fetchMunicipios = async (entidadId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/localidad/municipio/${municipioId}`);
      const data = await response.json();
      setLocalidades(data);
    } catch (error) {
      console.error("Error al cargar localidades:", error);
    }
  };

  const fetchColonias = async (localidadId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/colonia/localidad/${localidadId}`);
      const data = await response.json();
      setColonias(data);
    } catch (error) {
      console.error("Error al cargar colonias:", error);
    }
  };

  const fetchCodigosPostales = async (coloniaId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/codigo-postal/colonia/${coloniaId}`);
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
    if (name === "municipioId") fetchLocalidades(value);
    if (name === "localidadId") fetchColonias(value);
    if (name === "coloniaId") fetchCodigosPostales(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      const response = await fetch(`http://localhost:8080/api/elector/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
      alert("Elector actualizado exitosamente.");
      navigate("/electores");
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
          <label>Fecha de Inicio Elector:</label>
          <input
            type="date"
            name="fechaDeInicioElector"
            value={formData.fechaDeInicioElector}
            onChange={handleChange}
            min={oneHundredYearsAgo}
            max={today}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Guardando..." : "Guardar Cambios"}
        </button>
        <button type="button" onClick={() => navigate("/electores")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default ElectorEditar;
