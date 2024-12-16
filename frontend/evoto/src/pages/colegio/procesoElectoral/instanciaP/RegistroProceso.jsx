import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const RegistroProceso = () => {
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
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchEntidades();
    fetchNiveles();
    fetchProcesos();
  }, []);

  const fetchEntidades = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/entidades");
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
      const response = await fetch(`http://localhost:8080/api/municipios/${entidadId}`);
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
      const response = await fetch(`http://localhost:8080/api/localidades/${municipioId}`);
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

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    if (name === "idDeEntidadFederativa") {
      setFormData((prevData) => ({
        ...prevData,
        idDeMunicipio: "",
        idDeLocalidad: "",
      }));
      fetchMunicipios(value);
    }

    if (name === "idDeMunicipio") {
      setFormData((prevData) => ({
        ...prevData,
        idDeLocalidad: "",
      }));
      fetchLocalidades(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const response = await fetch("http://localhost:8080/api/instancia-proceso/registrar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(await response.text());
      }

      alert("Proceso registrado exitosamente.");
      navigate("/procesos");
    } catch (error) {
      console.error("Error al registrar proceso:", error);
      setError("Error al registrar el proceso. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Proceso</h1>
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
            {niveles.map((nivel) => (
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
            value={formData.idDeProceso}
            onChange={handleChange}
            required
          >
            <option value="">Seleccione Proceso</option>
            {procesos.map((proceso) => (
              <option key={proceso.id} value={proceso.id}>
                {proceso.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Fecha y Hora de Inicio:</label>
          <input
            type="datetime-local"
            name="fechaHoraDeInicio"
            value={formData.fechaHoraDeInicio}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Fecha y Hora de Fin:</label>
          <input
            type="datetime-local"
            name="fechaHoraDeFin"
            value={formData.fechaHoraDeFin}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Número de Ganadores:</label>
          <input
            type="number"
            name="ganadoresNum"
            value={formData.ganadoresNum}
            onChange={handleChange}
            required
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
            name="idDeMunicipio"
            value={formData.idDeMunicipio}
            onChange={handleChange}
            disabled={!municipios.length}
            required
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
            required
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
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/procesos")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default RegistroProceso;
