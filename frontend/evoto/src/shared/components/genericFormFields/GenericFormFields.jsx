// src/shared/components/form/GenericFormFields.jsx
import React from "react";
import { getTransformedValue } from "../../utils/normalizers"; // Adjust path as needed

const GenericFormFields = ({ config, values, onChange }) => {
  const handleChange = (e) => {
    const { name, value } = e.target;
    const field = config.find(f => f.name === name);
    const transformedValue = getTransformedValue(name, value, field?.type);

    onChange({ target: { name, value: transformedValue } }); // Bubble up
  };
  const today = new Date().toLocaleDateString("en-CA"); // → 'YYYY-MM-DD' in local time
  
  return (
    <>
      {config.map((field) => {
        const {
          name,
          label,
          type = "text",
          required,
          pattern,
          title,
          options = [],
          disabled = false,
          min,
          max,
        } = field;

       
        let dynamicMin = min;
        let dynamicMax = max;

        // ✅ Restrict fechaDeFin: cannot be before fechaDeNacimiento or after today
        if (type === "date") {
          if (name === "fechaDeNacimiento") {
            const oneHundredYearsAgo = new Date();
            oneHundredYearsAgo.setFullYear(oneHundredYearsAgo.getFullYear() - 100);
            dynamicMin = oneHundredYearsAgo.toISOString().split("T")[0];
            dynamicMax = today;
          }

          if (name === "fechaDeFin") {
            const startDate = values["fechaDeNacimiento"] || values["fechaDeInicio"];
            if (startDate) dynamicMin = startDate;
            dynamicMax = today;
          }

          if (name === "fechaDeInicio") {
            dynamicMin = today;
            dynamicMax = today;
          }
        }

  
        const commonProps = {
          name,
          value: values[name] || "",
          onChange: handleChange, // 👈 use transformed handler
          required,
          pattern,
          title,
          disabled,
          className: "form-control",
        };
  
        return (
          <div className="mb-3" key={name}>
            <label htmlFor={name} className="form-label">
              {label}
            </label>
  
            {type === "select" ? (
              <select {...commonProps}>
                <option value="">Seleccione una opción</option>
                {options.map((opt) => (
                  <option key={opt.value} value={opt.value}>
                    {opt.label}
                  </option>
                ))}
              </select>
            ) : (
              <input
                {...commonProps}
                type={type}
                min={type === "date" ? dynamicMin : min}
                max={type === "date" ? dynamicMax : max}
              />
            )}
          </div>
        );
      })}
    </>
  );
  
};

export default GenericFormFields;
