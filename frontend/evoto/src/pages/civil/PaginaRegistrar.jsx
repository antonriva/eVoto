import React, { useState } from "react";
import FormularioRegistro from "../../components/forms/FormularioRegistro";

const PaginaRegistrar = () => {
  // Estado inicial para los datos del formulario
  const [formData, setFormData] = useState({
    id: "",
    primerNombre: "",
    segundoNombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "", // Campo opcional
  });

  // Método que maneja la validación y el envío del formulario
  const handleRegistrar = async (data) => {
    // Procesar los datos para eliminar espacios en blanco al enviar
    const datosProcesados = {
      primerNombre: data.primerNombre.trim(),
      segundoNombre: data.segundoNombre?.trim() || "",
      apellidoPaterno: data.apellidoPaterno.trim(),
      apellidoMaterno: data.apellidoMaterno.trim(),
      fechaDeNacimiento: data.fechaDeNacimiento,
      fechaDeFin: data.fechaDeFin?.trim() || null,
    };
  
    const { primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, fechaDeNacimiento } =
      datosProcesados;
  
    // Validar que los campos obligatorios estén llenos
    if (!primerNombre || !apellidoPaterno || !apellidoMaterno || !fechaDeNacimiento) {
      alert("Todos los campos obligatorios deben estar llenos.");
      return;
    }
  
    // Validar que la fecha de nacimiento no sea mayor al día actual
    const hoy = new Date().toISOString().split("T")[0]; // Obtiene la fecha actual en formato YYYY-MM-DD
    if (fechaDeNacimiento > hoy) {
      alert("La fecha de nacimiento no puede ser mayor al día actual.");
      return;
    }
  
    // Combinar primerNombre y segundoNombre en un solo string
    const nombre = segundoNombre ? `${primerNombre} ${segundoNombre}` : primerNombre;
  
    // Crear el objeto para enviar al backend
    const datosAEnviar = {
      nombre,
      apellidoPaterno,
      apellidoMaterno,
      fechaDeNacimiento,
      fechaDeFin: datosProcesados.fechaDeFin,
    };
  
    // Realizar la solicitud al backend
    try {
      const response = await fetch("http://localhost:8080/api/persona", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(datosAEnviar),
      });
  
      if (response.ok) {
        const personaCreada = await response.json();
        alert(`Persona registrada con éxito: ID ${personaCreada.id}`);
  
        // Resetear el formulario después del registro
        setFormData({
          id: "",
          primerNombre: "",
          segundoNombre: "",
          apellidoPaterno: "",
          apellidoMaterno: "",
          fechaDeNacimiento: "",
          fechaDeFin: "",
        });
      } else {
        const errorData = await response.json();
        alert(`Error al registrar persona: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error al registrar persona:", error);
      alert("Hubo un problema al intentar registrar la persona.");
    }
  };
  return (
    <div>
      <h1>Registrar Persona</h1>
      {/* Componente FormularioRegistro */}
      <FormularioRegistro
        datos={formData} // Estado del formulario
        setDatos={setFormData} // Actualizador del estado
        onSubmit={handleRegistrar} // Método que maneja el registro
        modo="registro" // Indica que está en modo registro
      />
    </div>
  );
};

export default PaginaRegistrar;
