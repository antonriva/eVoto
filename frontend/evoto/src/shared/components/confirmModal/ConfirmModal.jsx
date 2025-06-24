// src/shared/components/modal/ConfirmModal.jsx
import React from "react";
import { Modal, Button } from "react-bootstrap";

const ConfirmModal = ({
  show,
  onHide,
  onConfirm,
  title = "¿Estás seguro?",
  body = "Esta acción no se puede deshacer.",
  confirmText = "Confirmar",
  cancelText = "Cancelar",
  loading = false
}) => {
  return (
    <Modal show={show} onHide={onHide} centered backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>

      <Modal.Body>
      <p>{body}</p>
      </Modal.Body>

      <Modal.Footer>
        <Button variant="secondary" onClick={onHide} disabled={loading}>
          {cancelText}
        </Button>
        <Button variant="danger" onClick={onConfirm} disabled={loading}>
          {loading ? "Procesando..." : confirmText}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ConfirmModal;
