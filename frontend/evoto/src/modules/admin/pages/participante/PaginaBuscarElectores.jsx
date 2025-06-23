import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table";
import ExpandableRow from "../../../../shared/components/expandableRow/ExpandableRow";
import FiltrosPersonas from "../../components/FiltrosElectores";

const PaginaBuscarElectores = () => {
  const [electores, setElectores] = useState([]);
  const [filtros, setFiltros] = useState({
    idElector: "",
    anioInicioElector: "",
    mesInicioElector: "",
    diaInicioElector: "",
    anioFinElector: "",
    mesFinElector: "",
    diaFinElector: "",
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
  });

  const [error, setError] = useState("");
  const navigate = useNavigate();

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
  
  const fetchElectores = async (params = {}) => {
    try {
    const sanitizedParams = sanitizeData(params);
    const query = new URLSearchParams(formatFilters(sanitizedParams)).toString();

      const response = await fetch(`${import.meta.env.VITE_API_URL}/elector/buscar?${query}`);
      if (!response.ok) {
        throw new Error("Error al cargar electores.");
      }
      const data = await response.json();

      const formattedData = data.map((elector) => ({
        ...elector,
        fechaDeInicioElector: elector.fechaDeInicioElector || "---",
        fechaDeFinElector: elector.fechaDeFinElector || "---",
        fechaDeNacimiento: elector.fechaDeNacimiento || "---",
        fechaDeFin: elector.fechaDeFin || "---",
      }));



      setElectores(formattedData);
      setError("");
    } catch (error) {
      console.error("Error al cargar electores:", error);
      setError("Error al cargar electores. Por favor, inténtalo de nuevo.");
    }
  };

  

  const handleRegresar = () => {
    navigate("/colegio/sistema/ele"); // Regresa al menú anterior
  };

  useEffect(() => {
    fetchElectores();
  }, []);

  const fetchCandidaturas = async (idElector) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/candidatura/elector/${idElector}`);
      if (!response.ok) {
        throw new Error(`Error al obtener candidaturas para el elector con ID ${idElector}`);
      }
      return await response.json();
    } catch (error) {
      console.error("Error al cargar candidaturas:", error);
      return [];
    }
  };

  // Función para obtener domicilios de una persona
  const fetchDomicilios = async (idPersona) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/persona/${idPersona}/detalles-domicilios`);
      if (!response.ok) {
        throw new Error(`Error al obtener domicilios para persona con ID ${idPersona}`);
      }
      return await response.json();
    } catch (error) {
      console.error("Error al cargar domicilios:", error);
      return []; // Retorna un arreglo vacío en caso de error
    }
  };

  const eliminarElector = async (idElector) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar este elector?");
    if (confirmar) {
      try {
        const response = await fetch(`${import.meta.env.VITE_API_URL}/elector/${idElector}`, {
          method: "DELETE",
        });

        if (response.ok) {
          alert("Elector eliminado correctamente.");
          setElectores((prev) => prev.filter((elector) => elector.idElector !== idElector));
        } else {
          const errorMessage = await response.text();
          if (response.status === 404) {
            alert(`Error: El elector con ID ${idElector} no existe.`);
          } else if (response.status === 409) {
            alert(`Error: ${errorMessage}`);
          } else {
            alert(`Error inesperado al eliminar el elector: ${errorMessage}`);
          }
        }
      } catch (error) {
        console.error(`Error al eliminar elector con ID ${idElector}:`, error);
        alert("Error inesperado al eliminar el elector. Por favor, inténtelo nuevamente.");
      }
    }
  };

  const editarElector = (idElector) => {
    navigate(`editar/${idElector}`);
  };

  const headers = [
    "ID Elector",
    "Nombre",
    "Apellido Paterno",
    "Apellido Materno",
    "Fecha de Nacimiento",
    "Fecha de Fin",
    "Domicilios",
    "Acciones",
  ];

  return (
    <div>
      <h1>Catálogo de Electores</h1>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <button onClick={handleRegresar} style={{ marginBottom: "20px" }}>
        Regresar
      </button>

      {/* Formulario de Filtros */}
      <FiltrosPersonas
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={() => fetchElectores(filtros)}
      />

      {/* Tabla de Electores */}
            <Table
  headers={headers}
  data={electores}
  renderRow={(elector) => (
    <ExpandableRow
      key={elector.idElector} // Usamos el identificador único de la fila
      idElector={elector.idElector}
      idPersona={elector.id} // Mapeamos el "id" recibido del backend como idPersona
      rowData={[
        elector.idElector,
        elector.nombre,
        elector.apellidoPaterno,
        elector.apellidoMaterno,
        elector.fechaDeNacimiento,
        elector.fechaDeFin,
      ]}
      fetchDomicilios={() => fetchDomicilios(elector.id)} // Pasamos "id" directamente
            colSpan={headers.length}
            onEdit={() => editarElector(elector.idElector)}
            onDelete={() => eliminarElector(elector.idElector)}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscarElectores;
