// src/modules/identidad/hooks/usePersonasActions.js
import { useCallback } from "react";

export const usePartidosActions = (setPartidos) => {
  const apiBase = import.meta.env.VITE_API_URL;


  const eliminarPartido = useCallback(async (id) => {
    try {
      const res = await fetch(`${apiBase}/partido/${id}/eliminar`, { method: "DELETE" });

      if (res.ok) {
        setPersonas(prev => prev.filter(p => p.id !== id));
        return { success: true, message: "Partido eliminado exitosamente." };
      } else {
        const msg = await res.text();
        if (res.status === 404) return { success: false, error: `No existe el partido con ID ${id}.` };
        if (res.status === 409) return { success: false, error: `Cuidado: ${msg}` };
        return { success: false, error: `Alerta inesperada: ${msg}` };
      }
    } catch (err) {
      console.error(err);
      return { success: false, error: "No se pudo eliminar. Inténtalo nuevamente." };
    }
  }, [apiBase, setPartidos]);

  const cargarLogo = useCallback((partidoId, fetchPartidos) => {
    return new Promise((resolve) => {
      const fileInput = document.createElement("input");
      fileInput.type = "file";
      fileInput.accept = "image/*"; // Only allow image files
  
      fileInput.onchange = async (event) => {
        const file = event.target.files[0];
        if (!file) return resolve({ success: false, error: "No se seleccionó ningún archivo." });
  
        // File size restriction: maximum 2 MB
        const maxFileSize = 2 * 1024 * 1024;
        if (file.size > maxFileSize) {
          return resolve({
            success: false,
            error: "El archivo es demasiado grande. El tamaño máximo permitido es 2 MB.",
          });
        }
  
        // Validate image dimensions
        const img = new Image();
        img.src = URL.createObjectURL(file);
  
        img.onload = async () => {
          const maxWidth = 500;
          const maxHeight = 500;
  
          if (img.width > maxWidth || img.height > maxHeight) {
            return resolve({
              success: false,
              error: `El logo excede las dimensiones permitidas (${maxWidth}x${maxHeight} píxeles).`,
            });
          }
  
          const formData = new FormData();
          formData.append("logoFile", file);
          formData.append("partidoId", partidoId);
          formData.append("tipoDeVisualId", 1);
          formData.append("recursoVigenteId", 1);
  
          try {
            const response = await fetch(`${apiBase}/visual/registrar`, {
              method: "POST",
              body: formData,
            });
  
            if (response.ok) {
              if (fetchPartidos) fetchPartidos();
              return resolve({ success: true, message: "Logo cargado exitosamente." });
            } else {
              const msg = await response.text();
  
              if (response.status === 400)
                return resolve({ success: false, error: "Datos inválidos. Verifica el formulario." });
  
              if (response.status === 409)
                return resolve({ success: false, error: `Conflicto: ${msg}` });
  
              return resolve({ success: false, error: `Error inesperado: ${msg}` });
            }
          } catch (err) {
            console.error("Error al cargar el logo:", err);
            return resolve({ success: false, error: "No se pudo cargar. Inténtalo nuevamente." });
          }
        };
      };
  
      fileInput.click();
    });
  }, [apiBase]);
  

  const actualizarPartido = useCallback(async ({ id, formData }) => {
    try {
      const res = await fetch(`${apiBase}/partido/${id}/editar`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (res.ok) {
        return { success: true, message: "Partido actualizado exitosamente." };
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
      console.error("Error al actualizar partido:", err);
      return { success: false, message: "No se pudo actualizar la información del partido. Inténtalo nuevamente." };
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

  return { eliminarPartido, actualizarPartido, registrarPersona, cargarLogo };
};
