import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarElector from "../../../modules/admin/components/modalBuscarElector/ModalBuscarElector";
import { Modal, Button } from "react-bootstrap";

const RegistroCandidaturaYElector = () => {

  
  const [idDeElector, setIdDeElector] = useState(""); // Estado para almacenar el ID del elector
  const [showModalElector, setShowModalElector] = useState(false); // Controla la apertura del modal
  const navigate = useNavigate();

  // Función para manejar la selección del elector desde el modal
  const handleSelectElector = (id) => {
    setIdDeElector(id); // Almacena el ID del elector seleccionado
    setShowModalElector(false); // Cierra el modal
  };

  // Función para navegar a la siguiente página pasando el ID del elector
  const handleNavigateToInicioVotante = () => {
    if (!idDeElector) {
      alert("Debe seleccionar un elector antes de continuar.");
      return;
    }
    console.log("ID ENVIADO:", idDeElector);
    navigate(`inicioVotante/${idDeElector}`); // Navega a la nueva página con el ID
  };

  const enviarVotante = (id) => {
    navigate(`inicioVotante/${id}`);
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
        <button type="button" onClick={() => setShowModalElector(true)}>
          Buscar Elector
        </button>
      </div>

      {/* Botón para ir a la siguiente página */}
      <div style={{ marginTop: "20px" }}>
        <button onClick={() => enviarVotante(idDeElector)}>
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
        handleSelectElector(idElector);  // your callback
        setShowModalElector(false);      // close the modal after selecting
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
