// src/shared/components/GenericFilterForm.jsx
import React, { useState, useEffect } from "react";

const GenericFilterForm = ({ config, values, setValues }) => {
  const { fields = [], onSearch } = config; // Add a fallback for fields

  // Local copy of options for dependent selects
  const [optionsMap, setOptionsMap] = useState(
    fields.reduce((acc, f) => {
      if (f.type === "select") acc[f.name] = f.options || [];
      return acc;
    }, {})
  );

  // Load initial options for selects without dependencies
  useEffect(() => {
    fields.forEach(f => {
      if (f.type === "select" && !f.fetchOptionsOn) {
        f.fetchOptions().then(arr =>
          setOptionsMap(o => ({ ...o, [f.name]: arr }))
        );
      }
    });
  }, [fields]);

  const handleChange = async e => {
    const { name, value } = e.target;
    setValues(v => ({ ...v, [name]: value || null }));

    // if other fields depend on this one, reload their options
    fields
      .filter(f => f.fetchOptionsOn === name)
      .forEach(f => {
        f.fetchOptions(value).then(arr =>
          setOptionsMap(o => ({ ...o, [f.name]: arr }))
        );
      });
  };

  const handleSubmit = e => {
    e.preventDefault();
    onSearch(values);
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      {fields.map(f => (
        <div className="mb-2" key={f.name}>
          <label className="form-label">{f.label}</label>
          {f.type === "text" ? (
            <input
              type="text"
              name={f.name}
              className="form-control"
              value={values[f.name] || ""}
              onChange={handleChange}
              pattern={f.validation?.pattern?.source}
              title={f.validation?.title}
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
          {console.log("Options Map for field:", f.name, optionsMap[f.name])}{" "}
          {/* Log options map */}
          {console.log("Values passed to FilterForm:", values)}{" "}
          {/* Log values */}
        </div>
      ))}

      <button type="submit" className="btn btn-primary">
        Buscar
      </button>
    </form>
  );
};

export default GenericFilterForm;
