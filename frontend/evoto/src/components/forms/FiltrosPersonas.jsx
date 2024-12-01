import React from "react";

const FiltrosPersonas = ({ filtros, setFiltros, onBuscar }) => {
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFiltros((prevFiltros) => ({
      ...prevFiltros,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onBuscar(filtros); // Llama a la función del padre para buscar con los filtros actuales
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      <div>
        <label>ID:</label>
        <input
          type="text"
          name="id"
          value={filtros.id || ""}
          onChange={handleChange}
          placeholder="ID"
        />
      </div>
      <div>
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={filtros.nombre || ""}
          onChange={handleChange}
          placeholder="Nombre"
        />
      </div>
      <div>
        <label>Apellido Paterno:</label>
        <input
          type="text"
          name="apellidoPaterno"
          value={filtros.apellidoPaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Paterno"
        />
      </div>
      <div>
        <label>Apellido Materno:</label>
        <input
          type="text"
          name="apellidoMaterno"
          value={filtros.apellidoMaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Materno"
        />
      </div>
      <div>
        <label>Fecha de Nacimiento:</label>
        <input
          type="date"
          name="fechaDeNacimiento"
          value={filtros.fechaDeNacimiento || ""}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Cantidad de Domicilios:</label>
        <input
          type="number"
          name="cantidadDomicilios"
          value={filtros.cantidadDomicilios || ""}
          onChange={handleChange}
          placeholder="Cantidad exacta"
          min="0"
        />
      </div>
      <div>
        <label>Fecha de Fin:</label>
        <input
          type="date"
          name="fechaDeFin"
          value={filtros.fechaDeFin || ""}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Buscar</button>
    </form>
  );
};

export default FiltrosPersonas;
