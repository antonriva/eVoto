import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const ProcesosAbiertos = () => {
  const { idElector } = useParams(); // Recupera el ID del elector desde la URL
  const [procesos, setProcesos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetchProcesosAbiertos();
  }, [idElector]);

  const fetchProcesosAbiertos = async () => {
    setLoading(true);
    setError("");

    try {
      const response = await fetch(`http://localhost:8080/api/elector/abiertos/${idElector}`);
      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      const data = await response.json();
      setProcesos(data);
    } catch (error) {
      console.error("Error al obtener procesos abiertos:", error);
      setError("Error al cargar procesos abiertos. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  const handleVerCandidatos = (idInstancia) => {
    // Redirige a la página de candidatos, pasando ID del elector y proceso
    navigate(`/candidatos/${idElector}/${idInstancia}`);
  };

  return (
    <div>
      <h1>Procesos Abiertos</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {loading ? (
        <p>Cargando...</p>
      ) : procesos.length > 0 ? (
        <table border="1" style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr>
              <th>ID Proceso</th>
              <th>Fecha Inicio</th>
              <th>Fecha Fin</th>
              <th>Nivel</th>
              <th>Tipo de Proceso</th>
              <th>Entidad Federativa</th>
              <th>Municipio</th>
              <th>Localidad</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {procesos.map((proceso) => (
              <tr key={proceso.idInstancia}>
                <td>{proceso.idInstancia}</td>
                <td>{proceso.fechaHoraDeInicio}</td>
                <td>{proceso.fechaHoraDeFin}</td>
                <td>{proceso.nivelDescripcion}</td>
                <td>{proceso.procesoDescripcion}</td>
                <td>{proceso.entidadFederativa || "---"}</td>
                <td>{proceso.municipio || "---"}</td>
                <td>{proceso.localidad || "---"}</td>
                <td>
                  <button
                    onClick={() => handleVerCandidatos(proceso.idInstancia)}
                    style={styles.button}
                  >
                    Ver Candidatos
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No hay procesos abiertos disponibles para este elector.</p>
      )}
      <button onClick={() => navigate("/")} style={styles.buttonRegresar}>
        Regresar
      </button>
    </div>
  );
};

// Estilos en línea
const styles = {
  button: {
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    padding: "8px 12px",
    borderRadius: "4px",
    cursor: "pointer",
  },
  buttonRegresar: {
    marginTop: "20px",
    backgroundColor: "#6c757d",
    color: "#fff",
    border: "none",
    padding: "10px 16px",
    borderRadius: "4px",
    cursor: "pointer",
  },
};

export default ProcesosAbiertos;
