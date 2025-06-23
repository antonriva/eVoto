import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

const hideMenuPages = [
  "/colegio/sistema",
  "/colegio/proceso",
  "/colegio/sistema/ele",
  "/colegio/sistema/ele/registro",
  "/colegio/sistema/ele/buscar",
  "/colegio/proceso/buscar",
  "/colegio/proceso/registro",
];

export function useMenuVisibility() {
  const [menuVisible, setMenuVisible] = useState(true);
  const location = useLocation();

  useEffect(() => {
    const path = location.pathname;
    setMenuVisible(!hideMenuPages.includes(path));
  }, [location]);

  const handleMenuToggle = (subpath) => {
    const fullPath = `/colegio/${subpath}`;
    setMenuVisible(!hideMenuPages.includes(fullPath));
  };

  return { menuVisible, handleMenuToggle };
}
