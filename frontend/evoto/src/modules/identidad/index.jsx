import React from "react";
import { Routes, Route } from "react-router-dom";
import PaginaBuscar from "./pages/PaginaBuscar"; // Import the PaginaBuscar component
import PaginaRegistro from "./pages/PaginaRegistro"; // Import the PaginaRegistro component
import PaginaEditar from "./pages/PaginaEditar"; // Import the PaginaEditar component
import Inicio from "../../modules/identidad/pages/Inicio"; // Import Inicio


const AppIdentidad = () => {
  return (
    <Routes>
      <Route index element={<Inicio />} />
      <Route path="buscar" element={<PaginaBuscar />} />
      <Route path="registro" element={<PaginaRegistro />} />
      <Route path="buscar/editar/:id" element={<PaginaEditar />} />
    </Routes>
  );
};

export default AppIdentidad;