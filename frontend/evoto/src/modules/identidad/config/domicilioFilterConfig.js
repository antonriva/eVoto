// domicilioFilterConfig.js
export const createDomicilioFilterConfig = ({
    entidades,
    municipios,
    //colonias,
    localidades,
    tiposDeDomicilio,
    fetchMunicipios,
    //fetchColonias,
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
      /*{
        name: "colonia",
        label: "Colonia",
        type: "select",
        options: colonias,
        fetchOptionsOn: "municipio",
        fetchOptions: fetchColonias
      },*/
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
  