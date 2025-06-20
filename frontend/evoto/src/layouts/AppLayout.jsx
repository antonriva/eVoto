import { Outlet, useLocation } from "react-router-dom";
import Footer from "../shared/components/Footer"; // Importa el pie de página
import AppNavbar from "../shared/components/Navbar/AppNavbar"; // Import the new AppNavbar component
import { getActiveSubNavbar } from "./SubnavbarConfig"; // Import the logic for subnavbars

const AppLayout = () => {
  const location = useLocation(); // Detectar la ruta actual
  const currentPath = location.pathname; // Obtener la ruta actual

  // Get the active subnavbar based on the current path
  const SubNavbarComponent = getActiveSubNavbar(currentPath);

  return (
    <div className="container-fluid">
      {/* Navbar */}
      <AppNavbar />

      {/* SubNavbar */}
      {SubNavbarComponent && <SubNavbarComponent />}

      {/* Contenido principal */}
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default AppLayout;
