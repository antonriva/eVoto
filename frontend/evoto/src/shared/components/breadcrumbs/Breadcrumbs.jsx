import React from "react";
import { Link } from "react-router-dom";

const Breadcrumbs = ({ items }) => {
  return (
    <nav className="container mt-3" aria-label="breadcrumb">
      <ol className="breadcrumb fs-5">
        <li className="breadcrumb-item">
          <Link to="/" className="text-dark text-decoration-none">
            <i className="bi bi-house-door-fill me-1 fs-5 text-dark"></i>
          </Link>
        </li>
        {items.map((item, index) => (
          <li
            key={index}
            className={`breadcrumb-item ${
              index === items.length - 1 ? "active text-dark" : ""
            }`}
            aria-current={index === items.length - 1 ? "page" : undefined}
          >
            {index === items.length - 1 ? (
              item.label
            ) : (
              <Link to={item.to} className="text-dark text-decoration-none">
                {item.label}
              </Link>
            )}
          </li>
        ))}
      </ol>
    </nav>
  );
};

export default Breadcrumbs;
