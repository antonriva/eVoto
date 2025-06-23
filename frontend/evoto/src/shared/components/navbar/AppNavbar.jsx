import { Link } from "react-router-dom";
import "./AppNavbar.css"; // Import styles if needed
import "../../layouts/AppLayout.css"; // Import global styles

const AppNavbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark navbar-custom bg-burgundy">

        {/* Logo del Gobierno de México */}
        <div className="app-layout-container d-flex w-100">
          <Link to="/" className="navbar-brand me-5">
            <img
              src="https://framework-gb.cdn.gob.mx/gobmx/img/logo_blanco.svg"
              alt="Logo Gobierno de México"
              className="navbar-logo"
            />
          </Link>



        {/* Botón de navegación móvil */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Opciones del menú */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto gap-4 ">
            <li className="nav-item">
              <Link className="nav-link nav-link-custom text-white" to="/civil">
                Registro civil
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link nav-link-custom text-white" to="/colegio">
                Colegio Electoral
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link nav-link-custom text-white" to="/votante">
                Votante
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default AppNavbar;