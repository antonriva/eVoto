import React from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../shared/components/table/Table";
import ExpandableRow from "../components/identidadExpandableRow/IdentidadExpandableRow";
import GenericFilterForm from "../../../shared/components/filterForm/FilterForm";
import TimerRedirect from "../../../shared/components/timerRefresher/TimerRefresher";
import { useBuscarPersonas } from "../hooks/useBuscarPersonas";
import { usePersonasActions } from "../hooks/usePersonasActions"; // Import usePersonasActions
import { personasTableHeaders } from "../config/personasTableConfig";
import { useUbicaciones } from "../hooks/useUbicaciones"; // Import useUbicaciones
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
    const identidadFilterConfig = createIdentidadFilterConfig({
      onSearch: (vals) => {
        setFiltros(prev => ({ ...prev, ...vals }));
        fetchPersonas({ ...filtros, ...vals });
      }
    });
    
    const domicilioFilterConfig = createDomicilioFilterConfig({
      entidades,
      municipios,
      localidades,
      //colonias,
      tiposDeDomicilio,
      fetchMunicipios,
      fetchLocalidades,
      //fetchColonias,
      onSearch: (vals) => {
        setFiltros(prev => ({ ...prev, ...vals }));
        fetchPersonas({ ...filtros, ...vals });
      }
    });

  const editarPersona = (id) => {
    navigate(`editar/${id}`);
  };

  return (
    <div>
      <h1>Catálogo de Personas</h1>
      <TimerRedirect />
      {error && <p style={{ color: "red" }}>{error}</p>}
      
      <GenericFilterForm
  config={identidadFilterConfig}
  values={filtros}
  setValues={setFiltros}
/>

<GenericFilterForm
  config={domicilioFilterConfig}
  values={filtros}
  setValues={setFiltros}
/>

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
  );
};

export default PaginaBuscar;
