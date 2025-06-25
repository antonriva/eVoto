import React from "react";
import { Routes, Route } from "react-router-dom";

// Pages
import Inicio from "../pages/Inicio"; // Correct path
import ResultadosPag from "../pages/ResultadosPag"; // Correct path

// Partido
import SistemaP from "../pages/SistemaInicio"; // Correct path
import BuscarPartidoP from "../pages/agrupacion/BuscarPartido"; // Correct path
import EditarPartidoP from "../pages/agrupacion/EditarPartido"; // Correct path
import RegistroPartidoP from "../pages/agrupacion/RegistroPartidoP"; // Correct path

// Elector
import BuscarElectorP from "../pages/participante/PaginaBuscarElectores"; // Correct path
import EditarElectorP from "../pages/participante/ElectorEditar"; // Correct path
import RegistroElectorP from "../pages/participante/ElectorRegistro"; // Correct path

// Proceso
import ProcesoP from "../pages/proceso/ProcesoInicio"; // Correct path
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
  

    {/* Partido */}
    <Route path="sistema/buscarpartido" element={<BuscarPartidoP />} />
    <Route path="sistema/buscarpartido/editar/:id" element={<EditarPartidoP />} />
    <Route path="sistema/registropartido" element={<RegistroPartidoP />} />

    {/* Elector */}
    <Route path="sistema/buscarelector" element={<BuscarElectorP />} />
    <Route path="sistema/buscarelector/editar/:id" element={<EditarElectorP />} />
    <Route path="sistema/registroelector" element={<RegistroElectorP />} />

    {/* Proceso Electoral */}
    <Route path="proceso" element={<ProcesoP />} />
    <Route path="proceso/registro" element={<RegistroProcesoP />} />
    <Route path="proceso/buscar" element={<BusquedaProcesoP />} />
    <Route path="proceso/buscar/editar/:id" element={<EditarProcesoP />} />
    <Route path="proceso/buscar/agregar/:id" element={<RegistroCandidatura />} />
  </Routes>
);

export default AdminRoutes;
