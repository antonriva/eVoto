// src/modules/identidad/hooks/usePersonasActions.js
import { useCallback } from "react";

export const usePersonasActions = (setPersonas) => {
  const apiBase = import.meta.env.VITE_API_URL;

  const fetchDomicilios = useCallback(async (id) => {
    try {
      const res = await fetch(`${apiBase}/persona/${id}/detalles-domicilios`);
      if (!res.ok) throw new Error(`No se pudieron obtener los domicilios para ID ${id}`);
      return await res.json();
    } catch (err) {
      console.error(err);
      return [];
    }
  }, [apiBase]);

  const eliminarPersona = useCallback(async (id) => {
    try {
      const res = await fetch(`${apiBase}/persona/${id}`, { method: "DELETE" });

      if (res.ok) {
        setPersonas(prev => prev.filter(p => p.id !== id));
        return { success: true };
      } else {
        const msg = await res.text();
        if (res.status === 404) return { success: false, error: `No existe la persona con ID ${id}.` };
        if (res.status === 409) return { success: false, error: `Cuidado: ${msg}` };
        return { success: false, error: `Alerta inesperada: ${msg}` };
      }
    } catch (err) {
      console.error(err);
      return { success: false, error: "No se pudo eliminar. Inténtalo nuevamente." };
    }
  }, [apiBase, setPersonas]);

  return { fetchDomicilios, eliminarPersona };
};
