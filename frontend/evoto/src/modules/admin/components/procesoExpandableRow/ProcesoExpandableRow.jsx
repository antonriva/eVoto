import React from "react";
import GenericExpandableRow from "./ProcesoGenericExpandableRow";

const renderCandidaturas = (candidaturas, onAddCandidatura, onDeleteCandidatura) => (
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
            <th>Acciones</th> {/* New column for actions */}
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
              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => onDeleteCandidatura(c.idCandidatura)}
                >
                  Eliminar
                </button>
              </td>
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
  onAddCandidatura,
  onDeleteCandidatura, // New prop for deleting candidatura
}) => (
  <GenericExpandableRow
    id={idInstancia}
    rowData={rowData}
    fetchData={fetchCandidaturas}
    colSpan={colSpan}
    onEdit={onEdit}
    onDelete={onDelete}
    renderExpandedContent={(candidaturas) =>
      renderCandidaturas(candidaturas, () => onAddCandidatura(idInstancia), onDeleteCandidatura)
    }
    loadingMessage="Cargando candidaturas..."
    emptyMessage="" // Remove the default empty message
  />
);

export default ProcesoExpandableRow;