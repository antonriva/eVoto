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
            <div className="card shadow-sm card-custom">
              <div className="card-body card-body-custom">
                <img
                  src="https://www.gob.mx/cms/uploads/image/file/487050/01_Identidad_dorado_2.png"
                  alt="Registro Civil"
                  className="mb-3 card-icon"
                />
                <h5 className="card-title card-title-custom">
                  <Link to="/civil">Registro Civil</Link>
                </h5>
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
    </div>
  );
};

export default Home;