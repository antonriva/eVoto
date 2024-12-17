import React, { useState, useEffect } from "react";

const ModalBuscarInstancia = ({ onSelect }) => {
  const [instancias, setInstancias] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchInstancias();
  }, []);

  const fetchInstancias = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/instancia/buscar");
      const data = await response.json();
      setInstancias(data);
    } catch (error) {
      console.error("Error al cargar instancias de proceso:", error);
      setError("Error al cargar instancias.");
    }
  };

  return (
    <div>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Descripción</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          {instancias.map((instancia) => (
            <tr key={instancia.id}>
              <td>{instancia.id}</td>
              <td>{instancia.descripcion}</td>
              <td>
                <button onClick={() => onSelect(instancia.id)}>Seleccionar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ModalBuscarInstancia;
