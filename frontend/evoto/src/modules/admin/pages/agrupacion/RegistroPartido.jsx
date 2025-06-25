import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../../../shared/context/ToastContext";
import { usePartidosActions } from "../../hooks/usePartidosActions"; 
import { getPartidoRegistroConfig } from "../../config/partidoRegistroConfig";
import GenericFormFields from "../../../../shared/components/genericFormFields/GenericFormFields";
import { Spinner, Alert } from "react-bootstrap";
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css"; 

const RegistroPartido = () => {
  const navigate = useNavigate();
  const { showToast } = useToast();
  const {registrarPartido} = usePartidosActions();
  const today = new Date().toISOString().split("T")[0];

  const config = getPartidoRegistroConfig(today);


  const initialFormData = config.reduce((acc, field) => {
    acc[field.name] = field.defaultValue || "";
    return acc;
  }, {});

  const [formData, setFormData] = useState(initialFormData);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);


  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0];

    const handleChange = (e) => {
      const { name, value } = e.target;
  
      setFormData((prev) => ({
        ...prev,
        [name]: value,
      }));
  
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      setLoading(true);
      setError("");
    
      const result = await registrarPartido({ formData });
    
      if (result.success) {
        showToast({
          title: "Éxito",
          body: result.message,
          variant: "success",
        });
        navigate("/colegio/sistema");
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
      { label: "Colegio electoral", to: "/colegio" },
      { label: "Sistema electoral", to: "/colegio/sistema" },
      { label: "Registro de partido" }
    ];

  return (
    <div>
      <div className="app-layout-container">
        <Breadcrumbs items={breadcrumbItems} />
        <h1 className="text-center">Registro de Partido</h1>
        {error && <p style={{ color: "red" }}>{error}</p>}
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
      </div>
    </div>
  );
};

export default RegistroPartido;
