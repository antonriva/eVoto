// src/modules/identidad/pages/ModalBuscarPersona.jsx
import React from "react";
import Table from "../../../../shared/components/table/Table";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";
import { useBuscarPersonas } from "../../../../modules/identidad/hooks/useBuscarPersonas";
import { createPersonasFilterConfig } from "../../../../modules/identidad/config/personasFilterConfig";

const ModalBuscarPersona = ({ onSelect }) => {
  const {
    personas,
    filtros,
    setFiltros,
    fetchPersonas,
    error,
  } = useBuscarPersonas({
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

      <GenericFilterForm
        config={{
          ...createPersonasFilterConfig,
          onSearch: (vals) => {
            setFiltros(vals);
            fetchPersonas(vals);
          }
        }}
        values={filtros}
        setValues={setFiltros}
      />

      <Table
        headers={headers}
        data={personas}
        renderRow={(p) => (
          <tr key={p.id}>
            <td>{p.id}</td>
            <td>{p.nombre}</td>
            <td>{p.apellidoPaterno}</td>
            <td>{p.apellidoMaterno}</td>
            <td>
              <button onClick={() => onSelect(p.id)}>Seleccionar</button>
            </td>
          </tr>
        )}
      />
    </div>
  );
};

export default ModalBuscarPersona;
