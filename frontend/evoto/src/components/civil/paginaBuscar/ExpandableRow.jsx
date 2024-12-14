import React, { useState } from "react";

const ExpandableRow = ({ idPersona, rowData, fetchDomicilios, colSpan, onEdit, onDelete }) => {
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
        <td>
          {/* Botones de acción */}
          <button onClick={() => onEdit(idPersona)}>Editar</button>
          <button onClick={() => onDelete(idPersona)}>Eliminar</button>
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
                    <th>Entidad Federativa</th>
                    <th>Municipio</th>
                    <th>Localidad</th>
                    <th>Colonia</th>
                    <th>Calle</th>
                    <th>Número exterior</th>
                    <th>Número interior</th>
                    <th>Código postal</th>
                    <th>Tipo de domicilio</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                  </tr>
                </thead>
                <tbody>
                  {domicilios.map((domicilio, index) => (
                    <tr key={index}>
                      <td>{domicilio.entidadFederativa}</td>
                      <td>{domicilio.municipio}</td>
                      <td>{domicilio.localidad || "---"}</td>
                      <td>{domicilio.colonia || "---"}</td>
                      <td>{domicilio.calle || "---"}</td>
                      <td>{domicilio.numeroExterior || "---"}</td>
                      <td>{domicilio.numeroInterior || "---"}</td>
                      <td>{domicilio.codigoPostal || "---"}</td>
                      <td>{domicilio.tipoDeDomicilio}</td>
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
