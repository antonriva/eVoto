import React from "react";
import Table from "../table/Table";
import GenericFilterForm from "../../../modules/identidad/components/modalBuscarPersona/ModalGenericFilters";

const GenericSearchModal = ({ title, filterConfig, headers, data, onSelect, error }) => (
  <div>
    <h1>{title}</h1>
    {error && <p style={{ color: "red" }}>{error}</p>}

    <GenericFilterForm
      fields={filterConfig.fields}
      onSearch={filterConfig.onSearch}
      values={filterConfig.values}
      setValues={filterConfig.setValues}
    />

    <Table
      headers={headers}
      data={data}
      renderRow={(row) => (
        <tr key={row.id}>
          {filterConfig.renderRow(row, onSelect)}
        </tr>
      )}
    />
  </div>
);

export default GenericSearchModal;
