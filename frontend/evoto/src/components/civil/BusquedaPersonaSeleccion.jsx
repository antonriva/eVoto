import React, { useState, useEffect } from "react";

const BusquedaPersonaSeleccion = ({ onSeleccionarPersona }) => {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState({
    id: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: ""
  });

  // Método para buscar personas
  const fetchPersonas = async (filtros) => {
    const queryParams = new URLSearchParams(filtros).toString();
    try {
      const response = await fetch(`http://localhost:8080/api/persona/buscar?${queryParams}`);
      if (!response.ok) {
        throw new Error(`Error en la respuesta: ${response.status} ${response.statusText}`);
      }
      const data = await response.json();
      setPersonas(data);
    } catch (error) {
      console.error("Error al buscar personas:", error);
      alert("Error al buscar personas. Por favor, intente nuevamente.");
    }
  };

  // Manejar el envío del formulario de búsqueda
  const handleBuscar = (e) => {
    e.preventDefault();
    fetchPersonas(filtros);
  };

  // Manejar la selección de una persona
  const handleSeleccionar = (id) => {
    onSeleccionarPersona(id); // Devuelve el ID de la persona seleccionada
  };

  return (
    <div>
      <h2>Buscar Persona</h2>
      {/* Formulario de Filtros */}
      <form onSubmit={handleBuscar} style={{ marginBottom: "20px" }}>
        <div>
          <label>ID:</label>
          <input
            type="text"
            name="id"
            value={filtros.id}
            onChange={(e) => setFiltros({ ...filtros, id: e.target.value })}
            placeholder="ID"
          />
        </div>
        <div>
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={filtros.nombre}
            onChange={(e) => setFiltros({ ...filtros, nombre: e.target.value })}
            placeholder="Nombre"
          />
        </div>
        <div>
          <label>Apellido Paterno:</label>
          <input
            type="text"
            name="apellidoPaterno"
            value={filtros.apellidoPaterno}
            onChange={(e) =>
              setFiltros({ ...filtros, apellidoPaterno: e.target.value })
            }
            placeholder="Apellido Paterno"
          />
        </div>
        <div>
          <label>Apellido Materno:</label>
          <input
            type="text"
            name="apellidoMaterno"
            value={filtros.apellidoMaterno}
            onChange={(e) =>
              setFiltros({ ...filtros, apellidoMaterno: e.target.value })
            }
            placeholder="Apellido Materno"
          />
        </div>
        <div>
          <label>Fecha de Nacimiento:</label>
          <input
            type="date"
            name="fechaDeNacimiento"
            value={filtros.fechaDeNacimiento}
            onChange={(e) =>
              setFiltros({ ...filtros, fechaDeNacimiento: e.target.value })
            }
          />
        </div>
        <div>
          <label>Fecha de Fin:</label>
          <input
            type="date"
            name="fechaDeFin"
            value={filtros.fechaDeFin}
            onChange={(e) =>
              setFiltros({ ...filtros, fechaDeFin: e.target.value })
            }
          />
        </div>
        
        <button type="submit">Buscar</button>
      </form>

      {/* Tabla de Resultados */}
      <table border="1" style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Fecha de Nacimiento</th>
            <th>Fecha de Fin</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          {personas.map((persona) => (
            <tr key={persona.id}>
              <td>{persona.id}</td>
              <td>{persona.nombre}</td>
              <td>{persona.apellidoPaterno}</td>
              <td>{persona.apellidoMaterno}</td>
              <td>{persona.fechaDeNacimiento}</td>
              <td>{persona.fechaDeFin}</td>
              <td>
                <button onClick={() => handleSeleccionar(persona.id)}>Seleccionar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BusquedaPersonaSeleccion;
