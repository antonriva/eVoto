import { Outlet, Link, useLocation } from "react-router-dom";
import Footer from "../components/externos/Footer"; // Importa el pie de página

const MainLayout = () => {
  const location = useLocation(); // Detectar la ruta actual

  return (
    <div className="container-fluid">
      {/* Barra de Navegación con Color Personalizado */}
      <nav
  className="navbar navbar-expand-lg navbar-dark"
  style={{
    backgroundColor: "#611232", // Color de fondo de la barra
    height: "60px", // Altura de la barra personalizada
    marginLeft: "-1%",
    marginRight: "-0.6%",
  }}
  >
  <div className="container-fluid d-flex align-items-center">
    {/* Logo del Gobierno de México */}
    <Link
  to="/"
  className="navbar-brand"
  style={{
    display: "flex", // Contenedor flexible
    alignItems: "center", // Centra verticalmente el logo
    justifyContent: "flex-start", // Ajusta el logo a la posición inicial
    width: "auto", // El ancho se adapta al contenido
    height: "100%", // Altura completa del contenedor de la barra
    position: "relative", // Evita conflictos con otros elementos
    zIndex: 10, // Asegura que esté frente a otros elementos
    marginLeft: "12.45rem", // Mover el logo hacia la derecha
  }}
  >
  <img
    src="https://framework-gb.cdn.gob.mx/gobmx/img/logo_blanco.svg"
    alt="Logo Gobierno de México"
    style={{
      width: "8rem", // Tamaño personalizado del logo
      height: "auto", // Mantiene las proporciones del logo
      pointerEvents: "none", // Evita que el logo bloquee clics
    }}
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
            <ul
              className="navbar-nav"
              style={{
                marginLeft: "68%", // Adjust this value to move all items to the left
              }}
            >
              <li className="nav-item">
                <Link
                  className="nav-link"
                  to="/civil"
                  style={{
                    color: "white", // Color blanco sólido
                    textDecoration: "none", // Eliminar subrayado
                  }}
                >
                Registro civil
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  className="nav-link"
                  to="/colegio"
                  style={{
                    color: "white", // Color blanco sólido
                    textDecoration: "none", // Eliminar subrayado
                  }}
                >
                  Colegio Electoral
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  className="nav-link"
                  to="/votante"
                  style={{
                    color: "white", // Color blanco sólido
                    textDecoration: "none", // Eliminar subrayado
                  }}
                >
                  Votante
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

            {/* Image Section */}
            {location.pathname === "/" && (            
            <div
        style={{
          width: "100%", // Full width
          height: "auto", // Auto-adjust height
          textAlign: "center", // Center the content
          backgroundImage: `url('path/to/your/image.png')`, // Background image
          backgroundSize: "cover", // Cover the area
          backgroundRepeat: "no-repeat", // Avoid repeating
          backgroundPosition: "center", // Center the image
          marginLeft: "-1%",
          marginRight: "-1%",
        }}
      >
        {/* Alternative: Add an <img> tag */}
        <img
          src="https://framework-gb.cdn.gob.mx/landing/img/gmx-04.jpg"
          alt="Main Banner"
          style={{
            width: "100%", // Full width
            height: "auto", // Maintain proportions
          }}
        />

<div style={{ padding: "2rem", textAlign: "center", backgroundColor: "#ffffff" }}> {/* Fondo blanco puro */}
<h2 style={{ fontWeight: "bold", color: "#343a40", marginBottom: "1rem", fontSize: "2rem" }}> {/* Texto más grande */}
              SUVECI - Sistema Único de Voto Electrónico y Consulta Ciudadana
            </h2>
            <p style={{ fontSize: "1.25rem", color: "#000000", lineHeight: "1.5", textAlign: "justify" }}> {/* Texto aumentado */}
              Este portal <strong>NO OFICIAL</strong> con fines académicos plantea la simulación de un sistema nacional de voto electrónico llamado 
              <strong> SUVECI</strong>. Está compuesto por tres aplicaciones principales: registro civil, colegio 
              electoral y la aplicación de ingreso para los votantes. El objetivo es demostrar las capacidades de un 
              sistema único que pueda administrar procesos y consultas electorales de manera digital y centralizada.
            </p>
          </div>
      </div>
  )}

        {/* Recuadros de Aplicaciones */}
        {location.pathname === "/" && (
        <div className="container my-4">
          <div className="row">
            {/* Registro Civil */}
            <div className="col-md-4">
  <div
    className="card shadow-sm"
    style={{
      border: "1px solid #ddd", // Borde similar
      borderRadius: "8px", // Bordes redondeados
    }}
  >
    <div className="card-body text-center">
      {/* Icono */}
      <img
        src="https://www.gob.mx/cms/uploads/image/file/487050/01_Identidad_dorado_2.png" // Cambia por un icono representativo
        alt="Registro Civil"
        className="mb-3"
        style={{
          width: "110px", // Tamaño reducido para ajustarse al diseño
          height: "90px", // Mantener proporciones
          marginTop: "25px", // Agregar espacio superior
        }}
      />


      {/* Título */}
      <h5
        className="card-title"
        style={{
          fontWeight: "bold",
          color: "#343a40", // Color negro suave
          fontSize: "1.3rem", // Tamaño de letra del título (aumentado)
          lineHeight: "2"
        }}
      >
        <Link
          to="/civil"
          style={{
            textDecoration: "none", // Eliminar subrayado
            color: "#343a40", // Color negro suave
          }}
        >
          Registro Civil
        </Link>
      </h5>
      {/* Opciones */}
      <ul
        className="list-unstyled"
        style={{
          padding: 0,
          margin: 0,
          color: "#6c757d", // Color gris claro
          fontSize: "1rem", // Tamaño de letra más pequeño
          lineHeight: "1.8", // Espaciado entre líneas más amplio
        }}
      >
        <li>
          <Link
            to="/civil/buscar"
            style={{
              textDecoration: "none", // Eliminar subrayado
              color: "#6c757d", // Color gris claro
            }}
          >
            Búsqueda
          </Link>
        </li>
        <li>
          <Link
            to="/civil/registro"
            style={{
              textDecoration: "none", // Eliminar subrayado
              color: "#6c757d", // Color gris claro
            }}
          >
            Registro
          </Link>
        </li>
      </ul>
    </div>
  </div>
</div>


            {/* Colegio Electoral */}
            <div className="col-md-4">
              <div className="card shadow-sm">
                <div className="card-body text-center">
                  <img
                    src="/visuales/insti.png" // Cambia por un icono representativo
                    alt="Colegio Electoral"
                    className="mb-3"
                    style={{
                      width: "200px", // Tamaño reducido para ajustarse al diseño
                      height: "120px", // Mantener proporciones
                    }}
                  />


      {/* Título */}
      <h5
        className="card-title"
        style={{
          fontWeight: "bold",
          color: "#343a40", // Color negro suave
          fontSize: "1.3rem", // Tamaño de letra del título (aumentado)
          lineHeight: "2",
          marginTop: "-10px" // Agregar espacio superior
        }}
      >
                    <Link to="/colegio" style={{ textDecoration: "none", color: "#343a40", fontWeight: "bold" }}>
                      Colegio Electoral
                    </Link>
                  </h5>
                  <ul className="list-unstyled"
                          style={{
                            padding: 0,
                            margin: 0,
                            color: "#6c757d", // Color gris claro
                            fontSize: "1rem", // Tamaño de letra más pequeño
                            lineHeight: "2", // Espaciado entre líneas más amplio
                          }}
                  >
                    <li><Link to="/colegio/sistema"
                                style={{
                                  textDecoration: "none", // Eliminar subrayado
                                  color: "#6c757d", // Color gris claro
                                }}
                    >Sistema</Link></li>
                    <li><Link to="/colegio/proceso"
                                style={{
                                  textDecoration: "none", // Eliminar subrayado
                                  color: "#6c757d", // Color gris claro
                                }}
                    >Proceso</Link></li>
                  </ul>
                </div>
              </div>
            </div>





            {/* Votante */}
            <div className="col-md-4">
              <div className="card shadow-sm">
                <div className="card-body text-center">
                  <img
                    src="/visuales/boleta.png" // Cambia por un icono representativo
                    alt="Votante"
                    className="mb-3"
                    style={{
                      width: "180px", // Tamaño reducido para ajustarse al diseño
                      height: "120px", // Mantener proporciones
                    }}
                  />
                  <h5 className="card-title"
                          style={{
                            fontWeight: "bold",
                            color: "#343a40", // Color negro suave
                            fontSize: "1.3rem", // Tamaño de letra del título (aumentado)
                            lineHeight: "2",
                            marginTop: "-10px", // Agregar espacio superior
                          }}
                  >
                    <Link to="/votante" style={{ textDecoration: "none", color: "#343a40", fontWeight: "bold" }}>
                      Votante
                    </Link>
                  </h5>
                  <ul className="list-unstyled"
                                            style={{
                                              padding: 0,
                                              margin: 0,
                                              color: "#6c757d", // Color gris claro
                                              fontSize: "1rem", // Tamaño de letra más pequeño
                                              lineHeight: "2", // Espaciado entre líneas más amplio
                                            }}
                  >
                    <li><Link to="/votante/ingreso"
                                                    style={{
                                                      textDecoration: "none", // Eliminar subrayado
                                                      color: "#6c757d", // Color gris claro
                                                    }}
                    >Ingreso</Link></li>
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
