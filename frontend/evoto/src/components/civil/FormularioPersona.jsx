import React, { useState } from "react";
import DateRegistroSelectorForm from "../forms/DateRegistroSelectorForm"; // Selector de fecha personalizado

const FormularioPersona = ({ onSubmit, datosIniciales = {} }) => {
  const [formulario, setFormulario] = useState({
    nombre: datosIniciales.nombre || "",
    apellidoPaterno: datosIniciales.apellidoPaterno || "",
    apellidoMaterno: datosIniciales.apellidoMaterno || "",
    fechaNacimiento: datosIniciales.fechaNacimiento || null,
  });

  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  const manejarEnvio = (e) => {
    e.preventDefault();
    onSubmit(formulario);
  };

  return (
    <form onSubmit={manejarEnvio}>
      <div>
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={formulario.nombre}
          onChange={manejarCambio}
          pattern="^[a-zA-Z\s]+$"
          title="Solo se permiten letras y espacios."
          required
        />
      </div>
      <div>
        <label>Apellido Paterno:</label>
        <input
          type="text"
          name="apellidoPaterno"
          value={formulario.apellidoPaterno}
          onChange={manejarCambio}
          pattern="^[a-zA-Z\s]+$"
          title="Solo se permiten letras y espacios."
          required
        />
      </div>
      <div>
        <label>Apellido Materno:</label>
        <input
          type="text"
          name="apellidoMaterno"
          value={formulario.apellidoMaterno}
          onChange={manejarCambio}
          pattern="^[a-zA-Z\s]+$"
          title="Solo se permiten letras y espacios."
          required
        />
      </div>
      <div>
        <label>Fecha de Nacimiento:</label>
        <DateRegistroSelectorForm
          fechaSeleccionada={formulario.fechaNacimiento}
          onChange={(fecha) => setFormulario({ ...formulario, fechaNacimiento: fecha })}
        />
      </div>
      <button type="submit">Enviar</button>
    </form>
  );
};

export default FormularioPersona;
