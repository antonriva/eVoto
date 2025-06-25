import React from 'react';
import { Link } from "react-router-dom";
import "../../../../shared/components/navbar/AppNavbar.css"; // Import styles
import "../../../../shared/layouts/AppLayout.css"; // Import global styles

const AdminNavbar = () => (
  <nav
    className="subnavbar"
  >
    <div className="app-layout-container d-flex">
      <span className="navbar-text">
        <Link to="colegio" className="type-link text-decoration-none">
          Módulo electoral
        </Link>
        </span>
      </div>
  </nav>
);

export default AdminNavbar;
