
import React, { useRef } from "react";

const UploadButton = ({ onUpload, accept = "image/*", label = "Subir" }) => {
  const inputRef = useRef(null);

  const handleClick = () => inputRef.current?.click();

  const handleChange = (e) => {
    const file = e.target.files[0];
    if (file) onUpload(file);
  };

  return (
    <>
      <button onClick={handleClick}>{label}</button>
      <input
        type="file"
        accept={accept}
        ref={inputRef}
        onChange={handleChange}
        style={{ display: "none" }}
      />
    </>
  );
};

export default UploadButton;
