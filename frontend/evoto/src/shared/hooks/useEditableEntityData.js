// src/shared/hooks/useEditableEntityData.js
import { useState, useEffect } from "react";

export const useEditableEntityData = ({ endpoint, id, initialValues = {}, onAfterLoad }) => {
  const [formData, setFormData] = useState(initialValues);
  const [originalData, setOriginalData] = useState(initialValues); // ✅ Added
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const res = await fetch(`${import.meta.env.VITE_API_URL}/${endpoint}/${id}/editar`);
        if (!res.ok) throw new Error("No se pudieron obtener los datos.");
        const data = await res.json();

        setFormData({ ...initialValues, ...data });
        setOriginalData({ ...initialValues, ...data }); // ✅ Save for comparison

        if (onAfterLoad) onAfterLoad(data); // useful for fetching related data like municipios
      } catch (err) {
        setError(err.message || "Alerta inesperada.");
      } finally {
        setLoading(false);
      }
    };

    if (id) fetchData();
  }, [endpoint, id]);

  return { formData, setFormData, originalData, loading, error }; // ✅ Expose originalData
};
