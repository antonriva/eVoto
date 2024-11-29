import React, { useState, useEffect } from "react";
import { obtenerTodasLasPersonas, actualizarPersona, eliminarPersona } from "../../services/servicioPersona";
import FormularioPersona from "../../components/civil/FormularioPersona";
import ListaPersonas from "../../components/civil/ListaPersonas";
import BarraBusqueda from "../../components/civil/BarraBusqueda";

const PaginaActualizarEliminar = () => {
  const [personas, setPersonas] = useState([]);
  const [personasFiltradas, setPersonasFiltradas] = useState([]);
  const [personaSeleccionada, setPersonaSeleccionada] = useState(null);

  // Cargar todas las personas al inicio
  useEffect(() => {
    const cargarPersonas = async () => {
      const data = await obtenerTodasLasPersonas();
      setPersonas(data);
      setPersonasFiltradas(data); // Inicialmente mostrar todas las personas
    };
    cargarPersonas();
  }, []);

  // Manejar la búsqueda
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

  // Manejar la actualización
  const manejarActualizar = async (datos) => {
    await actualizarPersona(personaSeleccionada.id, datos);
    alert("Persona actualizada correctamente.");
  };

  // Manejar la eliminación
  const manejarEliminar = async () => {
    await eliminarPersona(personaSeleccionada.id);
    alert("Persona eliminada correctamente.");
    setPersonaSeleccionada(null);
    // Recargar la lista después de eliminar
    const data = await obtenerTodasLasPersonas();
    setPersonas(data);
    setPersonasFiltradas(data);
  };

  return (
    <div>
      <h2>Actualizar o Eliminar Persona</h2>

      {/* Barra de búsqueda */}
      <BarraBusqueda onBuscar={manejarBusqueda} />

      {/* Lista de personas filtradas */}
      <ListaPersonas
        personas={personasFiltradas}
        onSeleccionar={(persona) => setPersonaSeleccionada(persona)}
      />

      {/* Formulario de actualización o botón de eliminación */}
      {personaSeleccionada ? (
        <div>
          <h3>Editando a: {personaSeleccionada.nombre} {personaSeleccionada.apellidoPaterno}</h3>
          <FormularioPersona onSubmit={manejarActualizar} datosIniciales={personaSeleccionada} />
          <button onClick={manejarEliminar} style={{ marginTop: "10px", backgroundColor: "red", color: "white" }}>
            Eliminar
          </button>
        </div>
      ) : (
        <p>Selecciona una persona desde la búsqueda para actualizar o eliminar.</p>
      )}
    </div>
  );
};

export default PaginaActualizarEliminar;