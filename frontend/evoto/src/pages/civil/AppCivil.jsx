import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import AboutCiv from "./AboutCiv";
import PaginaRegistrar from "./PaginaRegistrar";
import PaginaBuscar from "./PaginaBuscar";
import PaginaActualizarEliminar from "./PaginaActualizarEliminar";

const AppCivil = () => {
  return (
    <div style={{ backgroundColor: "#f0f4c3", minHeight: "100vh", padding: "20px" }}>
      <h1>Registro Civil</h1>
      <nav>
        <ul>
          <li><Link to="/">Regresar al Menu principal</Link></li>
          <li><Link to="about">Inicio</Link></li>
          <li><Link to="registrar">Registrar Persona</Link></li>
          <li><Link to="buscar">Buscar Personas</Link></li>
          <li><Link to="actualizar-eliminar">Actualizar o Eliminar Persona</Link></li>
        </ul>
      </nav>

      <Routes>
        <Route path="about" element={<AboutCiv />} />
        <Route path="registrar" element={<PaginaRegistrar />} />
        <Route path="buscar" element={<PaginaBuscar />} />
        <Route path="actualizar-eliminar" element={<PaginaActualizarEliminar />} />
      </Routes>
    </div>
  );
};

export default AppCivil;
