import React, { useState, useEffect } from "react";
import { Routes, Route, Link, useLocation } from "react-router-dom";
import AboutPag from "./AboutPag";
import ResultadosPag from "./ResultadosPag";

// Partido
import SistemaP from "./sistemaElectoral/SistemaP";
import PartidosP from "./sistemaElectoral/PartidosP";
import BuscarPartidoP from "./sistemaElectoral/partidosP/BuscarPartidoP";
import EditarPartidoP from "../../components/colegio/partido/EditarPartido";
import RegistroPartidoP from "./sistemaElectoral/partidosP/RegistroPartidoP";

// Elector
import ElectoresP from "./sistemaElectoral/ElectoresP";
import BuscarElectorP from "./sistemaElectoral/electoresP/PaginaBuscarElectores";
import EditarElectorP from "../../components/colegio/paginaBuscar/ElectorEditar";
import RegistroElectorP from "./sistemaElectoral/electoresP/ElectorRegistro";

// Proceso
import ProcesoP from "./procesoElectoral/ProcesoP";
import RegistroProcesoP from "./procesoElectoral/instanciaP/RegistroProceso";
import BusquedaProcesoP from "./procesoElectoral/instanciaP/BusquedaProceso";
import EditarProcesoP from "../../components/colegio/proceso/paginaBuscar/InstanciaEditar";
import RegistroCandidatura from "../../components/colegio/proceso/paginaBuscar/RegistroAsignacion";

const AppColegio = () => {
  const [menuVisible, setMenuVisible] = useState(true);
  const location = useLocation();

  const hideMenuPages = [
    "/colegio/sistema",
    "/colegio/proceso",
    "/colegio/sistema/ele",
    "/colegio/sistema/ele/registro",
    "/colegio/sistema/ele/buscar",
    "/colegio/proceso/buscar",
    "/colegio/proceso/registro",
  ];

  useEffect(() => {
    if (location.pathname === "/colegio") {
      setMenuVisible(true);
    } else if (hideMenuPages.includes(location.pathname)) {
      setMenuVisible(false);
    }
  }, [location]);

  const handleMenuToggle = (path) => {
    if (path.includes("sistema") || path.includes("proceso")) {
      setMenuVisible(false);
    }
  };

  return (
    <div style={{ backgroundColor: "#f8f9fa", minHeight: "100vh", padding: "20px" }}>
      {/* Header */}
      <header className="mb-4 text-center">
        <h1 className="display-4 text-primary">Colegio Electoral</h1>
        <p className="lead">
          Sistema para el registro de candidatos, electores y gestión de votos. Administra partidos, electores y procesos electorales.
        </p>
      </header>

      {/* Navigation */}
      {menuVisible && !hideMenuPages.includes(location.pathname) && (
        <div className="container mb-4">
          <div className="row justify-content-center">
            <div className="col-md-3">
              <Link to="/" className="btn btn-outline-secondary btn-block mb-2">
                Menú Principal
              </Link>
            </div>
            <div className="col-md-3">
              <Link
                to="about"
                onClick={() => setMenuVisible(true)}
                className="btn btn-outline-info btn-block mb-2"
              >
                Acerca del Colegio Electoral
              </Link>
            </div>
            <div className="col-md-3">
              <Link
                to="sistema"
                onClick={() => handleMenuToggle("sistema")}
                className="btn btn-outline-primary btn-block mb-2"
              >
                Sistema Electoral
              </Link>
            </div>
            <div className="col-md-3">
              <Link
                to="proceso"
                onClick={() => handleMenuToggle("proceso")}
                className="btn btn-outline-success btn-block mb-2"
              >
                Proceso Electoral
              </Link>
            </div>
          </div>
        </div>
      )}

      {/* Routes */}
      <div className="container">
        <div className="card shadow-sm">
          <div className="card-body">
            <Routes>
              <Route path="about" element={<AboutPag />} />
              <Route path="resultados" element={<ResultadosPag />} />

              {/* Sistema Electoral */}
              <Route path="sistema" element={<SistemaP />} />
              <Route path="sistema/par" element={<PartidosP />} />
              <Route path="sistema/ele" element={<ElectoresP />} />

              {/* Partido */}
              <Route path="sistema/par/buscar" element={<BuscarPartidoP />} />
              <Route path="sistema/par/buscar/editar/:id" element={<EditarPartidoP />} />
              <Route path="sistema/par/registro" element={<RegistroPartidoP />} />

              {/* Elector */}
              <Route path="sistema/ele/buscar" element={<BuscarElectorP />} />
              <Route path="sistema/ele/buscar/editar/:id" element={<EditarElectorP />} />
              <Route path="sistema/ele/registro" element={<RegistroElectorP />} />

              {/* Proceso Electoral */}
              <Route path="proceso" element={<ProcesoP />} />
              <Route path="proceso/registro" element={<RegistroProcesoP />} />
              <Route path="proceso/buscar" element={<BusquedaProcesoP />} />
              <Route path="proceso/buscar/editar/:id" element={<EditarProcesoP />} />
              <Route path="proceso/buscar/agregar/:id" element={<RegistroCandidatura />} />
            </Routes>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AppColegio;
