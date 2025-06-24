// src/shared/hooks/useConfirmDialog.js
import { useState } from "react";

export const useConfirmDialog = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [payload, setPayload] = useState(null);
  const [loading, setLoading] = useState(false);

  const open = (value) => {
    setPayload(value);
    setIsOpen(true);
  };

  const close = () => {
    setPayload(null);
    setIsOpen(false);
    setLoading(false);
  };

  return {
    isOpen,
    payload,
    open,
    close,
    loading,
    setLoading,
  };
};