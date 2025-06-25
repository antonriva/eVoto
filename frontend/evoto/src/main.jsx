// main.jsx or main.tsx
import ReactDOM from "react-dom/client";
import App from "./App";
import { ToastProvider } from "./shared/context/ToastContext"; // ✅

ReactDOM.createRoot(document.getElementById("root")).render(
  <ToastProvider>
    <App />
  </ToastProvider>
);
