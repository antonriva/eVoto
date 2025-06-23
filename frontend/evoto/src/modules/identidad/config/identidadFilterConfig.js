
export const createIdentidadFilterConfig = ({ onSearch }) => ({
  fields: [
    { name: "id", label: "ID", type: "text" },
    { name: "nombre", label: "Nombre", type: "text" },
    { name: "apellidoPaterno", label: "Apellido Paterno", type: "text" },
    { name: "apellidoMaterno", label: "Apellido Materno", type: "text" },
  ],
  onSearch,
});
