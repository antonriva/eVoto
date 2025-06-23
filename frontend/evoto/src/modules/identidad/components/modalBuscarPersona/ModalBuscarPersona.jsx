// src/modules/identidad/pages/ModalBuscarPersona.jsx
import React from "react";
import GenericSearchModal from "../../../../shared/components/modal/Modal";
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

  const filterConfig = {
    config: createPersonasFilterConfig,
    values: filtros,
    setValues: setFiltros,
    onSearch: (vals) => {
      setFiltros(vals);
      fetchPersonas(vals);
    },
    renderRow: (p, onSelect) => (
      <>
        <td>{p.id}</td>
        <td>{p.nombre}</td>
        <td>{p.apellidoPaterno}</td>
        <td>{p.apellidoMaterno}</td>
        <td>
          <button onClick={() => onSelect(p.id)}>Seleccionar</button>
        </td>
      </>
    )
  };

  const headers = ["ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Acciones"];

  return (
    <GenericSearchModal
      title="Catálogo de Personas"
      filterConfig={filterConfig}
      headers={headers}
      data={personas}
      onSelect={onSelect}
      error={error}
    />
  );
};

export default ModalBuscarPersona;