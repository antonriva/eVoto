import { BrowserRouter as Router, Link, Route, Routes } from "react-router-dom"
//Paginas main
import AboutPag from './pages/AboutPag'
import PersonaListPag from './pages/PersonaListPag'
import ColegioElectoralPag from './pages/ColegioElectoralPag'
//Colegio electoral
import ProcesoElectoralPag from "./pages/ColegioElectoralDir/ProcesoElectoralPag"

//Proceso electoral 
import DefineProcesoElectoralPag from "./pages/ColegioElectoralDir/ProcesoElectoralDir/DefineProcesoElectoralPag"
 
const App = () => {
  return (    
    <Router>
      <div>
        {/* Despliegue de links para subpaginas */}
        <nav>
          <ul>
            <li>
                <Link to ="/"> Acerca del sistema </Link>
            </li>
            <li>
                <Link to ="/ColegioElectoral"> Colegio Electoral </Link>
            </li>
            <li>
                <Link to = "/PersonaList"> Prueba HTTP para DML en persona </Link>
            </li>
          </ul>
        </nav>
        {/*Routes hacen funcion de render de la pagina unicamente*/}
        <Routes>
          {/* MAIN */}
          <Route path="/" element={<AboutPag/>} />
          <Route path="/PersonaList" element={<PersonaListPag/>}></Route>

          {/* COLEGIO ELECTORAL */}
          <Route path="/ColegioElectoral" element={<ColegioElectoralPag/>}></Route>
          <Route path="/ColegioElectoral/ProcesoElectoralPag/" element={<ProcesoElectoralPag/>}></Route>

          {/* PROCESO ELECTORAL */}
          <Route path="/ColegioElectoral/ProcesoElectoralPag/DefineProcesoElectoralPag" element={<DefineProcesoElectoralPag/>}></Route>
        </Routes>
      </div>
    </Router>
  )
}

export default App
