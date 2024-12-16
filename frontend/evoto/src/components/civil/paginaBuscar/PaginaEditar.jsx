import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const PaginaEditar = () => {
  const { id } = useParams(); // Obtiene el ID de la persona desde la URL
  const navigate = useNavigate();


  const [formData, setFormData] = useState({
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "",
    entidadFederativaId: "",
    municipioId: "",
    fechaDeInicio: "",
  });

  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Fetch inicial para cargar datos de la persona y listas de entidades/municipios
  useEffect(() => {
    fetchPersonaData();
    fetchEntidades();
  }, []);

  const fetchPersonaData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`http://localhost:8080/api/persona/${id}/editar`);
      if (!response.ok) {
        throw new Error("Error al obtener los datos de la persona.");
      }
      const data = await response.json();
      setFormData({
        nombre: data.nombre || "",
        apellidoPaterno: data.apellidoPaterno || "",
        apellidoMaterno: data.apellidoMaterno || "",
        fechaDeNacimiento: data.fechaDeNacimiento || "",
        fechaDeFin: data.fechaDeFin || "",
        entidadFederativaId: data.entidadFederativaId || "",
        municipioId: data.municipioId || "",
        fechaDeInicio: data.fechaDeInicio || "",
      });
      if (data.entidadFederativaId) {
        fetchMunicipios(data.entidadFederativaId);
      }
    } catch (error) {
      console.error("Error al cargar datos de la persona:", error);
      setError("No se pudo cargar la información de la persona.");
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


    ///CAMPO DE VALIDACIONES///
    const today = new Date().toISOString().split("T")[0]; // Fecha actual
    const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
        .toISOString()
        .split("T")[0]; // Hace 100 años
    ///////

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    // Si se selecciona una entidad, cargar municipios
    if (name === "entidadFederativaId") {
      setFormData((prevData) => ({
        ...prevData,
        municipioId: "", // Limpiar municipio seleccionado
      }));
      fetchMunicipios(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      const response = await fetch(`http://localhost:8080/api/persona/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
      alert("Persona actualizada exitosamente.");
      navigate("/civil"); // Redirige a la página principal
    } catch (error) {
      console.error("Error al actualizar persona:", error);
      setError("No se pudo actualizar la información de la persona.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h1>Editar Persona</h1>
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
          min={oneHundredYearsAgo} // No anterior a hace 100 años
          max={today} // No posterior al día de hoy
          required
        />
      </div>
      <div>
        <label>Fecha de Fin:</label>
        <input
          type="date"
          name="fechaDeFin"
          value={formData.fechaDeFin}
          onChange={handleChange}
          min={formData.fechaDeNacimiento || oneHundredYearsAgo} // No anterior a la fecha de nacimiento
          max={today} // No posterior al día de hoy
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
          min={formData.fechaDeNacimiento || oneHundredYearsAgo} // No anterior a la fecha de nacimiento
          max={formData.fechaDeFin || today} // No posterior a la fecha de fin o al día de hoy
          required
        />
      </div>
        <button type="submit">Guardar Cambios</button>
        <button type="button" onClick={() => navigate("/civil")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default PaginaEditar;
