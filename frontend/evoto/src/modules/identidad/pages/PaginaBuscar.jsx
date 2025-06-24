import React from "react";
import { useNavigate } from "react-router-dom";
//shared
import Table from "../../../shared/components/table/Table";
import TimerRedirect from "../../../shared/components/timerRefresher/TimerRefresher";
import GenericFilterForm from "../../../shared/components/filterForm/FilterForm";
import Breadcrumbs from "../../../shared/components/breadcrumbs/Breadcrumbs";
import "../../../shared/layouts/AppLayout.css"; 

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
  const navigate = useNavigate();
  const {
    personas,
    setPersonas,
    filtros,
    setFiltros,
    fetchPersonas,
    error,
  } = useBuscarPersonas();

  // Use the usePersonasActions hook
  const { fetchDomicilios, eliminarPersona } = usePersonasActions(setPersonas);

  // Use the useUbicaciones hook to get location-related data
  const {
    entidades,
    municipios,
    localidades,
    //colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchLocalidades,
    //fetchColonias
  } = useUbicaciones();

    // Create filter configuration using createPersonasFilterConfig
    const handleSearch = (partialFilters) => {
      const merged = { ...filtros, ...partialFilters };
      setFiltros(merged);
      fetchPersonas(merged);
    };

    const identidadFilterConfig = createIdentidadFilterConfig({ onSearch: handleSearch });

    const domicilioFilterConfig = createDomicilioFilterConfig({
      entidades,
      municipios,
      localidades,
      tiposDeDomicilio,
      fetchMunicipios,
      fetchLocalidades,
      onSearch: handleSearch
    });


  const editarPersona = (id) => {
    navigate(`editar/${id}`);
  };

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
              onDelete={eliminarPersona}
            />
          )}
        />
      </div>
    </div>
  );
};

export default PaginaBuscar;
