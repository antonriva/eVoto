import React, { useState } from "react";

const ExpandableRow = ({ idElector, rowData, fetchCandidaturas, colSpan, onEdit, onDelete }) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [loading, setLoading] = useState(false);
  const [candidaturas, setCandidaturas] = useState([]);

  const toggleExpand = async () => {
    setIsExpanded(!isExpanded);

    if (!isExpanded && candidaturas.length === 0) {
      setLoading(true);
      try {
        const data = await fetchCandidaturas(idElector); // Pasa idElector aquí
        setCandidaturas(data);
      } catch (error) {
        console.error("Error al cargar candidaturas:", error);
        setCandidaturas([]);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <>
      <tr>
        {rowData.map((cell, index) => (
          <td key={index}>{cell}</td>
        ))}
        <td>
          <button onClick={toggleExpand}>
            {isExpanded ? "Contraer" : "Expandir"}
          </button>
        </td>
        <td>
          {/* Botones de acción */}
          <button onClick={() => onEdit(idElector)}>Editar</button>
          <button onClick={() => onDelete(idElector)}>Eliminar</button>
        </td>
      </tr>
      {isExpanded && (
        <tr>
          <td colSpan={colSpan}>
            {loading ? (
              "Cargando candidaturas..."
            ) : candidaturas.length > 0 ? (
              <table>
                <thead>
                  <tr>
                    <th>ID Candidatura</th>
                    <th>Descripción Nivel</th>
                    <th>Descripción Proceso</th>
                    <th>Denominación Partido</th>
                    <th>Votos</th>
                    <th>Fecha/Hora de Inicio</th>
                    <th>Fecha/Hora de Fin</th>
                    <th>Entidad Federativa</th>
                    <th>Municipio</th>
                    <th>Localidad</th>
                  </tr>
                </thead>
                <tbody>
                  {candidaturas.map((candidatura, index) => (
                    <tr key={index}>
                      <td>{candidatura.idCandidatura}</td>
                      <td>{candidatura.descripcionNivel}</td>
                      <td>{candidatura.descripcionProceso}</td>
                      <td>{candidatura.denominacionPartido}</td>
                      <td>{candidatura.votos}</td>
                      <td>{candidatura.fechaHoraDeInicio}</td>
                      <td>{candidatura.fechaHoraDeFin || "---"}</td>
                      <td>{candidatura.entidadFederativa}</td>
                      <td>{candidatura.municipio}</td>
                      <td>{candidatura.localidad || "---"}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              "No hay candidaturas disponibles."
            )}
          </td>
        </tr>
      )}
    </>
  );
};

export default ExpandableRow;
