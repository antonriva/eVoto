import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../components/civil/paginaBuscar/Table";
import ExpandableRow from "../../components/civil/paginaBuscar/ExpandableRow";
import FiltrosPersonas from "../../components/civil/paginaBuscar/FiltrosPersonas";
import TimerRedirect from "../../components/externos/TimerRefresher";

const PaginaBuscar = () => {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState({
    id: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    anioNacimiento: "",
    mesNacimiento: "",
    diaNacimiento: "",
    anioFin: "",
    mesFin: "",
    diaFin: "",
    entidadFederativa: "",
    municipio: "",
    localidad: "",
    colonia: "",
    codigoPostal: "",
    tipoDeDomicilio: ""
  });

  const navigate = useNavigate();

  // Función para formatear los filtros, eliminando valores vacíos
  const formatFilters = (filters) => {
    return Object.fromEntries(
      Object.entries(filters).filter(([_, value]) => value !== "")
    );
  };

  const [error, setError] = useState("");

  // Función para obtener personas con filtros
  const fetchPersonas = async (params = {}) => {
    try {
      const query = new URLSearchParams(formatFilters(params)).toString();
      const response = await fetch(`http://localhost:8080/api/persona/buscar?${query}`);
      if (!response.ok) {
        throw new Error("Error al cargar personas.");
      }
      const data = await response.json();

      // Transformar datos si es necesario (fechas en formato LocalDate)
      const formattedData = data.map((persona) => ({
        ...persona,
        fechaDeNacimiento: persona.fechaDeNacimiento || "---",
        fechaDeFin: persona.fechaDeFin || "---",
      }));

      setPersonas(formattedData);
      setError(""); // Limpia el mensaje de error si la carga es exitosa
    } catch (error) {
      console.error("Error al cargar personas:", error);
      setError("Error al cargar personas. Por favor, inténtalo de nuevo."); // Actualiza el mensaje
    }
  };

  // Carga inicial sin filtros
  useEffect(() => {
    fetchPersonas();
  }, []);

    // Función para obtener domicilios de una persona
    const fetchDomicilios = async (idPersona) => {
      try {
        const response = await fetch(`http://localhost:8080/api/persona/${idPersona}/detalles-domicilios`);
        if (!response.ok) {
          throw new Error(`Error al obtener domicilios para persona con ID ${idPersona}`);
        }
        return await response.json();
      } catch (error) {
        console.error("Error al cargar domicilios:", error);
        return []; // Retorna un arreglo vacío en caso de error
      }
    };

    


  // Función para eliminar una persona
const eliminarPersona = async (id) => {
  const confirmar = window.confirm("¿Estás seguro de que deseas eliminar esta persona?");
  if (confirmar) {
    try {
      const response = await fetch(`http://localhost:8080/api/persona/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("Persona eliminada correctamente.");
        setPersonas((prev) => prev.filter((persona) => persona.id !== id));
      } else {
        const errorMessage = await response.text();
        if (response.status === 404) {
          alert(`Error: La persona con ID ${id} no existe.`);
        } else if (response.status === 409) {
          alert(`Error: ${errorMessage}`);
        } else {
          alert(`Error inesperado al eliminar la persona: ${errorMessage}`);
        }
      }
    } catch (error) {
      console.error(`Error al eliminar persona con ID ${id}:`, error);
      alert("Error inesperado al eliminar la persona. Por favor, inténtelo nuevamente.");
    }
  }
};


  // Función para redirigir a la página de edición
  const editarPersona = (id) => {
    navigate(`editar/${id}`);
  };

  const headers = [
    "ID",
    "Nombre",
    "Apellido Paterno",
    "Apellido Materno",
    "Fecha de Nacimiento",
    "Fecha de Fin",
    "Domicilios",
    "Acciones"
  ];

  return (
    <div>
      <h1>Catálogo de Personas</h1>
      <TimerRedirect/>

      {error && <p style={{ color: "red" }}>{error}</p>} {/* Muestra el error al usuario */}
      {/* Formulario de Filtros */}
      <FiltrosPersonas
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={() => fetchPersonas(filtros)}
      />

      {/* Tabla de Personas */}
      <Table
        headers={headers}
        data={personas}
        renderRow={(persona) => (
          <ExpandableRow
            key={persona.id}
            idPersona={persona.id}
            rowData={[
              persona.id,
              persona.nombre,
              persona.apellidoPaterno,
              persona.apellidoMaterno,
              persona.fechaDeNacimiento, // Mostrar la fecha de nacimiento directamente
              persona.fechaDeFin, // Mostrar la fecha de fin directamente
            ]}
            fetchDomicilios={() => fetchDomicilios(persona.id)}
            colSpan={headers.length}
            onEdit={(id) => editarPersona(id)}
            onDelete={(id) => eliminarPersona(id)}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscar;
