import { useState, useEffect } from "react";

export function useBuscarPersonas(initialFilters = {}) {
  const [personas, setPersonas] = useState([]);
  const [filtros, setFiltros] = useState(initialFilters);
  const [error, setError] = useState("");

  const formatFilters = (filters) =>
    Object.fromEntries(Object.entries(filters).filter(([_, v]) => v !== ""));
  const cleanEmpty = (obj) =>
    Object.fromEntries(
      Object.entries(obj).filter(
        ([_, v]) => v !== null && v !== "" && v !== "null"
      )
    );
    
    const fetchPersonas = async (params = {}) => {
      try {
        // 💡 Clean filters before sending
        const cleanEmpty = (obj) =>
          Object.fromEntries(
            Object.entries(obj).filter(
              ([_, v]) => v !== null && v !== "" && v !== "null"
            )
          );
    
        const cleanedParams = cleanEmpty(params);
        const query = new URLSearchParams(cleanedParams).toString();
    
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
