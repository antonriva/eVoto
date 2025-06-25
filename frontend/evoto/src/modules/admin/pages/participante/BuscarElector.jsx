import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table";
import ExpandableRow from "../../../identidad/components/identidadExpandableRow/IdentidadExpandableRow";
import FiltrosPersonas from "../../components/FiltrosElectores";
import { useBuscarElectores } from "../../hooks/useBuscarElectores";
import { usePersonasActions } from "../../../identidad/hooks/usePersonasActions";
import { useElectoresActions } from "../../hooks/useElectoresActions";
import { useUbicaciones } from "../../../identidad/hooks/useUbicaciones";
import { createDomicilioFilterConfig } from "../../config/domicilioElectorFilterConfig";
import createIdentidadFilterConfig from "../../../identidad/config/identidadFilterConfig";
import createElectorFilterConfig  from "../../config/electorFilterConfig";
import { electorTableHeaders } from "../../config/electorTableConfig";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";
import { personasTableHeaders } from "../../../../modules/identidad/config/personasTableConfig";
import Breadcrumbs from "../../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../../shared/layouts/AppLayout.css"; 
import {useConfirmDialog} from "../../../../shared/hooks/useConfirmDialog";
import ConfirmModal from "../../../../shared/components/confirmModal/ConfirmModal";
import { useToast } from "../../../../shared/context/ToastContext";

const PaginaBuscarElectores = () => {
    const navigate = useNavigate();
    const {
      personas,
      setPersonas,
      electores,
      setElectores,
      filtros,
      setFiltros,
      fetchPersonas,
      fetchElectores,
      isLoading,
      error,
    } = useBuscarElectores();

    const {fetchDomicilios} = usePersonasActions(setPersonas);
    const {eliminarElector} = useElectoresActions();

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

    const handleSearch = (partialFilters) => {
      const merged = { ...filtros, ...partialFilters };
      setFiltros(merged);
      fetchElectores(merged);
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

    const electorFilterConfig = createElectorFilterConfig({ onSearch: handleSearch });

      const { showToast } = useToast();
      const confirmDelete = useConfirmDialog();
  
    const handleDeleteConfirm = async () => {
      confirmDelete.setLoading(true);
      try {
        const result = await eliminarElector(confirmDelete.payload);
    
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
  



  const editarElector = (idElector) => {
    navigate(`editar/${idElector}`);
  };


    //Visuales
    const breadcrumbItems = [
      { label: "Inicio", to: "/" },
      { label: "Colegio electoral", to: "/colegio" },
      { label: "Sistema electoral", to: "/colegio/sistema" },
      { label: "Buscar Elector"}
    ];
  

  return (
    <div>
      <div className="app-layout-container">
      <Breadcrumbs items={breadcrumbItems} />
      <h1 className="display-4 fw-bold text-dark text-center">Catálogo de electores</h1>
      <div className="row mb-4 gx-3 align-items-stretch">
          <div className="col-md-6 d-flex">
            <div className="card p-3 w-100 h-100">

      {error && <p style={{ color: "red" }}>{error}</p>}
      <GenericFilterForm
        config={identidadFilterConfig}
        values={filtros}
        setValues={setFiltros}
      />
              </div>
              </div>
              <div className="col-md-6 d-flex">
              <div className="card p-3 w-100 h-100">
      <GenericFilterForm
        config={domicilioFilterConfig}
        values={filtros}
        setValues={setFiltros}
      />
        </div>
        </div>

        </div>
      {/* Tabla de Electores */}
      <Table
          headers={electorTableHeaders}
          data={electores}
          isLoading={isLoading} // ✅ Show spinner while loading
          error={error}         // ✅ Show any backend error
          renderRow={(p) => (
            <ExpandableRow
              key={p.idElector}
              idElector={p.idElector}
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
              onEdit={() => editarElector(p.idElector)}
              onDelete={(id) => confirmDelete.open(p.idElector)}
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

export default PaginaBuscarElectores;
