import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const ElectorRegistro = () => {
  const [formData, setFormData] = useState({
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
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const today = new Date().toISOString().split("T")[0];
  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0];

  useEffect(() => {
    fetchEntidades();
  }, []);

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
      if (!entidadId) {
        setMunicipios([]);
        return;
      }
      const response = await fetch(`http://localhost:8080/api/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
      if (!municipioId) {
        setLocalidades([]);
        return;
      }
      const response = await fetch(`http://localhost:8080/api/localidad/municipio/${municipioId}`);
      const data = await response.json();
      setLocalidades(data);
    } catch (error) {
      console.error("Error al cargar localidades:", error);
    }
  };

  const fetchColonias = async (localidadId) => {
    try {
      if (!localidadId) {
        setColonias([]);
        return;
      }
      const response = await fetch(`http://localhost:8080/api/colonia/localidad/${localidadId}`);
      const data = await response.json();
      setColonias(data);
    } catch (error) {
      console.error("Error al cargar colonias:", error);
    }
  };

  const fetchCodigosPostales = async (coloniaId) => {
    try {
      if (!coloniaId) {
        setCodigosPostales([]);
        return;
      }
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
      const response = await fetch("http://localhost:8080/api/elector/registrar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      alert("Elector registrado exitosamente.");
      navigate("/electores");
    } catch (error) {
      console.error("Error al registrar elector:", error);
      setError("Error al registrar el elector. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Elector</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Persona ID:</label>
          <input
            type="text"
            name="idPersona"
            value={formData.idPersona}
            onChange={handleChange}
            required
            pattern="^[0-9]+$"
            title="El ID de la persona debe ser un número válido."
          />
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
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/electores")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default ElectorRegistro;
