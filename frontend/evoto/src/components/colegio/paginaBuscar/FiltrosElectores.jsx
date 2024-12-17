import React, { useState, useEffect } from "react";

const FiltrosPersonas = ({ filtros, setFiltros, onBuscar }) => {
  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [tiposDeDomicilio, setTiposDeDomicilio] = useState([]);

  // Cargar datos iniciales
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
      setLocalidades([]);
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




  const handleChange = (e) => {
    const { name, value } = e.target;
    const newValue = value === "" ? null : value; // Convertir cadenas vacías a null
    setFiltros((prevFiltros) => ({
      ...prevFiltros,
      [name]: newValue,
    }));

    // Actualizar dependencias dinámicas
    if (name === "entidadFederativa") {
      fetchMunicipios(newValue);
    } else if (name === "municipio") {
      fetchLocalidades(newValue);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onBuscar(filtros); // Llamar a la función de búsqueda con los filtros actuales
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      <div>
        <label>ID de Elector:</label>
        <input
          type="text"
          name="idElector"
          value={filtros.idElector || null}
          onChange={handleChange}
          pattern="^\d*$" // Solo números
          title="El ID solo puede contener números."
        />
      </div>
      <div>
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={filtros.nombre || ""}
          onChange={handleChange}
          pattern="^[a-zA-Z]+( [a-zA-Z]+)*$" // Letras mayúsculas y minúsculas, con espacios intermedios
          title="El nombre debe contener solo letras (mayúsculas o minúsculas) y espacios intermedios, sin números ni caracteres especiales."
        />
      </div>
      <div>
        <label>Apellido Paterno:</label>
        <input
          type="text"
          name="apellidoPaterno"
          value={filtros.apellidoPaterno || ""}
          onChange={handleChange}
          pattern="^[a-zA-Z]+( [a-zA-Z]+)*$" // Letras mayúsculas y minúsculas, con espacios intermedios
          title="El apellido debe contener solo letras (mayúsculas o minúsculas) y espacios intermedios, sin números ni caracteres especiales."
        />
      </div>
      <div>
        <label>Apellido Materno:</label>
        <input
          type="text"
          name="apellidoMaterno"
          value={filtros.apellidoMaterno || ""}
          onChange={handleChange}
          pattern="^[a-zA-Z]+( [a-zA-Z]+)*$" // Letras mayúsculas y minúsculas, con espacios intermedios
          title="El apellido debe contener solo letras (mayúsculas o minúsculas) y espacios intermedios, sin números ni caracteres especiales."
        />
      </div>
      <div>
        <label>Año de Nacimiento:</label>
        <select
          name="anioNacimiento"
          value={filtros.anioNacimiento || null}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 100 }, (_, i) => {
            const year = new Date().getFullYear() - i;
            return (
              <option key={year} value={year}>
                {year}
              </option>
            );
          })}
        </select>
      </div>
      <div>
        <label>Mes de Nacimiento:</label>
        <select
          name="mesNacimiento"
          value={filtros.mesNacimiento || null}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 12 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {new Date(0, i).toLocaleString("es", { month: "long" })}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Día de Nacimiento:</label>
        <select
          name="diaNacimiento"
          value={filtros.diaNacimiento || null}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 31 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {i + 1}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Entidad Federativa:</label>
        <select
          name="entidadFederativa"
          value={filtros.entidadFederativa || ""}
          onChange={handleChange}
        >
        <option value="">Seleccione Entidad Federativa</option>
        {Array.isArray(entidades) &&
          entidades.map((entidad) => (
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
          value={filtros.municipio || null}
          onChange={handleChange}
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
          name="localidad"
          value={filtros.localidad || null}
          onChange={handleChange}
          disabled={!localidades.length}
        >
          <option value="">Seleccione Localidad</option>
          {Array.isArray(localidades) &&localidades.map((localidad) => (
            <option key={localidad.id} value={localidad.id}>
              {localidad.descripcion}
            </option>
          ))}
        </select>
      </div>

      <button type="submit">Buscar</button>
    </form>
  );
};

export default FiltrosPersonas;
