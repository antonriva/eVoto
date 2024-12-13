import React, { useState, useEffect } from "react";
import Table from "../../components/civil/Table";
import ExpandableRow from "../../components/civil/ExpandableRow";
import FiltrosPersonas from "../../components/forms/FiltrosPersonas"; // Nuevo componente

const PaginaBuscar = () => {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState({
    id: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "", // Nuevo campo para fecha de fin
  });

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

  const headers = [
    "ID",
    "Nombre",
    "Apellido Paterno",
    "Apellido Materno",
    "Fecha de Nacimiento",
    "Fecha de Fin",
    "Acciones",
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
            colSpan={8}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscar;
