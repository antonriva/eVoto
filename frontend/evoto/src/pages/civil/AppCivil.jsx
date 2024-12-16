import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import AboutCiv from "./AboutCiv";
import PaginaBuscar from "./PaginaBuscar";
import PaginaRegistro from "./PaginaRegistro";
import PaginaEditar from "../../components/civil/paginaBuscar/PaginaEditar";

const AppCivil = () => {
  return (
    <div style={{ backgroundColor: "#f0f4c3", minHeight: "100vh", padding: "20px" }}>
      <h1>Registro Civil</h1>
      <nav>
        <ul>
          <li><Link to="/">Regresar al Menú Principal</Link></li>
          <li><Link to="about">Inicio</Link></li>
          <li><Link to="buscar">Buscar Personas</Link></li>
          <li><Link to="registro">Registrar Persona</Link></li>
        </ul>
      </nav>

      <Routes>
        <Route path="about" element={<AboutCiv />} />
        <Route path="buscar" element={<PaginaBuscar />} />
        <Route path="registro" element={<PaginaRegistro />} />
        <Route path="buscar/editar/:id" element={<PaginaEditar />} />
      </Routes>
    </div>
  );
};

export default AppCivil;
