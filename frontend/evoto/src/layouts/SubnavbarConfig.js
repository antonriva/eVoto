import CivilNavbar from "../rComponents/CivilNavbar"; // Import CivilNavbar
//import ColegioNavbar from "../rComponents/ColegioNavbar"; // Import ColegioNavbar
//import VotanteNavbar from "../rComponents/VotanteNavbar"; // Import VotanteNavbar

// Mapping object for subnav bars
export const subnavbarMap = {
  "/civil": CivilNavbar,
//  "/colegio": ColegioNavbar,
 // "/votante": VotanteNavbar,
};

// Function to find the active subnavbar based on the current path
export const getActiveSubNavbar = (currentPath) => {
  const SubNavbarComponent = Object.entries(subnavbarMap).find(([prefix]) =>
    currentPath.startsWith(prefix)
  )?.[1];

  return SubNavbarComponent ? SubNavbarComponent : null;
};