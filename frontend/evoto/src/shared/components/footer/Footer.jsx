import "./Footer.css"; // Import the Footer.css file

const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="row">
          {/* Columna 1: Logo */}
          <div className="col-md-4 text-center text-md-left">
            <img
              src="https://framework-gb.cdn.gob.mx/gobmx/img/logo_blanco.svg"
              alt="Logo Gobierno de México"
              className="footer-logo"
            />
          </div>
          {/* Columna 2: Enlaces */}
          <div className="col-md-4 text-center">
            <h6></h6>
          </div>
          {/* Columna 3: Información */}
          <div className="col-md-4 text-center text-md-left">
            <h6>¿Qué es gob.mx?</h6>
            <p className="footer-info">
              Es el portal único de trámites, información y participación ciudadana.
            </p>
          </div>
        </div>
        <div className="text-center footer-small">
          <small>© 2024 Gobierno de México. Todos los derechos reservados.</small>
        </div>
      </div>
    </footer>
  );
};

export default Footer; // Exporta el componente para usarlo en otros archivos