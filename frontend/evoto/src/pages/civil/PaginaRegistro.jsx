import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";



const PaginaRegistro = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeInicio: "",
    fechaDeFin: "",
    entidadFederativaId: "",
    municipioId: "",
  });

  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
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

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    if (name === "entidadFederativaId") {
      fetchMunicipios(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const response = await fetch("http://localhost:8080/api/persona/registrar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.status === 409) {
        const errorMessage = await response.text();
        if (window.confirm(`${errorMessage}\n¿Desea continuar?`)) {
          await fetch("http://localhost:8080/api/persona/registrar?confirmar=true", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
          });
          alert("Persona registrada exitosamente.");
          navigate("/civil");
        }
      } else if (!response.ok) {
        throw new Error(await response.text());
      } else {
        alert("Persona registrada exitosamente.");
        navigate("/civil");
      }
    } catch (error) {
      console.error("Error al registrar persona:", error);
      setError("Error al registrar la persona. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Persona</h1>
      
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
            pattern="^[A-Z]+( [A-Z]+)*$"
            title="El nombre debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales."
          />
        </div>
        <div>
          <label>Apellido Paterno:</label>
          <input
            type="text"
            name="apellidoPaterno"
            value={formData.apellidoPaterno}
            onChange={handleChange}
            required
            pattern="^[A-Z]+( [A-Z]+)*$"
            title="El apellido paterno debe contener solo letras mayúsculas, sin números ni caracteres especiales."
          />
        </div>
        <div>
          <label>Apellido Materno:</label>
          <input
            type="text"
            name="apellidoMaterno"
            value={formData.apellidoMaterno}
            onChange={handleChange}
            required
            pattern="^[A-Z]+( [A-Z]+)*$"
            title="El apellido materno debe contener solo letras mayúsculas, sin números ni caracteres especiales."
          />
        </div>
        <div>
          <label>Fecha de Nacimiento:</label>
          <input
            type="date"
            name="fechaDeNacimiento"
            value={formData.fechaDeNacimiento}
            onChange={handleChange}
            min={oneHundredYearsAgo}
            max={today}
            required
          />
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
          <label>Fecha de Inicio:</label>
          <input
            type="date"
            name="fechaDeInicio"
            value={formData.fechaDeInicio}
            onChange={handleChange}
            min={formData.fechaDeNacimiento || oneHundredYearsAgo}
            max={formData.fechaDeFin || today}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/civil")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default PaginaRegistro;
