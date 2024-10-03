import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import RegistroCandidatoPag from './pages/RegistroCandidatoPag'
 
const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<div>Welcome to the homepage</div>} />
          <Route path="/RegistroCandidato" element={<RegistroCandidatoPag/>}> </Route>
        </Routes>
      </div>
    </Router>
  )
}

export default App
