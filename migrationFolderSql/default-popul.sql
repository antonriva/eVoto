---- RECORDAR COLONIA Y CODIGO POSTAL AGREGAR
---DATOS DEFAULT DEL SISTEMA

INSERT INTO Nivel (ID, DESCRIPCION) 
VALUES (1, 'Federal');

INSERT INTO Nivel (ID, DESCRIPCION) 
VALUES (2, 'Estatal');

INSERT INTO Nivel (ID, DESCRIPCION) 
VALUES (3, 'Municipal');

INSERT INTO Nivel (ID, DESCRIPCION) 
VALUES (4, 'Localidad');

-----

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (1, 1, 'Presidencia');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (2, 1, 'Cámara de Diputados');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (3, 1, 'Cámara de Senadores');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (4, 2, 'Gobernatura');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (5, 2, 'Cámara de Diputados');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (6, 2, 'Cámara de Senadores');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (7, 3, 'Presidencia');

INSERT INTO Proceso (ID, IDDENIVEL, DESCRIPCION) 
VALUES (8, 4, 'Delegado');

COMMIT;

--------

INSERT INTO TipoDeDomicilio (ID, DESCRIPCION)
VALUES (1, 'Nacimiento');

INSERT INTO TipoDeDomicilio (ID, DESCRIPCION)
VALUES (2, 'Residencia');

INSERT INTO TipoDeDomicilio (ID, DESCRIPCION)
VALUES (3, 'Secundario');

COMMIT;

--------

INSERT INTO RecursoVigente (ID, DESCRIPCION)
VALUES (1, 'Vigente');

INSERT INTO RecursoVigente (ID, DESCRIPCION)
VALUES (2, 'No vigente');

COMMIT;

-------------

INSERT INTO TipoDeVisual (ID, DESCRIPCION)
VALUES (1, 'Logo');

INSERT INTO TipoDeVisual (ID, DESCRIPCION)
VALUES (2, 'FotoCandidato');


COMMIT;


