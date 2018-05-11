CREATE DATABASE  IF NOT EXISTS `fog_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fog_test`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 159.89.19.132    Database: fog
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.17.10.1

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
-- Table structure for table `cladding`
--

DROP TABLE IF EXISTS `cladding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cladding` (
  `idcladding` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(280) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`idcladding`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cladding`
--

LOCK TABLES `cladding` WRITE;
/*!40000 ALTER TABLE `cladding` DISABLE KEYS */;
INSERT INTO `cladding` VALUES (8,'whitewoodk','This is a desciption of the woodtype!',4),(19,'Oak','Test ',123);
/*!40000 ALTER TABLE `cladding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `roleid` int(11) DEFAULT NULL,
  `firstname` varchar(25) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `employed` tinyint(4) NOT NULL DEFAULT '1',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `reset_password` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idemployee`,`username`),
  KEY `fk_employee_role_idx` (`roleid`),
  CONSTRAINT `fk_employee_role` FOREIGN KEY (`roleid`) REFERENCES `role` (`idrole`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'ADLA',2,'Trine','Larsen','1234','adlass97@gmail.com',1,'2018-05-06 20:12:58',0),(2,'USERNAME',2,'hej','pelt','|ytwiqmOz?s|y0uot','hej@gmail.com',1,'2018-05-08 11:01:59',1),(3,'nikop',2,'Perlt','PErlt','hej','karron11ea@gmail.com',0,'2018-05-08 18:46:29',1),(4,'perlten',1,'Nikolai','Perlt','perlt','karron11ea@gmail.com',0,'2018-05-10 12:58:36',0),(5,'Perlt3',1,'PErlt','PErlte','ÓÆÌÂÏÂÉÚÑÊ','karron11ea@gmail.com',0,'2018-05-10 13:00:50',1),(6,'p',1,'Niko','p','perlt','karron11ea@gmail.com',0,'2018-05-10 13:01:38',1),(7,'Flotte',1,'Tilde','Sjovsen','tilde','karron11ea@gmail.com',1,'2018-05-10 13:11:15',0),(8,'hej',2,'Nikolai','perlt','bcqnwpfwiilpxbsjluap','karron11ea@gmail.com',1,'2018-05-10 13:13:56',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `idevent` int(11) NOT NULL AUTO_INCREMENT,
  `idevent_type` int(11) NOT NULL,
  `idorder` int(11) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `employee` int(11) DEFAULT NULL,
  PRIMARY KEY (`idevent`),
  KEY `fk_event_order_idx` (`idorder`),
  KEY `fk_event_event_type_idx` (`idevent_type`),
  KEY `fk_event_employee_idx` (`employee`),
  CONSTRAINT `fk_event_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_event_event_type` FOREIGN KEY (`idevent_type`) REFERENCES `event_type` (`idevent_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_order` FOREIGN KEY (`idorder`) REFERENCES `order` (`idorder`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (10,1,1,'2018-05-08 20:16:12',1),(11,1,1,'2018-05-08 20:16:12',1),(12,1,1,'2018-05-08 21:12:03',NULL),(13,1,1,'2018-05-08 21:12:20',NULL),(14,1,1,'2018-05-08 21:12:27',NULL),(16,1,61,'2018-05-08 21:29:42',NULL),(17,1,62,'2018-05-08 21:36:28',NULL),(18,1,63,'2018-05-08 21:50:30',NULL),(19,1,64,'2018-05-08 21:59:58',NULL),(20,1,65,'2018-05-08 22:11:28',NULL),(21,1,66,'2018-05-08 22:12:27',NULL),(22,1,67,'2018-05-08 22:14:53',NULL),(23,1,68,'2018-05-08 22:15:42',NULL),(24,1,69,'2018-05-08 22:20:43',NULL),(25,1,70,'2018-05-08 22:22:07',NULL),(26,1,71,'2018-05-08 22:23:46',NULL),(27,1,72,'2018-05-08 22:30:18',NULL),(28,1,73,'2018-05-08 22:33:36',NULL),(29,1,74,'2018-05-08 22:34:43',NULL),(30,1,74,'2018-05-08 22:34:43',NULL),(31,1,74,'2018-05-08 22:34:43',NULL),(32,1,75,'2018-05-08 22:38:18',NULL),(33,2,75,'2018-05-08 22:45:37',NULL),(34,1,76,'2018-05-08 22:48:27',NULL),(35,1,77,'2018-05-08 22:49:12',NULL),(36,1,78,'2018-05-08 22:49:43',NULL),(37,1,79,'2018-05-08 22:51:38',NULL),(38,2,79,'2018-05-08 22:53:32',NULL),(39,1,80,'2018-05-08 22:54:45',NULL),(40,1,81,'2018-05-08 22:55:29',NULL),(41,1,82,'2018-05-08 22:57:21',NULL),(42,1,83,'2018-05-08 22:58:37',NULL),(43,1,84,'2018-05-08 23:01:08',NULL),(44,2,84,'2018-05-08 23:01:49',NULL),(45,1,85,'2018-05-09 06:37:29',NULL),(46,2,84,'2018-05-09 06:38:08',NULL),(47,2,85,'2018-05-09 06:38:13',NULL),(48,1,86,'2018-05-09 06:46:52',NULL),(49,2,77,'2018-05-09 07:49:05',NULL),(50,1,87,'2018-05-09 07:57:33',NULL),(51,2,NULL,'2018-05-09 10:00:42',1),(52,2,87,'2018-05-09 10:01:36',NULL),(53,2,86,'2018-05-09 10:01:41',NULL),(54,1,88,'2018-05-09 10:26:16',NULL),(55,1,89,'2018-05-09 10:53:22',NULL),(56,1,90,'2018-05-09 10:55:57',NULL),(57,1,91,'2018-05-09 10:58:53',NULL),(58,1,92,'2018-05-09 11:01:14',NULL),(59,2,91,'2018-05-09 11:11:40',NULL),(60,1,93,'2018-05-09 11:58:01',NULL),(61,1,94,'2018-05-09 12:08:22',NULL),(62,2,94,'2018-05-09 12:13:00',NULL),(63,2,94,'2018-05-09 12:13:25',NULL),(64,2,92,'2018-05-09 14:55:03',NULL),(65,2,94,'2018-05-09 15:02:13',NULL),(66,1,95,'2018-05-09 15:05:19',NULL),(67,2,87,'2018-05-09 15:31:59',NULL),(68,1,96,'2018-05-09 15:59:20',NULL),(69,1,97,'2018-05-09 16:00:42',NULL),(70,1,98,'2018-05-09 16:01:35',NULL),(71,2,98,'2018-05-09 16:05:06',NULL),(72,1,99,'2018-05-09 16:12:30',NULL),(73,2,98,'2018-05-09 16:13:16',NULL),(74,2,99,'2018-05-09 16:13:21',NULL),(75,2,96,'2018-05-09 16:48:30',NULL),(76,5,98,'2018-05-10 10:09:09',NULL),(77,4,98,'2018-05-10 10:09:33',NULL),(78,2,98,'2018-05-10 10:09:49',NULL),(80,1,100,'2018-05-10 15:35:08',NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_type` (
  `idevent_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `access_level` int(11) NOT NULL DEFAULT '0',
  `title` varchar(45) NOT NULL,
  `description` varchar(205) NOT NULL,
  `statuscolor` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`idevent_type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_type`
--

LOCK TABLES `event_type` WRITE;
/*!40000 ALTER TABLE `event_type` DISABLE KEYS */;
INSERT INTO `event_type` VALUES (1,'Order Created',0,'Request Submitted!','You have received an email with the details of your order. We will contact you once we have processed your request.',''),(2,'Order Confirmed',0,'Order Confirmed','Your order has been comfirmed by one of our employees. You will be contacted regarding the payment soon.','bg-success text-white'),(3,'Order Deleted',0,'Order has been deleted!','Order was deleted.','bg-danger text-white'),(4,'Order Updated',0,'Order updated!','Your order has been updated with new specifications.','bg-warning text-white'),(5,'Order Unconfirmed',0,'Order has been unconfirmed','Your order was unconfirmed by one of our employees. Please contact our support team for more information!','bg-danger text-white'),(6,'Employee Login',2,'Employee logged in','Employee logged in','bg-light text-dark');
/*!40000 ALTER TABLE `event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `idorder` int(11) NOT NULL AUTO_INCREMENT,
  `confirmed` tinyint(4) NOT NULL DEFAULT '0',
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `phonenumber` int(11) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `roofangle` double DEFAULT NULL,
  `shed` tinyint(4) NOT NULL DEFAULT '0',
  `shed_length` int(11) DEFAULT NULL,
  `shed_width` int(11) DEFAULT NULL,
  `tile` int(11) DEFAULT NULL,
  `cladding` int(11) DEFAULT NULL,
  PRIMARY KEY (`idorder`),
  KEY `fk_order_tile1_idx` (`tile`),
  KEY `fk_order_cladding_idx` (`cladding`),
  CONSTRAINT `fk_order_cladding` FOREIGN KEY (`cladding`) REFERENCES `cladding` (`idcladding`) ON DELETE SET NULL,
  CONSTRAINT `fk_order_tile` FOREIGN KEY (`tile`) REFERENCES `tile` (`idtile`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'2018-05-04 10:27:36','Nioklai','perlt','kd@gmail.com',232,700,600,200,0,0,0,0,14,8),(2,1,'2018-05-04 11:25:04','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,1000,0,0,0,0,NULL,8),(3,0,'2018-05-04 11:35:41','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,45,1,300,600,14,8),(4,1,'2018-05-04 11:48:58','Adam','Lass','adlass97@gmail.com',20681825,700,600,100,0,1,200,540,14,8),(5,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(6,1,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(7,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(9,1,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(10,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(11,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(12,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,700,600,100,0,1,600,500,14,8),(13,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(14,1,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(15,0,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(16,1,'2018-05-05 00:19:52','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(17,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(18,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(19,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(20,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(21,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(22,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(23,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(24,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(25,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(26,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(27,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(28,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(29,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(30,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(31,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(32,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(33,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(34,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(35,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(36,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(37,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(38,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(39,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(40,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(41,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(42,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(43,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(44,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(45,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(46,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(47,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(48,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(49,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(50,1,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(51,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(52,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(53,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(54,0,'2018-05-05 00:19:53','ds','DA','kar.gmail.com',321,100,100,100,0,0,0,0,14,8),(55,1,'2018-05-05 01:49:48','nikop','nioka','karron11ea@gmail.com',232,100,100,100,0,0,0,0,14,8),(56,0,'2018-05-05 12:48:55','nikopp','nikoppp','karron11ea@gmail.com',2321,100,100,500,0,0,0,0,14,8),(57,1,'2018-05-05 13:56:01','dsa','dsa','karron11ea@gmail.com',231,100,100,100,0,0,0,0,14,8),(58,0,'2018-05-07 09:00:52','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,1000,12,1,12,14,14,19),(59,0,'2018-05-07 09:02:26','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,1000,23,0,0,0,14,19),(60,0,'2018-05-08 21:27:52','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,17,8),(61,0,'2018-05-08 21:29:42','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,17,8),(62,0,'2018-05-08 21:36:28','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,17,8),(63,0,'2018-05-08 21:50:30','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,500,0,0,0,0,17,8),(64,0,'2018-05-08 21:59:58','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,19),(65,0,'2018-05-08 22:11:28','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(66,0,'2018-05-08 22:12:27','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(67,0,'2018-05-08 22:14:53','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(68,0,'2018-05-08 22:15:42','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(69,0,'2018-05-08 22:20:43','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,17,19),(70,0,'2018-05-08 22:22:07','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(71,0,'2018-05-08 22:23:46','Adam','Lass','adlass97@gmail.com',20681825,100,1000,100,0,0,0,0,14,8),(72,0,'2018-05-08 22:30:18','Adam','Lass','adlass97@gmail.com',20681825,100,1000,100,0,0,0,0,14,8),(73,0,'2018-05-08 22:33:36','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(74,0,'2018-05-08 22:34:43','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,19),(75,1,'2018-05-08 22:38:18','Adam','Lass','adlass97@gmail.com',20681825,1212,1212,122,0,0,0,0,14,8),(76,0,'2018-05-08 22:48:27','Adam','Lass','adlass97@gmail.com',20681825,1000,100,100,0,0,0,0,14,8),(77,1,'2018-05-08 22:49:12','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,19),(78,0,'2018-05-08 22:49:43','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(79,1,'2018-05-08 22:51:38','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(80,0,'2018-05-08 22:54:45','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(81,0,'2018-05-08 22:55:29','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(82,0,'2018-05-08 22:57:21','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,17,19),(83,0,'2018-05-08 22:58:37','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,19),(84,1,'2018-05-08 23:01:08','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,14,8),(85,1,'2018-05-09 06:37:29','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,19),(86,1,'2018-05-09 06:46:52','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,17,8),(87,1,'2018-05-09 07:57:33','Nikolai','perlt','karron11ea@gmail.com',321,500,500,500,0,1,200,200,14,8),(88,0,'2018-05-09 10:26:16','Nikolai','Perlt','karron11ea@gmail.com',231312,200,200,200,0,0,0,0,17,8),(89,0,'2018-05-09 10:53:22','Nikolai','sda','karron11ea@gmail.com',232131,100,100,100,0,1,100,40,17,19),(90,0,'2018-05-09 10:55:57','Nikolai','perlt','karron11ea@gmail.com',12312,100,100,100,0,0,0,0,14,8),(91,1,'2018-05-09 10:58:53','Nikolai','perlt','karron11ea@gmail.com',2312,100,100,100,0,0,0,0,14,8),(92,1,'2018-05-09 11:01:14','Nikolai','perlt','karron11ea@gmail.com',2131,100,100,100,0,0,0,0,14,8),(93,0,'2018-05-09 11:58:01','Adam','Lass','adlass97@gmail.com',20681825,1000,10000,100,0,0,0,0,14,8),(94,1,'2018-05-09 12:08:22','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,1,200,940,17,19),(95,0,'2018-05-09 15:05:19','Nikolai','Perlt','karron11ea@gmail.com',2321321,100,100,100,0,0,0,0,17,8),(96,1,'2018-05-09 15:59:20','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,14,8),(97,0,'2018-05-09 16:00:42','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,1,400,400,17,8),(98,1,'2018-05-09 16:01:35','Adam','Lass','adlass97@gmail.com',20681825,100,100,100,0,0,0,0,17,8),(99,1,'2018-05-09 16:12:30','Adam','Lass','adlass97@gmail.com',20681825,1000,1000,100,0,0,0,0,17,8),(100,0,'2018-05-10 15:35:08','Adam','Lass','adlass97@gmail.com',20681825,180,120,180,2,0,0,0,14,19);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `authentication_level` int(2) NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'salesman'),(2,2,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tile`
--

DROP TABLE IF EXISTS `tile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tile` (
  `idtile` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(280) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`idtile`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tile`
--

LOCK TABLES `tile` WRITE;
/*!40000 ALTER TABLE `tile` DISABLE KEYS */;
INSERT INTO `tile` VALUES (14,'Adams','Adam har lanceret hans helt egen tile. han er meget stolt og glaeder sig super meget til at vise jeg hvad han har arbejdet paa',75),(17,'Nikolai Tile','Bedre end Adams ',100);
/*!40000 ALTER TABLE `tile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-11 11:29:36
