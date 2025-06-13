import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useParams } from "react-router-dom";

const MostrarCandidatos = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { idDeElector,idDeInstanciaDeProceso} = useParams();
  // Recupera parámetros desde la navegación


  const [candidatos, setCandidatos] = useState([]); // Almacena los datos de candidatos
  const [candidaturaSeleccionada, setCandidaturaSeleccionada] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // Fetch para obtener candidatos desde el backend
  console.log("ID del Elector recibido desde la URL:", idDeElector);
  console.log("ID del Elector recibido desde la URL:", idDeInstanciaDeProceso);
  useEffect(() => {
    const fetchCandidatos = async () => {
      if (!idDeElector || !idDeInstanciaDeProceso) {
        setError("Faltan parámetros necesarios para mostrar candidatos.");
        return;
      }

      setLoading(true);
      setError("");
      try {
        const response = await fetch(
          `${import.meta.env.VITE_API_URL}/elector/abiertos/candidatos?idDeElector=${idDeElector}&idDeInstanciaDeProceso=${idDeInstanciaDeProceso}`
        );
        if (!response.ok) {
          throw new Error("Error al obtener la lista de candidatos.");
        }

        const data = await response.json();
        setCandidatos(data); // Guarda la lista de candidatos en el estado
      } catch (error) {
        console.error(error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCandidatos();
  }, [idDeElector, idDeInstanciaDeProceso]);

  // Función para registrar el voto
  const handleRegistrarVoto = async () => {
    if (!candidaturaSeleccionada) {
      alert("Debe seleccionar una candidatura para votar.");
      return;
    }

    try {
      // Construir los query params correctamente
      const queryParams = new URLSearchParams({
        idDeElector: idDeElector,
        idDeInstanciaDeProceso: idDeInstanciaDeProceso,
        idDeCandidatura: candidaturaSeleccionada,
      }).toString();
    
      // Hacer la petición POST con los parámetros en la URL
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/voto/registrar?${queryParams}`, 
        {
          method: "POST",
        }
      );
    

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(`Error al registrar voto: ${errorMessage}`);
      }

      alert("¡Voto registrado correctamente!");
      navigate("/"); // Redirigir a una página de confirmación o inicio
    } catch (error) {
      console.error(error);
      setError(error.message);
    }
  };

  return (
    <div>
      <h1>Lista de Candidatos</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {loading ? (
        <p>Cargando...</p>
      ) : (
        <div>
          {/* Tabla de candidatos */}
          <table border="1" style={{ width: "100%", marginTop: "10px" }}>
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Partido</th>
                <th>Siglas</th>
                <th>Imagen</th>
                <th>Seleccionar</th>
              </tr>
            </thead>
            <tbody>
              {candidatos.length > 0 ? (
                candidatos.map((candidato) => (
                  <tr key={candidato.idCandidatura}>
                    <td>{candidato.nombre}</td>
                    <td>{candidato.apellidoPaterno}</td>
                    <td>{candidato.denominacionPartido}</td>
                    <td>{candidato.siglas}</td>
                    <td>
                      <img
                        src={candidato.visualUrl}
                        alt="Logo del Partido"
                        style={{ width: "100px", height: "100px" }}
                      />
                    </td>
                    <td>
                      <input
                        type="radio"
                        name="candidaturaSeleccionada"
                        value={candidato.idCandidatura}
                        onChange={() =>
                          setCandidaturaSeleccionada(candidato.idCandidatura)
                        }
                      />
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6" style={{ textAlign: "center" }}>
                    No hay candidatos disponibles.
                  </td>
                </tr>
              )}
            </tbody>
          </table>

          {/* Botón para registrar el voto */}
          <button
            onClick={handleRegistrarVoto}
            style={{ marginTop: "20px", padding: "10px 20px" }}
          >
            Registrar Voto
          </button>
        </div>
      )}
    </div>
  );
};

export default MostrarCandidatos;
