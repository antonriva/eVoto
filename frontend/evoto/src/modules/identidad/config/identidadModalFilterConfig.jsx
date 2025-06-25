export const createIdentidadModalFilterConfig = ({ onSearch }) => ({
    fields: [
      { name: "id", label: "ID", type: "text" },
      { name: "nombre", label: "Nombre", type: "text" },
      { name: "apellidoPaterno", label: "Apellido paterno", type: "text" },
      { name: "apellidoMaterno", label: "Apellido materno", type: "text" },
      {
        name: "fechaNacimiento",
        label: "Fecha de nacimiento",
        type: "custom",
        render: ({ value, onChange, setValues, values }) => (
          <ProgressiveDatePicker
            value={value}
            onChange={(date, granularity) => {
              setValues({
                ...values,
                fechaNacimiento: date,
                granularidadNacimiento: granularity
              });
            }}
          />
        )
      },
      {
        name: "granularidadNacimiento",
        type: "hidden"
      }
    ],
    onSearch,
  
    renderRow: (row, onSelect) => (
      <>
        <td>{row.id}</td>
        <td>{row.nombre}</td>
        <td>{row.apellidoPaterno}</td>
        <td>{row.apellidoMaterno}</td>
        <td>
          <button onClick={() => onSelect(row)}>Seleccionar</button>
        </td>
      </>
    )
  });

export default createIdentidadModalFilterConfig  