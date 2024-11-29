import { Outlet, Link } from "react-router-dom";

const CivilLayout = () => {
  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">Regresar al Menú Principal</Link>
          </li>
          <li>
            <Link to="/civil">Inicio del Registro Civil</Link>
          </li>
          <li>
            <Link to="/civil/registro">Registrar Persona</Link>
          </li>
          <li>
            <Link to="/civil/consulta">Consultar Personas</Link>
          </li>
        </ul>
      </nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default CivilLayout;