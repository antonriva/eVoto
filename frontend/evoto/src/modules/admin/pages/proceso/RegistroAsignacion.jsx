import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarElector from "./ModalBuscarElector";
import { useParams } from "react-router-dom";

const RegistroCandidaturaYElector = () => {
  const today = new Date().toISOString().split("T")[0];
  const { id } = useParams(); // Obtiene el ID de la instancia desde la URL

  const [formData, setFormData] = useState({
    idDeProcesoElectoral: "",
    idDePartido: "",
    idDeElector: "",
    fechaDeInicio: today,
    fechaDeFin: "",
  });

  const [showModalElector, setShowModalElector] = useState(false);
  const [error, setError] = useState("");
  const [partidos, setPartidos] = useState([]);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const fetchPartidos = async () => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/partido/buscar/todo`);
      const data = await response.json();
      setPartidos(data);
    } catch (error) {
      console.error("Error al cargar partidos:", error);
    }
  };

  useEffect(() => {
    fetchPartidos();
  }, []);

    // Asigna automáticamente idInstancia al estado
    useEffect(() => {
      if (id) {
        setFormData((prev) => ({
          ...prev,
          idDeProcesoElectoral: id,
        }));
      }
    }, [id]);

  // Función para seleccionar elector desde el modal
  const handleSelectElector = (id) => {
    setFormData((prev) => ({ ...prev, idDeElector: id }));
    setShowModalElector(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

  };

  // Función principal para registrar candidatura y luego asignar elector
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    if (!formData.idDeProcesoElectoral || !formData.idDePartido || !formData.idDeElector) {
      setError("Debe seleccionar instancia, partido y elector.");
      setLoading(false);
      return;
    }

    try {
      // Paso 1: Crear candidatura
      const queryParams = new URLSearchParams({
        idDeProcesoElectoral: formData.idDeProcesoElectoral,
        idDePartido: formData.idDePartido,
      });



      

      const responseCandidatura = await fetch(
        `${import.meta.env.VITE_API_URL}/candidatura/base?${queryParams.toString()}`,
        { method: "POST" }
      );

      if (!responseCandidatura.ok) {
        const errorMessage = await responseCandidatura.text();
        throw new Error(`Error al crear candidatura: ${errorMessage}`);
      }

;
      const candidaturaId = await responseCandidatura.text();
      console.log("Candidatura ID recibido del servidor:", candidaturaId)
// Validar que los IDs no sean null, undefined o vacíos
if (!candidaturaId || isNaN(Number(candidaturaId))) {
  throw new Error("El ID de la candidatura es inválido.");
}

if (!formData.idDeElector || isNaN(Number(formData.idDeElector))) {
  throw new Error("El ID del elector es inválido.");
}

const relacionElectorCandidaturaParams = new URLSearchParams({
  idDeCandidatura: candidaturaId.toString(),   // Asegurar que sea string numérico
  idDeElector: formData.idDeElector.toString(),
  fechaHoraInicio: formData.fechaDeInicio || "", // Valores por defecto si están vacíos
  fechaHoraFin: formData.fechaDeFin || "",
}).toString();

const responseElector = await fetch(
  `${import.meta.env.VITE_API_URL}/candidatura/relacion?${relacionElectorCandidaturaParams}`, // Parámetros en la URL
  {
    method: "POST",
  }
);

    if (!responseElector.ok) {
      const errorMessage = await responseElector.text();
      throw new Error(`Error al asignar elector: ${errorMessage}`);
    }

    alert("Elector asignado correctamente a la candidatura.");
    navigate("/"); // Redireccionar al finalizar
  } catch (error) {
    console.error(error);
    setError(error.message);
  } finally {
    setLoading(false);
  }
};
  

  return (
    <div>
      <h1>Registro de Candidatura y Asignación de Elector</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handleSubmit}>

        {/* Partido */}
        <div>
          <label>Partido:</label>
          <select
            name="idDePartido"
            value={formData.idDePartido}
            onChange={handleChange}
            required
          >
            <option value="">Seleccione el partido</option>
            {Array.isArray(partidos)&&partidos.map((partido) => (
              <option key={partido.id} value={partido.id}>
                {partido.denominacion}
              </option>
            ))}
          </select>
        </div>

        {/* Elector */}
        <div>
          <label>ID de Elector:</label>
          <input
            type="text"
            value={formData.idDeElector}
            readOnly
            placeholder="Seleccione un elector"
          />
          <button type="button" onClick={() => setShowModalElector(true)}>
            Buscar Elector
          </button>
        </div>

        {/* Fechas */}
        <div>
          <label>Fecha de Inicio:</label>
          <input
            type="date"
            name="fechaDeInicio"
            value={formData.fechaDeInicio}
            onChange={(e) => setFormData({ ...formData, fechaDeInicio: e.target.value })}
            required
          />
        </div>
        <div>
          <label>Fecha de Fin:</label>
          <input
            type="date"
            name="fechaDeFin"
            value={formData.fechaDeFin}
            onChange={(e) => setFormData({ ...formData, fechaDeFin: e.target.value })}
            min={formData.fechaDeInicio}
            required
          />
        </div>

        <button type="submit" disabled={loading}>
          {loading ? "Procesando..." : "Registrar Candidatura y Asignar Elector"}
        </button>
        <button type="button" onClick={() => navigate("/candidaturas")}>
          Cancelar
        </button>
      </form>


      {showModalElector && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Elector</h2>
            <ModalBuscarElector onSelect={handleSelectElector} />
            <button onClick={() => setShowModalElector(false)}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default RegistroCandidaturaYElector;
