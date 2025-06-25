import React from "react";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import CardLink from "../../../shared/components/cardLink/CardLink";
import "../../../shared/layouts/AppLayout.css"; 

const Inicio = () => {

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Colegio electoral" }
  ];

  return (
    <div>
      <div className="app-layout-container">
        {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />
      {/* Header */}
      <header className="mb-4">
        <h1 className="display-4 fw-bold text-dark">Colegio electoral</h1>
        <p className="lead">
          Plataforma para la gestión electoral de principio a fin. Administra partidos, electores y procesos completos incluyendo candidatos y resultados
        </p>
      </header>

      {/* Navigation */}
      <div className="card-wrapper">
          <div className="card-column">
            <CardLink
              to="sistema"
              icon="🗳️"
              title="67"
              subtitle="Partidos y electores"
            />
          </div>
          <div className="card-column">
            <CardLink
              to="proceso"
              icon="📊"
              title="5780"
              subtitle="Candidatos y elecciones"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Inicio;
