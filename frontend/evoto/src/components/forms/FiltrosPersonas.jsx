import React, { useState, useEffect } from "react";

const FiltrosPersonas = ({ filtros, setFiltros, onBuscar }) => {
  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [colonias, setColonias] = useState([]);
  const [codigosPostales, setCodigosPostales] = useState([]);

  // Fetch inicial para cargar entidades federativas
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
      const response = await fetch(`http://localhost:8080/api/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
      setLocalidades([]);
      setColonias([]);
      setCodigosPostales([]);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/localidad/municipio/${municipioId}`);
      const data = await response.json();
      setLocalidades(data);
      setColonias([]);
      setCodigosPostales([]);
    } catch (error) {
      console.error("Error al cargar localidades:", error);
    }
  };

  const fetchColonias = async (municipioId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/colonia/municipio/${municipioId}`);
      const data = await response.json();
      setColonias(data);
      setCodigosPostales([]);
    } catch (error) {
      console.error("Error al cargar colonias:", error);
    }
  };

  const fetchCodigosPostales = async (coloniaId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/postal/colonia/${coloniaId}`);
      const data = await response.json();
      setCodigosPostales(data);
    } catch (error) {
      console.error("Error al cargar códigos postales:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFiltros((prevFiltros) => ({
      ...prevFiltros,
      [name]: value,
    }));

    // Actualizar datos dependientes
    if (name === "entidadFederativa") {
      fetchMunicipios(value);
      setMunicipios([]);
      setLocalidades([]);
      setColonias([]);
      setCodigosPostales([]);
    } else if (name === "municipio") {
      fetchLocalidades(value);
      fetchColonias(value);
      setLocalidades([]);
      setColonias([]);
      setCodigosPostales([]);
    } else if (name === "localidad") {
      fetchColonias(value);
      setColonias([]);
      setCodigosPostales([]);
    } else if (name === "colonia") {
      fetchCodigosPostales(value);
      setCodigosPostales([]);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onBuscar(filtros); // Llama a la función del padre para buscar con los filtros actuales
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      {/* Campos básicos */}
      <div>
        <label>ID:</label>
        <input
          type="text"
          name="id"
          value={filtros.id || ""}
          onChange={handleChange}
          placeholder="ID"
        />
      </div>
      <div>
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={filtros.nombre || ""}
          onChange={handleChange}
          placeholder="Nombre"
        />
      </div>
      <div>
        <label>Apellido Paterno:</label>
        <input
          type="text"
          name="apellidoPaterno"
          value={filtros.apellidoPaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Paterno"
        />
      </div>
      <div>
        <label>Apellido Materno:</label>
        <input
          type="text"
          name="apellidoMaterno"
          value={filtros.apellidoMaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Materno"
        />
      </div>
      <div>
        <label>Año de Nacimiento:</label>
        <select
          name="anioNacimiento"
          value={filtros.anioNacimiento || ""}
          onChange={handleChange}
          >
          <option value="">Seleccione</option>
          {[...Array(100)].map((_, i) => {
            const year = new Date().getFullYear() - i; // Últimos 100 años
            return <option key={year} value={year}>{year}</option>;
          })}
        </select>
      </div>

      <div>
        <label>Mes de Nacimiento:</label>
        <select
          name="mesNacimiento"
          value={filtros.mesNacimiento || ""}
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
          value={filtros.diaNacimiento || ""}
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
        <label>Año de Fin:</label>
        <select
          name="anioFin"
          value={filtros.anioFin || ""}
          onChange={handleChange}
          >
          <option value="">Seleccione</option>
          {[...Array(100)].map((_, i) => {
            const year = new Date().getFullYear() - i; // Últimos 100 años
            return <option key={year} value={year}>{year}</option>;
          })}
        </select>
      </div>

      <div>
        <label>Mes de Fin:</label>
        <select
          name="mesFin"
          value={filtros.mesFin || ""}
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
          name="diaFin"
          value={filtros.diaFin || ""}
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
          value={filtros.municipio || ""}
          onChange={handleChange}
          disabled={!municipios.length}
        >
          <option value="">Seleccione Municipio</option>
          {municipios.map((municipio) => (
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
          value={filtros.localidad || ""}
          onChange={handleChange}
          disabled={!localidades.length}
        >
          <option value="">Seleccione Localidad</option>
          {localidades.map((localidad) => (
            <option key={localidad.id} value={localidad.id}>
              {localidad.descripcion}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Colonia:</label>
        <select
          name="colonia"
          value={filtros.colonia || ""}
          onChange={handleChange}
          disabled={!colonias.length}
        >
          <option value="">Seleccione Colonia</option>
          {colonias.map((colonia) => (
            <option key={colonia.id} value={colonia.id}>
              {colonia.descripcion}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Código Postal:</label>
        <select
          name="codigoPostal"
          value={filtros.codigoPostal || ""}
          onChange={handleChange}
          disabled={!codigosPostales.length}
        >
          <option value="">Seleccione Código Postal</option>
          {codigosPostales.map((codigoPostal) => (
            <option key={codigoPostal.id} value={codigoPostal.id}>
              {codigoPostal.descripcion}
            </option>
          ))}
        </select>
      </div>
      <button type="submit">Buscar</button>
    </form>
  );
};

export default FiltrosPersonas;
