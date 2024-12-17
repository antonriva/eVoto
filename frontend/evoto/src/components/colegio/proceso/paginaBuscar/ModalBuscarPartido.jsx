import React, { useState, useEffect } from "react";

const ModalBuscarPartido = ({ onSelect }) => {
  const [partidos, setPartidos] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchPartidos();
  }, []);

  const fetchPartidos = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/partido/buscar");
      const data = await response.json();
      setPartidos(data);
    } catch (error) {
      console.error("Error al cargar partidos políticos:", error);
      setError("Error al cargar partidos.");
    }
  };

  return (
    <div>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          {partidos.map((partido) => (
            <tr key={partido.id}>
              <td>{partido.id}</td>
              <td>{partido.nombre}</td>
              <td>
                <button onClick={() => onSelect(partido.id)}>Seleccionar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ModalBuscarPartido;
