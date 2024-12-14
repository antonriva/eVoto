import React from 'react';
import { Link } from 'react-router-dom';

const ColegioElectoralPag = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* Enlace corregido para la ruta de "Nuevo proceso electoral" */}
          <li><Link to="">Partidos</Link></li>
          <li><Link to="">Electores</Link></li>
          <li><Link to="">Elementos generales</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default ColegioElectoralPag;
