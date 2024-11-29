import React, { useState, useEffect } from "react";
import { obtenerTodasLasPersonas } from "../../services/servicioPersona";
import ListaPersonas from "../../components/civil/ListaPersonas";
import BarraBusqueda from "../../components/civil/BarraBusqueda";

const PaginaBuscar = () => {
  const [personas, setPersonas] = useState([]);
  const [personasFiltradas, setPersonasFiltradas] = useState([]);

  // Cargar todas las personas al inicio
  useEffect(() => {
    const cargarPersonas = async () => {
      const data = await obtenerTodasLasPersonas();
      setPersonas(data);
      setPersonasFiltradas(data); // Inicialmente, mostrar todas las personas
    };
    cargarPersonas();
  }, []);

  // Función para manejar la búsqueda
  const manejarBusqueda = (criterio) => {
    const resultados = personas.filter(
      (persona) =>
        persona.nombre.toLowerCase().includes(criterio.toLowerCase()) ||
        persona.apellidoPaterno.toLowerCase().includes(criterio.toLowerCase()) ||
        persona.apellidoMaterno.toLowerCase().includes(criterio.toLowerCase()) ||
        persona.id.toString().includes(criterio)
    );
    setPersonasFiltradas(resultados);
  };

  return (
    <div>
      <h2>Buscar Personas</h2>
      {/* Barra de búsqueda */}
      <BarraBusqueda onBuscar={manejarBusqueda} />
      
      {/* Lista de personas filtradas */}
      <ListaPersonas
        personas={personasFiltradas}
        onSeleccionar={(persona) => alert(JSON.stringify(persona))}
      />
    </div>
  );
};

export default PaginaBuscar;