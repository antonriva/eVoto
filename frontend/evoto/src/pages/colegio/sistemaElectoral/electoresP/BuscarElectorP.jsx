import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../components/civil/paginaBuscar/Table";
import ExpandableRow from "../../../../components/civil/paginaBuscar/ExpandableRow";
import FiltrosPersonas from "../../../../components/civil/paginaBuscar/FiltrosPersonas";

const BuscarElectorP = () => {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState({
    id: "",
    nombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    fechaDeNacimiento: "",
    fechaDeFin: "",
    entidadFederativa: "",
    municipio: "",
    localidad: "",
    colonia: "",
    codigoPostal: "",
    tipoDeDomicilio: ""
  });

  const navigate = useNavigate();

  const fetchElectores = async (params = {}) => {
    try {
      const query = new URLSearchParams(params).toString();
      const response = await fetch(`http://localhost:8080/api/elector/buscar?${query}`);
      if (!response.ok) {
        throw new Error("Error al cargar electores.");
      }
      const data = await response.json();
      setPersonas(data);
    } catch (error) {
      console.error("Error al cargar electores:", error);
    }
  };

  useEffect(() => {
    fetchElectores();
  }, []);

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
      <h1>Buscar Electores</h1>
      <FiltrosPersonas
        filtros={filtros}
        setFiltros={setFiltros}
        onBuscar={fetchElectores}
      />
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
          />
        )}
      />
    </div>
  );
};

export default BuscarElectorP;
