import React from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table"
import { useBuscarPartidos } from "../../hooks/useBuscarPartidos";
import { usePartidosActions } from "../../hooks/usePartidosActions";
import createPartidoFilterConfig from "../../config/partidoFilterConfig"
import { useToast } from "../../../../shared/context/ToastContext";
import { useConfirmDialog } from "../../../../shared/hooks/useConfirmDialog";
import { partidoTableHeaders } from "../../config/partidoTableConfig";
import ConfirmModal from "../../../../shared/components/confirmModal/ConfirmModal";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css"; 
import "../../../../shared/styles/Buttons.css"; 


const BuscarPartido = () => {

  const navigate = useNavigate();

    const {
      partidos,
      setPartidos,
      filtros,
      setFiltros,
      fetchPartidos,
      isLoading,
      error,
    } = useBuscarPartidos();

    const {eliminarPartido, cargarLogo} = usePartidosActions(setPartidos);

    // Cargar los filtros
    const handleSearch = (partialFilters) => {
      const merged = { ...filtros, ...partialFilters };
      setFiltros(merged);
      fetchPartidos(merged);
    };
     
    const partidoFilterConfig = createPartidoFilterConfig({ onSearch: handleSearch });

    const { showToast } = useToast();
    const confirmDelete = useConfirmDialog();

    const handleCargarLogo = async (partidoId) => {
      const result = await cargarLogo(partidoId, fetchPartidos);
    
      if (result.success) {
        showToast({
          title: "Éxito",
          body: result.message,
          variant: "success",
        });
      } else {
        showToast({
          title: "Error",
          body: result.error || "No se pudo cargar el logo.",
          variant: "danger",
        });
      }
    };
    
    const handleDeleteConfirm = async () => {
      confirmDelete.setLoading(true);
      try {
        const result = await eliminarPartido(confirmDelete.payload);
    
        if (result.success) {
          showToast({
            title: "Éxito",
            body: "Partido eliminado correctamente",
            variant: "success",
          });
          confirmDelete.close();
        } else {
          showToast({
            title: "Alerta",
            body: result.error || "No se pudo eliminar",
            variant: "danger",
          });
        }
      } catch (err) {
        showToast({
          title: "Alerta inesperada",
          body: err.message || "Alerta inesperada",
          variant: "danger",
        });
      } finally {
        confirmDelete.setLoading(false);
      }
    };
      


 
  // Editar Partido
  const editarPartido = (id) => {
    navigate(`editar/${id}`);
  };


    const breadcrumbItems = [
      { label: "Inicio", to: "/" },
      { label: "Colegio electoral", to: "/colegio" },
      { label: "Sistema electoral", to:"/colegio/sistema"},
      { label: "Buscar partido"}
    ];
  

  return (
    <div>
      <div className="app-layout-container">
      <Breadcrumbs items={breadcrumbItems} />
      <h1 className="text-center">Buscar partido</h1>
      <GenericFilterForm
        config={partidoFilterConfig}
        values={filtros}
        setValues={setFiltros}
      />

      <Table
        headers={partidoTableHeaders}
        data={partidos}
        isLoading={isLoading}
        error={error} 
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
          <div className="d-flex flex-wrap gap-2">
            <button
            className="btn btn-outline-vino btn-sm"
            onClick={() => editarPartido(partido.id)}
          >
            Editar
          </button>
          <button
            className="btn btn-outline-lightred btn-sm"
            onClick={() => confirmDelete.open(partido.id)} // Abre el modal con el ID
          >
            Eliminar
          </button>
          <button
            className="btn btn-outline-vino btn-sm"
            onClick={() => handleCargarLogo(partido.id)} // Muestra toast según resultado
          >
            Cargar Logo
          </button>
          </div>
            </td>
          </tr>
        )}
      />
          <ConfirmModal
          show={confirmDelete.isOpen}
          onHide={confirmDelete.close}
          onConfirm={handleDeleteConfirm}
          body={`¿Estás seguro de eliminar al partido con ID ${confirmDelete.payload}?`}
          confirmText="Eliminar"
          cancelText="Cancelar"
          loading={confirmDelete.loading}
        />
        </div>
    </div>
  );
};

export default BuscarPartido;