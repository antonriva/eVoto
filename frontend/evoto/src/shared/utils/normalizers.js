// src/shared/utils/normalizers.js

export const cleanEmpty = (obj) =>
    Object.fromEntries(
      Object.entries(obj).filter(
        ([_, v]) => v !== null && v !== "" && v !== "null"
      )
    );
  
  export const normalizeSpanishName = (input) =>
    input
      .toUpperCase()
      .normalize("NFD")
      .replace(/[^A-ZÑ\s]/g, "") // Letras y espacios
      .replace(/\s+/g, " ")
      .trimStart();
  
  export const normalizeOthers = (input) =>
    input
      .toUpperCase()
      .normalize("NFD")
      .replace(/[^A-ZÑ\s]/g, "")
      .replace(/\s+/g, "")
      .trimStart();
  
  export const normalizeId = (input) => input.replace(/[^0-9]/g, "");
  
  export const transformers = {
    id: normalizeId,
    nombre: normalizeSpanishName,
    denominacion: normalizeSpanishName,
    default: normalizeOthers,
  };
  
  export const getTransformedValue = (name, value, type = "text") => {
    if (type === "select" || type === "date") return value; // ✅ Don't transform selects or dates
    const transform = transformers[name] || transformers.default;
    return transform(value);
  };
  
  export const trimStringValues = (obj) =>
    Object.fromEntries(
      Object.entries(obj).map(([k, v]) => [k, typeof v === "string" ? v.trim() : v])
    );
  