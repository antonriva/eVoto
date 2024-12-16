import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../components/civil/paginaBuscar/Table";
import ExpandableRow from "../../../../components/colegio/paginaBuscar/ExpandableRow";
import FiltrosPersonas from "../../../../components/colegio/paginaBuscar/FiltrosElectores";

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

  const fetchElectores = async (params = {}) => {
    try {
      const query = new URLSearchParams(formatFilters(params)).toString();
      const response = await fetch(`http://localhost:8080/api/elector/buscar?${query}`);
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
    navigate(-1); // Regresa al menú anterior
  };

  useEffect(() => {
    fetchElectores();
  }, []);

  const fetchCandidaturas = async (idElector) => {
    try {
      const response = await fetch(`http://localhost:8080/api/candidatura/elector/${idElector}`);
      if (!response.ok) {
        throw new Error(`Error al obtener candidaturas para el elector con ID ${idElector}`);
      }
      return await response.json();
    } catch (error) {
      console.error("Error al cargar candidaturas:", error);
      return [];
    }
  };

  const eliminarElector = async (idElector) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar este elector?");
    if (confirmar) {
      try {
        const response = await fetch(`http://localhost:8080/api/elector/${idElector}`, {
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
    "Candidaturas",
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
            key={elector.idElector}
            idElector={elector.idElector}
            rowData={[
              elector.idElector,
              elector.nombre,
              elector.apellidoPaterno,
              elector.apellidoMaterno,
              elector.fechaDeNacimiento,
              elector.fechaDeFin,
            ]}
            fetchCandidaturas={() => fetchCandidaturas(elector.idElector)}
            colSpan={headers.length}
            onEdit={(id) => editarElector(id)}
            onDelete={(id) => eliminarElector(id)}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscarElectores;
