import React from 'react';
import { Link } from 'react-router-dom';

const SistemaP = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="/colegio">Regresar</Link></li>
          <li><Link to="/colegio/sistema/par">Sistema de partidos</Link></li>
          <li><Link to="/colegio/sistema/ele">Sistema de electores</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default SistemaP;
