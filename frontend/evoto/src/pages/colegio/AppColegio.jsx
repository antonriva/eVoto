import React, { useState, useEffect } from 'react';
import { Routes, Route, Link, useLocation } from 'react-router-dom';
import AboutPag from './AboutPag';
import ColegioElectoralPag from './ColegioElectoralPag';
import ResultadosPag from './ResultadosPag';
import ProcesoElectoralPag from './ProcesoElectoralPag';
import DefineProcesoElectoralPag from './ColegioElectoralDir/ProcesoElectoralDir/DefineProcesoElectoralPag';
import SistemaP from './sistemaElectoral/SistemaP';

const AppColegio = () => {
    const [menuVisible, setMenuVisible] = useState(true); // Estado para mostrar/ocultar el menú
    const location = useLocation(); // Obtener la ruta actual
  
    // Páginas donde se debe ocultar el menú
    const hideMenuPages = ['/colegio/proceso', '/colegio/proceso/definir', '/colegio/sistema'];
  
    // Usamos useEffect para detectar cambios en la ruta
    useEffect(() => {
      // Mostrar el menú solo cuando estemos en la ruta /colegio
      if (location.pathname === '/colegio') {
        setMenuVisible(true);
      } else if (hideMenuPages.includes(location.pathname)) {
        setMenuVisible(false);
      }
    }, [location]); // Este efecto se ejecuta cada vez que la ubicación cambia
  
    // Cambiar la visibilidad del menú cuando el usuario haga clic en "Proceso Electoral"
    const handleMenuToggle = (path) => {
      if (path.includes("proceso")) {
        setMenuVisible(false);
      }
      if (path.includes("sistema")) {
        setMenuVisible(false);
      }
    };
  
  return (
      <div style={{ backgroundColor: "#add8e6", minHeight: "100vh", padding: "20px" }}>
      <h1>Colegio Electoral</h1>
      {/* Solo mostrar el menú si no estamos en una de las páginas de proceso electoral */}
      {menuVisible && !hideMenuPages.includes(location.pathname) && (
        <nav>
          <ul>
            <li><Link to="about" onClick={() => setMenuVisible(true)}>Acerca del Colegio Electoral</Link></li>
            <li><Link to="sistema" onClick={() => handleMenuToggle("sistema")}>Sistema electoral</Link></li>
            <li><Link to="proceso" onClick={() => handleMenuToggle("proceso")}>Proceso Electoral</Link></li>
            <li><Link to="resultados" onClick={() => setMenuVisible(true)}>Resultados</Link></li>
          </ul>
        </nav>
      )}

      {/* Rutas para mostrar el contenido de cada página */}
      <Routes>
        <Route path="about" element={<AboutPag />} />
        <Route path="proceso" element={<ColegioElectoralPag />} />
        <Route path="resultados" element={<ResultadosPag />} />
        <Route path="proceso/definir" element={<ProcesoElectoralPag />} />
        <Route path="proceso/definir/formulario" element={<DefineProcesoElectoralPag />} />

        {/* Sistema electoral */}
        <Route path="sistema" element={<SistemaP/>}/>
        <Route path="sistema/par" element={<SistemaP/>}/>
        <Route path="sistema/ele" element={<SistemaP/>}/>
        <Route path="sistema/gen" element={<SistemaP/>}/>
      </Routes>
    </div>
  );
};

export default AppColegio;
