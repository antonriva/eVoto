import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const EditarPartido = () => {
  const { id } = useParams(); // Obtiene el ID del partido desde la URL
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    denominacion: "",
    siglas: "",
    fechaDeInicio: "",
    fechaDeFin: "",
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // Fetch inicial para cargar los datos del partido
  useEffect(() => {
    fetchPartidoData();
  }, []);

  const today = new Date().toISOString().split("T")[0]; // Fecha actual
  const oneHundredYearsAgo = new Date(
    new Date().getFullYear() - 100,
    new Date().getMonth(),
    new Date().getDate()
  )
    .toISOString()
    .split("T")[0]; // Hace 100 años

  const fetchPartidoData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/partido/${id}/editar`);
      if (!response.ok) {
        throw new Error("Error al obtener los datos del partido.");
      }
      const data = await response.json();
      setFormData({
        denominacion: data.denominacion || "",
        siglas: data.siglas || "",
        fechaDeInicio: data.fechaDeInicio || "",
        fechaDeFin: data.fechaDeFin || "",
      });
    } catch (error) {
      console.error("Error al cargar datos del partido:", error);
      setError("No se pudo cargar la información del partido.");
    } finally {
      setLoading(false);
    }
  };

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
      const response = await fetch(`${import.meta.env.VITE_API_URL}/partido/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      alert("Partido actualizado exitosamente.");
      navigate("/colegio/sistema/par"); // Redirige a la página de partidos
    } catch (error) {
      console.error("Error al actualizar partido:", error);
      navigate("/colegio/sistema/par"); // Redirige a la página de partidos
      setError("No se pudo actualizar la información del partido.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <h1>Editar Partido</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Denominación:</label>
          <input
            type="text"
            name="denominacion"
            value={formData.denominacion}
            onChange={handleChange}
            required
            pattern="^[a-zA-Z]+( [a-zA-Z]+)*$"
            title="La denominación debe contener solo letras y espacios intermedios, sin caracteres especiales."
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
            pattern="^[A-Z]{2,5}$"
            title="Las siglas deben contener entre 2 y 5 letras mayúsculas, sin espacios ni caracteres especiales."
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
            max={formData.fechaDeFin || today} // No posterior a la fecha de fin
            required
          />
        </div>
        <div>
          <label>Fecha de Fin:</label>
          <input
            type="date"
            name="fechaDeFin"
            value={formData.fechaDeFin}
            onChange={handleChange}
            min={formData.fechaDeInicio || oneHundredYearsAgo} // No anterior a la fecha de inicio
            max={today}
          />
        </div>
        <button type="submit">Guardar Cambios</button>
        <button type="button" onClick={() => navigate("/colegio/sistema/par")}>
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default EditarPartido;
