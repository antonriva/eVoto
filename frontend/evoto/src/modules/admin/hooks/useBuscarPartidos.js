import { useState, useEffect } from "react";

export function useBuscarPartidos(initialFilters = {}) {
  const [partidos, setPartidos] = useState([]);
  const [filtros, setFiltros] = useState(initialFilters);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false); // ✅ Add this

  const formatFilters = (filters) => {
    const clean = {};
    //CHANGE
    const gran = filters.granularidadInicio || "completa";
  
    for (const [key, value] of Object.entries(filters)) {
      if (value === null || value === "" || value === "null") continue;
  
      if (key === "fechaInicio" && value instanceof Date && !isNaN(value)) {
        clean.anioInicio = value.getFullYear().toString();
  
        if (gran === "anio-mes" || gran === "completa") {
          clean.mesInicio = (value.getMonth() + 1).toString().padStart(2, "0");
        }
  
        if (gran === "completa") {
          clean.diaInicio = value.getDate().toString().padStart(2, "0");
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

    const fetchPartidos = async (params = {}) => {
      setIsLoading(true); // ✅ Start loading
      try {
        // 💡 Clean filters before sending

        const formattedParams = formatFilters(params);
        console.log("Formatted Filters:", formattedParams);
        const cleanedParams = cleanEmpty(formattedParams);
        const query = new URLSearchParams(cleanedParams).toString();

        console.log("🌐 Final URL:", `/api/partido/buscar?${query}`);
    
        const res = await fetch(`${import.meta.env.VITE_API_URL}/partido/buscar?${query}`);
        if (!res.ok) throw new Error("No se pudieron los partidos.");
    
        const data = await res.json();
    
        const formatted = data.map((p) => ({
          //CHANGE
          ...p,
          fechaDeNacimiento: p.fechaDeNacimiento || "---",
          fechaDeFin: p.fechaDeFin || "---",
        }));
    
        setPartidos(formatted);
        setError("");
      } catch (err) {
        console.error(err);
        setError("No se pudieron cargar los partidos. Inténtalo de nuevo.");
      } finally {
        setIsLoading(false); // ✅ Stop loading
      }
    };
    

  useEffect(() => {
    fetchPartidos();
  }, []);

  return {
    partidos,
    setPartidos,
    filtros,
    setFiltros,
    fetchPartidos,
    error,
    isLoading, // ✅ Return loading state
  };
}
