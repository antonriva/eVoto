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

  const handleChange = async e => {
    const { name, value } = e.target;
    setValues(v => ({ ...v, [name]: value || null }));

    // Fetch dependent select options
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
    onSearch(values);
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
        </div>
      ))}

      <button type="submit" className="btn btn-primary">
        Buscar
      </button>
    </form>
  );
};

export default GenericFilterForm;
