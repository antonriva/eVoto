import React from "react";
import { Routes, Route } from "react-router-dom";
import ColegioLayout from "../layouts/ColegioLayout";

// Pages
import AboutPag from "../pages/AboutPag";
import ResultadosPag from "../pages/ResultadosPag";

// Sistema Electoral
import SistemaP from "../pages/sistemaElectoral/SistemaP";
import PartidosP from "../pages/sistemaElectoral/PartidosP";
import BuscarPartidoP from "../pages/sistemaElectoral/partidosP/BuscarPartidoP";
import RegistroPartidoP from "../pages/sistemaElectoral/partidosP/RegistroPartidoP";

// Electores
import ElectoresP from "../pages/sistemaElectoral/ElectoresP";
import BuscarElectorP from "../pages/sistemaElectoral/electoresP/PaginaBuscarElectores";
import RegistroElectorP from "../pages/sistemaElectoral/electoresP/ElectorRegistro";

// Proceso Electoral
import ProcesoP from "../pages/procesoElectoral/ProcesoP";
import RegistroProcesoP from "../pages/procesoElectoral/instanciaP/RegistroProceso";
import BusquedaProcesoP from "../pages/procesoElectoral/instanciaP/BusquedaProceso";
import RegistroCandidatura from "../../components/colegio/proceso/paginaBuscar/RegistroAsignacion";
import EditarPartidoP from "../../components/colegio/partido/EditarPartido";
import EditarElectorP from "../../components/colegio/paginaBuscar/ElectorEditar";
import EditarProcesoP from "../../components/colegio/proceso/paginaBuscar/InstanciaEditar";

const ColegioRoutes = () => (
  <Routes>
    <Route path="colegio" element={<ColegioLayout />}>
      <Route path="about" element={<AboutPag />} />
      <Route path="resultados" element={<ResultadosPag />} />
      <Route path="sistema" element={<SistemaP />} />
      <Route path="sistema/par" element={<PartidosP />} />
      <Route path="sistema/par/buscar" element={<BuscarPartidoP />} />
      <Route path="sistema/par/registro" element={<RegistroPartidoP />} />
      <Route path="sistema/par/buscar/editar/:id" element={<EditarPartidoP />} />

      <Route path="sistema/ele" element={<ElectoresP />} />
      <Route path="sistema/ele/buscar" element={<BuscarElectorP />} />
      <Route path="sistema/ele/registro" element={<RegistroElectorP />} />
      <Route path="sistema/ele/buscar/editar/:id" element={<EditarElectorP />} />

      <Route path="proceso" element={<ProcesoP />} />
      <Route path="proceso/registro" element={<RegistroProcesoP />} />
      <Route path="proceso/buscar" element={<BusquedaProcesoP />} />
      <Route path="proceso/buscar/editar/:id" element={<EditarProcesoP />} />
      <Route path="proceso/buscar/agregar/:id" element={<RegistroCandidatura />} />
    </Route>
  </Routes>
);

export default ColegioRoutes;
