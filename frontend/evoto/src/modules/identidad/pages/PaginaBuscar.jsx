import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
//shared
import Table from "../../../shared/components/table/Table";
import TimerRedirect from "../../../shared/components/timerRefresher/TimerRefresher";
import GenericFilterForm from "../../../shared/components/filterForm/FilterForm";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../shared/layouts/AppLayout.css"; 
import {useConfirmDialog} from "../../../shared/hooks/useConfirmDialog";
import ConfirmModal from "../../../shared/components/confirmModal/ConfirmModal";
import { useToast } from "../../../shared/context/ToastContext";

//identidad/components
import ExpandableRow from "../components/identidadExpandableRow/IdentidadExpandableRow";
//identidad/hooks
import { useBuscarPersonas } from "../hooks/useBuscarPersonas";
import { usePersonasActions } from "../hooks/usePersonasActions"; 
import { useUbicaciones } from "../hooks/useUbicaciones"; 
//identidad/config
import { personasTableHeaders } from "../config/personasTableConfig";
import  createIdentidadFilterConfig  from "../config/identidadFilterConfig";
import { createDomicilioFilterConfig } from "../config/domicilioFilterConfig";



const PaginaBuscar = () => {
  //Fetch
  const navigate = useNavigate();
  const {
    personas,
    setPersonas,
    filtros,
    setFiltros,
    fetchPersonas,
    isLoading,
    error,
  } = useBuscarPersonas();

  // Preparar los datos y acciones a utilizar
  const { fetchDomicilios, eliminarPersona } = usePersonasActions(setPersonas);


  // Datos de ubicaciones
  const {
    entidades,
    municipios,
    //localidades,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    //fetchLocalidades,
    fetchColonias
  } = useUbicaciones();

    // Cargar los filtros
    const handleSearch = (partialFilters) => {
      const merged = { ...filtros, ...partialFilters };
      setFiltros(merged);
      fetchPersonas(merged);
    };
    
      //Filtros de persona
    const identidadFilterConfig = createIdentidadFilterConfig({ onSearch: handleSearch });

      //Filtros de domicilio
    const domicilioFilterConfig = createDomicilioFilterConfig({
      entidades,
      municipios,
      //localidades,
      colonias,
      tiposDeDomicilio,
      fetchMunicipios,
      //fetchLocalidades,
      fetchColonias,
      onSearch: handleSearch
    });

  //Eliminar
  const { showToast } = useToast();
  const confirmDelete = useConfirmDialog();
  
  const handleDeleteConfirm = async () => {
    confirmDelete.setLoading(true);
    try {
      const result = await eliminarPersona(confirmDelete.payload);
  
      if (result.success) {
        showToast({
          title: "Éxito",
          body: "Persona eliminada correctamente",
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

  //Editar
  const editarPersona = (id) => {
    navigate(`editar/${id}`);
  };

  //Visuales
  const breadcrumbItems = [
    { label: "Inicio", to: "/" },
    { label: "Registro civil", to: "/civil" },
    { label: "Buscar" }
  ];

  return (
    <div>
      <div className="app-layout-container">
        <Breadcrumbs items={breadcrumbItems} />
      
        <h1 className="text-center">Catálogo de Personas</h1>
        <div className="row mb-4 gx-3 align-items-stretch">
          <div className="col-md-6 d-flex">
            <div className="card p-3 w-100 h-100">
              <h5 className="mb-3 text-center">Identidad</h5>
              <GenericFilterForm
                config={identidadFilterConfig}
                values={filtros}
                setValues={setFiltros}
              />
            </div>
          </div>

          <div className="col-md-6 d-flex">
            <div className="card p-3 w-100 h-100">
              <h5 className="mb-3 text-center">Domicilio</h5>
              <GenericFilterForm
                config={domicilioFilterConfig}
                values={filtros}
                setValues={setFiltros}
              />
            </div>
          </div>

        </div>



        <Table
          headers={personasTableHeaders}
          data={personas}
          isLoading={isLoading} // ✅ Show spinner while loading
          error={error}         // ✅ Show any backend error
          renderRow={(p) => (
            <ExpandableRow
              key={p.id}
              idPersona={p.id}
              rowData={[
                p.id,
                p.nombre,
                p.apellidoPaterno,
                p.apellidoMaterno,
                p.fechaDeNacimiento,
                p.fechaDeFin
              ]}
              fetchDomicilios={() => fetchDomicilios(p.id)}
              colSpan={personasTableHeaders.length}
              onEdit={editarPersona}
              onDelete={(id) => confirmDelete.open(id)}
            />
          )}
        />

        <ConfirmModal
          show={confirmDelete.isOpen}
          onHide={confirmDelete.close}
          onConfirm={handleDeleteConfirm}
          body={`¿Estás seguro de eliminar a la persona con ID ${confirmDelete.payload}?`}
          confirmText="Eliminar"
          cancelText="Cancelar"
          loading={confirmDelete.loading}
        />

      </div>
    </div>
  );
};

export default PaginaBuscar;
