import React, { useState, useEffect } from "react";

const PaginaRegistrar = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    entidadFederativa: "",
    municipio: "",
  });
  const [entidades, setEntidades] = useState([]); // Lista de Entidades Federativas
  const [municipios, setMunicipios] = useState([]); // Lista de Municipios

  useEffect(() => {
    const fetchEntidades = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/entidad-federativa");
        if (!response.ok) {
          throw new Error("Error al cargar entidades federativas.");
        }
        const data = await response.json();
        setEntidades(data);
      } catch (error) {
        console.error("Error al cargar entidades federativas:", error);
      }
    };

    fetchEntidades();
  }, []);

  const cargarMunicipios = async (entidadId) => {
    if (!entidadId) return;
    try {
      const response = await fetch(`http://localhost:8080/api/municipio/entidad/${entidadId}`);
      if (!response.ok) {
        throw new Error("Error al cargar municipios.");
      }
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "entidadFederativa") {
      setFormData((prevData) => ({
        ...prevData,
        entidadFederativa: value,
        municipio: "", // Reiniciar municipio cuando cambia entidad federativa
      }));
      cargarMunicipios(value);
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value, // Permitir incluso valores vacíos
      }));
    }
  };

  const validateField = (name, value) => {
    if (["nombre", "apellidoPaterno", "apellidoMaterno"].includes(name) && value) {
      const regex = /^[A-ZÁÉÍÓÚÑ\s]+$/;
      return regex.test(value.trim());
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const today = new Date().toISOString().split("T")[0];
    if (formData.fechaDeNacimiento > today) {
      alert("La fecha de nacimiento no puede ser posterior al día de hoy.");
      return;
    }

    for (const [key, value] of Object.entries(formData)) {
      if (["nombre", "apellidoPaterno", "apellidoMaterno", "fechaDeNacimiento", "entidadFederativa", "municipio"].includes(key) && !value) {
        alert(`El campo ${key} es obligatorio.`);
        return;
      }
      if (["nombre", "apellidoPaterno", "apellidoMaterno"].includes(key) && !validateField(key, value)) {
        alert(`El campo ${key} contiene caracteres inválidos.`);
        return;
      }
    }

    try {
      const response = await fetch("http://localhost:8080/api/persona", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("Persona registrada con éxito.");
        setFormData({
          nombre: "",
          apellidoPaterno: "",
          apellidoMaterno: "",
          fechaDeNacimiento: "",
          entidadFederativa: "",
          municipio: "",
        });
      } else {
        const errorData = await response.json();
        alert(`Error al registrar persona: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error al registrar persona:", error);
      alert("Hubo un problema al intentar registrar la persona.");
    }
  };

  return (
    <div>
      <h1>Registrar Persona</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
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
          />
        </div>
        <div>
          <label>Apellido Materno:</label>
          <input
            type="text"
            name="apellidoMaterno"
            value={formData.apellidoMaterno}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Fecha de Nacimiento:</label>
          <input
            type="date"
            name="fechaDeNacimiento"
            value={formData.fechaDeNacimiento}
            onChange={handleChange}
            max={new Date().toISOString().split("T")[0]}
            required
          />
        </div>
        <div>
          <label>Entidad Federativa:</label>
          <select
            name="entidadFederativa"
            value={formData.entidadFederativa}
            onChange={handleChange}
            required
          >
            <option value="">Seleccione una entidad</option>
            {entidades.map((entidad) => (
              <option key={entidad.id} value={entidad.id}>
                {entidad.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Municipio:</label>
          <select
            name="municipio"
            value={formData.municipio}
            onChange={handleChange}
            disabled={!formData.entidadFederativa}
            required
          >
            <option value="">Seleccione un municipio</option>
            {municipios.map((municipio) => (
              <option key={municipio.id} value={municipio.id}>
                {municipio.descripcion}
              </option>
            ))}
          </select>
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default PaginaRegistrar;
