import { Link } from "react-router-dom";
import "./Home.css"; // Import styles if needed

const Home = () => {
  return (
    <div>
      {/* Image Section */}
      <div className="image-section">
        <img
          src="https://framework-gb.cdn.gob.mx/landing/img/gmx-04.jpg"
          alt="Main Banner"
          className="image-banner"
        />
      </div>

      {/* Description Section */}
      <div className="container my-4" style={{ backgroundColor: "#A97E29", color: "white", borderRadius: "8px", padding: "20px" }}>
        <div className="text-center">
          <h4 className="alert-heading">Bienvenido</h4>
          <p>
            Esta aplicación tiene como objetivo gestionar de forma integral un sistema de registro civil y electoral. Está dirigida a personal administrativo y operadores electorales, y permite realizar operaciones como registro de personas, búsqueda filtrada, gestión de electores, visualización de padrones y control de procesos de votación.
          </p>
        </div>
      </div>


      {/* Recuadros de Aplicaciones */}
      <div className="container my-4">
        <div className="row">
          {/* Registro Civil */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom w-100 h-100">
              <div className="card-body card-body-custom">
                <div className="card-icon-wrapper">
                  <img
                    src="/visuales/Persona.png"
                    alt="Registro Civil"
                    className="card-icon"
                  />
                </div>
                <h5 className="card-title card-title-custom">
                  <Link to="/civil">Registro civil</Link>
                </h5>
                <p className="card-text text-justify mb-3">
                  Realiza búsquedas, edición y nuevos registros de personas de manera sencilla
                </p>
              </div>
            </div>
          </div>

          {/* Colegio Electoral */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom w-100 h-100">
              <div className="card-body card-body-custom">
                <div className="card-icon-wrapper">
                  <img
                    src="/visuales/insti.png"
                    alt="Colegio Electoral"
                    className="card-icon"
                  />
                </div>
                <h5 className="card-title card-title-custom">
                  <Link to="/colegio">Colegio electoral</Link>
                </h5>
                <p className="card-text text-justify mb-3">
                  Administra partidos, electores y el proceso electoral de principio a fin de manera sencilla
                </p>
              </div>
            </div>
          </div>

          {/* Votante */}
          <div className="col-md-4">
            <div className="card shadow-sm card-custom w-100 h-100">
              <div className="card-body card-body-custom">
                <div className="card-icon-wrapper">
                  <img
                    src="/visuales/boleta.png"
                    alt="Votante"
                    className="card-icon"
                  />
                </div>
                <h5 className="card-title card-title-custom">
                  <Link to="/votante">Votante</Link>
                </h5>
                <p className="card-text text-justify mb-3">
                  Vista del votante. Consulta los procesos electores a los que tiene acceso y participa.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;