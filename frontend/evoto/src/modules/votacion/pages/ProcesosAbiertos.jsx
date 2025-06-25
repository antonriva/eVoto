import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../../../shared/styles/Buttons.css"; 

const InicioVotante = () => {
  const { idDeElector} = useParams(); // Recuperar el ID del elector desde la URL
  console.log("ID del Elector recibido desde la URL:", idDeElector);
  const navigate = useNavigate();


  const [procesos, setProcesos] = useState([]); // Almacena los datos de procesos abiertos
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [idDeInstanciaDeProceso, setidDeInstanciaDeProceso] = useState(null); // ID del proceso seleccionado

  // Llamada al backend para obtener procesos abiertos
  useEffect(() => {
    const fetchProcesosAbiertos = async () => {
      setLoading(true);
      setError("");
      try {
        const response = await fetch(
          `${import.meta.env.VITE_API_URL}/elector/abiertos/${idDeElector}` // Endpoint correcto con el ID del elector
        );
        if (!response.ok) {
          throw new Error("Error al obtener los procesos abiertos.");
        }

        const data = await response.json();
        setProcesos(data); // Guarda los datos obtenidos en el estado
      } catch (error) {
        console.error(error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProcesosAbiertos();
  }, [idDeElector]);

  // Función para navegar a la siguiente página
  const handleNavigateToDetalleProceso = () => {
    if (!procesoSeleccionado) {
      alert("Debe seleccionar un proceso antes de continuar.");
      return;
    }
    navigate(`/detalleProceso/${idDeElector}/${procesoSeleccionado}`); // Envía ID de elector e ID de proceso
  };

  const enviarTodo = (idDeElector,idDeInstanciaDeProceso) => {
    console.log("ID del Elector recibido desde la URL:", idDeElector);
    console.log("ID del Elector recibido desde la URL:", idDeInstanciaDeProceso);
    navigate(`detalleProceso/${idDeElector}/${idDeInstanciaDeProceso}`);
  };

  return (
    <div>
      <h1>Procesos Abiertos</h1>
      <p>ID del Elector: {idDeElector}</p>

      {error && <p style={{ color: "red" }}>{error}</p>}
      {loading ? (
        <p>Cargando procesos abiertos...</p>
      ) : (
        <div>
          {/* Tabla de procesos */}
          <table border="1" style={{ width: "100%", marginTop: "10px" }}>
            <thead>
              <tr>
                <th>ID de Proceso</th>
                <th>Descripción Nivel</th>
                <th>Descripción Proceso</th>
                <th>Fecha de Inicio</th>
                <th>Fecha de Fin</th>
                <th>Entidad Federativa</th>
                <th>Municipio</th>
                <th>Localidad</th>
                <th>Seleccionar</th>
              </tr>
            </thead>
            <tbody>
              {procesos.length > 0 ? (
                procesos.map((proceso) => (
                  <tr key={proceso.idDeInstanciaDeProceso}>
                    <td>{proceso.idDeInstanciaDeProceso}</td>
                    <td>{proceso.descripcionNivel}</td>
                    <td>{proceso.descripcionProceso}</td>
                    <td>{proceso.fechaHoraDeInicio}</td>
                    <td>{proceso.fechaHoraDeFin}</td>
                    <td>{proceso.entidadFederativa}</td>
                    <td>{proceso.municipio}</td>
                    <td>{proceso.localidad}</td>
                    <td>
                      <input
                        type="radio"
                        name="procesoSeleccionado"
                        value={proceso.idDeInstanciaDeProceso}
                        onChange={() =>
                          setidDeInstanciaDeProceso(proceso.idDeInstanciaDeProceso)
                        }
                      />
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="9" style={{ textAlign: "center" }}>
                    No hay procesos abiertos disponibles.
                  </td>
                </tr>
              )}
            </tbody>
          </table>

          {/* Botón para continuar a la siguiente página */}
          <button className="btn btn-vino"
            onClick={() => enviarTodo(idDeElector,idDeInstanciaDeProceso)}
            style={{ marginTop: "20px" }}
          >
            Continuar con el Proceso Seleccionado
          </button>
        </div>
      )}
    </div>
  );
};

export default InicioVotante;
