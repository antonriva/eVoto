DECLARE
    v_file_path VARCHAR2(1000); -- Ruta completa del archivo
    v_line      VARCHAR2(4000); -- Línea del archivo
    v_id        NUMBER;
    v_descripcion    VARCHAR2(100);
    v_file      UTL_FILE.FILE_TYPE;
    
BEGIN
    FOR registro IN (
        SELECT '/home/mrschopen/Documents/proyectoBDII/eVoto/migrationFolderSql/csv/entidadfederativa.csv' AS file_path FROM DUAL
        --UNION ALL
        --SELECT '/ruta/directorio2/archivo2.csv' FROM DUAL
        --UNION ALL
        --SELECT '/ruta/directorio3/archivo3.csv' FROM DUAL
    )
    LOOP
        v_file_path := registro.file_path;
        v_file := UTL_FILE.FOPEN(v_file_path, 'r');
        
        LOOP
            BEGIN
                -- Leer una línea del archivo
                UTL_FILE.GET_LINE(v_file, v_line);
                
                -- Dividir la línea y asignar a variables
                v_id := TO_NUMBER(REGEXP_SUBSTR(v_line, '[^,]+', 1, 1));
                v_descripcion := REGEXP_SUBSTR(v_line, '[^,]+', 1, 2);
                
                -- Insertar en la tabla
                INSERT INTO EntidadFederativa (id, descripcion)
                VALUES (v_id, v_descripcion);
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    EXIT;
            END;
        END LOOP;
        UTL_FILE.FCLOSE(v_file);
    END LOOP;
END;
/
