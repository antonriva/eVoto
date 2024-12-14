import React from 'react';
import { Link } from 'react-router-dom';

const ColegioElectoralPag = () => {
  return (
    <div>
      <nav>
        <ul>
          {/* Enlace corregido para la ruta de "Nuevo proceso electoral" */}
          <li><Link to="">Buscar</Link></li>
          <li><Link to="">Crear</Link></li>
          <li><Link to="">Modificar</Link></li> //La eliminacion sera colocar un fecha de fin 
        </ul>
      </nav>
    </div>
  );
};

export default ColegioElectoralPag;
