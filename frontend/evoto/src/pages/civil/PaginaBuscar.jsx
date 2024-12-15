import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../components/civil/paginaBuscar/Table";
import ExpandableRow from "../../components/civil/paginaBuscar/ExpandableRow";
import FiltrosPersonas from "../../components/civil/paginaBuscar/FiltrosPersonas"; // Nuevo componente

const PaginaBuscar = () => {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState({
    id: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "", // Nuevo campo para fecha de fin
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
    tipoDeDomicilio:""
  });

  const navigate = useNavigate(); // Para navegación entre páginas

  // Función para obtener personas con o sin filtros
  const fetchPersonas = async (params = {}) => {
    try {
      const query = new URLSearchParams(params).toString();
      const response = await fetch(`http://localhost:8080/api/persona/buscar?${query}`);
      if (!response.ok) {
        throw new Error("Error al cargar personas.");
      }
      const data = await response.json();
      setPersonas(data);
    } catch (error) {
      console.error("Error al cargar personas:", error);
    }
  };

  // Carga inicial sin filtros
  useEffect(() => {
    fetchPersonas();
  }, []);

  // Función para obtener domicilios
  const fetchDomicilios = async (id) => {
    const response = await fetch(
      `http://localhost:8080/api/persona/${id}/detalles-domicilios`
    );
    if (!response.ok) {
      throw new Error(`Error al obtener domicilios para persona con ID ${id}`);
    }
    return response.json();
  };

  // Función para eliminar una persona
  const eliminarPersona = async (id) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar esta persona?");
    if (confirmar) {
      try {
        const response = await fetch(`http://localhost:8080/api/persona/${id}`, { method: "DELETE" });
  
        if (response.ok) {
          alert("Persona eliminada exitosamente.");
          setPersonas((prev) => prev.filter((persona) => persona.id !== id));
        } else if (response.status === 409) {
          const errorMessage = await response.text();
          alert(`No se pudo eliminar la persona. ${errorMessage}`);
        } else {
          alert("Error al eliminar persona.");
        }
      } catch (error) {
        console.error(`Error al eliminar persona con ID ${id}:`, error);
        alert("Error inesperado al eliminar persona.");
      }
    }
  };
  

  // Función para redirigir a la página de edición
  //////SE QUITO UNA BARRA 
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
      
      {/* Formulario de Filtros */}
      <FiltrosPersonas
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={fetchPersonas}
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
              persona.fechaDeNacimiento,
              persona.fechaDeFin || "---",
            ]}
            fetchDomicilios={fetchDomicilios}
            colSpan={9}
            onEdit={(id) => editarPersona(id)} // Función de edición
            onDelete={(id) => eliminarPersona(id)} // Función de eliminación
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscar;
