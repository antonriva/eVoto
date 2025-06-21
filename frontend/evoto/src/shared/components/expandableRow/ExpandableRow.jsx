import React, { useState } from "react";

const ExpandableRow = ({ id, rowData, fetchData, colSpan, onEdit, onDelete, renderExpandedContent }) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);

  const toggleExpand = async () => {
    setIsExpanded(!isExpanded);
    if (!isExpanded && data.length === 0) {
      setLoading(true);
      try {
        const result = await fetchData(id);
        setData(result);
      } catch (error) {
        console.error("Error:", error);
        setData([]);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <>
      <tr className="align-middle">
        {rowData.map((cell, idx) => (
          <td key={idx}>{cell}</td>
        ))}
        <td>
          <button className="btn btn-outline-primary btn-sm me-2" onClick={toggleExpand}>
            {isExpanded ? "Contraer" : "Expandir"}
          </button>
        </td>
        <td>
          <button className="btn btn-warning btn-sm me-2" onClick={() => onEdit(id)}>Editar</button>
          <button className="btn btn-danger btn-sm" onClick={() => onDelete(id)}>Eliminar</button>
        </td>
      </tr>

      {isExpanded && (
        <tr>
          <td colSpan={colSpan}>
            {loading ? (
              <div className="text-center p-2">Cargando...</div>
            ) : (
              renderExpandedContent(data)
            )}
          </td>
        </tr>
      )}
    </>
  );
};

export default ExpandableRow;