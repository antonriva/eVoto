import { Routes, Route, Link } from "react-router-dom";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../shared/layouts/AppLayout.css";
import "../../../shared/styles/Buttons.css"; // Import custom button styles

import BuscarVotanteP from "./BuscarVotante";
import ConsultaPag from "./ProcesosAbiertos";
import EleccionPag from "./CandidatosVoto";

const VotanteApp = () => {
  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Votante" },
  ];

  return (
    <div>
      <div className="app-layout-container">
        {/* Breadcrumb */}
        <Breadcrumbs items={breadcrumbItems} />

        {/* Header */}
        <header className="mb-4">
          <h1 className="display-4 fw-bold text-dark text-center">Sistema de Votación</h1>
          <p className="lead text-center">
            Plataforma para realizar voto electrónico.
          </p>
        </header>

        {/* Navigation */}
        <nav className="mb-4">
          <ul className="nav justify-content-center">
            <li className="nav-item">
              <Link className="btn btn-custom-outline" to="ingreso">
                Ingreso
              </Link>
            </li>
          </ul>
        </nav>

        {/* Routes */}
        <main>
          <Routes>
            <Route path="ingreso" element={<BuscarVotanteP />} />
            <Route path="ingreso/inicioVotante/:idDeElector" element={<ConsultaPag />} />
            <Route
              path="ingreso/inicioVotante/:id/detalleProceso/:idDeElector/:idDeInstanciaDeProceso"
              element={<EleccionPag />}
            />
          </Routes>
        </main>
      </div>
    </div>
  );
};

export default VotanteApp;