import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../components/civil/paginaBuscar/Table";
import ExpandableRow from "../../../../components/../components/colegio/paginaBuscar/ExpandableRow";
import FiltrosProcesos from "../../../../components/colegio/paginaBuscar/FiltrosElectores";

const PaginaBuscarInstanciaDeProceso = () => {
  const [instancias, setInstancias] = useState([]);
  const [filtros, setFiltros] = useState({
    idDeInstanciaDeProceso: "", // Ajustado al nombre esperado
    idNivel: "", // Ajustado al nombre esperado
    idProceso: "",
    anioInicio: "",
    mesInicio: "",
    diaInicio: "",
    anioFin: "",
    mesFin: "",
    diaFin: "",
    idEntidadFederativa: "",
    idMunicipio: "",
    idLocalidad: "",
  });

  const navigate = useNavigate();
  const [error, setError] = useState("");

  // Función para formatear los filtros, eliminando valores vacíos
  const formatFilters = (filters) => {
    return Object.fromEntries(
      Object.entries(filters).filter(([_, value]) => value !== "")
    );
  };

  // Función para obtener instancias con filtros
  const fetchInstancias = async (params = {}) => {
    try {
      const query = new URLSearchParams(formatFilters(params)).toString();
      const response = await fetch(
        `http://localhost:8080/api/instancia/buscar?${query}`
      );
      if (!response.ok) {
        throw new Error("Error al cargar instancias de proceso.");
      }
      const data = await response.json();

      setInstancias(data);
      setError(""); // Limpia el mensaje de error si la carga es exitosa
    } catch (error) {
      console.error("Error al cargar instancias de proceso:", error);
      setError("Error al cargar instancias de proceso. Por favor, inténtalo de nuevo.");
    }
  };


  // Función para obtener candidaturas asociadas a una instancia
  const fetchCandidaturas = async (idInstancia) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/instancia-de-proceso/${idInstancia}/candidaturas`
      );
      if (!response.ok) {
        throw new Error(`Error al obtener candidaturas para instancia con ID ${idInstancia}`);
      }
      return await response.json();
    } catch (error) {
      console.error("Error al cargar candidaturas:", error);
      return []; // Retorna un arreglo vacío en caso de error
    }
  };

  // Carga inicial sin filtros
  useEffect(() => {
    fetchInstancias();
  }, []);

  // Función para eliminar una instancia de proceso
  const eliminarInstancia = async (id) => {
    const confirmar = window.confirm(
      "¿Estás seguro de que deseas eliminar esta instancia de proceso?"
    );
    if (confirmar) {
      try {
        const response = await fetch(
          `http://localhost:8080/api/instancia-de-proceso/${id}`,
          {
            method: "DELETE",
          }
        );

        if (response.ok) {
          alert("Instancia eliminada correctamente.");
          setInstancias((prev) => prev.filter((instancia) => instancia.id !== id));
        } else {
          const errorMessage = await response.text();
          if (response.status === 404) {
            alert(`Error: La instancia con ID ${id} no existe.`);
          } else if (response.status === 409) {
            alert(`Error: ${errorMessage}`);
          } else {
            alert(`Error inesperado al eliminar la instancia: ${errorMessage}`);
          }
        }
      } catch (error) {
        console.error(`Error al eliminar instancia con ID ${id}:`, error);
        alert("Error inesperado al eliminar la instancia. Por favor, inténtelo nuevamente.");
      }
    }
  };

  // Función para redirigir a la página de edición
  const editarInstancia = (id) => {
    navigate(`editar/${id}`);
  };

  const headers = [
    "ID",
    "Nivel",
    "Proceso",
    "Fecha de Inicio",
    "Fecha de Fin",
    "Entidad Federativa",
    "Municipio",
    "Localidad",
    "Voto total",
    "Candidaturas",
    "Acciones",
  ];

  return (
    <div>
      <h1>Catálogo de Instancias de Proceso</h1>

      {error && <p style={{ color: "red" }}>{error}</p>} {/* Muestra el error al usuario */}
      {/* Formulario de Filtros */}
      <FiltrosProcesos
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={() => fetchInstancias(filtros)}
      />

      {/* Tabla de Instancias */}
      <Table
        headers={headers}
        data={instancias}
        renderRow={(instancia) => (
          <ExpandableRow
            key={instancia.id}
            idInstancia={instancia.id}
            rowData={[
              instancia.idDeInstanciaDeProceso,
              instancia.descripcionNivel,
              instancia.descripcionProceso,
              instancia.fechaHoraDeInicio,
              instancia.fechaHoraDeFin,
              instancia.entidadFederativa,
              instancia.municipio,
              instancia.localidad,
              instancia.votoTotal
            ]}
            fetchCandidaturas={() => fetchCandidaturas(instancia.id)}
            colSpan={headers.length}
            onEdit={(id) => editarInstancia(id)}
            onDelete={(id) => eliminarInstancia(id)}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscarInstanciaDeProceso;
