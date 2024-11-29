import { Outlet, Link } from "react-router-dom";

const MainLayout = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="/civil">Registro Civil</Link></li>
          <li><Link to="/colegio">Colegio Electoral</Link></li>
          <li><Link to="/votante">Votante</Link></li>
        </ul>
      </nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default MainLayout;
