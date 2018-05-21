USE cafesoft;

DELIMITER $$
CREATE FUNCTION is_usado (var BOOLEAN) RETURNS VARCHAR(50)
BEGIN
	
    IF var THEN
         RETURN 'Usada';    
    END IF;    
    RETURN 'Nueva';
END $$
DELIMITER ;
-- DROP FUNCTION crear_vendedor

CREATE VIEW vista_viviendas_disponibles AS -- DROP view vista_viviendas_disponibles
	SELECT 
    v.n_rol,
    t.nombre AS 'tipo',
    d.nombre AS 'es_disponible',
    v.precio_arriendo,
    v.precio_venta,
    v.cant_banios,
    v.cant_piezas,
    v.direccion,
    (SELECT is_usado(v.condicion)) AS 'condicion'
    FROM
    vivienda v
    INNER JOIN tipo_vivienda t ON t.id = v.tipo_fk
    INNER JOIN disponibilidad_vivienda d ON d.id = v.dis_vivienda
    WHERE
    d.nombre = 'Disponible';
    
 --   SELECT * FROM vista_viviendas_disponibles

CREATE VIEW vista_logs AS
    SELECT
        l.fecha,
        l.descripcion,
        l.usuario_fk,
        u.nombre
    FROM
        log l
        INNER JOIN usuario u ON u.run = l.usuario_fk

--SELECT * FROM vista_logs

DELIMITER $$
CREATE FUNCTION crear_vendedor (nuevo_run VARCHAR(15),nuevo_nombre VARCHAR(50), run_user VARCHAR (50)) RETURNS INT
BEGIN
	DECLARE existe_run INT;
    
    SET existe_run = (SELECT COUNT(*) FROM usuario WHERE nuevo_run = run);    
    
    IF existe_run = 0 THEN
    
    INSERT INTO usuario VALUES (nuevo_run,nuevo_nombre,2);
    INSERT INTO log VALUES (NULL,CONCAT('Se registra al vendedor de run ',nuevo_run),now(),run_user);
        
    RETURN 1;
    
    END IF;    
    RETURN 0;
END $$
DELIMITER ;

-- SELECT crear_vendedor ('22-2','Alexis Zuniga','11-1');

-- DROP FUNCTION crear_cliente

DELIMITER $$
CREATE FUNCTION crear_cliente (nuevo_run VARCHAR(15),nuevo_nombre VARCHAR(50),nuevo_sueldo INT, run_user VARCHAR (50)) RETURNS INT
BEGIN
	DECLARE existe_run INT;
    
    SET existe_run = (SELECT COUNT(*) FROM cliente WHERE nuevo_run = run);    
    IF existe_run = 0 THEN
    
    INSERT INTO cliente VALUES (nuevo_run,nuevo_nombre,nuevo_sueldo);
    INSERT INTO log VALUES (NULL,CONCAT('Se registra al cliente de run ',nuevo_run),now(),run_user);
        
    RETURN existe_run;
    
    END IF;    
    RETURN existe_run;
END $$
DELIMITER ;

-- SELECT crear_cliente ('33-3','Marcelo Aranda',250000,'22-2');

-- DROP FUNCTION crear_vivienda
-- INSERT INTO vivienda VALUES (1111,1,1,280000,56000000,2,3,'Agustin Palominos #429',1);
-- INSERT INTO vivienda VALUES (1211,1,3,280000,56000000,2,3,'Agustin Palominos #429',1);
-- INSERT INTO vivienda VALUES (1311,1,2,280000,56000000,2,3,'Agustin Palominos #429',1);
-- INSERT INTO vivienda VALUES (3000,2,3,280000,56000000,2,3,'Agustin Palominos #429',1);

DELIMITER $$
CREATE FUNCTION crear_vivienda (nuevo_rol INT,set_tipo INT,disponibilidad INT,precio_a INT,precio_v INT,banios INT,piezas INT, new_direccion VARCHAR (50),new_condicion BOOLEAN, run_user VARCHAR (15)) RETURNS INT
BEGIN
	DECLARE existe_rol BOOLEAN;
    DECLARE rol VARCHAR(50);
    
    SET existe_rol = (SELECT COUNT(*) FROM vivienda WHERE nuevo_rol = n_rol);
    
    IF existe_rol = 0 THEN
    
    INSERT INTO vivienda VALUES (nuevo_rol,set_tipo,disponibilidad,precio_a,precio_v,banios,piezas,new_direccion,new_condicion);    
    INSERT INTO log VALUES (NULL,CONCAT('Se registra la vivienda de nº de rol ',nuevo_rol),now(),run_user);
        
    RETURN 1;
    
    END IF;    
    RETURN 0;
END $$
DELIMITER ;

-- SELECT crear_vivienda (333212,1,3,250000,98000000,3,12,'Aasdddddddddddasasrga #0149',1,'11-1');
-- SELECT crear_vivienda (123,1,3,250000,98000000,3,12,'Aasdddddddddddasasrga #0149',1,'11-1');
-- SELECT crear_vivienda (124,1,3,250344,98000000,3,12,'Aasdddddddddddasasrga #0149',1,'11-1');
-- SELECT crear_vivienda (125,1,3,260002,98000000,3,12,'Aasdddddddddddasasrga #0149',1,'11-1');

DELIMITER $$
CREATE PROCEDURE nuevo_log (detalle VARCHAR(200),run_user VARCHAR(200))
BEGIN   
    INSERT INTO log VALUES (NULL,detalle,now(),run_user);
END $$
DELIMITER ;

CALL nuevo_log('holaaaah','11-1');