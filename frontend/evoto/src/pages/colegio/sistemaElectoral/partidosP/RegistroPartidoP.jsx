import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const RegistroPartidoP = () => {
  const [formData, setFormData] = useState({
    denominacion: "",
    siglas: "",
    fechaDeInicio: "",
    fechaDeFin: "",
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const today = new Date().toISOString().split("T")[0];
  const oneHundredYearsAgo = new Date(new Date().getFullYear() - 100, new Date().getMonth(), new Date().getDate())
    .toISOString()
    .split("T")[0];

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
  
    try {
      const response = await fetch("http://localhost:8080/api/partido/registrar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
  
      if (response.status === 409) {
        const errorMessage = await response.text();
        alert(errorMessage); // Muestra el mensaje de error del backend
      } else if (!response.ok) {
        throw new Error(await response.text());
      } else {
        alert("Partido registrado exitosamente.");
        navigate("/colegio/sistema/par"); // Redirige a la lista de partidos
      }
    } catch (error) {
      console.error("Error al registrar partido:", error);
      setError("Error al registrar el partido. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Registro de Partido</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Denominación:</label>
          <input
            type="text"
            name="denominacion"
            value={formData.denominacion}
            onChange={handleChange}
            required
            pattern="^[A-Z]+( [A-Z]+)*$"
            title="La denominación debe contener solo letras y espacios."
          />
        </div>
        <div>
          <label>Siglas:</label>
          <input
            type="text"
            name="siglas"
            value={formData.siglas}
            onChange={handleChange}
            required
            pattern="^[A-Za-z]{1,5}$"
            title="Las siglas deben contener solo letras y un máximo de 5 caracteres."
          />
        </div>
        <div>
          <label>Fecha de Inicio:</label>
          <input
            type="date"
            name="fechaDeInicio"
            value={formData.fechaDeInicio}
            onChange={handleChange}
            min={oneHundredYearsAgo}
            max={formData.fechaDeFin || today}
            required
          />
        </div>
      
        <button type="submit" disabled={loading}>
          {loading ? "Registrando..." : "Registrar"}
        </button>
        <button type="button" onClick={() => navigate("/colegio/sistema/par")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default RegistroPartidoP;
