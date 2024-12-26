import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import AboutCiv from "./AboutCiv";
import PaginaBuscar from "./PaginaBuscar";
import PaginaRegistro from "./PaginaRegistro";
import PaginaEditar from "../../components/civil/paginaBuscar/PaginaEditar";

const AppCivil = () => {
  return (
    <div style={{ backgroundColor: "#f8f9fa", minHeight: "100vh", padding: "20px" }}>
      {/* Header */}
      <header className="mb-4 text-center">
        <h1 className="display-4 text-primary">Registro Civil</h1>
        <p className="lead">
          Plataforma para la visualización, registro y edición de personas.
        </p>
      </header>

      {/* Navigation */}
      <div className="container">
        <div className="row justify-content-center mb-4">
          <div className="col-md-4">
            <Link to="buscar" className="btn btn-outline-primary btn-block mb-2">
              Buscar Personas
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="registro" className="btn btn-outline-success btn-block mb-2">
              Registrar Persona
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="/" className="btn btn-outline-secondary btn-block mb-2">
              Regresar al Menú Principal
            </Link>
          </div>
        </div>
      </div>

      {/* Routes Section */}
      <div className="container">
        <div className="card shadow-sm">
          <div className="card-body">
            <Routes>
              <Route path="about" element={<AboutCiv />} />
              <Route path="buscar" element={<PaginaBuscar />} />
              <Route path="registro" element={<PaginaRegistro />} />
              <Route path="buscar/editar/:id" element={<PaginaEditar />} />
            </Routes>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AppCivil;
