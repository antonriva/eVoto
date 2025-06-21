import React from "react";
import { Routes, Route } from "react-router-dom";
import AboutCiv from "./AboutCiv";
import PaginaBuscar from "./PaginaBuscar";
import PaginaRegistro from "./PaginaRegistro";
import PaginaEditar from "../../components/civil/paginaBuscar/PaginaEditar";
import Inicio from "../../modules/identidad/pages/Inicio"; // Import Inicio


const AppIdentidad = () => {
  return (
    <Routes>
      <Route index element={<Inicio />} />
      <Route path="about" element={<AboutCiv />} />
      <Route path="buscar" element={<PaginaBuscar />} />
      <Route path="registro" element={<PaginaRegistro />} />
      <Route path="buscar/editar/:id" element={<PaginaEditar />} />
    </Routes>
  );
};

export default AppIdentidad;