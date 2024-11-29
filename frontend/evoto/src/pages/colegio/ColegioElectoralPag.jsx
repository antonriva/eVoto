import React from 'react';
import { Link } from 'react-router-dom';

const ColegioElectoralPag = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* Enlace corregido para la ruta de "Nuevo proceso electoral" */}
          <li><Link to="definir">Nuevo proceso electoral</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default ColegioElectoralPag;
