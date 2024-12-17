import React, { useState, useEffect } from "react";

const ModalBuscarElector = ({ onSelect }) => {
  const [electores, setElectores] = useState([]);
  const [filtro, setFiltro] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchElectores();
  }, []);

  const fetchElectores = async () => {
    setLoading(true);
    try {
      const response = await fetch(`http://localhost:8080/api/elector/buscar?nombre=${filtro}`);
      if (!response.ok) {
        throw new Error("Error al cargar los electores.");
      }
      const data = await response.json();
      setElectores(data);
    } catch (error) {
      console.error(error);
      setError("No se pudieron cargar los electores.");
    } finally {
      setLoading(false);
    }
  };

  const handleFiltroChange = (e) => setFiltro(e.target.value);

  return (
    <div>
      <input
        type="text"
        placeholder="Buscar por nombre"
        value={filtro}
        onChange={handleFiltroChange}
      />
      <button onClick={fetchElectores}>Buscar</button>

      {loading ? (
        <p>Cargando...</p>
      ) : error ? (
        <p style={{ color: "red" }}>{error}</p>
      ) : (
        <table border="1" style={{ width: "100%", marginTop: "10px" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {electores.map((elector) => (
              <tr key={elector.id}>
                <td>{elector.id}</td>
                <td>{`${elector.nombre} ${elector.apellidoPaterno} ${elector.apellidoMaterno}`}</td>
                <td>
                  <button onClick={() => onSelect(elector.id)}>Seleccionar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ModalBuscarElector;
