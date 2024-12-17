import React, { useState, useEffect } from "react";

const FiltrosInstancia = ({ filtros, setFiltros, onBuscar }) => {
  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [niveles, setNiveles] = useState([]);
  const [procesos, setProcesos] = useState([]);

  // Cargar datos iniciales
  useEffect(() => {
    fetchEntidades();
    fetchNiveles();
    fetchProcesos();
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
        setLocalidades([]);
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

  const fetchNiveles = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/niveles");
      const data = await response.json();
      setNiveles(data);
    } catch (error) {
      console.error("Error al cargar niveles:", error);
    }
  };

  const fetchProcesos = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/procesos");
      const data = await response.json();
      setProcesos(data);
    } catch (error) {
      console.error("Error al cargar procesos:", error);
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
    if (name === "idEntidadFederativa") {
      fetchMunicipios(newValue);
    } else if (name === "idMunicipio") {
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
        <label>ID de Instancia:</label>
        <input
          type="text"
          name="idDeInstanciaDeProceso"
          value={filtros.idDeInstanciaDeProceso || null}
          onChange={handleChange}
          pattern="^\d*$" // Solo números
          title="El ID solo puede contener números."
        />
      </div>
      <div>
        <label>Nivel:</label>
        <select
          name="idNivel"
          value={filtros.idNivel || null}
          onChange={handleChange}
          disabled={!niveles.length}
        >
          <option value="">Seleccione Nivel</option>
          {Array.isArray(niveles)&&niveles.map((nivel) => (
            <option key={nivel.id} value={nivel.id}>
              {nivel.descripcion}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Proceso:</label>
        <select
          name="idProceso"
          value={filtros.idProceso || null}
          onChange={handleChange}
          disabled={!procesos.length}
        >
          <option value="">Seleccione Proceso</option>
          {Array.isArray(procesos)&&procesos.map((proceso) => (
            <option key={proceso.id} value={proceso.id}>
              {proceso.descripcion}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Año de Inicio:</label>
        <select
          name="anioInicio"
          value={filtros.anioInicio || null}
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
        <label>Mes de Inicio:</label>
        <select
          name="mesInicio"
          value={filtros.mesInicio || null}
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
        <label>Día de Inicio:</label>
        <select
          name="diaInicio"
          value={filtros.diaInicio || null}
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
          name="idEntidadFederativa"
          value={filtros.idEntidadFederativa || ""}
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
          name="idMunicipio"
          value={filtros.idMunicipio || null}
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
          name="idLocalidad"
          value={filtros.idLocalidad || null}
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

export default FiltrosInstancia;
