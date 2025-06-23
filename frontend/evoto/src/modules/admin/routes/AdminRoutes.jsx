import React from "react";
import { Routes, Route } from "react-router-dom";

// Pages
import Inicio from "../pages/Inicio"; // Correct path
import ResultadosPag from "../pages/ResultadosPag"; // Correct path

// Partido
import SistemaP from "../pages/SistemaP"; // Correct path
import PartidosP from "../pages/agrupacion/PartidosP"; // Correct path
import BuscarPartidoP from "../pages/agrupacion/BuscarPartidoP"; // Correct path
import EditarPartidoP from "../pages/agrupacion/EditarPartido"; // Correct path
import RegistroPartidoP from "../pages/agrupacion/RegistroPartidoP"; // Correct path

// Elector
import ElectoresP from "../pages/participante/ElectoresP"; // Correct path
import BuscarElectorP from "../pages/participante/PaginaBuscarElectores"; // Correct path
import EditarElectorP from "../pages/participante/ElectorEditar"; // Correct path
import RegistroElectorP from "../pages/participante/ElectorRegistro"; // Correct path

// Proceso
import ProcesoP from "../pages/proceso/ProcesoP"; // Correct path
import RegistroProcesoP from "../pages/proceso/RegistroProceso"; // Correct path
import BusquedaProcesoP from "../pages/proceso/BusquedaProceso"; // Correct path
import EditarProcesoP from "../pages/proceso/InstanciaEditar"; // Correct path
import RegistroCandidatura from "../pages/proceso/RegistroAsignacion"; // Correct path

const AdminRoutes = () => (
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

export default AdminRoutes;
