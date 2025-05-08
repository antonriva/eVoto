import { Outlet } from "react-router-dom";

const CivilLayout = () => {
  return (
    <div>
      {/* Barra de navegación específica para civil */}
      <nav
        className="navbar"
        style={{
          backgroundColor: "#FFC107", // Color mostaza
          padding: "10px",
        }}
      >
        <div className="container-fluid">
          <span style={{ fontWeight: "bold", fontSize: "1.2rem", color: "#000" }}>
            Sistema de Registro Civil
          </span>
        </div>
      </nav>
      {/* Contenido de la página */}
      <div>
        <Outlet />
      </div>
    </div>
  );
};

export default CivilLayout;
