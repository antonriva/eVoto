import React, { useState } from "react";
import DatePicker from "react-datepicker";
import '../../../node_modules/react-datepicker/src/stylesheets/datepicker.scss'

const DateRegistroSelectorForm = ({ fechaSeleccionada, onChange }) => {
  // Estado local para la fecha seleccionada
  const [fechaNacimiento, setFechaNacimiento] = useState(fechaSeleccionada || null);

  // Rango permitido: últimas 100 años hasta hoy
  const filtroFecha = (date) => {
    const hoy = new Date();
    const haceCienAnios = new Date();
    haceCienAnios.setFullYear(hoy.getFullYear() - 100);
    return date >= haceCienAnios && date <= hoy;
  };

  // Manejar el cambio de la fecha seleccionada
  const manejarCambioFecha = (fecha) => {
    setFechaNacimiento(fecha);
    if (onChange) {
      onChange(fecha);
    }
  };

  return (
    <div>
      <label htmlFor="fechaNacimiento">Fecha de Nacimiento:</label>
      <DatePicker
        selected={fechaNacimiento}
        onChange={manejarCambioFecha}
        filterDate={filtroFecha}
        placeholderText="Selecciona tu fecha de nacimiento"
        dateFormat="yyyy/MM/dd"
        showYearDropdown
        scrollableYearDropdown
        yearDropdownItemNumber={100} // Mostrar últimos 100 años en el dropdown
        id="fechaNacimiento"
      />
    </div>
  );
};

export default DateRegistroSelectorForm;
