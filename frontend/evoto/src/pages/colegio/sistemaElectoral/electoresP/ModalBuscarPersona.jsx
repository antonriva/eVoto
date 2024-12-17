import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../components/civil/paginaBuscar/Table";
import FiltrosPersonas from "../../../../components/civil/paginaBuscar/FiltrosPersonas";

const PaginaBuscar = ({ onSelect }) => {
    const [personas, setPersonas] = useState([]);
    const [error, setError] = useState([]);
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
  
      // Función para formatear los filtros, eliminando valores vacíos
  const formatFilters = (filters) => {
    return Object.fromEntries(
      Object.entries(filters).filter(([_, value]) => value !== "")
    );
  };
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

  
    useEffect(() => {
      fetchPersonas();
    }, []);

    const headers = [
        "ID",
        "Nombre",
        "Apellido Paterno",
        "Apellido Materno",
        "Acciones"
      ];
  
    return (
      <div>
        <h1>Catálogo de Personas</h1>

        {error && <p style={{ color: "red" }}>{error}</p>}

        <FiltrosPersonas
          filtros={filtros}
          setFiltros={setFiltros}
          onBuscar={() => fetchPersonas(filtros)}
        />
        <Table
            headers={headers}
            data={personas}
            renderRow={(persona) => (
              <tr key={persona.id}>
                <td>{persona.id}</td>
                <td>{persona.nombre}</td>
                <td>{persona.apellidoPaterno}</td>
                <td>{persona.apellidoMaterno}</td>
                <td>
                  <button onClick={() => onSelect(persona.id)}>Seleccionar</button>
                </td>
              </tr>
            )}
        />
      </div>
    );
  };
  
  export default PaginaBuscar;
  