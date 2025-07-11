import React from "react";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";
import Table from "../../../../shared/components/table/Table";
import { useBuscarPersonas } from "../../hooks/useBuscarPersonas";
import { createIdentidadFilterConfig } from "../../config/identidadFilterConfig";
import { createDomicilioFilterConfig } from "../../config/domicilioFilterConfig";
import { useUbicaciones } from "../../hooks/useUbicaciones";
import "../../../../shared/styles/Buttons.css";

const personasTableHeaders = ["ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de Nacimiento", "Fecha de Fin", "Seleccionar"];

const ModalBuscarPersona = ({ onSelect }) => {
  const {
    personas,
    setPersonas,
    filtros,
    setFiltros,
    fetchPersonas,
    isLoading,
    error,
  } = useBuscarPersonas();

  const {
    entidades,
    municipios,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchColonias
  } = useUbicaciones();

  const handleSearch = (partialFilters) => {
    const merged = { ...filtros, ...partialFilters };
    setFiltros(merged);
    fetchPersonas(merged);
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

  return (
    <div className="modal-content p-4">
      <h2 className="text-center">Buscar persona</h2>

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

      <Table
        headers={personasTableHeaders}
        data={personas}
        isLoading={isLoading}
        error={error}
        renderRow={(p) => (
          <tr key={p.id}>
            <td>{p.id}</td>
            <td>{p.nombre}</td>
            <td>{p.apellidoPaterno}</td>
            <td>{p.apellidoMaterno}</td>
            <td>{p.fechaDeNacimiento}</td>
            <td>{p.fechaDeFin}</td>
            <td>
              <button className="btn btn-outline-vino" onClick={() => onSelect(p.id)}>
                Seleccionar
              </button>
            </td>
          </tr>
        )}
      />
    </div>
  );
};

export default ModalBuscarPersona;
