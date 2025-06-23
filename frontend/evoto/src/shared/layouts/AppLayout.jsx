import { Outlet, useLocation } from "react-router-dom";
import Footer from "../../shared/components/footer/Footer"; // Importa el pie de página
import AppNavbar from "../../shared/components/navbar/AppNavbar"; // Import the new AppNavbar component
import { getActiveSubNavbar } from "../components/navbar/SubnavbarConfig"; // Import the logic for subnavbars
import "./AppLayout.css"; // Import global styles for the layout

const AppLayout = () => {
  const location = useLocation(); // Detectar la ruta actual
  const currentPath = location.pathname; // Obtener la ruta actual

  // Get the active subnavbar based on the current path
  const SubNavbarComponent = getActiveSubNavbar(currentPath);

  return (
    <div className="app-layout-wrapper">
      {/* Navbar */}
      <AppNavbar />

      {/* SubNavbar */}
      {SubNavbarComponent && <SubNavbarComponent />}

      {/* Contenido principal */}
      <main className="app-main">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default AppLayout;
