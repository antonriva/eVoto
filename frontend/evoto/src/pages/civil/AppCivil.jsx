import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import AboutCiv from "./AboutCiv";
import PaginaBuscar from "./PaginaBuscar";
import PaginaRegistrar from "./PaginaRegistrar";
import EditarPersona from "../../components/civil/paginaBuscar/EditarPersona";

const AppCivil = () => {
  return (
    <div style={{ backgroundColor: "#f0f4c3", minHeight: "100vh", padding: "20px" }}>
      <h1>Registro Civil</h1>
      <nav>
        <ul>
          <li><Link to="/">Regresar al Menú Principal</Link></li>
          <li><Link to="about">Inicio</Link></li>
          <li><Link to="buscar">Buscar Personas</Link></li>
          <li><Link to="registrar">Registrar Persona</Link></li>
        </ul>
      </nav>

      <Routes>
        <Route path="about" element={<AboutCiv />} />
        <Route path="buscar" element={<PaginaBuscar />} />
        <Route path="registrar" element={<PaginaRegistrar />} />
        <Route path="buscar/editar/:id" element={<EditarPersona />} />
      </Routes>
    </div>
  );
};

export default AppCivil;
