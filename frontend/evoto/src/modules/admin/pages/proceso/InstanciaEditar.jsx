import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import TimerRedirect from "../../../../shared/components/timerRefresher/TimerRefresher";

const InstanciaEditar = () => {
  const { id } = useParams(); // Obtiene el ID de la instancia desde la URL
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    idDeNivel: "",
    idDeProceso: "",
    fechaHoraDeInicio: "",
    fechaHoraDeFin: "",
    ganadoresNum: "",
    idDeEntidadFederativa: "",
    idDeMunicipio: "",
    idDeLocalidad: "",
  });

  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [niveles, setNiveles] = useState([]);
  const [procesos, setProcesos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Cargar los datos iniciales
  useEffect(() => {
    fetchInstanciaData();
    fetchEntidades();
    fetchProcesos();
    fetchNiveles();
  }, []);

  const fetchInstanciaData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/instancia/editar/${id}`);
      if (!response.ok) {
        throw new Error("Error al obtener los datos de la instancia.");
      }
      const data = await response.json();
      setFormData({
        idDeNivel: data.idDeNivel || "",
        idDeProceso: data.idDeProceso || "",
        fechaHoraDeInicio: data.fechaHoraDeInicio || "",
        fechaHoraDeFin: data.fechaHoraDeFin || "",
        ganadoresNum: data.ganadoresNum || "",
        idDeEntidadFederativa: data.idDeEntidadFederativa || "",
        idDeMunicipio: data.idDeMunicipio || "",
        idDeLocalidad: data.idDeLocalidad || "",
      });
      if (data.idDeEntidadFederativa) fetchMunicipios(data.idDeEntidadFederativa);
      if (data.idDeMunicipio) fetchLocalidades(data.idDeMunicipio);
    } catch (error) {
      console.error("Error al cargar instancia:", error);
      setError("No se pudo cargar la información de la instancia.");
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
      if (!entidadId) {
        setMunicipios([]);
        setLocalidades([]);
        return;
      }
      const response = await fetch(`${import.meta.env.VITE_API_URL}/municipio/entidad/${entidadId}`);
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
      const response = await fetch(`${import.meta.env.VITE_API_URL}/localidad/municipio/${municipioId}`);
      const data = await response.json();
      setLocalidades(data);
    } catch (error) {
      console.error("Error al cargar localidades:", error);
    }
  };

  const fetchNiveles = async () => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/niveles`);
      const data = await response.json();
      setNiveles(data);
    } catch (error) {
      console.error("Error al cargar niveles:", error);
    }
  };

  const fetchProcesos = async () => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/procesos`);
      const data = await response.json();
      setProcesos(data);
    } catch (error) {
      console.error("Error al cargar procesos:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    // Cargar datos dependientes
    if (name === "idDeEntidadFederativa") fetchMunicipios(value);
    if (name === "idDeMunicipio") fetchLocalidades(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/instancia/editar/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
      alert("Instancia actualizada exitosamente.");
      navigate("/colegio/proceso");
    } catch (error) {
      console.error("Error al actualizar instancia:", error);
      setError("No se pudo actualizar la información de la instancia.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <TimerRedirect rutaDestino ="/colegio/proceso"/>
      <h1>Editar Instancia</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
      <div>
          <label>Nivel:</label>
          <select
            name="idDeNivel"
            value={formData.idDeNivel}
            onChange={handleChange}
            required
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
          name="idDeProceso"
          value={formData.idDeProceso }
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
          <label>Fecha/Hora de Inicio:</label>
          <input
            type="datetime-local"
            name="fechaHoraDeInicio"
            value={formData.fechaHoraDeInicio}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Fecha/Hora de Fin:</label>
          <input
            type="datetime-local"
            name="fechaHoraDeFin"
            value={formData.fechaHoraDeFin}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Entidad Federativa:</label>
          <select
            name="idDeEntidadFederativa"
            value={formData.idDeEntidadFederativa}
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
            name="idDeMunicipio"
            value={formData.idDeMunicipio}
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
            name="idDeLocalidad"
            value={formData.idDeLocalidad}
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
        <button type="submit" disabled={loading}>
          {loading ? "Guardando..." : "Guardar Cambios"}
        </button>
        <button type="button" onClick={() => navigate("/colegio/proceso")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default InstanciaEditar;
