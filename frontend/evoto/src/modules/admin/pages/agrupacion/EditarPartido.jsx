import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useToast } from "../../../../shared/context/ToastContext";
import GenericFormFields from "../../../../shared/components/genericFormFields/GenericFormFields";
import { getPartidoEditableConfig } from "../../config/partidoEditableConfig";
import { useEditableEntityData } from "../../../../shared/hooks/useEditableEntityData";
import { usePartidosActions } from "../../hooks/usePartidosActions";
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css"; 
import { Spinner, Alert } from "react-bootstrap";
import "../../../../shared/styles/Buttons.css"; // Import global styles for buttons

const EditarPartido = () => {
  const { id } = useParams(); // Obtiene el ID del partido desde la URL
  const navigate = useNavigate();

  const { formData, setFormData, originalData, loading, error } = useEditableEntityData({
    endpoint: "partido", // << 👈 Cambiar aquí a partido
    id,
    initialValues: {
      denominacion: "",
      siglas: "",
      fechaDeInicio: "",
      fechaDeFin: "",
    },
  });

  const { actualizarPartido } = usePartidosActions();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const { showToast } = useToast(); // Make sure this is imported from your toast context

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const result = await actualizarPartido({ id, formData });
  
    if (result.success) {
      const changes = Object.entries(formData)
        .filter(([key, value]) => originalData[key] !== value)
        .map(([key, value]) => `${key}: ${value}`)
        .join(", ");
  
      showToast({
        title: "Éxito",
        body: `Partido actualizado. Cambios: ${changes || "ninguno"}`,
        variant: "success",
      });
  
      navigate("/colegio/sistema");
    } else {
      showToast({
        title: "Alerta",
        body: result.message,
        variant: "danger",
      });
    }
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
    { label: "Colegio electoral", to: "/colegio" },
    { label: "Sistema electoral", to: "/colegio/sistema" },
    { label: "Buscar partido", to: "/colegio/sistema/buscarpartido" }
  ];

  const config = getPartidoEditableConfig();
  return (
    <div>
      <div className="app-layout-container">
        <Breadcrumbs items={breadcrumbItems} />
        <h1 className="text-center">Editar Partido</h1>
        <div className="row">
          <div className="col-md-8 col-lg-6 mx-auto">
            <form onSubmit={handleSubmit}>
              <GenericFormFields
                config={config}
                values={formData}
                onChange={handleChange}
              />

              <div className="d-flex justify-content-between mt-3">
                <button type="submit" className="btn btn-vino">Guardar Cambios</button>
                <button
                  type="button"
                  className="btn btn-vino"
                  onClick={() => navigate("/colegio/sistema")}
                >
                  Cancelar
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditarPartido;
