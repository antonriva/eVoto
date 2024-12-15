import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../components/civil/paginaBuscar/Table"

const BuscarPartido = () => {
  const [partidos, setPartidos] = useState([]);
  const [nombre, setNombre] = useState("");
  const navigate = useNavigate();

  const fetchPartidos = async (nombre = "") => {
    try {
      const query = new URLSearchParams({ nombre }).toString();
      const response = await fetch(`http://localhost:8080/api/partido/buscar?${query}`);
      if (!response.ok) {
        throw new Error("Error al cargar partidos.");
      }
      const data = await response.json();
      setPartidos(data);
    } catch (error) {
      console.error("Error al cargar partidos:", error);
    }
  };

  useEffect(() => {
    fetchPartidos();
  }, []);

  const eliminarPartido = async (id) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar este partido?");
    if (confirmar) {
      try {
        const response = await fetch(`http://localhost:8080/api/partido/${id}`, { method: "DELETE" });
        if (response.ok) {
          alert("Partido eliminado exitosamente.");
          setPartidos((prev) => prev.filter((partido) => partido.id !== id));
        } else {
          alert("Error al eliminar partido.");
        }
      } catch (error) {
        console.error("Error al eliminar partido:", error);
        alert("Error inesperado al eliminar partido.");
      }
    }
  };

  const editarPartido = (id) => {
    navigate(`editar/${id}`);
  };

  const headers = ["ID", "Denominación", "Siglas", "Fecha de Inicio", "Fecha de Fin", "Acciones"];

  return (
    <div>
      <h1>Buscar Partidos</h1>
      <div>
        <label>Nombre del Partido:</label>
        <input
          type="text"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          placeholder="Buscar por nombre"
        />
        <button onClick={() => fetchPartidos(nombre)}>Buscar</button>
      </div>
      <Table
        headers={headers}
        data={partidos}
        renderRow={(partido) => (
          <tr key={partido.id}>
            <td>{partido.id}</td>
            <td>{partido.denominacion}</td>
            <td>{partido.siglas}</td>
            <td>{partido.fechaDeInicio}</td>
            <td>{partido.fechaDeFin || "---"}</td>
            <td>
              <button onClick={() => editarPartido(partido.id)}>Editar</button>
              <button onClick={() => eliminarPartido(partido.id)}>Eliminar</button>
            </td>
          </tr>
        )}
      />
    </div>
  );
};

export default BuscarPartido;
