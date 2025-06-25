// src/modules/identidad/config/personaEditableConfig.js

export const getPersonaEditableConfig = ({ entidades = [], municipios = [] }) => [
    {
      name: "nombre",
      label: "Nombre",
      type: "text",
      required: true,
      pattern: "^[A-Z]+( [A-Z]+)*$",
      title: "Solo letras mayúsculas y espacios.",
    },
    {
      name: "apellidoPaterno",
      label: "Apellido paterno",
      type: "text",
      required: true,
      pattern: "^[A-Z]+( [A-Z]+)*$",
      title: "Solo letras mayúsculas.",
    },
    {
      name: "apellidoMaterno",
      label: "Apellido materno",
      type: "text",
      required: true,
      pattern: "^[A-Z]+( [A-Z]+)*$",
      title: "Solo letras mayúsculas.",
    },
    {
      name: "fechaDeNacimiento",
      label: "Fecha de nacimiento",
      type: "date",
      required: true,
      disabled: true, // Fecha de nacimiento no editable
    },
    {
      name: "fechaDeFin",
      label: "Fecha de Fin",
      type: "date",
    },
    {
      name: "entidadFederativaId",
      label: "Entidad federativa",
      type: "select",
      required: true,
      options: entidades.map((e) => ({ label: e.descripcion, value: e.id })),
      disabled: true,
    },
    {
      name: "municipioId",
      label: "Municipio",
      type: "select",
      required: true,
      options: municipios.map((m) => ({ label: m.descripcion, value: m.id })),
      disabled: !municipios.length,
      disabled: true
    },
    {
      name: "fechaDeInicio",
      label: "Fecha de registro",
      type: "date",
      required: true,
      disabled: true
    },
  ];
  