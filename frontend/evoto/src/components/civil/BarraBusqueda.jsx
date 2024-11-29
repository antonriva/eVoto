import React, { useState } from "react";

const BarraBusqueda = ({ onBuscar }) => {
  const [criterio, setCriterio] = useState("");

  const manejarCambio = (e) => {
    setCriterio(e.target.value);
  };

  const manejarEnvio = (e) => {
    e.preventDefault();
    if (criterio.trim() === "") {
      alert("Por favor, ingresa un criterio de búsqueda.");
      return;
    }
    onBuscar(criterio);
  };

  return (
    <form onSubmit={manejarEnvio} style={{ marginBottom: "20px" }}>
      <label htmlFor="busqueda">Buscar: </label>
      <input
        type="text"
        id="busqueda"
        placeholder="Ingresa un nombre o ID"
        value={criterio}
        onChange={manejarCambio}
        required
      />
      <button type="submit">Buscar</button>
    </form>
  );
};

export default BarraBusqueda;