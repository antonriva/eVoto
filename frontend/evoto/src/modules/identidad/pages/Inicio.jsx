import React from "react";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import { Link } from "react-router-dom";



const Inicio = () => {

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Registro civil" }
  ];

  return (
    <div>
      <div className="container mt-3">
      {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />

        {/* Header */}
        <header className="mb-4 ">
          <h1 className="display-4 fw-bold text-dark">Registro civil</h1>
          <p className="lead">
            Plataforma para la visualización, registro y edición de personas
          </p>
        </header>
      </div>
      

      {/* Navigation */}
      <div className="container">
        <div className="row justify-content-center mb-4">
        <div className="col-md-4">
            <Link to="/" className="btn btn-outline-secondary btn-block mb-2">
              Menú Principal
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="buscar" className="btn btn-outline-primary btn-block mb-2">
              Buscar Personas
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="registro" className="btn btn-outline-success btn-block mb-2">
              Registrar Persona
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Inicio;