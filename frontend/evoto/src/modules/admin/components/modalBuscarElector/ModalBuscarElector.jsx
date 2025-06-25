import React from "react";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";
import Table from "../../../../shared/components/table/Table";
import { useBuscarElectores } from "../../hooks/useBuscarElectores";
import { createIdentidadFilterConfig } from "../../../identidad/config/identidadFilterConfig";
import { createDomicilioFilterConfig } from "../../../identidad/config/domicilioFilterConfig";
import { createElectorFilterConfig } from "../../config/electorFilterConfig";
import { useUbicaciones } from "../../../identidad/hooks/useUbicaciones";
import { usePersonasActions } from "../../../identidad/hooks/usePersonasActions";

const electorTableHeaders = [
  "ID", "Nombre", "Apellido Paterno", "Apellido Materno",
  "Fecha de Nacimiento", "Fecha de Fin", "Seleccionar"
];

const ModalBuscarElector = ({ onSelect }) => {
  const {
    personas,
    setPersonas,
    electores,
    filtros,
    setFiltros,
    fetchElectores,
    isLoading,
    error,
  } = useBuscarElectores();

  const { fetchDomicilios } = usePersonasActions(setPersonas);

  const {
    entidades,
    municipios,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchColonias,
  } = useUbicaciones();

  const handleSearch = (partialFilters) => {
    const merged = { ...filtros, ...partialFilters };
    setFiltros(merged);
    fetchElectores(merged);
  };

  const identidadFilterConfig = createIdentidadFilterConfig({ onSearch: handleSearch });
  const domicilioFilterConfig = createDomicilioFilterConfig({
    entidades,
    municipios,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchColonias,
    onSearch: handleSearch
  });
  const electorFilterConfig = createElectorFilterConfig({ onSearch: handleSearch });

  return (
    <div className="modal-content p-4">
      <h2 className="text-center">Buscar Elector</h2>

      <div className="row mb-4 gx-3">
        <div className="col-md-6">
          <div className="card p-3">
            <h5 className="mb-3 text-center">Filtros de Identidad</h5>
            <GenericFilterForm
              config={identidadFilterConfig}
              values={filtros}
              setValues={setFiltros}
            />
          </div>
        </div>

        <div className="col-md-6">
          <div className="card p-3">
            <h5 className="mb-3 text-center">Filtros de Domicilio</h5>
            <GenericFilterForm
              config={domicilioFilterConfig}
              values={filtros}
              setValues={setFiltros}
            />
          </div>
        </div>
      </div>

      <div className="mb-4">
        <GenericFilterForm
          config={electorFilterConfig}
          values={filtros}
          setValues={setFiltros}
        />
      </div>

      <Table
        headers={electorTableHeaders}
        data={electores}
        isLoading={isLoading}
        error={error}
        renderRow={(p) => (
          <tr key={p.idElector}>
            <td>{p.id}</td>
            <td>{p.nombre}</td>
            <td>{p.apellidoPaterno}</td>
            <td>{p.apellidoMaterno}</td>
            <td>{p.fechaDeNacimiento}</td>
            <td>{p.fechaDeFin}</td>
            <td>
              <button
                className="btn btn-sm btn-primary"
                onClick={() => onSelect(p.idElector)}
              >
                Seleccionar
              </button>
            </td>
          </tr>
        )}
      />
    </div>
  );
};

export default ModalBuscarElector;
