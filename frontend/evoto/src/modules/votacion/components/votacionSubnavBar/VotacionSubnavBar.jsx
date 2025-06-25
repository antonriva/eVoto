import React from 'react';
import { Link } from "react-router-dom";
import "../../../../shared/components/navbar/AppNavbar.css"; // Import styles
import "../../../../shared/layouts/AppLayout.css"; // Import global styles

const VotacionNavbar = () => (
  <nav
    className="subnavbar"
  >
    <div className="app-layout-container d-flex">
      <span className="navbar-text">
        <Link to="votante" className="type-link text-decoration-none">
            Votación
        </Link>
        </span>
      </div>
  </nav>
);

export default VotacionNavbar;
