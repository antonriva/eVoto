import React from "react";
import { useNavigate } from "react-router-dom";
import Table from "../../../../shared/components/table/Table"
import { useBuscarPartidos } from "../../hooks/useBuscarPartidos";
import { usePartidosActions } from "../../hooks/usePartidosActions";
import createPartidoFilterConfig from "../../config/partidoFilterConfig"
import { useToast } from "../../../../shared/context/ToastContext";
import { useConfirmDialog } from "../../../../shared/hooks/useConfirmDialog";
import { partidoTableHeaders } from "../../config/partidoTableConfig";
import ConfirmModal from "../../../../shared/components/confirmModal/ConfirmModal";
import GenericFilterForm from "../../../../shared/components/filterForm/FilterForm";


const BuscarPartido = () => {

  // /THIS IS FOR FETCH 

  // const [partidos, setPartidos] = useState([]);
  // const [error, setError] = useState("");
  // const [loading, setLoading] = useState(false); 
  // const [filtros, setFiltros] = useState({
  //   id: "",
  //   denominacion: "",
  //   siglas: "",
  //   anioInicio: "",
  //   mesInicio: "",
  //   diaInicio: "",
  //   anioFin: "",
  //   mesFin: "",
  //   diaFin: "",
  // });

    // Fetch Partidos con filtros
  // const fetchPartidos = async (params = {}) => {
  //   try {
  //     const query = new URLSearchParams(params).toString();
  //     const response = await fetch(`${import.meta.env.VITE_API_URL}/partido/buscar?${query}`);
  //     if (!response.ok) {
  //       throw new Error("Error al cargar partidos.");
  //     }
  //     const data = await response.json();
  //     setError(""); // Limpia el mensaje de error si la carga es exitosa
  //     setPartidos(data);
  //   } catch (error) {
  //     console.error("Error al cargar partidos:", error);
  //     setError("Error al cargar partidos. Por favor, inténtalo de nuevo."); // Actualiza el mensaje
  //   }
  // };
    // useEffect(() => {
  //   fetchPartidos();
  // }, []);

  const navigate = useNavigate();

    const {
      partidos,
      setPartidos,
      filtros,
      setFiltros,
      fetchPartidos,
      isLoading,
      error,
    } = useBuscarPartidos();

    const {eliminarPartido, cargarLogo} = usePartidosActions(setPartidos);

    // Cargar los filtros
    const handleSearch = (partialFilters) => {
      const merged = { ...filtros, ...partialFilters };
      setFiltros(merged);
      fetchPartidos(merged);
    };
     
    const partidoFilterConfig = createPartidoFilterConfig({ onSearch: handleSearch });

    const { showToast } = useToast();
    const confirmDelete = useConfirmDialog();
    
    const handleDeleteConfirm = async () => {
      confirmDelete.setLoading(true);
      try {
        const result = await eliminarPartido(confirmDelete.payload);
    
        if (result.success) {
          showToast({
            title: "Éxito",
            body: "Partido eliminado correctamente",
            variant: "success",
          });
          confirmDelete.close();
        } else {
          showToast({
            title: "Alerta",
            body: result.error || "No se pudo eliminar",
            variant: "danger",
          });
        }
      } catch (err) {
        showToast({
          title: "Alerta inesperada",
          body: err.message || "Alerta inesperada",
          variant: "danger",
        });
      } finally {
        confirmDelete.setLoading(false);
      }
    };
      


    //PERSONAS ACTIONS


// Eliminar Partido
// const eliminarPartido = async (id) => {
//   const confirmar = window.confirm("¿Estás seguro de que deseas eliminar este partido?");
//   if (confirmar) {
//     try {
//       const response = await fetch(`${import.meta.env.VITE_API_URL}/partido/${id}/eliminar`, {
//         method: "DELETE",
//       });

//       if (response.ok) {
//         alert("Partido eliminado exitosamente.");
//         setPartidos((prev) => prev.filter((partido) => partido.id !== id));
//       } else {
//         const errorMessage = await response.text();
//         if (response.status === 404) {
//           alert(`Error: El partido con ID ${id} no existe.`);
//         } else if (response.status === 409) {
//           alert(`Error: ${errorMessage}`);
//         } else {
//           alert(`Error inesperado al eliminar el partido: ${errorMessage}`);
//         }
//       }
//     } catch (error) {
//       console.error(`Error al eliminar el partido con ID ${id}:`, error);
//       alert("Error inesperado al eliminar el partido. Por favor, inténtelo nuevamente.");
//     }
//   }
// };

// const handleRegresar = () => {
//   navigate(-1); // Regresa al menú anterior
// };

  // Editar Partido
  const editarPartido = (id) => {
    navigate(`editar/${id}`);
  };
  //This keeps for now
  // const cargarLogo = async (partidoId) => {
  //   const fileInput = document.createElement("input");
  //   fileInput.type = "file";
  //   fileInput.accept = "image/*"; // Solo permitir imágenes
  
  //   fileInput.onchange = async (event) => {
  //     const file = event.target.files[0];
  //     if (!file) return;
  
  //     // Restricción de peso: máximo 2 MB
  //     const maxFileSize = 2 * 1024 * 1024; // 2 MB en bytes
  //     if (file.size > maxFileSize) {
  //       alert("El archivo es demasiado grande. El tamaño máximo permitido es 2 MB.");
  //       return;
  //     }
  
  //     // Validación de dimensiones
  //     const img = new Image();
  //     img.src = URL.createObjectURL(file);
  
  //     img.onload = async () => {
  //       const maxWidth = 500; // Máximo ancho en píxeles
  //       const maxHeight = 500; // Máximo alto en píxeles
  
  //       if (img.width > maxWidth || img.height > maxHeight) {
  //         alert(`El logo excede las dimensiones permitidas (${maxWidth}x${maxHeight} píxeles).`);
  //         return;
  //       }
  
  //       // Enviar el archivo si pasa todas las validaciones
  //       const formData = new FormData();
  //       formData.append("logoFile", file);
  //       formData.append("partidoId", partidoId);
  //       formData.append("tipoDeVisualId", 1); // Siempre tipoDeVisualId = 1
  //       formData.append("recursoVigenteId", 1); // Siempre recursoVigenteId = 1
  
  //       try {
  //         setLoading(true);
  //         setError("");
  //         const response = await fetch(`${import.meta.env.VITE_API_URL}/visual/registrar`, {
  //           method: "POST",
  //           body: formData,
  //         });
  
  //         if (!response.ok) {
  //           const errorMessage = await response.text();
  //           throw new Error(errorMessage || "Error al cargar el logo.");
  //         }
  
  //         alert("Logo cargado exitosamente.");
  //         fetchPartidos(); // Actualizar la lista de partidos
  //       } catch (error) {
  //         console.error("Error al cargar el logo:", error);
  //         setError("No se pudo cargar el logo. Intente nuevamente.");
  //       } finally {
  //         setLoading(false);
  //       }
  //     };
  //   };
  
  //   fileInput.click();
  // };



  // Manejo de cambio en los filtros
  // const handleChange = (e) => {
  //   const { name, value } = e.target;
  //   const newValue = value === "" ? null : value; // Convertir cadenas vacías a null
  //   setFiltros((prevFiltros) => ({
  //     ...prevFiltros,
  //     [name]: value,
  //   }));
  // };

  // // Encabezados de la tabla
  // const headers = [
  //   "ID",
  //   "Denominación",
  //   "Siglas",
  //   "Fecha de Inicio",
  //   "Fecha de Fin",
  //   "Logo",
  //   "Acciones",
  // ];

  return (
    <div>
      <h1>Buscar Partidos</h1>
      <GenericFilterForm
        config={partidoFilterConfig}
        values={filtros}
        setValues={setFiltros}
      />
      {/* {error && <p style={{ color: "red" }}>{error}</p>} */}
      {/* <button onClick={handleRegresar} style={{ marginBottom: "20px" }}>
        Regresar
      </button> */}

      {/* Formulario de filtros */}
      {/* <form
        onSubmit={(e) => {
          e.preventDefault();
          fetchPartidos(filtros);
        }}
      >
        <div>
          <label>ID:</label>
          <input
            type="text"
            name="id"
            value={filtros.id || ""}
            onChange={handleChange}
          pattern="^\d*$" // Solo números
          title="El ID solo puede contener números."
          />
        </div>
        <div>
          <label>Denominación:</label>
          <input
            type="text"
            name="denominacion"
            value={filtros.denominacion || ""}
            onChange={handleChange}
            pattern="^[a-zA-Z]+( [a-zA-Z]+)*$" // Letras mayúsculas y minúsculas, con espacios intermedios
            title="La denominación debe contener solo letras (mayúsculas o minúsculas) y espacios intermedios, sin números ni caracteres especiales."
          />
        </div>
        <div>
          <label>Siglas:</label>
          <input
            type="text"
            name="siglas"
            value={filtros.siglas}
            onChange={handleChange || ""}
            pattern="^[a-zA-Z]*$" // Letras mayúsculas y minúsculas, con espacios intermedios
            title="La denominación debe contener solo letras (mayúsculas o minúsculas) y espacios intermedios, sin números ni caracteres especiales."
          />
        </div>
        <div>
        <label>Año de Inicio:</label>
        <select
          name="anioInicio"
          value={filtros.anioInicio || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 100 }, (_, i) => {
            const year = new Date().getFullYear() - i;
            return (
              <option key={year} value={year}>
                {year}
              </option>
            );
          })}
        </select>
      </div>
      <div>
        <label>Mes de Inicio:</label>
        <select
          name="mesInicio"
          value={filtros.mesInicio || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 12 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {new Date(0, i).toLocaleString("es", { month: "long" })}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Día de Inicio:</label>
        <select
          name="diaInicio"
          value={filtros.diaInicio || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 31 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {i + 1}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Año de Fin:</label>
        <select
          name="anioFin"
          value={filtros.anioFin || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 100 }, (_, i) => {
            const year = new Date().getFullYear() - i;
            return (
              <option key={year} value={year}>
                {year}
              </option>
            );
          })}
        </select>
      </div>
      <div>
        <label>Mes de Fin:</label>
        <select
          name="mesFin"
          value={filtros.mesFin || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 12 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {new Date(0, i).toLocaleString("es", { month: "long" })}
            </option>
          ))}
        </select>
      </div>
        <div>
        <label>Día de Fin:</label>
        <select
          name="diaFin"
          value={filtros.diaFin || ""}
          onChange={handleChange}
        >
          <option value="">Seleccione</option>
          {Array.from({ length: 31 }, (_, i) => (
            <option key={i + 1} value={i + 1}>
              {i + 1}
            </option>
          ))}
        </select>
      </div>
        <button type="submit">Buscar</button>
      </form> */}

      {/* Tabla de resultados */}
      <Table
        headers={partidoTableHeaders}
        data={partidos}
        isLoading={isLoading}
        error={error} 
        renderRow={(partido) => (
          <tr key={partido.id}>
            <td>{partido.id}</td>
            <td>{partido.denominacion}</td>
            <td>{partido.siglas}</td>
            <td>{partido.fechaDeInicio}</td>
            <td>{partido.fechaDeFin || "---"}</td>
            <td>
              {partido.visualUrl ? (
                <img
                  src={partido.visualUrl}
                  alt={`Logo de ${partido.denominacion}`}
                  style={{ width: "50px", height: "50px" }}
                />
              ) : (
                "No hay logo disponible"
              )}
            </td>
            <td>
            <button
            className="btn btn-outline-primary btn-sm"
            onClick={() => editarPartido(partido.id)}
          >
            Editar
          </button>
          <button
            className="btn btn-outline-danger btn-sm"
            onClick={() => eliminarPartido(partido.id)}
          >
            Eliminar
          </button>
          <button
            className="btn btn-outline-secondary btn-sm"
            onClick={() => cargarLogo(partido.id)}
          >
            Cargar Logo
          </button>
            </td>
          </tr>
        )}
      />
          <ConfirmModal
          show={confirmDelete.isOpen}
          onHide={confirmDelete.close}
          onConfirm={handleDeleteConfirm}
          body={`¿Estás seguro de eliminar al partido con ID ${confirmDelete.payload}?`}
          confirmText="Eliminar"
          cancelText="Cancelar"
          loading={confirmDelete.loading}
        />
    </div>
  );
};

export default BuscarPartido;