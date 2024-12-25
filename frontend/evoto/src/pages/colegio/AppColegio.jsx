import React, { useState, useEffect } from 'react';
import { Routes, Route, Link, useLocation } from 'react-router-dom';
import AboutPag from './AboutPag';
import ResultadosPag from './ResultadosPag';

//Partido
import SistemaP from './sistemaElectoral/SistemaP';
import PartidosP from './sistemaElectoral/PartidosP'
import BuscarPartidoP from './sistemaElectoral/partidosP/BuscarPartidoP'
import EditarPartidoP from '../../components/colegio/partido/EditarPartido'
import RegistroPartidoP from './sistemaElectoral/partidosP/RegistroPartidoP';

//Elector
import ElectoresP from './sistemaElectoral/ElectoresP'
import BuscarElectorP from './sistemaElectoral/electoresP/PaginaBuscarElectores';
import EditarElectorP from '../../components/colegio/paginaBuscar/ElectorEditar';
import RegistroElectorP from './sistemaElectoral/electoresP/ElectorRegistro';


//PROCESO
import ProcesoP from './procesoElectoral/ProcesoP'
import RegistroProcesoP from './procesoElectoral/instanciaP/RegistroProceso'
import BusquedaProcesoP from './procesoElectoral/instanciaP/BusquedaProceso'
import EditarProcesoP from '../../components/colegio/proceso/paginaBuscar/InstanciaEditar'
import RegistroCandidatura from '../../components/colegio/proceso/paginaBuscar/RegistroAsignacion'

const AppColegio = () => {
    const [menuVisible, setMenuVisible] = useState(true); // Estado para mostrar/ocultar el menú
    const location = useLocation(); // Obtener la ruta actual
  
    // Páginas donde se debe ocultar el menú
    const hideMenuPages = ['/colegio/sistema','/colegio/proceso','/colegio/sistema/ele','/colegio/sistema/ele/registro','/colegio/sistema/ele/buscar'
      ,'/colegio/proceso/buscar','/colegio/proceso/registro'];
  
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
      if (path.includes("sistema")) {
        setMenuVisible(false);
      }
      if (path.includes("proceso")) {
        setMenuVisible(false);
      }
    };
  
  return (
      <div style={{ backgroundColor: "#add8e6", minHeight: "100vh", padding: "20px" }}>
      <h1>Colegio Electoral</h1>
      <div>
            <h1>
            Sistema de registro de candidatos, electores y gestion del voto. Como funcion el almacenamiento 
            de electores, partidos, candidatos y administracion del voto.
            </h1> 
        </div>
      {/* Solo mostrar el menú si no estamos en una de las páginas de proceso electoral */}
      {menuVisible && !hideMenuPages.includes(location.pathname) && (
        <nav>
          <ul>
            <li><Link to="/">Regresar al Menú Principal</Link></li>
            <li><Link to="about" onClick={() => setMenuVisible(true)}>Acerca del Colegio Electoral</Link></li>
            <li><Link to="sistema" onClick={() => handleMenuToggle("sistema")}>Sistema electoral</Link></li>
            <li><Link to="proceso" onClick={() => handleMenuToggle("proceso")}>Proceso Electoral</Link></li>
          </ul>
        </nav>
      )}

      {/* Rutas para mostrar el contenido de cada página */}
      <Routes>
        <Route path="about" element={<AboutPag />} />
        <Route path="resultados" element={<ResultadosPag />} />

        {/* Sistema electoral */}
        <Route path="sistema" element={<SistemaP/>}/>

        <Route path="sistema/par" element={<PartidosP/>}/>
        <Route path="sistema/ele" element={<ElectoresP/>}/>
          
          {/* Partido */}
          <Route path="sistema/par/buscar" element={<BuscarPartidoP/>}/>
          <Route path="sistema/par/buscar/editar/:id" element={<EditarPartidoP/>}/>
          <Route path="sistema/par/registro" element={<RegistroPartidoP/>}/>


          {/* Elector */}

          <Route path="sistema/ele/buscar" element={<BuscarElectorP/>}/>
          <Route path="sistema/ele/buscar/editar/:id" element={<EditarElectorP/>}/>
          <Route path="sistema/ele/registro" element={<RegistroElectorP/>}/>

          {/* Proceso electoral */}
          <Route path="proceso" element={<ProcesoP/>}/>
          <Route path="proceso/registro" element={<RegistroProcesoP/>}/>
          <Route path="proceso/buscar" element={<BusquedaProcesoP/>}/>
          <Route path="proceso/buscar/editar/:id" element={<EditarProcesoP/>}/>
          <Route path="proceso/buscar/agregar/:id" element={<RegistroCandidatura/>}/>
          

          {/* Instancia */}

          {/* Votante */}

      </Routes>
    </div>
  );
};

export default AppColegio;
