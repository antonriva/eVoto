import { useState, useEffect } from "react";

export function useBuscarPersonas(initialFilters = {}) {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState(initialFilters);
  const [error, setError] = useState("");

  const formatFilters = (filters) => {
    const clean = {};
  
    const gran = filters.granularidadNacimiento || "completa";
  
    for (const [key, value] of Object.entries(filters)) {
      if (value === null || value === "" || value === "null") continue;
  
      if (key === "fechaNacimiento" && value instanceof Date && !isNaN(value)) {
        clean.anioNacimiento = value.getFullYear().toString();
  
        if (gran === "anio-mes" || gran === "completa") {
          clean.mesNacimiento = (value.getMonth() + 1).toString().padStart(2, "0");
        }
  
        if (gran === "completa") {
          clean.diaNacimiento = value.getDate().toString().padStart(2, "0");
        }
      } else if (key !== "granularidadNacimiento") {
        clean[key] = value;
      }
    }
  
    return clean;
  };
  

  const cleanEmpty = (obj) =>
    Object.fromEntries(
      Object.entries(obj).filter(
        ([_, v]) => v !== null && v !== "" && v !== "null"
      )
    );

    const fetchPersonas = async (params = {}) => {
      try {
        // 💡 Clean filters before sending

        const formattedParams = formatFilters(params);
        console.log("Formatted Filters:", formattedParams);
        const cleanedParams = cleanEmpty(formattedParams);
        const query = new URLSearchParams(cleanedParams).toString();

        console.log("🌐 Final URL:", `/api/personas?${query}`);
    
        const res = await fetch(`${import.meta.env.VITE_API_URL}/persona/buscar?${query}`);
        if (!res.ok) throw new Error("Error al cargar personas.");
    
        const data = await res.json();
    
        const formatted = data.map((p) => ({
          ...p,
          fechaDeNacimiento: p.fechaDeNacimiento || "---",
          fechaDeFin: p.fechaDeFin || "---",
        }));
    
        setPersonas(formatted);
        setError("");
      } catch (err) {
        console.error(err);
        setError("Error al cargar personas. Inténtalo de nuevo.");
      }
    };
    

  useEffect(() => {
    fetchPersonas();
  }, []);

  return {
    personas,
    setPersonas,
    filtros,
    setFiltros,
    fetchPersonas,
    error,
  };
}
