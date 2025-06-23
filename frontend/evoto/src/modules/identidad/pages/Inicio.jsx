import React from "react";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import CardLink from "../../../shared/components/cardLink/CardLink";
import "../../../shared/layouts/AppLayout.css"; // Import global styles



const Inicio = () => {

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Registro civil" }
  ];

  return (
    <div>
      <div className="app-layout-container">
      {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />

        {/* Header */}
        <header className="mb-4 ">
          <h1 className="display-4 fw-bold text-dark">Registro civil</h1>
          <p className="lead">
            Plataforma para la búsqueda, registro y edición de personas
          </p>
        </header>
      </div>
      

      {/* Navigation */}

      <div className="card-wrapper">
          <div className="card-column">
            <CardLink
              to="buscar"
              icon="🔍"
              title="67"
              subtitle="Buscar persona"
            />
          </div>
          <div className="col-md-5">
            <CardLink
              to="registro"
              icon="📝"
              title="5780"
              subtitle="Registrar persona"
            />
          </div>
        </div>
      </div>
  );
};

export default Inicio;