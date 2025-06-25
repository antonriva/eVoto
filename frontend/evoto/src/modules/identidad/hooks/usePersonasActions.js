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
        return { success: true, message: "Persona eliminada exitosamente." };
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

  const actualizarPersona = useCallback(async ({ id, formData }) => {
    try {
      const res = await fetch(`${apiBase}/persona/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (res.ok) {
        return { success: true, message: "Persona actualizada exitosamente." };
      } else {
        const msg = await res.text();
        if (res.status === 400) {
          return { success: false, message: `Solicitud incorrecta: ${msg}` };
        }
        if (res.status === 422) {
          return { success: false, message: `Problema de validación: ${msg}` };
        }
        if (res.status === 409) {
          return { success: false, message: `Cuidado: ${msg}` };
        }
        return { success: false, message: `Alerta inesperada: ${msg}` };
      }
    } catch (err) {
      console.error("Error al actualizar persona:", err);
      return { success: false, message: "No se pudo actualizar la información de la persona. Inténtalo nuevamente." };
    }
  }, [apiBase]);

  const registrarPersona = useCallback(async ({ formData, confirmar = false }) => {
    try {
      const url = `${apiBase}/persona/registrar${confirmar ? "?confirmar=true" : ""}`;

      const res = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (res.ok) {
        return { success: true, message: "Persona registrada exitosamente." };
      }

      const msg = await res.text();

      if (res.status === 409) {
        return { success: false, conflict: true, message: msg };
      }

      if (res.status === 400) {
        return { success: false, message: `Solicitud incorrecta: ${msg}` };
      }

      if (res.status === 422) {
        return { success: false, message: `Error de validación: ${msg}` };
      }

      return { success: false, message: `Error inesperado: ${msg}` };
    } catch (err) {
      console.error("Error al registrar persona:", err);
      return { success: false, message: "No se pudo registrar a la persona. Inténtalo nuevamente." };
    }
  }, [apiBase]);

  return { fetchDomicilios, eliminarPersona, actualizarPersona, registrarPersona };
};
