import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const TimerRedirect = ({ rutaDestino = "/" }) => {
  const [timeLeft, setTimeLeft] = useState(240); // Tiempo en segundos
  const navigate = useNavigate();

  useEffect(() => {
    // Refrescar conexión al iniciar el temporizador
    refrescarConexion();

    const timer = setInterval(() => {
      setTimeLeft((prevTime) => {
        if (prevTime <= 1) {
          clearInterval(timer);
          cerrarConexionYRedirigir(); // Cerrar conexión y redirigir
          return 0;
        }
        return prevTime - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  // Función para refrescar la conexión
  const refrescarConexion = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/conexion/refrescar", {
        method: "GET",
      });

      if (response.ok) {
        console.log("Conexión refrescada exitosamente.");
      } else {
        console.error("Error al refrescar la conexión.");
      }
    } catch (error) {
      console.error("Error al refrescar la conexión:", error);
    }
  };

  // Función para redirigir a una ruta específica
  const cerrarConexionYRedirigir = async () => {
    try {
      console.log("Refrescando conexión antes de redirigir...");
      await refrescarConexion(); // Refresca la conexión una última vez
    } finally {
      navigate(rutaDestino); // Redirige al destino especificado
    }
  };

  // Formato para mostrar el tiempo como reloj (MM:SS)
  const formatTime = (time) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(2, "0")}`;
  };

  return (
    <div style={{ textAlign: "right", marginTop: "20px" }}>
      <p>Serás redirigido en:</p>
      <h2 style={{ fontSize: "2rem", color: "blue" }}>{formatTime(timeLeft)}</h2>
    </div>
  );
};

export default TimerRedirect;
