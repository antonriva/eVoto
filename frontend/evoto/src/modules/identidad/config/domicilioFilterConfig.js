// domicilioFilterConfig.js
export const createDomicilioFilterConfig = ({
    entidades,
    municipios,
    localidades,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchLocalidades,
    onSearch
  }) => ({
    fields: [
      {
        name: "entidadFederativa",
        label: "Entidad Federativa",
        type: "select",
        options: entidades,
        fetchOptions: async () => entidades
      },
      {
        name: "municipio",
        label: "Municipio",
        type: "select",
        options: municipios,
        fetchOptionsOn: "entidadFederativa",
        fetchOptions: fetchMunicipios
      },
      {
        name: "localidad",
        label: "Localidad",
        type: "select",
        options: localidades,
        fetchOptionsOn: "municipio",
        fetchOptions: fetchLocalidades
      },
      {
        name: "tipoDeDomicilio",
        label: "Tipo de Domicilio",
        type: "select",
        options: tiposDeDomicilio,
        fetchOptions: async () => tiposDeDomicilio
      }
    ],
    onSearch
  });
  