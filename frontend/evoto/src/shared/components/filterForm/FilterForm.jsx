// src/shared/components/GenericFilterForm.jsx
import React, { useState, useEffect } from "react";
import "../../../shared/styles/Buttons.css"; // Import your CSS styles

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

    const normalizeOthers = (input) => {
      return input
        .toUpperCase()
        .normalize("NFD")                             // Normalize accented characters
        .replace(/[^A-ZÑ\s]/g, "")             // Allow only letters and spaces
        .replace(/\s+/g, "")                        // Collapse multiple spaces
        .trimStart()                                 // Remove leading and trailing spaces
      };

  const handleChange = async (e) => {
    const { name, value } = e.target;
    
    const field = fields.find(f => f.name === name); // ✅ Find field config

    let transformedValue;
    if (field?.type === "select") {
      transformedValue = value;
    } else {
      const transformers = {
        id: (v) => v.replace(/[^0-9]/g, ""),
        nombre: normalizeSpanishName,
        default: normalizeOthers,
      };
      const transform = transformers[name] || transformers.default;
      transformedValue = transform(value);
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

  const visibleFields = fields.filter(f => f.type !== "hidden");

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      {visibleFields.map(f => (
        <div className="mb-2" key={f.name}>
          <label className="form-label">{f.label}</label>

          {f.type === "custom" ? (
            f.render({
              value: values[f.name] || null,
              onChange: (val) =>
                setValues((prev) => ({ ...prev, [f.name]: val })),
              setValues,
              values
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
      <div className="mb-3">
        <small className="form-text text-muted">
          Los filtros son opcionales. Puedes dejar los campos vacíos.
        </small>
      </div>
      <div className="mb-3 text-center">
        <button type="submit" className="btn btn-vino">
          Buscar
        </button>
      </div>

    </form>
  );
};

export default GenericFilterForm;
