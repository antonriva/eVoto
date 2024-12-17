import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarElector from "../../components/colegio/proceso/paginaBuscar/ModalBuscarElector";

const BuscarVotante = () => {
  const [formData, setFormData] = useState({
    idElector: "",
  });

  const [showModal, setShowModal] = useState(false); // Controla la visibilidad del modal
  const navigate = useNavigate();

  // Maneja la selección del elector desde el modal
  const handleSelectElector = (id) => {
    setFormData({ idElector: id });
    setShowModal(false); // Cierra el modal
  };

  // Envía al usuario a la página de procesos abiertos
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!formData.idElector || isNaN(formData.idElector)) {
      alert("Debe seleccionar un ID de Elector válido.");
      return;
    }

    navigate(`/procesos-abiertos/${formData.idElector}`);
  };

  return (
    <div>
      <h1>Búsqueda de Votante</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID de Elector:</label>
          <input
            type="text"
            value={formData.idElector}
            readOnly
            placeholder="Seleccione un elector"
          />
          <button type="button" onClick={() => setShowModal(true)}>
            Buscar Elector
          </button>
        </div>
        <button type="submit">Ver Procesos Abiertos</button>
        <button type="button" onClick={() => navigate("/")}>
          Cancelar
        </button>
      </form>

      {/* Modal para buscar electores */}
      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Elector</h2>
            <ModalBuscarElector onSelect={handleSelectElector} />
            <button onClick={() => setShowModal(false)}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default BuscarVotante;
