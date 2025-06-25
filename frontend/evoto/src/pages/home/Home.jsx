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