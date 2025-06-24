// DateSelector.jsx (for React)
import React, { useEffect, useState } from "react";

const getDaysInMonth = (year, month) => new Date(year, month, 0).getDate();

export default function DateSelector({ value, onChange, label = "Fecha" }) {
  const currentYear = new Date().getFullYear();
  const [selectedYear, setSelectedYear] = useState(value?.year || "");
  const [selectedMonth, setSelectedMonth] = useState(value?.month || "");
  const [selectedDay, setSelectedDay] = useState(value?.day || "");
  const [daysInMonth, setDaysInMonth] = useState([]);

  useEffect(() => {
    if (selectedYear && selectedMonth) {
      const days = getDaysInMonth(selectedYear, selectedMonth);
      setDaysInMonth(Array.from({ length: days }, (_, i) => i + 1));
    } else {
      setDaysInMonth([]);
    }
  }, [selectedYear, selectedMonth]);

  useEffect(() => {
    onChange({
      year: selectedYear,
      month: selectedMonth,
      day: selectedDay
    });
  }, [selectedYear, selectedMonth, selectedDay]);

  return (
    <div className="row g-2 mb-2">
      <label>{label}</label>
      <div className="col">
        <select
          className="form-select"
          value={selectedYear}
          onChange={(e) => setSelectedYear(Number(e.target.value))}
        >
          <option value="">Año</option>
          {Array.from({ length: 100 }, (_, i) => currentYear - i).map((y) => (
            <option key={y} value={y}>{y}</option>
          ))}
        </select>
      </div>
      <div className="col">
        <select
          className="form-select"
          value={selectedMonth}
          onChange={(e) => setSelectedMonth(Number(e.target.value))}
        >
          <option value="">Mes</option>
          {Array.from({ length: 12 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {new Date(0, i).toLocaleString("es", { month: "long" })}
            </option>
          ))}
        </select>
      </div>
      <div className="col">
        <select
          className="form-select"
          value={selectedDay}
          onChange={(e) => setSelectedDay(Number(e.target.value))}
          disabled={!daysInMonth.length}
        >
          <option value="">Día</option>
          {daysInMonth.map((d) => (
            <option key={d} value={d}>{d}</option>
          ))}
        </select>
      </div>
    </div>
  );
}
