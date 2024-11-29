import { Routes, Route, Link } from "react-router-dom";

const InicioVotantePag = () => <h1>¡Hola, bienvenido a la sección de votantes!</h1>;
const ConsultaPag = () => <h1>Consulta de registro de votante</h1>;
const EleccionPag = () => <h1>Realiza tu elección aquí</h1>;

const VotanteApp = () => {
  return (
    <div>
      {/* Barra de navegación del votante */}
      <nav>
        <ul>
          <li>
            <Link to="/">Regresar al Menú Principal</Link>
          </li>
          <li>
            <Link to="/votante">Inicio del Votante</Link>
          </li>
          <li>
            <Link to="/votante/consulta">Consulta de Registro</Link>
          </li>
          <li>
            <Link to="/votante/eleccion">Realizar Elección</Link>
          </li>
        </ul>
      </nav>

      {/* Rutas internas del módulo Votante */}
      <main>
        <Routes>
          <Route path="/votante" element={<InicioVotantePag />} />
          <Route path="/votante/consulta" element={<ConsultaPag />} />
          <Route path="/votante/eleccion" element={<EleccionPag />} />
        </Routes>
      </main>
    </div>
  );
};

export default VotanteApp;