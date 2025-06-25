import React from "react";
import { Link } from "react-router-dom";
import "./CardLink.css"; // We'll add styles here

const CardLink = ({ to, icon, title, subtitle, description }) => {
  return (
    <div className="cardlink-wrapper">
      <Link to={to} className="text-decoration-none">
        <div className="custom-card d-flex align-items-center justify-content-between">
          <div>
            <div className="card-number">{icon}</div>
            <div className="card-label">{subtitle}</div>
            {description && <div className="card-description text-muted">{description}</div>}
          </div>
          <div className="card-arrow">
            <span>&gt;</span>
          </div>
        </div>
      </Link>
    </div>
  );
};

export default CardLink;
