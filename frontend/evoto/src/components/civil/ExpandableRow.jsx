import React, { useState } from "react";

const ExpandableRow = ({ idPersona, rowData, fetchDomicilios, colSpan }) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [loading, setLoading] = useState(false);
  const [domicilios, setDomicilios] = useState([]);

  const toggleExpand = async () => {
    setIsExpanded(!isExpanded);

    if (!isExpanded && domicilios.length === 0) {
      setLoading(true);
      try {
        const data = await fetchDomicilios(idPersona); // Pasa idPersona aquí
        setDomicilios(data);
      } catch (error) {
        console.error("Error al cargar domicilios:", error);
        setDomicilios([]);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <>
      <tr>
        {rowData.map((cell, index) => (
          <td key={index}>{cell}</td>
        ))}
        <td>
          <button onClick={toggleExpand}>
            {isExpanded ? "Contraer" : "Expandir"}
          </button>
        </td>
      </tr>
      {isExpanded && (
        <tr>
          <td colSpan={colSpan}>
            {loading ? (
              "Cargando domicilios..."
            ) : domicilios.length > 0 ? (
              <table>
                <thead>
                  <tr>
                    <th>Calle</th>
                    <th>Número Exterior</th>
                    <th>Municipio</th>
                    <th>Colonia</th>
                    <th>Entidad Federativa</th>
                    <th>Código Postal</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                  </tr>
                </thead>
                <tbody>
                  {domicilios.map((domicilio, index) => (
                    <tr key={index}>
                      <td>{domicilio.calle}</td>
                      <td>{domicilio.numeroExterior}</td>
                      <td>{domicilio.municipio}</td>
                      <td>{domicilio.colonia}</td>
                      <td>{domicilio.entidadFederativa}</td>
                      <td>{domicilio.codigoPostal}</td>
                      <td>{domicilio.fechaDeInicio}</td>
                      <td>{domicilio.fechaDeFin || "---"}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              "No hay domicilios disponibles."
            )}
          </td>
        </tr>
      )}
    </>
  );
};

export default ExpandableRow;
