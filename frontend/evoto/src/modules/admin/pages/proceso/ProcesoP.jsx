import React from 'react';
import { Link } from 'react-router-dom';

const ProcesoP = () => {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="/colegio">Regresar</Link></li>
          <li><Link to="/colegio/proceso/registro">Registro de proceso</Link></li>
          <li><Link to="/colegio/proceso/buscar">Editar proceso y candidaturas</Link></li>
          <li><Link to="">Resultados por proceso</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default ProcesoP;
