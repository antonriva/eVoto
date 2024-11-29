import React from 'react';
import { Link } from 'react-router-dom';

const ProcesoElectoralPag = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* El enlace ahora es relativo a la ruta actual */}
          <li><Link to="formulario">Definir proceso electoral</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default ProcesoElectoralPag;
