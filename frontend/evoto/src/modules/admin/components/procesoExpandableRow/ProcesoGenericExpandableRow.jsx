import React, { useState } from "react";
import "../../../../shared/styles/Buttons.css"; // Import custom button styles

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
        setData([]); // Ensure data is empty if an error occurs
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
          <button className="btn btn-outline-vino btn-sm me-2" onClick={toggleExpand}>
            {isExpanded ? "Contraer" : "Expandir"}
          </button>
        </td>
        <td className="d-flex justify-content-start align-items-center">
          <button className="btn btn-outline-vino btn-sm me-2" onClick={() => onEdit(id)}>Editar</button>
          <button className="btn btn-outline-lightred btn-sm" onClick={() => onDelete(id)}>Eliminar</button>
        </td>
      </tr>

      {isExpanded && (
        <tr>
          <td colSpan={colSpan}>
            {loading ? (
              <div className="text-center py-3">
                <div className="spinner-border text-secondary" role="status">
                  <span className="visually-hidden">Cargando...</span>
                </div>
              </div>
            ) : (
              renderExpandedContent(data) // Always call renderExpandedContent
            )}
          </td>
        </tr>
      )}
    </>
  );
};

export default ExpandableRow;