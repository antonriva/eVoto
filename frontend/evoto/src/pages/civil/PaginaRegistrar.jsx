import React from "react";
import { registrarPersona } from "../../services/servicioPersona";
import FormularioPersona from "../../components/civil/FormularioPersona";

const PaginaRegistrar = () => {
  const manejarEnvio = async (datos) => {
    await registrarPersona(datos);
    alert("Persona registrada exitosamente.");
  };

  return (
    <div>
      <h2>Registrar Persona</h2>
      <FormularioPersona onSubmit={manejarEnvio} />
    </div>
  );
};

export default PaginaRegistrar;
