import React, { useState, useEffect } from "react";

const FormularioDomicilio = ({ personaId, onDomicilioRegistrado }) => {
  const [domicilio, setDomicilio] = useState({
    calle: "",
    numeroExterior: "",
    numeroInterior: "",
    entidadFederativaId: "",
    municipioId: "",
    coloniaId: "",
  });
  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [colonias, setColonias] = useState([]);
  const [fechaDeInicio, setFechaDeInicio] = useState("");

  useEffect(() => {
    fetchEntidades();
  }, []);

  const fetchEntidades = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/entidad-federativa");
      const data = await response.json();
      setEntidades(data);
    } catch (error) {
      console.error("Error al cargar entidades federativas:", error);
    }
  };

  const fetchMunicipios = async (entidadId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/municipio/entidad/${entidadId}`);
      const data = await response.json();
      setMunicipios(data);
    } catch (error) {
      console.error("Error al cargar municipios:", error);
    }
  };

  const fetchColonias = async (municipioId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/colonia/municipio/${municipioId}`);
      const data = await response.json();
      setColonias(data);
    } catch (error) {
      console.error("Error al cargar colonias:", error);
    }
  };

  const handleRegistrar = async (e) => {
    e.preventDefault();

    // Construir la solicitud para el domicilio
    const datosDomicilio = {
      calle: domicilio.calle,
      numeroExterior: parseInt(domicilio.numeroExterior, 10),
      numeroInterior: domicilio.numeroInterior ? parseInt(domicilio.numeroInterior, 10) : null,
      entidadFederativa: { id: parseInt(domicilio.entidadFederativaId, 10) },
      municipio: { id: parseInt(domicilio.municipioId, 10) },
      colonia: { id: parseInt(domicilio.coloniaId, 10) },
      postal: { id: parseInt(domicilio.coloniaId, 10) }, // Reutilizamos el id de colonia para postal
    };

    try {
      const response = await fetch("http://localhost:8080/api/domicilio", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datosDomicilio),
      });

      if (response.ok) {
        const nuevoDomicilio = await response.json();
        await vincularDomicilio(nuevoDomicilio.id);
        alert("Domicilio registrado con éxito");
        onDomicilioRegistrado();
      } else {
        const errorData = await response.json();
        alert(`Error al registrar domicilio: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error al registrar domicilio:", error);
      alert("Error inesperado al registrar domicilio.");
    }
  };

  const vincularDomicilio = async (domicilioId) => {
    // Validar que la fecha de inicio no sea futura
    const today = new Date().toISOString().split("T")[0];
    if (!fechaDeInicio) {
      alert("Debe proporcionar una fecha de inicio.");
      return;
    }
    if (fechaDeInicio > today) {
      alert("La fecha de inicio no puede ser posterior al día actual.");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:8080/api/persona/${personaId}/domicilio/${domicilioId}?fechaDeInicio=${fechaDeInicio}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
        }
      );

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Error al vincular domicilio");
      }

      alert("Domicilio vinculado con éxito.");
    } catch (error) {
      console.error("Error al vincular domicilio:", error);
      alert("Error inesperado al vincular domicilio.");
    }
  };

  return (
    <form onSubmit={handleRegistrar}>
      <h2>Registrar Domicilio</h2>
      <input
        type="text"
        placeholder="Calle"
        value={domicilio.calle}
        onChange={(e) => setDomicilio({ ...domicilio, calle: e.target.value })}
        required
      />
      <input
        type="number"
        placeholder="Número Exterior"
        value={domicilio.numeroExterior}
        onChange={(e) => setDomicilio({ ...domicilio, numeroExterior: e.target.value })}
        required
      />
      <input
        type="number"
        placeholder="Número Interior (opcional)"
        value={domicilio.numeroInterior || ""}
        onChange={(e) => setDomicilio({ ...domicilio, numeroInterior: e.target.value })}
      />
      <select
        onChange={(e) => {
          const entidadFederativaId = e.target.value;
          setDomicilio({ ...domicilio, entidadFederativaId });
          fetchMunicipios(entidadFederativaId);
        }}
        required
      >
        <option value="">Seleccione Entidad Federativa</option>
        {entidades.map((entidad) => (
          <option key={entidad.id} value={entidad.id}>
            {entidad.descripcion}
          </option>
        ))}
      </select>
      <select
        onChange={(e) => {
          const municipioId = e.target.value;
          setDomicilio({ ...domicilio, municipioId });
          fetchColonias(municipioId);
        }}
        required
        disabled={!domicilio.entidadFederativaId}
      >
        <option value="">Seleccione Municipio</option>
        {municipios.map((municipio) => (
          <option key={municipio.id} value={municipio.id}>
            {municipio.descripcion}
          </option>
        ))}
      </select>
      <select
        onChange={(e) => {
          const coloniaId = e.target.value;
          setDomicilio({ ...domicilio, coloniaId });
        }}
        required
        disabled={!domicilio.municipioId}
      >
        <option value="">Seleccione Colonia</option>
        {colonias.map((colonia) => (
          <option key={colonia.id} value={colonia.id}>
            {colonia.descripcion}
          </option>
        ))}
      </select>
      <input
        type="date"
        placeholder="Fecha de Inicio"
        value={fechaDeInicio}
        onChange={(e) => setFechaDeInicio(e.target.value)}
        required
      />
      <button type="submit">Registrar Domicilio</button>
    </form>
  );
};

export default FormularioDomicilio;
