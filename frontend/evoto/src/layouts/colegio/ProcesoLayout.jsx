import React from 'react';
import { Link, Outlet } from 'react-router-dom';

const ProcesoLayout = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="definir">Definir Proceso Electoral</Link></li>
        </ul>
      </nav>

      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default ProcesoLayout;
