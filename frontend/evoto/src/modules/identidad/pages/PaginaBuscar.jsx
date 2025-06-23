import React from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../shared/components/table/Table";
import ExpandableRow from "../components/identidadExpandableRow/IdentidadExpandableRow";
import GenericFilterForm from "../../../shared/components/filterForm/FilterForm";
import { createPersonasFilterConfig } from "../config/personasFilterConfig";
import TimerRedirect from "../../../shared/components/timerRefresher/TimerRefresher";
import { useBuscarPersonas } from "../hooks/useBuscarPersonas";

const PaginaBuscar = () => {
  const navigate = useNavigate();
  const {
    personas,
    setPersonas,
    filtros,
    setFiltros,
    fetchPersonas,
    error,
  } = useBuscarPersonas();

  const fetchDomicilios = async (id) => {
    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/persona/${id}/detalles-domicilios`);
      if (!res.ok) throw new Error(`Error al obtener domicilios para ID ${id}`);
      return await res.json();
    } catch (err) {
      console.error(err);
      return [];
    }
  };

  const eliminarPersona = async (id) => {
    const confirmar = window.confirm("¿Estás seguro de que deseas eliminar esta persona?");
    if (!confirmar) return;

    try {
      const res = await fetch(`${import.meta.env.VITE_API_URL}/persona/${id}`, {
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
  };

  const editarPersona = (id) => {
    navigate(`editar/${id}`);
  };

  const headers = [
    "ID",
    "Nombre",
    "Apellido Paterno",
    "Apellido Materno",
    "Fecha de Nacimiento",
    "Fecha de Fin",
    "Domicilios",
    "Acciones"
  ];

  return (
    <div>
      <h1>Catálogo de Personas</h1>
      <TimerRedirect />
      {error && <p style={{ color: "red" }}>{error}</p>}

      <GenericFilterForm
        config={{
          ...createPersonasFilterConfig,
          onSearch: (vals) => {
            setFiltros(vals);
            fetchPersonas(vals);
          }
        }}
        values={filtros}
        setValues={setFiltros}
      />

      <Table
        headers={headers}
        data={personas}
        renderRow={(p) => (
          <ExpandableRow
            key={p.id}
            idPersona={p.id}
            rowData={[
              p.id,
              p.nombre,
              p.apellidoPaterno,
              p.apellidoMaterno,
              p.fechaDeNacimiento,
              p.fechaDeFin
            ]}
            fetchDomicilios={() => fetchDomicilios(p.id)}
            colSpan={headers.length}
            onEdit={editarPersona}
            onDelete={eliminarPersona}
          />
        )}
      />
    </div>
  );
};

export default PaginaBuscar;
