import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../../shared/context/ToastContext";
import { usePersonasActions } from "../hooks/usePersonasActions";
import { getPersonaRegistroConfig } from "../config/personaRegisterConfig";
import GenericFormFields from "../../../shared/components/genericFormFields/GenericFormFields";
import { useUbicaciones } from "../hooks/useUbicaciones";
import { useConfirmDialog } from "../../../shared/hooks/useConfirmDialog";
import ConfirmModal from "../../../shared/components/confirmModal/ConfirmModal";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../shared/layouts/AppLayout.css"; 
import { Spinner, Alert } from "react-bootstrap";
import "../../../shared/styles/Buttons.css"; // Import global styles for buttons

const PaginaRegistro = () => {
  const navigate = useNavigate();
  const { showToast } = useToast();
  const { registrarPersona } = usePersonasActions();
  const { entidades, municipios, fetchMunicipios } = useUbicaciones();

  const today = new Date().toISOString().split("T")[0];

  const config = getPersonaRegistroConfig({ entidades, municipios, today });

  const confirmConflict = useConfirmDialog(); // ⬅️ for handling 409 confirm modal

  const initialFormData = config.reduce((acc, field) => {
    acc[field.name] = field.defaultValue || "";
    return acc;
  }, {});

  const [formData, setFormData] = useState(initialFormData);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (name === "entidadFederativaId") {
      fetchMunicipios(value);
    }
  };
  const handleConflictConfirm = async () => {
    confirmConflict.setLoading(true);
  
    const confirmResult = await registrarPersona({ formData, confirmar: true });
  
    if (confirmResult.success) {
      showToast({
        title: "Éxito",
        body: confirmResult.message,
        variant: "success",
      });
      navigate("/civil");
    } else {
      showToast({
        title: "Error",
        body: confirmResult.message,
        variant: "danger",
      });
    }
  
    confirmConflict.close();
    confirmConflict.setLoading(false);
  };
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
  
    const result = await registrarPersona({ formData });
  
    if (result.success) {
      showToast({
        title: "Éxito",
        body: result.message,
        variant: "success",
      });
      navigate("/civil");
    } else if (result.conflict) {
      confirmConflict.open(result.message); // open modal
    } else {
      showToast({
        title: "Error",
        body: result.message,
        variant: "danger",
      });
      setError(result.message);
    }
  
    setLoading(false);
  };

    if (loading) {
      return (
        <div className="d-flex justify-content-center align-items-center mt-4">
          <Spinner animation="border" role="status">
            <span className="visually-hidden">Cargando...</span>
          </Spinner>
          <span className="ms-2">Cargando...</span>
        </div>
      );
    }
    
  
    if (error) {
      return (
        <Alert variant="danger" className="mt-4 text-center">
          <Alert.Heading>Ooops! Estamos solucionando esto...</Alert.Heading>
          <p>{error}</p>
        </Alert>
      );
    }

    const breadcrumbItems = [
      { label: "Inicio", to: "/" },
      { label: "Registro civil", to: "/civil" },
      { label: "Registrar" }
    ];
  

  return (
    <div>
      <div className="app-layout-container">
        <Breadcrumbs items={breadcrumbItems} />
        <h1 className="text-center">Registrar persona</h1>


        {error && <p style={{ color: "red" }}>{error}</p>}

        {/* ✅ Centered and narrower form */}
        <div className="row">
          <div className="col-md-8 col-lg-6 mx-auto">
            <form onSubmit={handleSubmit}>
              <GenericFormFields
                config={config}
                values={formData}
                onChange={handleChange}
              />

              <div className="d-flex justify-content-between mt-4">
                <button type="submit" className="btn btn-vino" disabled={loading}>
                  {loading ? "Registrando..." : "Registrar"}
                </button>
                <button
                  type="button"
                  className="btn btn-vino"
                  onClick={() => navigate("/civil")}
                >
                  Cancelar
                </button>
              </div>
            </form>
          </div>
        </div>
        <ConfirmModal
          show={confirmConflict.isOpen}
          onHide={confirmConflict.close}
          onConfirm={handleConflictConfirm}
          title="Posible duplicado"
          body={`${confirmConflict.payload}\n¿Desea continuar?`}
          confirmText="Registrar"
          cancelText="Cancelar"
          loading={confirmConflict.loading}
        />
      </div>
    </div>
  );
};

export default PaginaRegistro;
