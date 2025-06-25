// src/config/personaRegistroConfig.js

export const getPersonaRegistroConfig = ({ entidades = [], municipios = [], today }) => [
      {
        name: "nombre",
        label: "Nombre",
        type: "text",
        required: true,
        pattern: "^[A-ZÑ]+( [A-ZÑ]+)*$",
        title: "El nombre debe contener solo letras mayúsculas y espacios intermedios, quita espacio al final.",
      },
      {
        name: "apellidoPaterno",
        label: "Apellido paterno",
        type: "text",
        required: true,
        pattern: "^[A-ZÑ]+( [A-ZÑ]+)*$",
        title: "Solo letras mayúsculas.",
      },
      {
        name: "apellidoMaterno",
        label: "Apellido materno",
        type: "text",
        required: true,
        pattern: "^[A-ZÑ]+( [A-ZÑ]+)*$",
        title: "Solo letras mayúsculas.",
      },
      {
        name: "fechaDeNacimiento",
        label: "Fecha de nacimiento",
        type: "date",
        required: true,
      },
      {
        name: "entidadFederativaId",
        label: "Entidad federativa",
        type: "select",
        required: true,
        options: entidades.map((e) => ({ label: e.descripcion, value: e.id })),
      },
      {
        name: "municipioId",
        label: "Municipio",
        type: "select",
        required: true,
        options: municipios.map((m) => ({ label: m.descripcion, value: m.id })),
        disabled: !municipios.length,
      },
      {
        name: "fechaDeInicio",
        label: "Fecha de registro",
        type: "date",
        required: true,
        disabled: true, // make it non-editable
        defaultValue: today, // 👈 pre-fill today’s date
      }      
    ];

  