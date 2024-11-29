-- Limpia la tabla antes de insertar los datos (opcional)
DELETE FROM EntidadFederativa;

-- Inserta las entidades federativas de México (sin especificar el id)
INSERT INTO EntidadFederativa (descripcion) VALUES ('Aguascalientes');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Baja California');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Baja California Sur');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Campeche');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Chiapas');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Chihuahua');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Ciudad de México');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Coahuila');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Colima');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Durango');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Estado de México');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Guanajuato');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Guerrero');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Hidalgo');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Jalisco');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Michoacán');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Morelos');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Nayarit');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Nuevo León');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Oaxaca');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Puebla');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Querétaro');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Quintana Roo');
INSERT INTO EntidadFederativa (descripcion) VALUES ('San Luis Potosí');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Sinaloa');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Sonora');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Tabasco');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Tamaulipas');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Tlaxcala');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Veracruz');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Yucatán');
INSERT INTO EntidadFederativa (descripcion) VALUES ('Zacatecas');

-- Confirma los datos insertados
COMMIT;

-- Verifica los datos
SELECT * FROM EntidadFederativa;


-- Declara una variable para obtener el ID de Aguascalientes
DECLARE
    v_id_aguascalientes NUMBER; -- Variable para almacenar el ID de Aguascalientes
BEGIN
    -- Obtén el ID recién generado para la entidad "Aguascalientes"
    SELECT id
    INTO v_id_aguascalientes
    FROM EntidadFederativa
    WHERE descripcion = 'Aguascalientes';

    -- Inserta los municipios de Aguascalientes
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Aguascalientes', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Asientos', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Calvillo', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Cosío', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Jesús María', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Pabellón de Arteaga', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Rincón de Romos', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('San José de Gracia', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('Tepezalá', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('San Francisco de los Romo', v_id_aguascalientes);
    INSERT INTO Municipio (descripcion, idDeEntidad) VALUES ('El Llano', v_id_aguascalientes);

    -- Confirma los cambios
    COMMIT;
END;
/

SELECT * FROM Municipio;

-- Declara una variable para obtener el ID de Aguascalientes
DECLARE
    v_id_aguascalientes NUMBER; -- Variable para almacenar el ID de Aguascalientes
BEGIN
    -- Obtén el ID recién generado para la entidad "Aguascalientes"
    SELECT id
    INTO v_id_aguascalientes
    FROM Municipio
    WHERE descripcion = 'Aguascalientes';

    -- Inserta los municipios de Aguascalientes
    INSERT INTO Colonia (descripcion, idDeMunicipio) VALUES ('Centro', v_id_aguascalientes);
    INSERT INTO Colonia (descripcion, idDeMunicipio) VALUES ('San Cayetano', v_id_aguascalientes);
    INSERT INTO Colonia (descripcion, idDeMunicipio) VALUES ('Colonias del Rio', v_id_aguascalientes);

    -- Confirma los cambios
    COMMIT;
END;
/

SELECT * FROM Colonia;

DECLARE
    v_id_colonia_centro NUMBER;
    v_id_colonia_san_cayetano NUMBER;
    v_id_colonia_colonias_rio NUMBER;
BEGIN
    -- Obtener los IDs de las colonias
    SELECT id INTO v_id_colonia_centro 
    FROM Colonia 
    WHERE descripcion = 'Centro';

    SELECT id INTO v_id_colonia_san_cayetano 
    FROM Colonia 
    WHERE descripcion = 'San Cayetano';

    SELECT id INTO v_id_colonia_colonias_rio 
    FROM Colonia 
    WHERE descripcion = 'Colonias del Rio';

    -- Inserciones en la tabla Postal
    INSERT INTO Postal (descripcion, idDeColonia) 
    VALUES ('20000', v_id_colonia_centro);

    INSERT INTO Postal (descripcion, idDeColonia) 
    VALUES ('20010', v_id_colonia_san_cayetano);

    INSERT INTO Postal (descripcion, idDeColonia) 
    VALUES ('20015', v_id_colonia_colonias_rio);

    DBMS_OUTPUT.PUT_LINE('Códigos postales insertados exitosamente en la tabla Postal.');
    
    COMMIT;
END;
/

SELECT * FROM Postal;



