import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarElector from "../../../modules/admin/components/modalBuscarElector/ModalBuscarElector";
import { Modal, Button } from "react-bootstrap";
import "../../../shared/styles/Buttons.css"; 

const RegistroCandidaturaYElector = () => {
  const [idDeElector, setIdDeElector] = useState(""); // Estado para almacenar el ID del elector
  const [showModalElector, setShowModalElector] = useState(false); // Controla la apertura del modal
  const [errorMessage, setErrorMessage] = useState(""); // Estado para mostrar mensaje de error
  const navigate = useNavigate();

  // Función para manejar la selección del elector desde el modal
  const handleSelectElector = (id) => {
    setIdDeElector(id); // Almacena el ID del elector seleccionado
    setErrorMessage(""); // Limpia el mensaje de error
    setShowModalElector(false); // Cierra el modal
  };

  // Función para navegar a la siguiente página pasando el ID del elector
  const handleNavigateToInicioVotante = () => {
    if (!idDeElector) {
      setErrorMessage("Debe seleccionar un elector antes de continuar."); // Muestra mensaje de error
      return;
    }
    console.log("ID ENVIADO:", idDeElector);
    navigate(`inicioVotante/${idDeElector}`); // Navega a la nueva página con el ID
  };

  return (
    <div>
      <h1>Seleccionar Elector</h1>

      {/* Campo para mostrar el ID del elector */}
      <div>
        <label>ID de Elector:</label>
        <input
          type="text"
          value={idDeElector}
          readOnly
          placeholder="Seleccione un elector"
        />
        <button className="btn btn-vino" type="button" onClick={() => setShowModalElector(true)}>
          Buscar Elector
        </button>
      </div>

      {/* Mensaje de error si no se selecciona un elector */}
      {errorMessage && (
        <p style={{ color: "red", marginTop: "10px" }}>{errorMessage}</p>
      )}

      {/* Botón para ir a la siguiente página */}
      <div style={{ marginTop: "20px" }}>
        <button className="btn btn-vino" onClick={handleNavigateToInicioVotante}>
          Continuar a Inicio Votante
        </button>
      </div>

      {/* Modal para buscar electores */}
      <Modal
        show={showModalElector}
        onHide={() => setShowModalElector(false)}
        size="xl"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>Buscar Elector</Modal.Title>
        </Modal.Header>

        <Modal.Body style={{ maxHeight: "70vh", overflowY: "auto" }}>
          <ModalBuscarElector
            onSelect={(idElector) => {
              handleSelectElector(idElector); // Callback para manejar la selección
              setShowModalElector(false); // Cierra el modal después de seleccionar
            }}
          />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModalElector(false)}>
            Cerrar
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default RegistroCandidaturaYElector;
