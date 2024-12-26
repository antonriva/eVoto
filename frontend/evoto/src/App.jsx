import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";

// Páginas de cada aplicación
import AppCivil from "./pages/civil/AppCivil";
import AppColegio from "./pages/colegio/AppColegio";
import AppVotante from "./pages/votante/AppVotante";

// Import Bootstrap CSS globally
import "bootstrap/dist/css/bootstrap.min.css";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route path="/civil/*" element={<AppCivil />} />
          <Route path="/colegio/*" element={<AppColegio />} />
          <Route path="/votante/*" element={<AppVotante />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
