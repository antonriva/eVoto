import React from "react";
import { Routes, Route } from "react-router-dom";

// Pages
import Inicio from "../pages/Inicio"; // Import the Inicio component
import ResultadosPag from "../../../pages/colegio/ResultadosPag";

// Partido
import SistemaP from "../../../pages/colegio/sistemaElectoral/SistemaP";
import PartidosP from "../../../pages/colegio/sistemaElectoral/PartidosP";
import BuscarPartidoP from "../../../pages/colegio/sistemaElectoral/partidosP/BuscarPartidoP";
import EditarPartidoP from "../../../components/colegio/partido/EditarPartido";
import RegistroPartidoP from "../../../pages/colegio/sistemaElectoral/partidosP/RegistroPartidoP";

// Elector
import ElectoresP from "../../../pages/colegio/sistemaElectoral/ElectoresP";
import BuscarElectorP from "../../../pages/colegio/sistemaElectoral/electoresP/PaginaBuscarElectores";
import EditarElectorP from "../../../components/colegio/paginaBuscar/ElectorEditar";
import RegistroElectorP from "../../../pages/colegio/sistemaElectoral/electoresP/ElectorRegistro";

// Proceso
import ProcesoP from "../../../pages/colegio/procesoElectoral/ProcesoP";
import RegistroProcesoP from "../../../pages/colegio/procesoElectoral/instanciaP/RegistroProceso";
import BusquedaProcesoP from "../../../pages/colegio/procesoElectoral/instanciaP/BusquedaProceso";
import EditarProcesoP from "../../../components/colegio/proceso/paginaBuscar/InstanciaEditar";
import RegistroCandidatura from "../../../components/colegio/proceso/paginaBuscar/RegistroAsignacion";

const ColegioRoutes = () => (
  <Routes>
              <Route index element={<Inicio />} />
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
);

export default ColegioRoutes;
