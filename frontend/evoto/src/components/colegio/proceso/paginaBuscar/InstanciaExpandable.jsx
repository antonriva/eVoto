import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const ExpandableRow = ({
  idInstancia,
  rowData,
  fetchCandidaturas,
  colSpan,
  onEliminarCandidatura,
  onEdit,
  onDelete,
  onAdd
}) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [loading, setLoading] = useState(false);
  const [candidaturas, setCandidaturas] = useState([]);

  const navigate = useNavigate();


  const toggleExpand = async () => {
    setIsExpanded(!isExpanded);

    if (!isExpanded && candidaturas.length === 0) {
      setLoading(true);
      try {
        const data = await fetchCandidaturas(idInstancia); // Llamada a la API
        setCandidaturas(data);
      } catch (error) {
        console.error("Error al cargar candidaturas:", error);
        setCandidaturas([]);
      } finally {
        setLoading(false);
      }
    }
  };


  // Función para eliminar una candidatura
const eliminarCandidatura = async (id) => {
    const confirmar = window.confirm(
      "¿Estás seguro de que deseas eliminar esta candidatura?"
    );
    if (confirmar) {
      try {
        const response = await fetch(
          `http://localhost:8080/api/candidatura/${id}`,
          {
            method: "DELETE",
          }
        );
  
        if (response.ok) {
          alert("Candidatura eliminada correctamente.");
          // Actualizar el estado, eliminando la candidatura con el ID correspondiente
          setCandidaturas((prev) =>
            prev.filter((candidatura) => candidatura.idCandidatura !== id)
          );
        } else {
          const errorMessage = await response.text();
          if (response.status === 404) {
            alert(`Error: La candidatura con ID ${id} no existe.`);
          } else if (response.status === 409) {
            alert(`Error: ${errorMessage}`);
          } else {
            alert(`Error inesperado al eliminar la candidatura: ${errorMessage}`);
          }
        }
      } catch (error) {
        console.error(`Error al eliminar candidatura con ID ${id}:`, error);
        alert(
          "Error inesperado al eliminar la candidatura. Por favor, inténtelo nuevamente."
        );
      }
    }
  };
  

  


  return (
    <>
      {/* Fila principal */}
      <tr>
        {rowData.map((cell, index) => (
          <td key={index}>{cell}</td>
        ))}
        <td>
          <button onClick={toggleExpand}>
            {isExpanded ? "Contraer" : "Expandir"}
          </button>
          <button onClick={() => onAdd(idInstancia)}>
           Agregar
          </button>
        </td>
        <td>
          {/* Botones de acción */}
          <button onClick={() => onEdit(idInstancia)}>Editar</button>
          <button onClick={() => onDelete(idInstancia)}>Eliminar</button>
        </td>
      </tr>



      {/* Fila expandida */}
      {isExpanded && (
        <tr>
          <td colSpan={colSpan}>
            {loading ? (
              "Cargando candidaturas..."
            ) : candidaturas.length > 0 ? (
              <table border="1" style={{ width: "100%", borderCollapse: "collapse" }}>
                <thead>
                  <tr>
                    <th>ID Candidatura</th>
                    <th>Denominación Partido</th>
                    <th>Votos</th>
                    <th>Fecha/Hora Inicio</th>
                    <th>Fecha/Hora Fin</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Entidad Federativa</th>
                    <th>Municipio</th>
                    <th>Localidad</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {candidaturas.map((candidatura, index) => (
                    <tr key={index}>
                      <td>{candidatura.idCandidatura}</td>
                      <td>{candidatura.denominacionPartido}</td>
                      <td>{candidatura.votos}</td>
                      <td>{candidatura.fechaHoraDeInicio}</td>
                      <td>{candidatura.fechaHoraDeFin || "---"}</td>
                      <td>{candidatura.nombre}</td>
                      <td>{candidatura.apellidoPaterno}</td>
                      <td>{candidatura.apellidoMaterno}</td>
                      <td>{candidatura.fechaDeNacimiento || "---"}</td>
                      <td>{candidatura.entidadFederativaDescripcion}</td>
                      <td>{candidatura.municipioDescripcion}</td>
                      <td>{candidatura.localidadDescripcion || "---"}</td>
                      <td>
                      <button onClick={() =>navigate(`agregar/${candidatura.idCandidatura}`)}>
                          Agregar Elector
                        </button>
                        <button onClick={() => onEliminarCandidatura(candidatura.idCandidatura)}>
                          Eliminar Candidatura
                        </button>
                      </td>
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
