export const createResidenciaFilterConfig = ({
    entidades,
    municipios,
    colonias,
    codigosPostales, // <- pass this in
    fetchMunicipios,
    fetchColonias,
    fetchCodigosPostales, // <- use this if dynamic fetching needed
    onSearch
  }) => ({
    fields: [
        {
            name: "idElector",
            label: "ID Elector",
            type: "text",
            disabled: true,
        },
      {
        name: "entidadFederativaId",
        label: "Entidad federativa",
        type: "select",
        options: entidades,
        fetchOptions: async () => entidades,
        required: true
      },
      {
        name: "municipioId",
        label: "Municipio",
        type: "select",
        options: municipios,
        fetchOptionsOn: "entidadFederativaId",
        fetchOptions: fetchMunicipios,
        required: true
      },
      {
        name: "coloniaId",
        label: "Colonia",
        type: "select",
        options: colonias,
        fetchOptionsOn: "municipioId",
        fetchOptions: fetchColonias,
        required: true
      },
      {
        name: "codigoPostalId",
        label: "Código postal",
        type: "select",
        options: codigosPostales,
        fetchOptionsOn: "coloniaId", // Optional if codigosPostales are linked to colonia
        fetchOptions: fetchCodigosPostales, // optional
        disabled: !codigosPostales.length,
        required: true
      },
      {
        name: "calle",
        label: "Calle",
        type: "text",
        pattern: "^[A-Z]+( [A-Z]+)*$",
        title: "Solo letras mayúsculas y espacios.",
        required: true
      },
      {
        name: "numeroExterior",
        label: "Número exterior",
        type: "number",
        min: 1,
      },
      {
        name: "numeroInterior",
        label: "Número interior",
        type: "number",
        min: 1,
      },
      {
        name: "fechaDeInicio",
        label: "Fecha de inicio",
        type: "date",
        required: true,
      },
    ],
    onSearch,
  });
  