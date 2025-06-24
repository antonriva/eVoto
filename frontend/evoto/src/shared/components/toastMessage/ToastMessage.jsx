// src/shared/components/ToastMessage.jsx
import React from "react";
import { Toast, ToastContainer } from "react-bootstrap";

const ToastMessage = ({ show, onClose, title = "Error", body = "", variant = "danger" }) => {
  return (
    <ToastContainer position="top-start" className="p-3" style={{ zIndex: 9999 }}>
      <Toast bg={variant} onClose={onClose} show={show} delay={5000} autohide>
        <Toast.Header closeButton>
          <strong className="me-auto">{title}</strong>
        </Toast.Header>
        <Toast.Body className="text-white">{body}</Toast.Body>
      </Toast>
    </ToastContainer>
  );
};

export default ToastMessage;
