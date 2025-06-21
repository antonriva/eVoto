// personasFilterConfig.js
export const createPersonasFilterConfig = ({
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
        name: "id",
        label: "ID",
        type: "text",
        validation: {
          pattern: /^\d*$/,
          title: "Solo se permiten números.",
        }
      },
      { name: "nombre", label: "Nombre", type: "text" },
      { name: "apellidoPaterno", label: "Apellido Paterno", type: "text" },
      { name: "apellidoMaterno", label: "Apellido Materno", type: "text" },
      {
        name: "anioNacimiento",
        label: "Año de Nacimiento",
        type: "select",
        options: Array.from({ length: 100 }, (_, i) => {
          const year = new Date().getFullYear() - i;
          return { id: year, descripcion: year };
        })
      },
      {
        name: "mesNacimiento",
        label: "Mes de Nacimiento",
        type: "select",
        options: Array.from({ length: 12 }, (_, i) => ({
          id: i + 1,
          descripcion: new Date(0, i).toLocaleString("es", { month: "long" })
        }))
      },
      {
        name: "diaNacimiento",
        label: "Día de Nacimiento",
        type: "select",
        options: Array.from({ length: 31 }, (_, i) => ({
          id: i + 1,
          descripcion: i + 1
        }))
      },
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
  