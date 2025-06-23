import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table";
import UploadButton from "../../../../shared/components/uploadButton/UploadButton";
import FilterForm from "../../../../shared/components/filterForm/FilterForm"; // Import FilterForm
import { partidoFilterConfig } from "../../config/partidoFilterConfig";
import { useBuscarPartidos } from "../../hooks/useBuscarPartidos"; // Import the hook
import Spinner from "../../../../shared/components/spinner/Spinner"; // Import Spinner
import ConfirmModal from "../../../../shared/components/confirmModal/ConfirmModal"; // Import ConfirmModal

const BuscarPartido = () => {
  const navigate = useNavigate();
  const {
    partidos,
    error,
    loading,
    fetchPartidos,
    eliminarPartido,
    cargarLogo,
  } = useBuscarPartidos(); // Use the hook

  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const [selectedPartidoId, setSelectedPartidoId] = useState(null);

  const handleRegresar = () => {
    navigate(-1); // Navigate back
  };

  const handleDelete = (id) => {
    setSelectedPartidoId(id);
    setShowConfirmModal(true); // Show confirmation modal
  };

  const confirmDelete = async () => {
    if (selectedPartidoId) {
      await eliminarPartido(selectedPartidoId);
      setShowConfirmModal(false);
      setSelectedPartidoId(null);
    }
  };

  const headers = [
    "ID",
    "Denominación",
    "Siglas",
    "Fecha de Inicio",
    "Fecha de Fin",
    "Logo",
    "Acciones",
  ];

  return (
    <div>
      <h1>Buscar Partidos</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <button onClick={handleRegresar} style={{ marginBottom: "20px" }}>
        Regresar
      </button>

      {/* Filter Form */}
      <FilterForm config={partidoFilterConfig({ onSearch: fetchPartidos })} />

      {/* Loading Spinner */}
      {loading && <Spinner message="Cargando partidos..." centered size="md" />}

      {/* Results Table */}
      {!loading && (
        partidos.length > 0 ? (
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
                  {partido.visualUrl ? (
                    <img
                      src={partido.visualUrl}
                      alt={`Logo de ${partido.denominacion}`}
                      style={{ width: "50px", height: "50px" }}
                    />
                  ) : (
                    "No hay logo disponible"
                  )}
                </td>
                <td>
                  <button onClick={() => navigate(`editar/${partido.id}`)}>Editar</button>
                  <button onClick={() => handleDelete(partido.id)}>Eliminar</button>
                  <UploadButton
                    onUpload={(file) => cargarLogo(file, partido.id)}
                    label="Cargar Logo"
                  />
                </td>
              </tr>
            )}
          />
        ) : (
          <p style={{ textAlign: "center", marginTop: "20px", color: "gray" }}>
            No se encontraron resultados para la búsqueda.
          </p>
        )
      )}

      {/* Confirm Modal */}
      <ConfirmModal
        show={showConfirmModal}
        onHide={() => setShowConfirmModal(false)}
        onConfirm={confirmDelete}
        title="Confirmar eliminación"
        body="¿Estás seguro de que deseas eliminar este partido? Esta acción no se puede deshacer."
        confirmText="Eliminar"
        cancelText="Cancelar"
      />
    </div>
  );
};

export default BuscarPartido;
