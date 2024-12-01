import React from "react";

const FormularioRegistro = ({ datos, setDatos, onSubmit, modo }) => {
  const handleChange = (e) => {
    const { name, value } = e.target;

    setDatos((prevDatos) => ({
      ...prevDatos,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(datos); // Llama al método handleRegistrar
  };

  const esRegistro = modo === "registro";

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      <div>
        <label>Primer Nombre:</label>
        <input
          type="text"
          name="primerNombre"
          value={datos.primerNombre || ""}
          onChange={handleChange}
          placeholder="Primer Nombre"
          required={esRegistro}
        />
      </div>
      <div>
        <label>Segundo Nombre:</label>
        <input
          type="text"
          name="segundoNombre"
          value={datos.segundoNombre || ""}
          onChange={handleChange}
          placeholder="Segundo Nombre (Opcional)"
        />
      </div>
      <div>
        <label>Apellido Paterno:</label>
        <input
          type="text"
          name="apellidoPaterno"
          value={datos.apellidoPaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Paterno"
          required={esRegistro}
        />
      </div>
      <div>
        <label>Apellido Materno:</label>
        <input
          type="text"
          name="apellidoMaterno"
          value={datos.apellidoMaterno || ""}
          onChange={handleChange}
          placeholder="Apellido Materno"
          required={esRegistro}
        />
      </div>
      <div>
            <label>Fecha de Nacimiento:</label>
            <input
            type="date"
            name="fechaDeNacimiento"
            value={datos.fechaDeNacimiento || ""}
            onChange={handleChange}
            required
            max={new Date().toISOString().split("T")[0]} // Limita la fecha máxima al día actual
            />
        </div>

      <button type="submit">{esRegistro ? "Registrar" : "Buscar"}</button>
    </form>
  );
};

export default FormularioRegistro;
