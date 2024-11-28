//Tablas del proceso electoral


    //Clases atomicas

CREATE TABLE nivel(id NUMBER GENERATED ALWAYS AS IDENTITY, descripcion VARCHAR2(20)  NOT NULL, PRIMARY KEY (id));

CREATE TABLE proceso(id NUMBER GENERATED ALWAYS AS IDENTITY, nombre VARCHAR2(20)  NOT NULL, PRIMARY KEY (id));

CREATE TABLE InstanciaDeProceso (id NUMBER GENERATED ALWAYS AS IDENTITY, fechaDeInicio DATE NOT NULL, fechaDeFin DATE NOT NULL, ganadoresNum INT NOT NULL, idDeProceso NUMBER, PRIMARY KEY(id), FOREIGN KEY(idDeProceso) REFERENCES Proceso(id));


    //Clases que son derivadas

CREATE TABLE nivelproceso(idDeNivel NUMBER, idDeProceso NUMBER,PRIMARY KEY (idDeNivel, idDeProceso), FOREIGN KEY (idDeNivel) REFERENCES nivel(id), FOREIGN KEY (idDeProceso) REFERENCES proceso(id))

DROP TABLE ProcesoElectoral;
//Tablas del sistema de registro civil


//Tablas del sistema de registro politico


//Tablas de control geografico