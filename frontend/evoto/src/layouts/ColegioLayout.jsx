import React from 'react';
import { Link, Outlet } from 'react-router-dom';

const ColegioLayout = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="about">Acerca del Colegio Electoral</Link></li>
          <li><Link to="personas">Prueba HTTP para DML en persona</Link></li>
          <li><Link to="proceso">Proceso Electoral</Link></li>
          <li><Link to="resultados">Resultados</Link></li>
        </ul>
      </nav>

      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default ColegioLayout;
