import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const EditarPersona = () => {
  const { id } = useParams(); // ID de la persona desde la URL
  const navigate = useNavigate(); // Para redirigir después de guardar
  const [formData, setFormData] = useState({
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "",
  });
  const [loading, setLoading] = useState(true);

  // Obtener datos iniciales
  useEffect(() => {
    const fetchData = async () => {
      try {
        const personaResponse = await fetch(`http://localhost:8080/api/persona/${id}/editar`);
        if (!personaResponse.ok) {
          throw new Error("Error al cargar los datos de la persona.");
        }
        const personaData = await personaResponse.json();

        setFormData({
          nombre: personaData.nombre,
          apellidoPaterno: personaData.apellidoPaterno,
          apellidoMaterno: personaData.apellidoMaterno,
          fechaDeNacimiento: personaData.fechaDeNacimiento,
          fechaDeFin: personaData.fechaDeFin || "",
        });
      } catch (error) {
        console.error(error);
        alert("Error al cargar los datos iniciales.");
        navigate(-1); // Regresar a la página anterior
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [id, navigate]);

  // Manejo de cambios en el formulario
  const handleChange = (e) => {
    const { name, value } = e.target;

    // Validar que las fechas no sean posteriores a hoy
    if ((name === "fechaDeNacimiento" || name === "fechaDeFin") && value > new Date().toISOString().split("T")[0]) {
      alert("Las fechas no pueden ser posteriores al día de hoy.");
      return;
    }

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  // Manejo del envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/api/persona/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Error al actualizar los datos de la persona.");
      }

      alert("Persona actualizada correctamente.");
      navigate("/civil/buscar"); // Redirigir al catálogo
    } catch (error) {
      console.error("Error al actualizar la persona:", error);
      alert("Error al actualizar la persona.");
    }
  };

  if (loading) {
    return <p>Cargando datos...</p>;
  }

  return (
    <div>
      <h1>Editar Persona</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Apellido Paterno:</label>
          <input
            type="text"
            name="apellidoPaterno"
            value={formData.apellidoPaterno}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Apellido Materno:</label>
          <input
            type="text"
            name="apellidoMaterno"
            value={formData.apellidoMaterno}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Fecha de Nacimiento:</label>
          <input
            type="date"
            name="fechaDeNacimiento"
            value={formData.fechaDeNacimiento}
            onChange={handleChange}
            max={new Date().toISOString().split("T")[0]} // No permitir fechas futuras
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
            max={new Date().toISOString().split("T")[0]} // No permitir fechas futuras
          />
        </div>
        <button type="submit">Guardar Cambios</button>
        <button type="button" onClick={() => navigate("/civil/buscar")}>Cancelar</button>
      </form>
    </div>
  );
};

export default EditarPersona;
