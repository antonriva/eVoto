// src/modules/admin/config/partidoFilterConfig.js

export const partidoFilterConfig = ({ onSearch }) => ({
  onSearch,
  fields: [
    {
      name: "id",
      label: "ID",
      type: "text",
      validation: {
        pattern: /^\d*$/,
        title: "El ID solo puede contener números.",
      },
    },
    {
      name: "denominacion",
      label: "Denominación",
      type: "text",
      validation: {
        pattern: /^[a-zA-Z]+( [a-zA-Z]+)*$/,
        title: "La denominación debe contener solo letras y espacios.",
      },
    },
    {
      name: "siglas",
      label: "Siglas",
      type: "text",
      validation: {
        pattern: /^[a-zA-Z]*$/,
        title: "Las siglas deben contener solo letras.",
      },
    },
    {
      name: "anioInicio",
      label: "Año de Inicio",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 100 }, (_, i) => {
          const year = new Date().getFullYear() - i;
          return { id: year, descripcion: year };
        }),
    },
    {
      name: "mesInicio",
      label: "Mes de Inicio",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 12 }, (_, i) => ({
          id: i + 1,
          descripcion: new Date(0, i).toLocaleString("es", { month: "long" }),
        })),
    },
    {
      name: "diaInicio",
      label: "Día de Inicio",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 31 }, (_, i) => ({
          id: i + 1,
          descripcion: i + 1,
        })),
    },
    {
      name: "anioFin",
      label: "Año de Fin",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 100 }, (_, i) => {
          const year = new Date().getFullYear() - i;
          return { id: year, descripcion: year };
        }),
    },
    {
      name: "mesFin",
      label: "Mes de Fin",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 12 }, (_, i) => ({
          id: i + 1,
          descripcion: new Date(0, i).toLocaleString("es", { month: "long" }),
        })),
    },
    {
      name: "diaFin",
      label: "Día de Fin",
      type: "select",
      fetchOptions: async () =>
        Array.from({ length: 31 }, (_, i) => ({
          id: i + 1,
          descripcion: i + 1,
        })),
    },
  ],
});
