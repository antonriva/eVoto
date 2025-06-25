import React from 'react';
import { Link } from 'react-router-dom';
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import CardLink from "../../../shared/components/cardLink/CardLink";
import "../../../shared/layouts/AppLayout.css"; 

const SistemaInicio = () => {
  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Colegio electoral", to: "/colegio" },
    { label: "Sistema electoral" }
  ];

  return (
    <div>
      <div className="app-layout-container">
        {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />

        {/* Header */}
        <header className="mb-4 ">
          <h1 className="display-4 fw-bold text-dark">Sistema electoral</h1>
          <p className="lead">
            Gestión del sistema electoral. Realiza operaciones con partidos y electores. 
          </p>
        </header>
      
      

        {/* Navigation */}

                  

        <div className="d-flex justify-content-center flex-wrap gap-4">
          {/* Columna 1 */}
          <div className="card-column">
            <CardLink
              to="/colegio/sistema/buscarpartido"
              icon="🏛️"
              subtitle="Buscar partido"
            />
            <CardLink
              to="/colegio/sistema/registropartido"
              icon="🔍"
              subtitle="Buscar elector"
            />
          </div>

          {/* Columna 2 */}
          <div className="card-column">
            <CardLink
              to="/colegio/sistema/buscarelector"
              icon="📝"
              subtitle="Registrar partido"
            />
            <CardLink
              to="/colegio/sistema/registroelector"
              icon="🗳️"
              subtitle="Registrar elector"
            />
          </div>
        </div>

      </div>
    </div>
  );
};

export default SistemaInicio;
