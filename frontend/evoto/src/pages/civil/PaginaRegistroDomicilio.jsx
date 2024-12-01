import React, { useState } from "react";
import BusquedaPersonaSeleccion from "../../components/civil/BusquedaPersonaSeleccion";
import FormularioDomicilio from "../../components/forms/FormularioDomicilio";

const PaginaRegistroDomicilio = () => {
  const [personaSeleccionada, setPersonaSeleccionada] = useState(null);

  const handleSeleccionarPersona = (id) => {
    setPersonaSeleccionada(id); // Guarda el ID de la persona seleccionada
    console.log("Persona seleccionada con ID:", id);
  };

  const handleDomicilioRegistrado = () => {
    alert("Domicilio registrado con éxito. Puedes registrar otro domicilio.");
    setPersonaSeleccionada(null); // Reinicia la selección para buscar otra persona
  };

  return (
    <div>
      <h1>Registro de Domicilio</h1>
      {!personaSeleccionada ? (
        // Componente para buscar y seleccionar una persona
        <BusquedaPersonaSeleccion onSeleccionarPersona={handleSeleccionarPersona} />
      ) : (
        // Componente para registrar un domicilio asociado a la persona seleccionada
        <FormularioDomicilio
          personaId={personaSeleccionada}
          onDomicilioRegistrado={handleDomicilioRegistrado}
        />
      )}
    </div>
  );
};

export default PaginaRegistroDomicilio;
