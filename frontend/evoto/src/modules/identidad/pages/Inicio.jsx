import React from "react";
import { Link } from "react-router-dom";

const Inicio = () => {
  return (
    <div>
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
    </div>
  );
};

export default Inicio;