import { Routes, Route } from "react-router-dom";
import AppLayout from "../layouts/AppLayout";
import Home from "../paginas/home/Home"; // Import the Home component

// Páginas de cada aplicación
import AppCivil from "../pages/civil/AppCivil";
import AppColegio from "../pages/colegio/AppColegio";
import AppVotante from "../pages/votante/AppVotante";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<AppLayout />}>
        <Route index element={<Home />} /> {/* Render Home for the root path */}
        <Route path="/civil/*" element={<AppCivil />} />
        <Route path="/colegio/*" element={<AppColegio />} />
        <Route path="/votante/*" element={<AppVotante />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;