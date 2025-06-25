import React from 'react';
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import CardLink from "../../../../shared/components/cardLink/CardLink";
import "../../../../shared/layouts/AppLayout.css"; 

const ProcesoP = () => {
  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Colegio electoral", to: "/colegio" },
    { label: "Procesos electorales" }
  ];
  return (
    <div>
      <div className="app-layout-container">
        {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />

        {/* Header */}
        <header className="mb-4 ">
          <h1 className="display-4 fw-bold text-dark">Procesos electorales</h1>
          <p className="lead">
            Búsqueda, registro y edición de procesos electorales, incluyendo candidaturas.
          </p>
        </header>

      

        {/* Navigation */}

        <div className="card-wrapper">
            <div className="card-column">
              <CardLink
                to="/colegio/proceso/buscar"
                icon="📂"
                title="67"
                subtitle="Buscar proceso"
              />
            </div>
            <div className="card-column">
              <CardLink
                to="/colegio/proceso/registro"
                icon="🗓️"
                title="5780"
                subtitle="Registrar proceso"
              />
            </div>
          </div>

      </div>

    </div>

      
  );
};

export default ProcesoP;
