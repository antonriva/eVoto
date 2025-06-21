// src/modules/Identidad/IdentidadExpandableRow.jsx
import React from "react";
import GenericExpandableRow from "../../../../shared/components/expandableRow/ExpandableRow";

const renderDomicilios = (domicilios) => (
  <table className="table table-sm">
    <thead>
      <tr>
        <th>Entidad Federativa</th>
        <th>Municipio</th>
        <th>Localidad</th>
        <th>Colonia</th>
        <th>Calle</th>
        <th>N° Exterior</th>
        <th>N° Interior</th>
        <th>Código Postal</th>
        <th>Tipo</th>
        <th>Fecha Inicio</th>
        <th>Fecha Fin</th>
      </tr>
    </thead>
    <tbody>
      {domicilios.map((d, i) => (
        <tr key={i}>
          <td>{d.entidadFederativa}</td>
          <td>{d.municipio}</td>
          <td>{d.localidad || "---"}</td>
          <td>{d.colonia || "---"}</td>
          <td>{d.calle || "---"}</td>
          <td>{d.numeroExterior || "---"}</td>
          <td>{d.numeroInterior || "---"}</td>
          <td>{d.codigoPostal || "---"}</td>
          <td>{d.tipoDeDomicilio}</td>
          <td>{d.fechaDeInicio}</td>
          <td>{d.fechaDeFin || "---"}</td>
        </tr>
      ))}
    </tbody>
  </table>
);

const IdentidadExpandableRow = ({
  idPersona,
  rowData,
  fetchDomicilios,
  colSpan,
  onEdit,
  onDelete,
}) => (
  <GenericExpandableRow
    id={idPersona}
    rowData={rowData}
    fetchData={fetchDomicilios}
    colSpan={colSpan}
    onEdit={onEdit}
    onDelete={onDelete}
    renderExpandedContent={renderDomicilios}
    loadingMessage="Cargando domicilios..."
    emptyMessage="No hay domicilios disponibles."
  />
);

export default IdentidadExpandableRow;
