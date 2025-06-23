// src/shared/components/loading/Spinner.jsx
import React from "react";

const Spinner = ({
  message = "Cargando...",
  centered = true,
  size = "md", // sm | md | lg
  type = "border", // or "grow"
  className = "",
}) => {
  const sizeClasses = {
    sm: "spinner-border-sm",
    md: "",
    lg: "spinner-border-lg",
  };

  const spinnerClass = `spinner-${type} ${sizeClasses[size]} text-primary ${className}`;

  return (
    <div className={centered ? "d-flex flex-column align-items-center py-3" : "py-2"}>
      <div className={spinnerClass} role="status">
        <span className="visually-hidden">{message}</span>
      </div>
      <div className="mt-2">{message}</div>
    </div>
  );
};

export default Spinner;
