import React from 'react';

const ListaPersonas = ({ personas, onSeleccionar }) => {
  return (
    <div>
      <h2>Resultados de la Búsqueda</h2>
      <ul>
        {personas.map((persona) => (
          <li key={persona.id} onClick={() => onSeleccionar(persona)}>
            {persona.nombre} {persona.apellidoPaterno} {persona.apellidoMaterno}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListaPersonas;