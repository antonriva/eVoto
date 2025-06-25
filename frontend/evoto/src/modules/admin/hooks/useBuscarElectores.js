import { useState, useEffect } from "react";

export function useBuscarElectores(initialFilters = {}) {
  const [electores, setElectores] = useState([]);
  const [filtros, setFiltros] = useState(initialFilters);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false); // ✅ Add this

  const formatFilters = (filters) => {
    const clean = {};
  
    const gran = filters.granularidadInicio || "completa";
  
    for (const [key, value] of Object.entries(filters)) {
      if (value === null || value === "" || value === "null") continue;
  
      if (key === "fechaInicio" && value instanceof Date && !isNaN(value)) {
        clean.anioNacimiento = value.getFullYear().toString();
  
        if (gran === "anio-mes" || gran === "completa") {
          clean.mesNacimiento = (value.getMonth() + 1).toString().padStart(2, "0");
        }
  
        if (gran === "completa") {
          clean.diaNacimiento = value.getDate().toString().padStart(2, "0");
        }
      } else if (key !== "granularidadInicio") {
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

    const fetchElectores = async (params = {}) => {
      setIsLoading(true); // ✅ Start loading
      try {
        // 💡 Clean filters before sending

        const formattedParams = formatFilters(params);
        console.log("Formatted Filters:", formattedParams);
        const cleanedParams = cleanEmpty(formattedParams);
        const query = new URLSearchParams(cleanedParams).toString();

        console.log("🌐 Final URL:", `/api/elector/buscar?${query}`);
    
        const res = await fetch(`${import.meta.env.VITE_API_URL}/elector/buscar?${query}`);
        if (!res.ok) throw new Error("No se pudieron cargar los electores.");
    
        const data = await res.json();
    
        const formatted = data.map((p) => ({
          ...p,
          fechaDeNacimiento: p.fechaDeNacimiento || "---",
          fechaDeFin: p.fechaDeFin || "---",
        }));
    
        setElectores(formatted);
        setError("");
      } catch (err) {
        console.error(err);
        setError("No se pudieron cargar los electores. Inténtalo de nuevo.");
      } finally {
        setIsLoading(false); // ✅ Stop loading
      }
    };
    

  useEffect(() => {
    fetchElectores();
  }, []);

  return {
    electores,
    setElectores,
    filtros,
    setFiltros,
    fetchElectores,
    error,
    isLoading, // ✅ Return loading state
  };
}
