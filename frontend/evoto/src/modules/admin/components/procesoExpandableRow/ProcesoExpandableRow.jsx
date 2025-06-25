import React from "react";
import GenericExpandableRow from "./ProcesoGenericExpandableRow";

const renderCandidaturas = (candidaturas, onAddCandidatura) => (
  <div>
    {candidaturas.length > 0 ? (
      <table className="table table-sm">
        <thead>
          <tr>
            <th>ID Candidatura</th>
            <th>Nombre</th>
            <th>Partido</th>
            <th>Votos</th>
            <th>Fecha de Inicio</th>
            <th>Fecha de Fin</th>
          </tr>
        </thead>
        <tbody>
          {candidaturas.map((c, i) => (
            <tr key={i}>
              <td>{c.idCandidatura}</td>
              <td>{c.nombre}</td>
              <td>{c.partido}</td>
              <td>{c.votos}</td>
              <td>{c.fechaDeInicio || "---"}</td>
              <td>{c.fechaDeFin || "---"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    ) : (
      <p>No hay candidaturas disponibles.</p>
    )}
    <button
      className="btn btn-primary mt-2"
      onClick={() => onAddCandidatura()}
    >
      Agregar Candidatura
    </button>
  </div>
);

const ProcesoExpandableRow = ({
  idInstancia,
  rowData,
  fetchCandidaturas,
  colSpan,
  onEdit,
  onDelete,
  onAddCandidatura, // New prop for adding candidatura
}) => (
  <GenericExpandableRow
    id={idInstancia}
    rowData={rowData}
    fetchData={fetchCandidaturas}
    colSpan={colSpan}
    onEdit={onEdit}
    onDelete={onDelete}
    renderExpandedContent={(candidaturas) =>
      renderCandidaturas(candidaturas, () => onAddCandidatura(idInstancia))
    }
    loadingMessage="Cargando candidaturas..."
    emptyMessage="" // Remove the default empty message
  />
);

export default ProcesoExpandableRow;