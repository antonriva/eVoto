import ProgressiveDatePicker from "../../../shared/components/progressiveDatePicker/ProgressiveDatePicker";

export const createIdentidadFilterConfig = ({ onSearch }) => ({
  fields: [
    { name: "id", label: "ID", type: "text" },
    { name: "nombre", label: "Nombre", type: "text" },
    { name: "apellidoPaterno", label: "Apellido Paterno", type: "text" },
    { name: "apellidoMaterno", label: "Apellido Materno", type: "text" },
    {
        name: "fechaNacimiento",
        label: "Fecha de Nacimiento",
        type: "custom",
        render: ({ value, onChange }) => (
          <ProgressiveDatePicker value={value} onChange={onChange} />
        )
      }
  ],
  onSearch,
});

export default createIdentidadFilterConfig