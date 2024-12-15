import React from 'react';
import { Link } from 'react-router-dom';

const SistemaP = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="/colegio/sistema/par">Partidos</Link></li>
          <li><Link to="/colegio/sistema/ele">Electores</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default SistemaP;
