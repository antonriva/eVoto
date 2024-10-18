CREATE TABLE Persona (
	id INTEGER NOT NULL,	-- type mapped from: int
	nombre str NOT NULL,	-- unknown type
	apellidoPaterno str NOT NULL,	-- unknown type
	apellidoMaterno str NOT NULL,	-- unknown type
	fechaDeNacimiento date NOT NULL,
	fechaDeFin date NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE Domicilio (
	id INTEGER NOT NULL,	-- type mapped from: int
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Genero (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Email (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Elector (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Candidato (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Partido (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Cargo (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE Voto (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	PRIMARY KEY (id)
);
CREATE TABLE PersonaDomicilio (
	idDeDomicilio VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id INTEGER NOT NULL,	-- type mapped from: int
	PRIMARY KEY (idDeDomicilio, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Domicilio (id) ON DELETE CASCADE
);
CREATE TABLE PersonaDomicilio (
	idDeDomicilio VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id INTEGER NOT NULL,	-- type mapped from: int
	PRIMARY KEY (idDeDomicilio, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Persona (id) ON DELETE CASCADE
);
CREATE TABLE PersonaGenero (
	idDeGenero VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeGenero, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Genero (id) ON DELETE CASCADE
);
CREATE TABLE PersonaGenero (
	idDeGenero VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id INTEGER NOT NULL,	-- type mapped from: int
	PRIMARY KEY (idDeGenero, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Persona (id) ON DELETE CASCADE
);
CREATE TABLE PersonaEmail (
	idDeEmail VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeEmail, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Email (id) ON DELETE CASCADE
);
CREATE TABLE PersonaEmail (
	idDeEmail VARCHAR(255) NOT NULL,	-- added default type
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	fechaDeInicio date NOT NULL,
	fechaDeFin date NOT NULL,
	id INTEGER NOT NULL,	-- type mapped from: int
	PRIMARY KEY (idDeEmail, idDePersona, id),
	FOREIGN KEY (id) REFERENCES Persona (id) ON DELETE CASCADE
);
CREATE TABLE Elector (
	id VARCHAR(255) NOT NULL,	-- added default type
	descripcion str NOT NULL,	-- unknown type
	id2 INTEGER NOT NULL,	-- renamed from: id; type mapped from: int
	PRIMARY KEY (id2),
	FOREIGN KEY (id2) REFERENCES Persona (id) ON DELETE CASCADE
);
CREATE TABLE PersonaCandidato (
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDePersona, idDeCandidato, id),
	FOREIGN KEY (id) REFERENCES Candidato (id) ON DELETE CASCADE
);
CREATE TABLE PersonaCandidato (
	idDePersona VARCHAR(255) NOT NULL,	-- added default type
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	id INTEGER NOT NULL,	-- type mapped from: int
	PRIMARY KEY (idDePersona, idDeCandidato, id),
	FOREIGN KEY (id) REFERENCES Persona (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoPartido (
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDePartido VARCHAR(255) NOT NULL,	-- added default type
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeCandidato, id),
	FOREIGN KEY (id) REFERENCES Partido (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoPartido (
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDePartido VARCHAR(255) NOT NULL,	-- added default type
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeCandidato, id),
	FOREIGN KEY (id) REFERENCES Candidato (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoCargo (
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDeCargo VARCHAR(255) NOT NULL,	-- added default type
	generoMaximo INTEGER NOT NULL,	-- type mapped from: int
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeCandidato, idDeCargo, id),
	FOREIGN KEY (id) REFERENCES Cargo (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoCargo (
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDeCargo VARCHAR(255) NOT NULL,	-- added default type
	generoMaximo INTEGER NOT NULL,	-- type mapped from: int
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeCandidato, idDeCargo, id),
	FOREIGN KEY (id) REFERENCES Candidato (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoElectorEsVoto (
	idDeVoto VARCHAR(255) NOT NULL,	-- added default type
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDeElector VARCHAR(255) NOT NULL,	-- added default type
	id2 INTEGER NOT NULL,	-- renamed from: id; type mapped from: int
	PRIMARY KEY (idDeVoto, idDeCandidato, idDeElector, id2),
	FOREIGN KEY (id2) REFERENCES Elector (id2) ON DELETE CASCADE
);
CREATE TABLE CandidatoElectorEsVoto (
	idDeVoto VARCHAR(255) NOT NULL,	-- added default type
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDeElector VARCHAR(255) NOT NULL,	-- added default type
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeVoto, idDeCandidato, idDeElector, id),
	FOREIGN KEY (id) REFERENCES Candidato (id) ON DELETE CASCADE
);
CREATE TABLE CandidatoElectorEsVoto (
	idDeVoto VARCHAR(255) NOT NULL,	-- added default type
	idDeCandidato VARCHAR(255) NOT NULL,	-- added default type
	idDeElector VARCHAR(255) NOT NULL,	-- added default type
	id VARCHAR(255) NOT NULL,	-- added default type
	PRIMARY KEY (idDeVoto, idDeCandidato, idDeElector, id),
	FOREIGN KEY (id) REFERENCES Voto (id) ON DELETE CASCADE
);
