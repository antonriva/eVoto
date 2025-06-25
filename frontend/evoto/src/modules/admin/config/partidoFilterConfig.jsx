import ProgressiveDatePicker from "../../../shared/components/progressiveDatePicker/ProgressiveDatePicker";

export const createPartidoFilterConfig = ({ onSearch }) => ({
  fields: [
    { name: "id", label: "ID", type: "text" },
    { name: "denominacion", label: "Denominación", type: "text" },
    { name: "siglas", label: "Siglas", type: "text" },
  
    {
      name: "fechaInicio",
      label: "Fecha de registro",
      type: "custom",
      render: ({ value, onChange, setValues, values }) => (
        <ProgressiveDatePicker
          value={value}
          onChange={(date, granularity) => {
            setValues({
              ...values,
              fechaInicio: date,
              granularidadInicio: granularity
            });
          }}
        />
      )
    },
    {
      name: "granularidadInicio",
      type: "hidden"
    }
  ],
  onSearch,
});

export default createPartidoFilterConfig