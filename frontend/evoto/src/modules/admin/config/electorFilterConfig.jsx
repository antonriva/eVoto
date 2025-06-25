import ProgressiveDatePicker from "../../../shared/components/progressiveDatePicker/ProgressiveDatePicker";

export const createElectorFilterConfig = ({ onSearch }) => ({
  fields: [
    { name: "idElector", label: "IDE", type: "hidden" },
    {
      name: "fechaInicio",
      label: "Fecha de nacimiento",
      type: "hidden",
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

export default createElectorFilterConfig