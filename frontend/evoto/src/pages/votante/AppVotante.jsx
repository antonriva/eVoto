import { Routes, Route, Link } from "react-router-dom";

import BuscarVotanteP from "./BuscarVotante"
import ConsultaPag from "./ProcesosAbiertos"
import EleccionPag from "./CandidatosVoto"



const VotanteApp = () => {
  return (
    <div>
      {/* Barra de navegación del votante */}
      <nav>
        <ul>
          <li>
            <Link to="/">Regresar al Menú Principal</Link>
          </li>
          <li>
            <Link to="ingreso">Ingreso</Link>
          </li>
        </ul>
      </nav>

      {/* Rutas internas del módulo Votante */}
      <main>

        <Routes>
  <Route path="ingreso" element={<BuscarVotanteP />} />
  <Route path="ingreso/procesos-abiertos/:idElector" element={<ConsultaPag />} />
  <Route path="ingreso/candidatos" element={<EleccionPag />} />
</Routes>


      </main>
    </div>
  );
};

export default VotanteApp;