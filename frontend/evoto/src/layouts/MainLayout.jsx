import { Outlet, Link, useLocation } from "react-router-dom";
import Footer from "../components/externos/Footer"; // Importa el pie de página
import CivilNavbar from "../rComponents/CivilNavbar"; // Importa la barra de navegación civil
import "../styles/layouts.css"; // Importa los estilos personalizados

const MainLayout = () => {
  const location = useLocation(); // Detectar la ruta actual
  const path = location.pathname; // Obtener la ruta actual

  let SubNavbar=null;
  if (path.startsWith("/civil")) {
    SubNavbar = <CivilNavbar />;
  }

  return (
    <div className="container-fluid">

      {/* Barra de Navegación con Color Personalizado */}
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

          {/* Botón para móviles */}
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

    {SubNavbar}


    {/* Image Section */}
    {location.pathname === "/" && (
     <div className="image-section">
       <img
       src="https://framework-gb.cdn.gob.mx/landing/img/gmx-04.jpg"
       alt="Main Banner"
       className="image-banner"
       />
     </div>
    )}

    {/* Recuadros de Aplicaciones */}
    {location.pathname === "/" && (
      <div className="container my-4">
        <div className="row">
          {/* Registro Civil */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom">
              <div className="card-body card-body-custom">
                {/* Icono */}
                <img
                  src="https://www.gob.mx/cms/uploads/image/file/487050/01_Identidad_dorado_2.png"
                  alt="Registro Civil"
                  className="mb-3 card-icon"
                />


                {/* Título */}
                <h5 className="card-title card-title-custom">
                  <Link to="/civil">Registro Civil</Link>
                </h5>

                {/* Opciones */}
                <ul className="list-unstyled card-options">
                  <li>
                    <Link to="/civil/buscar">Búsqueda</Link>
                  </li>
                  <li>
                    <Link to="/civil/registro">Registro</Link>
                  </li>
                </ul>
              </div>
            </div>
          </div>


          {/* Colegio Electoral */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom">
              <div className="card-body card-body-custom">
                <img
                  src="/visuales/insti.png"
                  alt="Colegio Electoral"
                  className="mb-3 card-icon"
                />
                <h5 className="card-title card-title-custom">
                  <Link to="/colegio">Colegio Electoral</Link>
                </h5>
                <ul className="list-unstyled card-options">
                  <li>
                    <Link to="/colegio/sistema">Sistema</Link>
                  </li>
                  <li>
                    <Link to="/colegio/proceso">Proceso</Link>
                  </li>
                </ul>
              </div>
            </div>
          </div>





          {/* Votante */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom">
              <div className="card-body card-body-custom">
                <img
                  src="/visuales/boleta.png"
                  alt="Votante"
                  className="mb-3 card-icon"
                />
                <h5 className="card-title card-title-custom">
                  <Link to="/votante">Votante</Link>
                </h5>
                <ul className="list-unstyled card-options">
                  <li>
                    <Link to="/votante/ingreso">Ingreso</Link>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    )}

    {/* Contenido principal */}
      <main >
        <Outlet />
      </main>
        <Footer/>
    </div>
  );
};

export default MainLayout;
