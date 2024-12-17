import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalBuscarElector from "./ModalBuscarElector";

const AgregarElector = () => {
  const today = new Date().toISOString().split("T")[0];

  const [formData, setFormData] = useState({
    idElector: "",
    fechaDeInicio: today,
    fechaDeFin: "",
  });

  const [showModal, setShowModal] = useState(false); // Controla la visibilidad del modal
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  // Maneja la selección de elector desde el modal
  const handleSelectElector = (id) => {
    setFormData((prev) => ({ ...prev, idElector: id }));
    setShowModal(false); // Cierra el modal al seleccionar
  };

  // Manejador de cambios en los campos del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.idElector || isNaN(formData.idElector)) {
      alert("Debe seleccionar un ID de Elector válido.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const response = await fetch("http://localhost:8080/api/elector/asignar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      alert("Elector asignado correctamente.");
      navigate("/colegio/proceso");
    } catch (error) {
      console.error("Error al asignar elector:", error);
      setError("Error al asignar el elector. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Buscar Elector</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handleSubmit}>
        <div>
          <label>ID de Elector:</label>
          <input
            type="text"
            name="idElector"
            value={formData.idElector}
            onChange={handleChange}
            required
            disabled
            pattern="^[0-9]+$"
            title="El ID del elector debe ser un número válido."
          />
          <button type="button" onClick={() => setShowModal(true)}>
            Buscar Elector
          </button>
        </div>
        <div>
          <label>Fecha de Inicio:</label>
          <input
            type="date"
            name="fechaDeInicio"
            value={formData.fechaDeInicio}
            onChange={handleChange}
            max={today}
            required
          />
        </div>
        <div>
          <label>Fecha de Fin:</label>
          <input
            type="date"
            name="fechaDeFin"
            value={formData.fechaDeFin}
            onChange={handleChange}
            min={formData.fechaDeInicio}
            max={today}
            required
          />
        </div>

        <button type="submit" disabled={loading}>
          {loading ? "Asignando..." : "Asignar Elector"}
        </button>
        <button type="button" onClick={() => navigate("/colegio/proceso")}>
          Cancelar
        </button>
      </form>

      {/* Modal para buscar electores */}
      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h2>Buscar Elector</h2>
            <ModalBuscarElector onSelect={handleSelectElector} />
            <button onClick={() => setShowModal(false)}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default AgregarElector;
