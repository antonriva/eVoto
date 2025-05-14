import axios from "axios"

// Base URL for the backend
const BASE_URL = `${import.meta.env.VITE_API_URL}/persona`; 

// Axios instance with default configurations
const axiosInstance = axios.create({
  baseURL: BASE_URL,
  timeout: 5000, // Set a timeout of 5 seconds
});

// Function to fetch all personas
export const obtenerTodasLasPersonas = async () => {
  try {
    const response = await axiosInstance.get("/");
    return response.data;
  } catch (error) {
    console.error("Error al obtener todas las personas:", error.message);
    handleError(error);
  }
};

// Function to fetch personas with filters
export const buscarPersonasConFiltros = async (filtros) => {
  try {
    const query = new URLSearchParams(filtros).toString(); // Convert filters object to query string
    const response = await axiosInstance.get(`/buscar?${query}`);
    return response.data;
  } catch (error) {
    console.error("Error al buscar personas con filtros:", error.message);
    handleError(error);
  }
};

// Function to register a new persona
export const registrarPersona = async (nuevaPersona) => {
  try {
    const response = await axiosInstance.post("/", nuevaPersona);
    return response.data;
  } catch (error) {
    console.error("Error al registrar persona:", error.message);
    handleError(error);
  }
};

// Function to update a persona
export const actualizarPersona = async (id, personaActualizada) => {
  try {
    const response = await axiosInstance.put(`/${id}`, personaActualizada);
    return response.data;
  } catch (error) {
    console.error("Error al actualizar persona:", error.message);
    handleError(error);
  }
};

// Function to delete a persona
export const eliminarPersona = async (id) => {
  try {
    await axiosInstance.delete(`/${id}`);
  } catch (error) {
    console.error("Error al eliminar persona:", error.message);
    handleError(error);
  }
};

// Function to assign a domicilio to a persona
export const asignarDomicilioAPersona = async (personaId, domicilioId, fechaDeInicio) => {
  try {
    const response = await axiosInstance.post(
      `/${personaId}/domicilio/${domicilioId}`,
      null, // No body is needed
      { params: { fechaDeInicio } } // Send fechaDeInicio as query param
    );
    return response.data;
  } catch (error) {
    console.error("Error al asignar domicilio a persona:", error.message);
    handleError(error);
  }
};

// Error handler function
const handleError = (error) => {
  if (error.response) {
    // Backend responded with a status code outside 2xx range
    console.error("Backend Error:", error.response.status, error.response.data);
  } else if (error.request) {
    // Request made but no response received
    console.error("No response received from backend:", error.request);
  } else {
    // Something else happened during the request
    console.error("Error in setup or configuration:", error.message);
  }
  throw error; // Re-throw error to be handled by caller
};
