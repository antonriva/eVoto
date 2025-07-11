import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css";
import "../../../../shared/styles/Buttons.css"; // Import global styles for buttons

const RegistroProceso = () => {
  const [formData, setFormData] = useState({
    idDeNivel: "",
    idDeProceso: "",
    fechaHoraDeInicio: "",
    fechaHoraDeFin: "",
    ganadoresNum: 1,
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

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Colegio electoral", to: "/colegio" },
    { label: "Procesos electorales", to: "/colegio/proceso" },
    { label: "Registrar proceso" },
  ];

  useEffect(() => {
    fetchEntidades();
    fetchNiveles();
    fetchProcesos();
  }, []);

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
      const response = await fetch(`${import.meta.env.VITE_API_URL}/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
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

  const today = new Date().toISOString().slice(0, 16); // Fecha y hora actual en formato "YYYY-MM-DDTHH:MM"


  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
      ...(name === "idDeEntidadFederativa" && { idDeMunicipio: "", idDeLocalidad: "" }),
      ...(name === "idDeMunicipio" && { idDeLocalidad: "" }),
    }));

    if (name === "idDeEntidadFederativa") fetchMunicipios(value); // Correct name check
    if (name === "idDeMunicipio") fetchLocalidades(value);
  };



  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Datos a enviar:", formData);
    setLoading(true);
    setError("");

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/instancia/crear`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(await response.text());
      }

      alert("Proceso registrado exitosamente.");
      navigate("/colegio/proceso");
    } catch (error) {
      console.error("Error al registrar proceso:", error);
      setError("Error al registrar el proceso. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

    const handleRegresar = () => {
    navigate("/colegio/proceso"); // Regresa al menú anterior
  };

  return (
    <div>
      <div className="app-layout-container"> 
      <Breadcrumbs items={breadcrumbItems} />
      <h1 className="text-center">Registrar proceso</h1>
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
            value={formData.idDeProceso}
            onChange={handleChange}
            required
            disabled={!procesos.length}
          >
            <option value="">Seleccione el tipo de proceso</option>
            {Array.isArray(procesos)&&procesos.map((proceso) => (
              <option key={proceso.id} value={proceso.id}>
                {proceso.descripcion}
              </option>
            ))}
          </select>
        </div>
        <div>
        <div>
  {/* Fecha y Hora de Inicio */}
  <div>
    <label>Fecha y Hora de Inicio:</label>
    <input
      type="datetime-local"
      name="fechaHoraDeInicio"
      value={formData.fechaHoraDeInicio}
      onChange={handleChange}
      //min={today} // Restringe a fechas no anteriores al día de hoy
      required
    />
  </div>

  {/* Fecha y Hora de Fin */}
  <div>
    <label>Fecha y Hora de Fin:</label>
    <input
      type="datetime-local"
      name="fechaHoraDeFin"
      value={formData.fechaHoraDeFin}
      onChange={handleChange}
      min={formData.fechaHoraDeInicio || today} // Restringe a después de la fecha de inicio
      required
      disabled={!formData.fechaHoraDeInicio} // Desactiva hasta que se seleccione Fecha de Inicio
    />
  </div>
</div>
    </div>
    <div>
          <label>Entidad Federativa:</label>
          <select
            name="idDeEntidadFederativa"
            value={formData.idDeEntidadFederativa}
            onChange={handleChange}
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
            {Array.isArray(municipios)&&municipios.map((municipio) => (
              <option key={municipio.id} value={municipio.id}>
                {municipio.descripcion}
              </option>
            ))}
          </select>
        </div>
        {/* <div>
          <label>Localidad:</label>
          <select
            name="idDeLocalidad"
            value={formData.idDeLocalidad}
            onChange={handleChange}
            disabled={!localidades.length}
          >
            <option value="">Seleccione Localidad</option>
            {Array.isArray(localidades)&&localidades.map((localidad) => (
              <option key={localidad.id} value={localidad.id}>
                {localidad.descripcion}
              </option>
            ))}
          </select>
        </div> */}
        <button className="btn btn-vino" type="submit" disabled={loading}>
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button className="btn btn-vino" type="button" onClick={() => navigate("/colegio/proceso")}>
          Cancelar
        </button>
      </form>
      </div>
    </div>
  );
};

export default RegistroProceso;
