import React from 'react';
import { Link } from 'react-router-dom';

const PartidosP = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* Enlace corregido para la ruta de "Nuevo proceso electoral" */}
          <li><Link to="/colegio/sistema/par/buscar">Buscar</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default PartidosP;
