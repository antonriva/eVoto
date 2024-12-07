-- Desactivar restricciones de claves foráneas temporalmente
BEGIN
    EXECUTE IMMEDIATE 'ALTER SESSION SET CONSTRAINTS = DEFERRED';
END;
/

-- Eliminar tablas en orden inverso de las dependencias
DROP TABLE Voto CASCADE CONSTRAINTS;
DROP TABLE PersonaCandidatura CASCADE CONSTRAINTS;
DROP TABLE Elector CASCADE CONSTRAINTS;
DROP TABLE Candidatura CASCADE CONSTRAINTS;
DROP TABLE PersonaDomicilio CASCADE CONSTRAINTS;
DROP TABLE Domicilio CASCADE CONSTRAINTS;
--DROP TABLE Postal CASCADE CONSTRAINTS; se uso en la version anterior del sistema
DROP TABLE Localidad CASCADE CONSTRAINTS;
--DROP TABLE Colonia CASCADE CONSTRAINTS;, esta se uso en la version anterior del sistema
DROP TABLE Municipio CASCADE CONSTRAINTS;
DROP TABLE EntidadFederativa CASCADE CONSTRAINTS;
DROP TABLE NivelProceso CASCADE CONSTRAINTS;
DROP TABLE InstanciaDeProceso CASCADE CONSTRAINTS;
DROP TABLE Nivel CASCADE CONSTRAINTS;
DROP TABLE Proceso CASCADE CONSTRAINTS;
DROP TABLE Partido CASCADE CONSTRAINTS;
DROP TABLE Persona CASCADE CONSTRAINTS;


--Crea las tablas

--Registro civil

--Registro civil ---- Sistema geografico

CREATE TABLE EntidadFederativa (
    Id INT PRIMARY KEY,
    descripcion VARCHAR2(255) NOT NULL
);

CREATE TABLE Municipio (
    Id INT PRIMARY KEY,
    IdDeEntidad INT NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    FOREIGN KEY (IdDeEntidad) REFERENCES EntidadFederativa(Id)
);

---ESTE NOMBRE SE VA A TENER QUE CAMBIAR EN ECLIPSE
CREATE TABLE Localidad (
    Id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    IdDeMunicipio INT NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    FOREIGN KEY (IdDeMunicipio) REFERENCES Municipio(Id)
);

--SE USO EN VERSION ANTERIOR
--CREATE TABLE Postal (
--    Id INT PRIMARY KEY,
--    IdDeAsentamiento INT NOT NULL,
--    descripcion VARCHAR2(255) NOT NULL,
--    FOREIGN KEY (IdDeAsentamiento) REFERENCES Localidad(Id)
--);


--Registro civil -- Persona y domicilio

CREATE TABLE Persona (
    Id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    apellidoPaterno VARCHAR2(100) NOT NULL,
    apellidoMaterno VARCHAR2(100) NOT NULL,
    fechaDeNacimiento DATE NOT NULL,
    fechaDeFin DATE
);

CREATE TABLE RelacionFamiliar (
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_persona NUMBER NOT NULL,
    id_padre NUMBER, -- Puede ser NULL si no hay padre definido
    id_madre NUMBER, -- Puede ser NULL si no hay madre definida
    tipo_relacion VARCHAR2(50), -- Ejemplo: 'Biológico', 'Adoptivo', etc.
    CONSTRAINT fk_relacion_persona FOREIGN KEY (id_persona) REFERENCES Persona(id) ON DELETE CASCADE,
    CONSTRAINT fk_relacion_padre FOREIGN KEY (id_padre) REFERENCES Persona(id),
    CONSTRAINT fk_relacion_madre FOREIGN KEY (id_madre) REFERENCES Persona(id)
);


CREATE TABLE TipoDeDomicilio (
    Id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR2(100) NOT NULL
);

CREATE TABLE Domicilio (
    Id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    IdDeFederativa INT NOT NULL,
    IdDeMunicipio INT NOT NULL,
    IdDeLocalidad INT,
    IdDeTipo INT,
    numExt INT,
    numInt INT,
    calle VARCHAR2(255),
    FOREIGN KEY (IdDeFederativa) REFERENCES EntidadFederativa(Id),
    FOREIGN KEY (IdDeMunicipio) REFERENCES Municipio(Id),
    FOREIGN KEY (IdDeLocalidad) REFERENCES Localidad(Id),
    FOREIGN KEY (IdDeTipo) REFERENCES TipoDeDomicilio(Id)
);

CREATE TABLE PersonaDomicilio (
    IdDePersona INT,
    IdDeDomicilio INT,
    fechaDeInicio DATE,
    fechaDeFin DATE,
    PRIMARY KEY (IdDePersona, IdDeDomicilio),
    FOREIGN KEY (IdDePersona) REFERENCES Persona(Id),
    FOREIGN KEY (IdDeDomicilio) REFERENCES Domicilio(Id)
);



-- Reactivar restricciones de claves foráneas
BEGIN
    EXECUTE IMMEDIATE 'ALTER SESSION SET CONSTRAINTS = IMMEDIATE';
END;
/

-- Confirmar transacciones
COMMIT;
