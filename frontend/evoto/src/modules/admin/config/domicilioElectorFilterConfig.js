export const createDomicilioFilterConfig = ({
    entidades,
    municipios,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchColonias,
    onSearch
  }) => ({
    fields: [
      {
        name: "entidadFederativa",
        label: "Entidad federativa",
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
        
            name: "tipoDeDomicilio",
            label: "Tipo de domicilio",
            type: "hidden",
            options: tiposDeDomicilio,
            fetchOptions: async () => tiposDeDomicilio,
            defaultValue:"2"
          }
      
    ],
    onSearch
  });
  