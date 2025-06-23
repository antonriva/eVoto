// src/modules/identidad/hooks/usePersonasActions.js
import { useCallback } from "react";

export const usePersonasActions = (setPersonas) => {
  const apiBase = import.meta.env.VITE_API_URL;

  const fetchDomicilios = useCallback(async (id) => {
    try {
      const res = await fetch(`${apiBase}/persona/${id}/detalles-domicilios`);
      if (!res.ok) throw new Error(`Error al obtener domicilios para ID ${id}`);
      return await res.json();
    } catch (err) {
      console.error(err);
      return [];
    }
  }, [apiBase]);

  const eliminarPersona = useCallback(async (id) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar esta persona?");
    if (!confirmar) return;

    try {
      const res = await fetch(`${apiBase}/persona/${id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        alert("Persona eliminada correctamente.");
        setPersonas(prev => prev.filter(p => p.id !== id));
      } else {
        const msg = await res.text();
        if (res.status === 404) alert(`No existe la persona con ID ${id}.`);
        else if (res.status === 409) alert(`Error: ${msg}`);
        else alert(`Error inesperado: ${msg}`);
      }
    } catch (err) {
      console.error(err);
      alert("Error al eliminar. Inténtalo nuevamente.");
    }
  }, [apiBase, setPersonas]);

  return { fetchDomicilios, eliminarPersona };
};
