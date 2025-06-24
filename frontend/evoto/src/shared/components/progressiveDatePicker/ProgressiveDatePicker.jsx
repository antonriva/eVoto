// src/shared/components/ProgressiveDatePicker.jsx
import React, { useState, useEffect } from "react";

const ProgressiveDatePicker = ({ value, onChange }) => {
  const currentYear = new Date().getFullYear();
  const [year, setYear] = useState(value?.getFullYear() || "");
  const [month, setMonth] = useState(
    value ? value.getMonth() + 1 : ""
  );
  const [day, setDay] = useState(value?.getDate() || "");

  const [daysInMonth, setDaysInMonth] = useState([]);

  // Update valid days when month or year changes
  useEffect(() => {
    if (year && month) {
      const numDays = new Date(year, month, 0).getDate(); // last day of previous month
      setDaysInMonth([...Array(numDays).keys()].map(i => i + 1));
    } else {
      setDaysInMonth([]);
    }
  }, [year, month]);

  useEffect(() => {
    if (year && month && day) {
      onChange(new Date(year, month - 1, day), "completa");
    } else if (year && month) {
      onChange(new Date(year, month - 1), "anio-mes");
    } else if (year) {
      onChange(new Date(year, 0), "anio");
    } else {
      onChange(null, null);
    }
  }, [year, month, day]);

  return (
    <div className="d-flex gap-2">
      {/* Year Selector */}
      <select
        className="form-select"
        value={year}
        onChange={(e) => {
          setYear(parseInt(e.target.value) || "");
          setMonth("");
          setDay("");
        }}
      >
        <option value="">Año</option>
        {Array.from({ length: 100 }, (_, i) => currentYear - i).map((yr) => (
          <option key={yr} value={yr}>
            {yr}
          </option>
        ))}
      </select>

      {/* Month Selector */}
      <select
        className="form-select"
        value={month}
        onChange={(e) => {
          setMonth(parseInt(e.target.value) || "");
          setDay("");
        }}
        disabled={!year}
      >
        <option value="">Mes</option>
        {Array.from({ length: 12 }, (_, i) => (
          <option key={i + 1} value={i + 1}>
            {new Date(0, i).toLocaleString("es", { month: "long" })}
          </option>
        ))}
      </select>

      {/* Day Selector */}
      <select
        className="form-select"
        value={day}
        onChange={(e) => setDay(parseInt(e.target.value) || "")}
        disabled={!month}
      >
        <option value="">Día</option>
        {daysInMonth.map((d) => (
          <option key={d} value={d}>
            {d}
          </option>
        ))}
      </select>
    </div>
  );
};

export default ProgressiveDatePicker;
