import { Routes, Route } from "react-router-dom";
import AppLayout from "../shared/layouts/AppLayout";
import Home from "../pages/home/Home"; // Import the Home component

// Páginas de cada aplicación
import AppIdentidad from "../modules/identidad/index"; // Import the AppCivil component
import AppAdmin from "../modules/admin/index"
import AppVotante from "../modules/votacion/pages/AppVotante";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<AppLayout />}>
        <Route index element={<Home />} /> {/* Render Home for the root path */}
        <Route path="/civil/*" element={<AppIdentidad />} />
        <Route path="/colegio/*" element={<AppAdmin />} />
        <Route path="/votante/*" element={<AppVotante />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;