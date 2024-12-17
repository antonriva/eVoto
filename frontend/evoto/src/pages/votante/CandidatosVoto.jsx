import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const CandidatosDisponibles = () => {
  const { idElector, idInstanciaDeProceso } = useParams(); // Recupera los parámetros desde la URL
  const [candidatos, setCandidatos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  // Fetch para cargar los candidatos disponibles
  useEffect(() => {
    fetchCandidatos();
  }, [idElector, idInstanciaDeProceso]);

  const fetchCandidatos = async () => {
    setLoading(true);
    setError("");

    try {
      const response = await fetch(
        `http://localhost:8080/api/proceso/candidatos/${idElector}/${idInstanciaDeProceso}`
      );

      if (!response.ok) {
        throw new Error(await response.text());
      }

      const data = await response.json();
      setCandidatos(data);
    } catch (error) {
      console.error("Error al cargar candidatos:", error);
      setError("Error al cargar los candidatos. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  // Maneja el voto por un candidato
  const handleVotar = async (idDeCandidatura) => {
    const confirmar = window.confirm("¿Está seguro de que desea votar por este candidato?");
    if (!confirmar) return;

    setLoading(true);
    try {
      const queryParams = new URLSearchParams({
        idDeElector: idElector,
        idDeInstanciaDeProceso: idInstanciaDeProceso,
        idDeCandidatura: idDeCandidatura,
      }).toString();

      const response = await fetch(
        `http://localhost:8080/api/voto/registrar?${queryParams}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      alert("Voto registrado exitosamente.");
      navigate("/"); // Redirige al root
    } catch (error) {
      console.error("Error al registrar el voto:", error);
      alert(`Error: ${error.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Candidatos Disponibles para Votar</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {loading ? (
        <p>Cargando candidatos...</p>
      ) : (
        <div style={styles.gridContainer}>
          {candidatos.length > 0 ? (
            candidatos.map((candidato) => (
              <div key={candidato.idCandidatura} style={styles.card}>
                <img
                  src={candidato.urlVisual || "https://via.placeholder.com/150"}
                  alt="Logo del partido"
                  style={styles.image}
                />
                <h2>{candidato.denominacionPartido}</h2>
                <p>
                  <strong>Candidato:</strong> {candidato.nombre} {candidato.apellidoPaterno}
                </p>
                <button
                  onClick={() => handleVotar(candidato.idCandidatura)}
                  style={styles.button}
                >
                  Votar
                </button>
              </div>
            ))
          ) : (
            <p>No hay candidatos disponibles en este proceso.</p>
          )}
        </div>
      )}
    </div>
  );
};

// Estilos en línea
const styles = {
  gridContainer: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fill, minmax(250px, 1fr))",
    gap: "20px",
    marginTop: "20px",
  },
  card: {
    border: "1px solid #ddd",
    borderRadius: "8px",
    padding: "16px",
    textAlign: "center",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
  },
  image: {
    width: "100%",
    height: "150px",
    objectFit: "cover",
    marginBottom: "10px",
    borderRadius: "8px",
  },
  button: {
    backgroundColor: "#28a745",
    color: "#fff",
    border: "none",
    padding: "10px 16px",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default CandidatosDisponibles;
