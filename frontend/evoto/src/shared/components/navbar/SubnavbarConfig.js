import IdentidadNavbar from "../../../modules/identidad/components/identidadSubNavbar/IdentidadNavbar"; // Import CivilNavbar
import AdminNavbar from "../../../modules/admin/components/adminSubNavBar/AdminNavBar"; // Import ColegioNavbar
import VotacionNavbar from "../../../modules/votacion/components/votacionSubnavBar/VotacionSubnavBar"; // Import VotanteNavbar

// Mapping object for subnav bars
export const subnavbarMap = {
  "/civil": IdentidadNavbar,
  "/colegio": AdminNavbar,
  "/votante": VotacionNavbar,
};

// Function to find the active subnavbar based on the current path
export const getActiveSubNavbar = (currentPath) => {
  const SubNavbarComponent = Object.entries(subnavbarMap).find(([prefix]) =>
    currentPath.startsWith(prefix)
  )?.[1];

  return SubNavbarComponent ? SubNavbarComponent : null;
};