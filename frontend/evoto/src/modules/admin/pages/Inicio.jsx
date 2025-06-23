import React from "react";
import { Link } from "react-router-dom";
import { useMenuVisibility } from "../hooks/useMenuVisibility";

const Inicio = () => {
  const { menuVisible, handleMenuToggle } = useMenuVisibility();

  return (
    <div>
      {/* Header */}
      <header className="mb-4 text-center">
        <h1 className="display-4 text-primary">Colegio Electoral</h1>
        <p className="lead">
          Sistema para el registro de candidatos, electores y gestión de votos.
          Administra partidos, electores y procesos electorales.
        </p>
      </header>

      {/* Navigation */}
      {menuVisible && (
        <div className="container mb-4">
          <div className="row justify-content-center">
            <div className="col-md-3">
              <Link
                to="/"
                className="btn btn-outline-secondary btn-block mb-2"
              >
                Menú Principal
              </Link>
            </div>
            <div className="col-md-3">
              <Link
                to="sistema"
                onClick={() => handleMenuToggle("sistema")}
                className="btn btn-outline-primary btn-block mb-2"
              >
                Sistema Electoral
              </Link>
            </div>
            <div className="col-md-3">
              <Link
                to="proceso"
                onClick={() => handleMenuToggle("proceso")}
                className="btn btn-outline-success btn-block mb-2"
              >
                Proceso Electoral
              </Link>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Inicio;
