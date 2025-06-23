import { useState } from "react";

const API_URL = import.meta.env.VITE_API_URL;

export const useBuscarPartidos = () => {
  const [partidos, setPartidos] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // Fetch partidos con o sin filtros
  const fetchPartidos = async (params = {}) => {
    try {
      setLoading(true);

      console.log("Original Params:", params); // Log original params
      const validParams = Object.fromEntries(
        Object.entries(params).filter(([key, value]) => value !== undefined && value !== null && value !== "")
      );
      console.log("Valid Params:", validParams); // Log filtered params

      const query = new URLSearchParams(validParams).toString();
      console.log("Query String:", query); // Log query string

      const response = await fetch(`${API_URL}/partido/buscar?${query}`);
      console.log("Response Status:", response.status); // Log response status

      if (!response.ok) {
        throw new Error("Error al cargar partidos.");
      }

      const data = await response.json();
      console.log("Data received from server:", data); // Log server data
      setPartidos(data);
      setError("");
    } catch (err) {
      console.error("Error al cargar partidos:", err);
      setError("Error al cargar partidos. Por favor, inténtalo de nuevo.");
    } finally {
      setLoading(false);
    }
  };

  // Eliminar partido
  const eliminarPartido = async (id) => {
    try {
      const response = await fetch(`${API_URL}/partido/${id}/eliminar`, {
        method: "DELETE",
      });

      if (response.ok) {
        setPartidos((prev) => prev.filter((p) => p.id !== id));
        return { success: true };
      } else {
        const message = await response.text();
        return {
          success: false,
          status: response.status,
          message,
        };
      }
    } catch (err) {
      console.error(`Error al eliminar el partido con ID ${id}:`, err);
      return {
        success: false,
        status: 500,
        message: "Error inesperado al eliminar el partido.",
      };
    }
  };

  // Subir logo con validaciones
  const cargarLogo = async (file, partidoId) => {
    const maxSize = 2 * 1024 * 1024; // 2MB
    const maxWidth = 500;
    const maxHeight = 500;

    if (!file) return { success: false, message: "No se seleccionó ningún archivo." };

    if (file.size > maxSize) {
      return { success: false, message: "El archivo supera los 2MB permitidos." };
    }

    const image = new Image();
    const fileUrl = URL.createObjectURL(file);

    return new Promise((resolve) => {
      image.onload = async () => {
        if (image.width > maxWidth || image.height > maxHeight) {
          resolve({
            success: false,
            message: `Las dimensiones máximas permitidas son ${maxWidth}x${maxHeight}px.`,
          });
          return;
        }

        const formData = new FormData();
        formData.append("logoFile", file);
        formData.append("partidoId", partidoId);
        formData.append("tipoDeVisualId", 1);
        formData.append("recursoVigenteId", 1);

        try {
          setLoading(true);
          const response = await fetch(`${API_URL}/visual/registrar`, {
            method: "POST",
            body: formData,
          });

          if (!response.ok) {
            const errorText = await response.text();
            resolve({ success: false, message: errorText || "Error al cargar el logo." });
            return;
          }

          await fetchPartidos(); // refresh list
          resolve({ success: true, message: "Logo cargado exitosamente." });
        } catch (err) {
          console.error("Error al subir el logo:", err);
          resolve({ success: false, message: "Error inesperado al subir el logo." });
        } finally {
          setLoading(false);
        }
      };

      image.onerror = () => {
        resolve({ success: false, message: "El archivo no es una imagen válida." });
      };

      image.src = fileUrl;
    });
  };

  return {
    partidos,
    error,
    loading,
    fetchPartidos,
    eliminarPartido,
    cargarLogo,
  };
};
