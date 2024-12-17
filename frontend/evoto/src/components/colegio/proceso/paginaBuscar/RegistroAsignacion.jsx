import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarInstancia from "./ModalBuscarInstancia";
import ModalBuscarPartido from "./ModalBuscarPartido";
import ModalBuscarElector from "./ModalBuscarElector";

const RegistroCandidaturaYElector = () => {
  const today = new Date().toISOString().split("T")[0];

  const [formData, setFormData] = useState({
    idDeProcesoElectoral: "",
    idDePartido: "",
    idElector: "",
    fechaDeInicio: today,
    fechaDeFin: "",
  });

  const [showModalInstancia, setShowModalInstancia] = useState(false);
  const [showModalPartido, setShowModalPartido] = useState(false);
  const [showModalElector, setShowModalElector] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  // Función para seleccionar instancia desde el modal
  const handleSelectInstancia = (id) => {
    setFormData((prev) => ({ ...prev, idDeProcesoElectoral: id }));
    setShowModalInstancia(false);
  };

  // Función para seleccionar partido desde el modal
  const handleSelectPartido = (id) => {
    setFormData((prev) => ({ ...prev, idDePartido: id }));
    setShowModalPartido(false);
  };

  // Función para seleccionar elector desde el modal
  const handleSelectElector = (id) => {
    setFormData((prev) => ({ ...prev, idElector: id }));
    setShowModalElector(false);
  };

  // Función principal para registrar candidatura y luego asignar elector
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    if (!formData.idDeProcesoElectoral || !formData.idDePartido || !formData.idElector) {
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
        `http://localhost:8080/api/candidatura/base?${queryParams.toString()}`,
        { method: "POST" }
      );

      if (!responseCandidatura.ok) {
        const errorMessage = await responseCandidatura.text();
        throw new Error(`Error al crear candidatura: ${errorMessage}`);
      }

      const candidaturaId = await responseCandidatura.text(); // Devuelve el ID de la candidatura
      alert(`Candidatura creada exitosamente con ID: ${candidaturaId}`);

      // Paso 2: Asignar elector a la candidatura
      const asignarElectorData = {
        idElector: formData.idElector,
        fechaDeInicio: formData.fechaDeInicio,
        fechaDeFin: formData.fechaDeFin,
        idCandidatura: candidaturaId, // ID de la candidatura creada
      };

      const responseElector = await fetch(
        "http://localhost:8080/api/elector/asignar",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(asignarElectorData),
        }
      );

      if (!responseElector.ok) {
        const errorMessage = await responseElector.text();
        throw new Error(`Error al asignar elector: ${errorMessage}`);
      }

      alert("Elector asignado correctamente a la candidatura.");
      navigate("/candidaturas");
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
        {/* Instancia */}
        <div>
          <label>Instancia de Proceso:</label>
          <input
            type="text"
            value={formData.idDeProcesoElectoral}
            readOnly
            placeholder="Seleccione una instancia"
          />
          <button type="button" onClick={() => setShowModalInstancia(true)}>
            Buscar Instancia
          </button>
        </div>

        {/* Partido */}
        <div>
          <label>Partido Político:</label>
          <input
            type="text"
            value={formData.idDePartido}
            readOnly
            placeholder="Seleccione un partido"
          />
          <button type="button" onClick={() => setShowModalPartido(true)}>
            Buscar Partido
          </button>
        </div>

        {/* Elector */}
        <div>
          <label>ID de Elector:</label>
          <input
            type="text"
            value={formData.idElector}
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

      {/* Modales */}
      {showModalInstancia && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Instancia de Proceso</h2>
            <ModalBuscarInstancia onSelect={handleSelectInstancia} />
            <button onClick={() => setShowModalInstancia(false)}>Cerrar</button>
          </div>
        </div>
      )}

      {showModalPartido && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Partido Político</h2>
            <ModalBuscarPartido onSelect={handleSelectPartido} />
            <button onClick={() => setShowModalPartido(false)}>Cerrar</button>
          </div>
        </div>
      )}

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
