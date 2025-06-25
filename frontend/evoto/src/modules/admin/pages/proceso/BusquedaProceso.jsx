import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table";
import ExpandableRow from "../../components/procesoExpandableRow/ProcesoExpandableRow";
import FiltrosProcesos from "../../components/FiltrosInstancia"
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css"; 


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

  const sanitizeData = (data) => {
    return Object.keys(data).reduce((result, key) => {
      if (data[key] !== null && data[key] !== undefined && data[key] !== "") {
        result[key] = data[key];
      }
      return result;
    }, {});
  };
  

  ////////////////VISUALIZACION

// Función para obtener instancias con filtros
const fetchInstancias = async (params = {}) => {
  try {
    // Sanitiza los parámetros antes de enviar la petición
    const sanitizedParams = sanitizeData(params);

    // Formatea los parámetros (elimina valores vacíos o inválidos)
    const query = new URLSearchParams(formatFilters(sanitizedParams)).toString();

    // Realiza la solicitud al backend
    const response = await fetch(
      `${import.meta.env.VITE_API_URL}/instancia/buscar?${query}`
    );

    if (!response.ok) {
      throw new Error("Error al cargar instancias de proceso.");
    }

    // Convierte la respuesta en JSON
    const data = await response.json();

    // Opcional: Formateo adicional si necesitas estandarizar campos vacíos
    const formattedData = data.map((instancia) => ({
      ...instancia,
      fechaDeInicio: instancia.fechaDeInicio || "---",
      fechaDeFin: instancia.fechaDeFin || "---",
      descripcion: instancia.descripcion || "Sin descripción",
    }));

    // Actualiza el estado con las instancias obtenidas
    setInstancias(formattedData);
    setError(""); // Limpia cualquier error previo si la carga es exitosa
  } catch (error) {
    console.error("Error al cargar instancias de proceso:", error);
    setError("Error al cargar instancias de proceso. Por favor, inténtalo de nuevo.");
  }
};


  // Función para obtener candidaturas asociadas a una instancia
  const fetchCandidaturas = async (idDeInstanciaDeProceso) => {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/candidatura/${idDeInstanciaDeProceso}`
      );
      if (!response.ok) {
        throw new Error(`Error al obtener candidaturas para instancia con ID ${idDeInstanciaDeProceso}`);
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




  ///////////////METODOS MULTIPLES

  // Función para eliminar una instancia de proceso
  const eliminarInstancia = async (id) => {
    const confirmar = window.confirm(
      "¿Estás seguro de que deseas eliminar esta instancia de proceso?"
    );
    if (confirmar) {
      try {
        const response = await fetch(
          `${import.meta.env.VITE_API_URL}/instancia/eliminar/${id}`,
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

  const agregarInstancia = (id ) => {
    navigate(`agregar/${id}`);
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



  const handleRegresar = () => {
    navigate("/colegio/proceso"); // Regresa al menú anterior
  };

  const agregarCandidatura = (idInstancia) => {
    navigate(`/colegio/proceso/buscar/agregar/${idInstancia}`); // Navigate to the route with idInstancia
  };

  // Function to delete a candidatura
  const eliminarCandidatura = async (idCandidatura) => {
    const confirmar = window.confirm(
      `¿Estás seguro de que deseas eliminar la candidatura con ID ${idCandidatura}?`
    );

    if (confirmar) {
      try {
        const response = await fetch(
          `${import.meta.env.VITE_API_URL}/candidatura/eliminar/${idCandidatura}`,
          {
            method: "DELETE",
          }
        );

        if (response.ok) {
          alert(`Candidatura con ID ${idCandidatura} eliminada exitosamente.`);
          // Optionally refresh candidaturas or update state
          setInstancias((prevInstancias) =>
            prevInstancias.map((instancia) => ({
              ...instancia,
              candidaturas: instancia.candidaturas.filter(
                (candidatura) => candidatura.idCandidatura !== idCandidatura
              ),
            }))
          );
        } else {
          const errorMessage = await response.text();
          if (response.status === 404) {
            alert(`Error: La candidatura con ID ${idCandidatura} no existe.`);
          } else if (response.status === 409) {
            alert(`Error: ${errorMessage}`);
          } else {
            alert(`Error inesperado: ${errorMessage}`);
          }
        }
      } catch (error) {
        console.error(`Error al eliminar candidatura con ID ${idCandidatura}:`, error);
        alert("Error inesperado al eliminar la candidatura. Por favor, inténtelo nuevamente.");
      }
    }
  };

  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Colegio electoral", to: "/colegio" },
    { label: "Procesos electorales", to: "/colegio/proceso" },
    { label: "Catálogo de procesos" },
  ];


  return (
    <div>
      <div className="app-layout-container">
        <Breadcrumbs items={breadcrumbItems} />
        <h1 className="display-4 fw-bold text-dark text-center">Catálogo de Instancias de Proceso</h1>
        <div className="row mb-4 gx-3 align-items-stretch">
          <div className="col-md-6 d-flex">
            <div className="card p-3 w-100 h-100">

            {error && <p style={{ color: "red" }}>{error}</p>} {/* Muestra el error al usuario */}
            <FiltrosProcesos
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={() => fetchInstancias(filtros)}
      />
            </div>
          </div>
        </div>


      {/* Tabla de Instancias */}
      <Table
        headers={headers}
        data={instancias}
        renderRow={(instancia) => (
          <ExpandableRow
            key={instancia.idDeInstanciaDeProceso}
            idInstancia={instancia.idDeInstanciaDeProceso}
            rowData={[
              instancia.idDeInstanciaDeProceso,
              instancia.descripcionNivel,
              instancia.descripcionProceso,
              instancia.fechaHoraDeInicio,
              instancia.fechaHoraDeFin,
              instancia.entidadFederativa,
              instancia.municipio,
              instancia.localidad,
              instancia.votoTotal,
            ]}
            fetchCandidaturas={() => fetchCandidaturas(instancia.idDeInstanciaDeProceso)}
            colSpan={headers.length}
            onEdit={(id) => editarInstancia(id)}
            onDelete={(id) => eliminarInstancia(id)}
            onAdd={(id) => agregarInstancia(id)}
            onAddCandidatura={(id) => agregarCandidatura(id)}
            onDeleteCandidatura={(idCandidatura) => eliminarCandidatura(idCandidatura)} // Pass delete function
          />
        )}
      />
            </div>
    </div>
  );
};

export default PaginaBuscarInstanciaDeProceso;
