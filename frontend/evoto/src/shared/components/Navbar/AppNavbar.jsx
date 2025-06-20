import { Link } from "react-router-dom";
import "./AppNavbar.css"; // Import styles if needed

const AppNavbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark navbar-custom">
      <div className="container-fluid d-flex align-items-center">
        {/* Logo del Gobierno de México */}
        <Link to="/" className="navbar-brand">
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
          <ul className="navbar-nav navbar-nav-custom">
            <li className="nav-item">
              <Link className="nav-link nav-link-custom" to="/civil">
                Registro civil
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link nav-link-custom" to="/colegio">
                Colegio Electoral
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link nav-link-custom" to="/votante">
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