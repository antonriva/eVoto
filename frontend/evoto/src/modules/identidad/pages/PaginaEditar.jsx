import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useUbicaciones } from "../hooks/useUbicaciones";
import { useToast } from "../../../shared/context/ToastContext";
import GenericFormFields from "../../../shared/components/genericFormFields/GenericFormFields";
import { getPersonaEditableConfig } from "../../../modules/identidad/config/personaEditableConfig";
import { useEditableEntityData } from "../../../shared/hooks/useEditableEntityData";
import { usePersonasActions } from "../hooks/usePersonasActions";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../shared/layouts/AppLayout.css"; 
import { Spinner, Alert } from "react-bootstrap";
import "../../../shared/styles/Buttons.css"; // Import global styles for buttons

const PaginaEditar = () => {
  const { id } = useParams(); // Obtiene el ID de la persona desde la URL
  const navigate = useNavigate();

  // Use the shared useUbicaciones hook
  const { entidades, municipios, fetchMunicipios } = useUbicaciones();

  // Use the shared useEditableEntityData hook
  const { formData, setFormData, originalData, loading, error } = useEditableEntityData({
    endpoint: "persona",
    id,
    initialValues: {
      nombre: "",
      apellidoPaterno: "",
      apellidoMaterno: "",
      fechaDeNacimiento: "",
      fechaDeFin: "",
      entidadFederativaId: "",
      municipioId: "",
      fechaDeInicio: "",
    },
    onAfterLoad: (data) => {
      if (data.entidadFederativaId) {
        fetchMunicipios(data.entidadFederativaId); // Fetch municipios if entidad is loaded
      }
    },
  });

  // Use the shared usePersonasActions hook
  const { actualizarPersona } = usePersonasActions();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    // Si se selecciona una entidad, cargar municipios
    if (name === "entidadFederativaId") {
      setFormData((prevData) => ({
        ...prevData,
        municipioId: "", // Limpiar municipio seleccionado
      }));
      fetchMunicipios(value); // Use the shared hook
    }
  };

  const { showToast } = useToast(); // Make sure this is imported from your toast context

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const result = await actualizarPersona({ id, formData });
  
    if (result.success) {
      const changes = Object.entries(formData)
        .filter(([key, value]) => originalData[key] !== value)
        .map(([key, value]) => `${key}: ${value}`)
        .join(", ");
  
      showToast({
        title: "Éxito",
        body: `Persona actualizada. Cambios: ${changes || "ninguno"}`,
        variant: "success",
      });
  
      navigate("/civil");
    } else {
      showToast({
        title: "Alerta",
        body: result.message,
        variant: "danger",
      });
    }
  };

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Registro civil", to: "/civil" },
    { label: "Buscar", to: "/civil/buscar"},
    { label: "Editar" }
  ];
  
  
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

  const config = getPersonaEditableConfig({ entidades, municipios });

  return (
    <div>
    <div className="app-layout-container">
      <Breadcrumbs items={breadcrumbItems} />
      <h1 className="text-center">Editar persona</h1>

      {/* ✅ Add a responsive centered column */}
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
                onClick={() => navigate("/civil")}
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

export default PaginaEditar;
