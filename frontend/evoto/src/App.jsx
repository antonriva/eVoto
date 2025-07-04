import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";


// Import Bootstrap CSS globally
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap-icons/font/bootstrap-icons.css';

const App = () => {
  return (
    <Router>
      <AppRoutes />
    </Router>
  );
};

export default App;
