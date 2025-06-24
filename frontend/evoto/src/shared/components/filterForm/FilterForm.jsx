// src/shared/components/GenericFilterForm.jsx
import React, { useState, useEffect } from "react";

const GenericFilterForm = ({ config, values, setValues }) => {
  const { fields = [], onSearch } = config;

  const [optionsMap, setOptionsMap] = useState(
    fields.reduce((acc, f) => {
      if (f.type === "select") acc[f.name] = f.options || [];
      return acc;
    }, {})
  );

  // Load initial options
  useEffect(() => {
    fields.forEach(f => {
      if (
        f.type === "select" &&
        !f.fetchOptionsOn &&
        typeof f.fetchOptions === "function"
      ) {
        f.fetchOptions().then(arr =>
          setOptionsMap(o => ({ ...o, [f.name]: arr }))
        );
      }
    });
  }, [fields]);

  const cleanEmpty = (obj) =>
    Object.fromEntries(
      Object.entries(obj).filter(
        ([_, v]) => v !== null && v !== "" && v !== "null"
      )
    );
  

  const normalizeSpanishName = (input) => {
    return input
      .toUpperCase()
      .normalize("NFD")                             // Normalize accented characters
      .replace(/[^A-ZÑ\s]/g, "")             // Allow only letters and spaces
      .replace(/\s+/g, " ")                        // Collapse multiple spaces
      .trimStart()                                 // Remove leading and trailing spaces
    };

  const handleChange = async (e) => {
    const { name, value } = e.target;

    let transformedValue;

    if (name === "id") {
      // Only digits for ID
      transformedValue = value.replace(/[^0-9]/g, "");
    } else if (name === "nombre") {
      // Normalize Spanish name for "nombre"
      transformedValue = normalizeSpanishName(value);
    } else {
      // Restrict other text inputs to a single word (no spaces)
      transformedValue = value.replace(/\s/g, "").toUpperCase(); // Remove spaces and convert to uppercase
    }

    setValues((v) => ({ ...v, [name]: transformedValue || null }));

    // Handle dynamic selects
    fields
      .filter(f => f.fetchOptionsOn === name && typeof f.fetchOptions === "function")
      .forEach(f => {
        f.fetchOptions(value).then(arr =>
          setOptionsMap(o => ({ ...o, [f.name]: arr }))
        );
      });
  };

  const handleSubmit = e => {
    e.preventDefault();
    const cleaned = Object.fromEntries(
      Object.entries(values).map(([k, v]) => [k, typeof v === "string" ? v.trim() : v])
    );
    const cleanedEmpty = cleanEmpty(cleaned);
    console.log("Submitted filters:", cleanedEmpty); // ✅ Add this line to log

    onSearch(cleanedEmpty);
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      {fields.map(f => (
        <div className="mb-2" key={f.name}>
          <label className="form-label">{f.label}</label>

          {f.type === "custom" ? (
            f.render({
              value: values[f.name] || null,
              onChange: (val) =>
                setValues((prev) => ({ ...prev, [f.name]: val })),
            })
          ) : f.type === "text" ? (
            <input
              type="text"
              name={f.name}
              className="form-control"
              value={values[f.name] || ""}
              onChange={handleChange}
              pattern={f.name === "id" ? "[0-9]*" : "[A-ZÑ\\s]*"} // Restrict input based on field name
              title={
                f.name === "id"
                  ? "Solo se permiten números."
                  : "Solo letras y espacios"
              }
            />
          ) : (
            <select
              name={f.name}
              className="form-select"
              value={values[f.name] || ""}
              onChange={handleChange}
              disabled={f.fetchOptionsOn && !values[f.fetchOptionsOn]}
            >
              <option value="">Seleccione {f.label}</option>
              {optionsMap[f.name]?.map(opt => (
                <option key={opt.id} value={opt.id}>
                  {opt.descripcion}
                </option>
              ))}
            </select>
          )}
        </div>
      ))}

      <button type="submit" className="btn btn-primary">
        Buscar
      </button>
    </form>
  );
};

export default GenericFilterForm;
