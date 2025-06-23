import React, { useState, useEffect } from "react";
import FiltrosPersonas from "../../paginaBuscar/FiltrosElectores";


const ModalBuscarElector = ({ onSelect }) => {
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
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchElectores();
  }, []);

  // Función para limpiar filtros eliminando campos vacíos
  const formatFilters = (filters) => {
    return Object.fromEntries(
      Object.entries(filters).filter(([_, value]) => value !== "")
    );
  };

  // Función para sanitizar parámetros (evitar null, undefined o vacíos)
  const sanitizeData = (data) => {
    return Object.keys(data).reduce((result, key) => {
      if (data[key] !== null && data[key] !== undefined && data[key] !== "") {
        result[key] = data[key];
      }
      return result;
    }, {});
  };

  // Función para obtener electores de la API
  const fetchElectores = async (params = {}) => {
    setLoading(true);
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
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      {/* Formulario de Filtros */}
      <FiltrosPersonas
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={() => fetchElectores(filtros)} // Realiza búsqueda con filtros
      />

      {/* Mostrar Cargando/Error o la Tabla */}
      {loading ? (
        <p>Cargando...</p>
      ) : error ? (
        <p style={{ color: "red" }}>{error}</p>
      ) : (
        <table border="1" style={{ width: "100%", marginTop: "10px" }}>
          <thead>
            <tr>
              <th>ID Elector</th>
              <th>Nombre</th>
              <th>Apellido apterno</th>
              <th>Fecha de nacimiento</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {electores.map((elector) => (
              <tr key={elector.idElector}>
                <td>{elector.idElector}</td>
                <td>{elector.nombre}</td>
                <td>{elector.apellidoPaterno}</td>
                <td>{elector.fechaDeNacimiento || "---"}</td>
                <td>
                  <button onClick={() => onSelect(elector.idElector)}>Seleccionar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ModalBuscarElector;
