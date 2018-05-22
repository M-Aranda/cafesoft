-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cafesoft
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `run` varchar(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `sueldo` int(11) DEFAULT NULL,
  PRIMARY KEY (`run`),
  UNIQUE KEY `run` (`run`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('12.367.354-9','Miriam',12312312),('17-5','julia muñoz',250000),('19017603-7','Franco Barrera',80000),('ilios','ololo',2000);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_fk` varchar(15) DEFAULT NULL,
  `usuario_fk` varchar(15) DEFAULT NULL,
  `vivienda_fk` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_fk` (`cliente_fk`),
  KEY `usuario_fk` (`usuario_fk`),
  KEY `vivienda_fk` (`vivienda_fk`),
  CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`cliente_fk`) REFERENCES `cliente` (`run`),
  CONSTRAINT `contrato_ibfk_2` FOREIGN KEY (`usuario_fk`) REFERENCES `usuario` (`run`),
  CONSTRAINT `contrato_ibfk_3` FOREIGN KEY (`vivienda_fk`) REFERENCES `vivienda` (`n_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,'19017603-7','22-2',65454,'2018-05-22'),(2,'ilios','22-2',12345,'2018-05-22'),(3,'17-5','16-6',245002,'2018-05-22');
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidad_vivienda`
--

DROP TABLE IF EXISTS `disponibilidad_vivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disponibilidad_vivienda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidad_vivienda`
--

LOCK TABLES `disponibilidad_vivienda` WRITE;
/*!40000 ALTER TABLE `disponibilidad_vivienda` DISABLE KEYS */;
INSERT INTO `disponibilidad_vivienda` VALUES (1,'Arrendada'),(2,'Vendida'),(3,'Disponible');
/*!40000 ALTER TABLE `disponibilidad_vivienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `estat_default`
--

DROP TABLE IF EXISTS `estat_default`;
/*!50001 DROP VIEW IF EXISTS `estat_default`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `estat_default` AS SELECT 
 1 AS `Name_exp_1`,
 1 AS `Name_exp_2`,
 1 AS `(SELECT COUNT(*) FROM vivienda WHERE dis_vivienda != '3')`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `usuario_fk` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_fk` (`usuario_fk`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`usuario_fk`) REFERENCES `usuario` (`run`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'holaaaah','2018-05-22 11:42:20','11-1'),(2,'vendedor Ha iniciado sesion','2018-05-22 11:42:29','22-2'),(3,'vendedor ha salido de CafeSoft','2018-05-22 11:43:02','22-2'),(4,'admin Ha iniciado sesion','2018-05-22 11:46:17','11-1'),(5,'admin ha salido de CafeSoft','2018-05-22 11:46:20','11-1'),(6,'vendedor Ha iniciado sesion','2018-05-22 11:46:30','22-2'),(7,'vendedor ha salido de CafeSoft','2018-05-22 11:46:45','22-2'),(8,'vendedor Ha iniciado sesion','2018-05-22 12:34:18','22-2'),(9,'vendedor ha salido de CafeSoft','2018-05-22 12:34:42','22-2'),(10,'admin Ha iniciado sesion','2018-05-22 12:34:47','11-1'),(11,'admin ha salido de CafeSoft','2018-05-22 12:34:59','11-1'),(12,'vendedor Ha iniciado sesion','2018-05-22 12:35:11','22-2'),(13,'Se registra al cliente de run 19017603-7','2018-05-22 12:35:35','22-2'),(14,'vendedor ha salido de CafeSoft','2018-05-22 12:35:49','22-2'),(15,'admin Ha iniciado sesion','2018-05-22 12:35:52','11-1'),(16,'Se registra la vivienda de nº de rol 12345','2018-05-22 12:36:47','11-1'),(17,'admin ha salido de CafeSoft','2018-05-22 12:37:30','11-1'),(18,'vendedor Ha iniciado sesion','2018-05-22 12:37:34','22-2'),(19,'vendedor ha salido de CafeSoft','2018-05-22 12:37:53','22-2'),(20,'vendedor Ha iniciado sesion','2018-05-22 12:46:16','22-2'),(21,'Se registra al cliente de run 12.367.354-9','2018-05-22 12:46:48','22-2'),(22,'vendedor ha salido de CafeSoft','2018-05-22 12:48:02','22-2'),(23,'admin Ha iniciado sesion','2018-05-22 12:48:13','11-1'),(24,'Se registra la vivienda de nº de rol 65454','2018-05-22 12:48:35','11-1'),(25,'admin ha salido de CafeSoft','2018-05-22 12:48:39','11-1'),(26,'vendedor Ha iniciado sesion','2018-05-22 12:48:48','22-2'),(27,'Se crea contrato de venta de la vivienda de numero de rol 65454 para el/la cliente de run 19017603-7 conocido/a como Franco Barrera','2018-05-22 12:50:36','22-2'),(28,'vendedor ha salido de CafeSoft','2018-05-22 12:50:54','22-2'),(29,'admin Ha iniciado sesion','2018-05-22 12:54:59','11-1'),(30,'admin ha salido de CafeSoft','2018-05-22 12:56:10','11-1'),(31,'vendedor Ha iniciado sesion','2018-05-22 12:56:19','22-2'),(32,'vendedor ha salido de CafeSoft','2018-05-22 12:58:15','22-2'),(33,'vendedor Ha iniciado sesion','2018-05-22 13:04:52','22-2'),(34,'vendedor ha salido de CafeSoft','2018-05-22 13:05:19','22-2'),(35,'vendedor Ha iniciado sesion','2018-05-22 13:08:53','22-2'),(36,'vendedor ha salido de CafeSoft','2018-05-22 13:09:40','22-2'),(37,'vendedor Ha iniciado sesion','2018-05-22 13:27:48','22-2'),(38,'Se registra al cliente de run ilios','2018-05-22 13:28:12','22-2'),(39,'Se crea contrato de venta de la vivienda de numero de rol 12345 para el/la cliente de run ilios conocido/a como ololo','2018-05-22 13:28:49','22-2'),(40,'vendedor ha salido de CafeSoft','2018-05-22 13:28:56','22-2'),(41,'admin Ha iniciado sesion','2018-05-22 13:29:05','11-1'),(42,'Se registra al vendedor de run 20','2018-05-22 13:29:17','11-1'),(43,'Se registra la vivienda de nº de rol 12333','2018-05-22 13:29:59','11-1'),(44,'admin ha salido de CafeSoft','2018-05-22 13:31:18','11-1'),(45,'vendedor Ha iniciado sesion','2018-05-22 13:31:36','22-2'),(46,'vendedor ha salido de CafeSoft','2018-05-22 13:34:48','22-2'),(47,'admin Ha iniciado sesion','2018-05-22 13:40:37','11-1'),(48,'admin ha salido de CafeSoft','2018-05-22 13:41:29','11-1'),(49,'vendedor Ha iniciado sesion','2018-05-22 13:41:33','22-2'),(50,'vendedor ha salido de CafeSoft','2018-05-22 13:41:49','22-2'),(51,'admin Ha iniciado sesion','2018-05-22 13:41:52','11-1'),(52,'Se registra la vivienda de nº de rol 44444','2018-05-22 13:42:24','11-1'),(53,'admin ha salido de CafeSoft','2018-05-22 13:42:41','11-1'),(54,'vendedor Ha iniciado sesion','2018-05-22 13:42:44','22-2'),(55,'vendedor ha salido de CafeSoft','2018-05-22 13:42:56','22-2'),(56,'admin Ha iniciado sesion','2018-05-22 13:42:59','11-1'),(57,'admin ha salido de CafeSoft','2018-05-22 13:43:25','11-1'),(58,'vendedor Ha iniciado sesion','2018-05-22 13:43:35','22-2'),(59,'vendedor ha salido de CafeSoft','2018-05-22 13:44:16','22-2'),(60,'vendedor Ha iniciado sesion','2018-05-22 13:52:25','22-2'),(61,'vendedor ha salido de CafeSoft','2018-05-22 13:52:46','22-2'),(62,'admin Ha iniciado sesion','2018-05-22 14:00:27','11-1'),(63,'Se registra la vivienda de nº de rol 245002','2018-05-22 14:02:09','11-1'),(64,'admin ha salido de CafeSoft','2018-05-22 14:02:32','11-1'),(65,'vendedor Ha iniciado sesion','2018-05-22 14:02:35','22-2'),(66,'vendedor ha salido de CafeSoft','2018-05-22 14:03:40','22-2'),(67,'admin Ha iniciado sesion','2018-05-22 14:03:43','11-1'),(68,'admin ha salido de CafeSoft','2018-05-22 14:07:40','11-1'),(69,'admin Ha iniciado sesion','2018-05-22 14:07:49','11-1'),(70,'Se registra al vendedor de run 16-6','2018-05-22 14:09:16','11-1'),(71,'admin ha salido de CafeSoft','2018-05-22 14:09:34','11-1'),(72,'Patricio Perez Ha iniciado sesion','2018-05-22 14:09:39','16-6'),(73,'Patricio Perez ha salido de CafeSoft','2018-05-22 14:09:58','16-6'),(74,'admin Ha iniciado sesion','2018-05-22 14:10:00','11-1'),(75,'admin ha salido de CafeSoft','2018-05-22 14:13:44','11-1'),(76,'Patricio Perez Ha iniciado sesion','2018-05-22 14:13:48','16-6'),(77,'Se registra al cliente de run 17-5','2018-05-22 14:15:31','16-6'),(78,'Se crea contrato de arriendo de la vivienda de numero de rol 245002 para el/la cliente de run 17-5 conocido/a como julia muñoz','2018-05-22 14:18:58','16-6'),(79,'Patricio Perez ha salido de CafeSoft','2018-05-22 14:20:50','16-6'),(80,'admin Ha iniciado sesion','2018-05-22 14:20:54','11-1'),(81,'Se registra la vivienda de nº de rol 666','2018-05-22 14:21:32','11-1'),(82,'admin ha salido de CafeSoft','2018-05-22 14:21:36','11-1'),(83,'Patricio Perez Ha iniciado sesion','2018-05-22 14:21:39','16-6'),(84,'Patricio Perez ha salido de CafeSoft','2018-05-22 14:23:01','16-6'),(85,'vendedor Ha iniciado sesion','2018-05-22 14:25:42','22-2'),(86,'vendedor Ha iniciado sesion','2018-05-22 14:27:32','22-2'),(87,'vendedor ha salido de CafeSoft','2018-05-22 14:29:50','22-2'),(88,'admin Ha iniciado sesion','2018-05-22 14:31:23','11-1'),(89,'admin Ha iniciado sesion','2018-05-22 14:31:53','11-1'),(90,'admin Ha iniciado sesion','2018-05-22 14:34:47','11-1'),(91,'admin ha salido de CafeSoft','2018-05-22 14:36:50','11-1'),(92,'admin Ha iniciado sesion','2018-05-22 14:47:27','11-1'),(93,'admin ha salido de CafeSoft','2018-05-22 14:49:39','11-1'),(94,'admin Ha iniciado sesion','2018-05-22 14:49:44','11-1'),(95,'admin Ha iniciado sesion','2018-05-22 14:54:39','11-1'),(96,'admin Ha iniciado sesion','2018-05-22 14:59:28','11-1'),(97,'admin ha salido de CafeSoft','2018-05-22 14:59:38','11-1'),(98,'admin Ha iniciado sesion','2018-05-22 15:00:14','11-1'),(99,'admin Ha iniciado sesion','2018-05-22 15:05:11','11-1'),(100,'admin creo el punto de restauracion backup','2018-05-22 15:05:14','11-1'),(101,'admin ha salido de CafeSoft','2018-05-22 15:05:27','11-1'),(102,'admin Ha iniciado sesion','2018-05-22 15:05:41','11-1'),(103,'admin creo el punto de restauracion backup','2018-05-22 15:05:45','11-1'),(104,'admin ha salido de CafeSoft','2018-05-22 15:05:58','11-1'),(105,'admin Ha iniciado sesion','2018-05-22 15:06:16','11-1'),(106,'admin ha restaurado desde backup 2242018 15620.sql','2018-05-22 15:08:36','11-1'),(107,'admin Ha iniciado sesion','2018-05-22 15:09:20','11-1');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'Administrador'),(2,'Vendedor');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_vivienda`
--

DROP TABLE IF EXISTS `tipo_vivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_vivienda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_vivienda`
--

LOCK TABLES `tipo_vivienda` WRITE;
/*!40000 ALTER TABLE `tipo_vivienda` DISABLE KEYS */;
INSERT INTO `tipo_vivienda` VALUES (1,'Casa'),(2,'Departamento');
/*!40000 ALTER TABLE `tipo_vivienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `run` varchar(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`run`),
  KEY `tipo_fk` (`tipo_fk`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`tipo_fk`) REFERENCES `tipo_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('11-1','admin',1),('16-6','Patricio Perez',2),('20','marcelo',2),('22-2','vendedor',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vista_estadisticas_viviendas`
--

DROP TABLE IF EXISTS `vista_estadisticas_viviendas`;
/*!50001 DROP VIEW IF EXISTS `vista_estadisticas_viviendas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vista_estadisticas_viviendas` AS SELECT 
 1 AS `n_rol`,
 1 AS `tipo`,
 1 AS `es_disponible`,
 1 AS `precio_arriendo`,
 1 AS `precio_venta`,
 1 AS `cant_banios`,
 1 AS `cant_piezas`,
 1 AS `direccion`,
 1 AS `cliente_fk`,
 1 AS `usuario_fk`,
 1 AS `fecha`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_logs`
--

DROP TABLE IF EXISTS `vista_logs`;
/*!50001 DROP VIEW IF EXISTS `vista_logs`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vista_logs` AS SELECT 
 1 AS `fecha`,
 1 AS `descripcion`,
 1 AS `usuario_fk`,
 1 AS `nombre`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_viviendas_disponibles`
--

DROP TABLE IF EXISTS `vista_viviendas_disponibles`;
/*!50001 DROP VIEW IF EXISTS `vista_viviendas_disponibles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vista_viviendas_disponibles` AS SELECT 
 1 AS `n_rol`,
 1 AS `tipo`,
 1 AS `es_disponible`,
 1 AS `precio_arriendo`,
 1 AS `precio_venta`,
 1 AS `cant_banios`,
 1 AS `cant_piezas`,
 1 AS `direccion`,
 1 AS `condicion`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `vivienda`
--

DROP TABLE IF EXISTS `vivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vivienda` (
  `n_rol` int(11) NOT NULL,
  `tipo_fk` int(11) DEFAULT NULL,
  `dis_vivienda` int(11) DEFAULT NULL,
  `precio_arriendo` int(11) DEFAULT NULL,
  `precio_venta` int(11) DEFAULT NULL,
  `cant_banios` int(11) DEFAULT NULL,
  `cant_piezas` int(11) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `condicion` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`n_rol`),
  KEY `tipo_fk` (`tipo_fk`),
  KEY `dis_vivienda` (`dis_vivienda`),
  CONSTRAINT `vivienda_ibfk_1` FOREIGN KEY (`tipo_fk`) REFERENCES `tipo_vivienda` (`id`),
  CONSTRAINT `vivienda_ibfk_2` FOREIGN KEY (`dis_vivienda`) REFERENCES `disponibilidad_vivienda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vivienda`
--

LOCK TABLES `vivienda` WRITE;
/*!40000 ALTER TABLE `vivienda` DISABLE KEYS */;
INSERT INTO `vivienda` VALUES (666,2,3,666666,1,2,3,'Villa Infierno',0),(12333,1,2,300,900,2,2,'cualquiera',0),(12345,1,2,450000,63000000,2,8,'Palominos 429',0),(44444,1,3,2344430,523340,530,350,'yyyyyyyy',1),(65454,2,2,12312410,-1949513846,430,56340,'wwewe',1),(245002,1,1,280000,50000000,2,3,'Villa Florencia',0);
/*!40000 ALTER TABLE `vivienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `estat_default`
--

/*!50001 DROP VIEW IF EXISTS `estat_default`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `estat_default` AS select (select count(0) from `vivienda` where ((`vivienda`.`dis_vivienda` <> '3') and (`vivienda`.`tipo_fk` = '1'))) AS `Name_exp_1`,(select count(0) from `vivienda` where ((`vivienda`.`dis_vivienda` <> '3') and (`vivienda`.`tipo_fk` = '2'))) AS `Name_exp_2`,(select count(0) from `vivienda` where (`vivienda`.`dis_vivienda` <> '3')) AS `(SELECT COUNT(*) FROM vivienda WHERE dis_vivienda != '3')` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_estadisticas_viviendas`
--

/*!50001 DROP VIEW IF EXISTS `vista_estadisticas_viviendas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_estadisticas_viviendas` AS select `v`.`n_rol` AS `n_rol`,`t`.`nombre` AS `tipo`,`d`.`nombre` AS `es_disponible`,`v`.`precio_arriendo` AS `precio_arriendo`,`v`.`precio_venta` AS `precio_venta`,`v`.`cant_banios` AS `cant_banios`,`v`.`cant_piezas` AS `cant_piezas`,`v`.`direccion` AS `direccion`,`c`.`cliente_fk` AS `cliente_fk`,`c`.`usuario_fk` AS `usuario_fk`,`c`.`fecha` AS `fecha` from (((`vivienda` `v` join `tipo_vivienda` `t` on((`t`.`id` = `v`.`tipo_fk`))) join `disponibilidad_vivienda` `d` on((`d`.`id` = `v`.`dis_vivienda`))) join `contrato` `c` on((`c`.`vivienda_fk` = `v`.`n_rol`))) where ((`d`.`nombre` = 'Arrendada') or (`d`.`nombre` = 'Vendida')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_logs`
--

/*!50001 DROP VIEW IF EXISTS `vista_logs`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_logs` AS select `l`.`fecha` AS `fecha`,`l`.`descripcion` AS `descripcion`,`l`.`usuario_fk` AS `usuario_fk`,`u`.`nombre` AS `nombre` from (`log` `l` join `usuario` `u` on((`u`.`run` = `l`.`usuario_fk`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_viviendas_disponibles`
--

/*!50001 DROP VIEW IF EXISTS `vista_viviendas_disponibles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_viviendas_disponibles` AS select `v`.`n_rol` AS `n_rol`,`t`.`nombre` AS `tipo`,`d`.`nombre` AS `es_disponible`,`v`.`precio_arriendo` AS `precio_arriendo`,`v`.`precio_venta` AS `precio_venta`,`v`.`cant_banios` AS `cant_banios`,`v`.`cant_piezas` AS `cant_piezas`,`v`.`direccion` AS `direccion`,(select `is_usado`(`v`.`condicion`)) AS `condicion` from ((`vivienda` `v` join `tipo_vivienda` `t` on((`t`.`id` = `v`.`tipo_fk`))) join `disponibilidad_vivienda` `d` on((`d`.`id` = `v`.`dis_vivienda`))) where (`d`.`nombre` = 'Disponible') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-22 15:10:01
