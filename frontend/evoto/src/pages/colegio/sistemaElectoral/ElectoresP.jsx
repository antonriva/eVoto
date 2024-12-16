import React from 'react';
import { Link } from 'react-router-dom';

const ElectoresP = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* Enlace corregido para la ruta de "Nuevo proceso electoral" */}
          <li><Link to="/colegio/sistema">Regresar</Link></li>
          <li><Link to="/colegio/sistema/ele/buscar">Buscar</Link></li>
          <li><Link to="/colegio/sistema/ele/registro">Registro</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default ElectoresP;
