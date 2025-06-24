// shared/Table.jsx
import React, { useState } from "react";
import "./Table.css"; // Create this file for styling

const Table = ({ headers, data, renderRow, isLoading=false, error="", rowsPerPageOptions = [5, 10, 20] }) => {
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage, setRowsPerPage] = useState(rowsPerPageOptions[0]);

  const totalPages = Math.ceil(data.length / rowsPerPage);
  const paginatedData = data.slice((currentPage - 1) * rowsPerPage, currentPage * rowsPerPage);

  const handlePrev = () => setCurrentPage(p => Math.max(1, p - 1));
  const handleNext = () => setCurrentPage(p => Math.min(totalPages, p + 1));

  const handleRowsChange = (e) => {
    setRowsPerPage(Number(e.target.value));
    setCurrentPage(1); // Reset to first page
  };

  return (
    <div className="table-container">
      <table className="custom-table">
        <thead>
          <tr>
            {headers.map((header, idx) => (
              <th key={idx}>{header}</th>
            ))}
          </tr>
        </thead>
        <tbody>
        {isLoading ? (
            <tr>
              <td colSpan={headers.length} className="text-center">
                <div className="d-flex justify-content-center py-3">
                  <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Cargando...</span>
                  </div>
                </div>
              </td>
            </tr>
          ) : error ? (
            <tr>
              <td colSpan={headers.length} className="text-center text-danger py-3">
                {error}
              </td>
            </tr>
          ) : paginatedData.length > 0 ? (
            paginatedData.map(renderRow)
          ) : (
            <tr>
              <td colSpan={headers.length} className="text-center">Sin resultados</td>
            </tr>
          )}
        </tbody>
      </table>

      <div className="table-controls">
        <div className="rows-selector">
          Mostrar{" "}
          <select value={rowsPerPage} onChange={handleRowsChange}>
            {rowsPerPageOptions.map((opt) => (
              <option key={opt} value={opt}>
                {opt}
              </option>
            ))}
          </select>{" "}
          resultados por página
        </div>

        <div className="pagination-buttons">
          <button onClick={handlePrev} disabled={currentPage === 1}>
            Anterior
          </button>
          <span>
             {currentPage} / {totalPages}
          </span>
          <button onClick={handleNext} disabled={currentPage === totalPages}>
            Siguiente
          </button>
        </div>
      </div>
    </div>
  );
};

export default Table;
