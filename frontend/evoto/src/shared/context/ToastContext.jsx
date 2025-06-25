import React, { createContext, useContext, useState, useCallback } from "react";
import ToastMessage from "../components/toastMessage/ToastMessage";

const ToastContext = createContext();

export const ToastProvider = ({ children }) => {
  const [toast, setToast] = useState({
    show: false,
    title: "",
    body: "",
    variant: "success", // "success" | "danger" | "warning" | etc.
  });

  const showToast = useCallback(({ title, body, variant = "success" }) => {
    setToast({
      show: true,
      title,
      body,
      variant,
    });
  }, []);

  const hideToast = () => {
    setToast((prev) => ({ ...prev, show: false }));
  };

  return (
    <ToastContext.Provider value={{ showToast, hideToast, toast }}>
      {children}
      <ToastMessage /> {/* ✅ Now self-contained and reads from context */}
    </ToastContext.Provider>
  );
};

// ✅ Hook to use in any component
export const useToast = () => useContext(ToastContext);
