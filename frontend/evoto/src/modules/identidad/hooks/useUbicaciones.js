// src/modules/personas/hooks/useUbicaciones.js
import { useState, useEffect } from "react";

export const useUbicaciones = () => {
  const [entidades, setEntidades] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [localidades, setLocalidades] = useState([]);
  const [colonias, setColonias] = useState([]);
  const [tiposDeDomicilio, setTiposDeDomicilio] = useState([]);

  // Fetch entidades and tipos on mount
  useEffect(() => {
    fetchEntidades();
    fetchTiposDeDomicilio();
  }, []);

  const fetchEntidades = async () => {
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/entidad-federativa`);
      setEntidades(await res.json());
    } catch (err) {
      console.error("Error al cargar entidades federativas:", err);
    }
  };

  const fetchMunicipios = async (entidadId) => {
    try {
      if (!entidadId) return [];
      const res = await fetch(`${import.meta.env.VITE_API_URL}/municipio/entidad/${entidadId}`);
      const data = await res.json();
      setMunicipios(data);
      setLocalidades([]); // reset dependents
      setColonias([]);
      return data;
    } catch (err) {
      console.error("Error al cargar municipios:", err);
      return [];
    }
  };

  const fetchLocalidades = async (municipioId) => {
    try {
      if (!municipioId) return [];
      const res = await fetch(`${import.meta.env.VITE_API_URL}/localidad/municipio/${municipioId}`);
      const data = await res.json();
      setLocalidades(data);
      return data;
    } catch (err) {
      console.error("Error al cargar localidades:", err);
      return [];
    }
  };

  const fetchColonias = async (municipioId) => {
    try {
      if (!municipioId) return [];
      const res = await fetch(`${import.meta.env.VITE_API_URL}/colonia/municipio/${municipioId}`);
      const data = await res.json();
      setColonias(data);
      return data;
    } catch (err) {
      console.error("Error al cargar colonias:", err);
      return [];
    }
  };

  const fetchTiposDeDomicilio = async () => {
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/tipoDeDomicilio`);
      setTiposDeDomicilio(await res.json());
    } catch (err) {
      console.error("Error al cargar tipos de domicilio:", err);
    }
  };

  return {
    entidades,
    municipios,
    localidades,
    colonias,
    tiposDeDomicilio,
    fetchMunicipios,
    fetchLocalidades,
    fetchColonias
  };
};
