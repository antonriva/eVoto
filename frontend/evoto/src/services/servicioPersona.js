import axios from "axios";

// URL base para la API del backend
const URL_API = "http://localhost:8080/api/personas";

/**
 * Registra una nueva persona en el backend.
 * @param {Object} datosPersona - Datos de la persona a registrar.
 * @returns {Promise<Object>} - Respuesta del servidor con la persona registrada.
 */
export const registrarPersona = async (datosPersona) => {
  try {
    const respuesta = await axios.post(URL_API, datosPersona);
    return respuesta.data;
  } catch (error) {
    console.error("Error al registrar persona:", error);
    throw error;
  }
};

/**
 * Obtiene todas las personas registradas.
 * @returns {Promise<Array>} - Lista de personas registradas.
 */
export const obtenerTodasLasPersonas = async () => {
  try {
    const respuesta = await axios.get(URL_API);
    return respuesta.data;
  } catch (error) {
    console.error("Error al obtener las personas:", error);
    throw error;
  }
};

/**
 * Obtiene una persona específica por su ID.
 * @param {number} id - ID de la persona.
 * @returns {Promise<Object>} - Datos de la persona encontrada.
 */
export const obtenerPersonaPorId = async (id) => {
  try {
    const respuesta = await axios.get(`${URL_API}/${id}`);
    return respuesta.data;
  } catch (error) {
    console.error(`Error al obtener la persona con ID ${id}:`, error);
    throw error;
  }
};

/**
 * Actualiza los datos de una persona específica.
 * @param {number} id - ID de la persona a actualizar.
 * @param {Object} datosPersona - Datos actualizados de la persona.
 * @returns {Promise<Object>} - Respuesta del servidor con los datos actualizados.
 */
export const actualizarPersona = async (id, datosPersona) => {
  try {
    const respuesta = await axios.put(`${URL_API}/${id}`, datosPersona);
    return respuesta.data;
  } catch (error) {
    console.error(`Error al actualizar la persona con ID ${id}:`, error);
    throw error;
  }
};

/**
 * Elimina una persona del registro.
 * @param {number} id - ID de la persona a eliminar.
 * @returns {Promise<void>} - Respuesta del servidor indicando éxito o error.
 */
export const eliminarPersona = async (id) => {
  try {
    await axios.delete(`${URL_API}/${id}`);
  } catch (error) {
    console.error(`Error al eliminar la persona con ID ${id}:`, error);
    throw error;
  }
};
