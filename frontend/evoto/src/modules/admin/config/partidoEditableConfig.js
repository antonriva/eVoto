export const getPartidoEditableConfig = () => [
    {
      name: "denominacion",
      label: "Denominación",
      type: "text",
      required: true,
      pattern: "^[A-Z]+( [A-Z]+)*$",
      title: "Solo letras mayúsculas y espacios.",
    },
    {
      name: "siglas",
      label: "Siglas",
      type: "text",
      required: true,
      pattern: "^[A-Z]+( [A-Z]+)*$",
      title: "Solo entre 2 y 6 letras mayúsculas.",
    },
    {
      name: "fechaDeInicio",
      label: "Fecha de registro",
      type: "date",
      required: true,
      disabled: true, // No editable
    },
    {
      name: "fechaDeFin",
      label: "Fecha de fin de vigencia",
      type: "date",
      required: false,
    },
  ];
  