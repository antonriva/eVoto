// src/shared/components/ToastMessage.jsx

// src/shared/components/toastMessage/ToastMessage.jsx
import React from "react";
import { Toast, ToastContainer } from "react-bootstrap";
import { useToast } from "../../context/ToastContext";

const ToastMessage = () => {
  const { toast, hideToast } = useToast();

  if (!toast?.show) return null;

  return (
    <ToastContainer position="bottom-end" className="p-3" style={{ zIndex: 9999 }}>
      <Toast
        bg={toast.variant || "danger"}
        onClose={hideToast}
        show={toast.show}
        delay={5000}
        autohide
      >
        <Toast.Header closeButton>
          <strong className="me-auto">{toast.title || "Mensaje"}</strong>
        </Toast.Header>
        <Toast.Body className="text-white">{toast.body || ""}</Toast.Body>
      </Toast>
    </ToastContainer>
  );
};

export default ToastMessage;
