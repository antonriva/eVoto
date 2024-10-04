import { BrowserRouter as Router, Link, Route, Routes } from "react-router-dom"
import RegistroCandidatoPag from './pages/RegistroCandidatoPag'
import AboutPag from './pages/AboutPag'
 
const App = () => {
  return (    
    <Router>
      <div>
        {/* Despliegue de links para subpaginas */}
        <nav>
          <ul>
            <li>
                <Link to ="/About"> Acerca del sistema </Link>
            </li>
            <li>
                <Link to ="/RegistroCandidato"> Registro de candidatos </Link>
            </li>
          </ul>
        </nav>
        {/*Routes hacen funcion de render de la pagina unicamente*/}
        <Routes>
          <Route path="/About" element={<div> <AboutPag/> </div>} />
          <Route path="/RegistroCandidato" element={<RegistroCandidatoPag/>}> </Route>
        </Routes>
      </div>
    </Router>
  )
}

export default App
