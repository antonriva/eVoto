import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarInstancia from "./ModalBuscarInstancia";
import ModalBuscarPartido from "./ModalBuscarPartido";

const RegistroCandidatura = () => {
  const [formData, setFormData] = useState({
    idDeProcesoElectoral: "",
    idDePartido: "",
  });

  const [showModalInstancia, setShowModalInstancia] = useState(false);
  const [showModalPartido, setShowModalPartido] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  // Función para seleccionar instancia desde el modal
  const handleSelectInstancia = (id) => {
    setFormData((prev) => ({ ...prev, idDeProcesoElectoral: id }));
    setShowModalInstancia(false); // Cierra el modal
  };

  // Función para seleccionar partido desde el modal
  const handleSelectPartido = (id) => {
    setFormData((prev) => ({ ...prev, idDePartido: id }));
    setShowModalPartido(false); // Cierra el modal
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    if (!formData.idDeProcesoElectoral || !formData.idDePartido) {
      setError("Debe seleccionar tanto una instancia de proceso como un partido.");
      setLoading(false);
      return;
    }

    try {
      const queryParams = new URLSearchParams({
        idDeProcesoElectoral: formData.idDeProcesoElectoral,
        idDePartido: formData.idDePartido,
      });

      const response = await fetch(
        `http://localhost:8080/api/candidatura/base?${queryParams.toString()}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      alert("Candidatura registrada exitosamente.");
      navigate("/candidaturas");
    } catch (error) {
      console.error("Error al registrar candidatura:", error);
      setError(error.message || "Error al registrar la candidatura.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Candidatura</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Instancia de Proceso:</label>
          <input
            type="text"
            name="idDeProcesoElectoral"
            value={formData.idDeProcesoElectoral}
            readOnly
            placeholder="Seleccione una instancia de proceso"
          />
          <button type="button" onClick={() => setShowModalInstancia(true)}>
            Buscar Instancia
          </button>
        </div>
        <div>
          <label>Partido Político:</label>
          <input
            type="text"
            name="idDePartido"
            value={formData.idDePartido}
            readOnly
            placeholder="Seleccione un partido político"
          />
          <button type="button" onClick={() => setShowModalPartido(true)}>
            Buscar Partido
          </button>
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/candidaturas")}>
          Cancelar
        </button>

        

        
      </form>

      {/* Modal para buscar instancia de proceso */}
      {showModalInstancia && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Instancia de Proceso</h2>
            <ModalBuscarInstancia onSelect={handleSelectInstancia} />
            <button onClick={() => setShowModalInstancia(false)}>Cerrar</button>
          </div>
        </div>
      )}

      {/* Modal para buscar partido político */}
      {showModalPartido && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Partido Político</h2>
            <ModalBuscarPartido onSelect={handleSelectPartido} />
            <button onClick={() => setShowModalPartido(false)}>Cerrar</button>
          </div>
        </div>
      )}
    </div>







  );
};

export default RegistroCandidatura;
