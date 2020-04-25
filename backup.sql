-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: computer_catalogation_system
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer` (
  `cabinet_model` varchar(255) DEFAULT NULL,
  `has_cd_burner` bit(1) DEFAULT NULL,
  `host_name` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `mother_board_name` varchar(255) DEFAULT NULL,
  `on_the_domain` bit(1) NOT NULL,
  `operating_system` int(11) NOT NULL,
  `operating_system_architecture` int(11) NOT NULL,
  `total_ram_memory` double DEFAULT NULL,
  `total_storage_memory` double DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `monitor_id` bigint(20) DEFAULT NULL,
  `processor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdekrnj6t7iw8t4yonqjucvi03` (`monitor_id`),
  KEY `FK3jbp9x2se63ij7uswppbuiysx` (`processor_id`),
  CONSTRAINT `FK3jbp9x2se63ij7uswppbuiysx` FOREIGN KEY (`processor_id`) REFERENCES `processor` (`id`),
  CONSTRAINT `FKdekrnj6t7iw8t4yonqjucvi03` FOREIGN KEY (`monitor_id`) REFERENCES `monitor` (`id`),
  CONSTRAINT `FKt182pj2btoak59lnp806vxf1s` FOREIGN KEY (`id`) REFERENCES `machine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_computer_user`
--

DROP TABLE IF EXISTS `computer_computer_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_computer_user` (
  `computer_id` bigint(20) NOT NULL,
  `computer_user_id` bigint(20) NOT NULL,
  KEY `FKcho2tw8ms9nr6w44tlar0eqxa` (`computer_user_id`),
  KEY `FKspi9moc0s22olxdpoudb9225i` (`computer_id`),
  CONSTRAINT `FKcho2tw8ms9nr6w44tlar0eqxa` FOREIGN KEY (`computer_user_id`) REFERENCES `computer_user` (`id`),
  CONSTRAINT `FKspi9moc0s22olxdpoudb9225i` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_computer_user`
--

LOCK TABLES `computer_computer_user` WRITE;
/*!40000 ALTER TABLE `computer_computer_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `computer_computer_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_user`
--

DROP TABLE IF EXISTS `computer_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sector` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_user`
--

LOCK TABLES `computer_user` WRITE;
/*!40000 ALTER TABLE `computer_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `computer_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electronic`
--

DROP TABLE IF EXISTS `electronic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electronic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `equipment_type` int(11) DEFAULT NULL,
  `it_composed` bit(1) NOT NULL,
  `it_works` bit(1) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electronic`
--

LOCK TABLES `electronic` WRITE;
/*!40000 ALTER TABLE `electronic` DISABLE KEYS */;
/*!40000 ALTER TABLE `electronic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `machine` (
  `patrimony_id` varchar(10) DEFAULT NULL,
  `sector` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_borlebacld5kxy0pd0pd6ota7` (`patrimony_id`),
  CONSTRAINT `FKcyfekdsv2i6qx8usekpu6n6v9` FOREIGN KEY (`id`) REFERENCES `electronic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine`
--

LOCK TABLES `machine` WRITE;
/*!40000 ALTER TABLE `machine` DISABLE KEYS */;
/*!40000 ALTER TABLE `machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitor`
--

DROP TABLE IF EXISTS `monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitor` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK1eqbidtog8x2g3280yb1h470i` FOREIGN KEY (`id`) REFERENCES `machine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitor`
--

LOCK TABLES `monitor` WRITE;
/*!40000 ALTER TABLE `monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processor`
--

DROP TABLE IF EXISTS `processor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processor` (
  `architecture` int(11) NOT NULL,
  `processor_name` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKpyo5x9qdcrkqvixv944msghos` FOREIGN KEY (`id`) REFERENCES `electronic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processor`
--

LOCK TABLES `processor` WRITE;
/*!40000 ALTER TABLE `processor` DISABLE KEYS */;
/*!40000 ALTER TABLE `processor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ram_memory`
--

DROP TABLE IF EXISTS `ram_memory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ram_memory` (
  `architecture` int(11) NOT NULL,
  `size_inmb` double NOT NULL,
  `id` bigint(20) NOT NULL,
  `computer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnlrckn7tc96ravj5fcdyyem7i` (`computer_id`),
  CONSTRAINT `FKnlrckn7tc96ravj5fcdyyem7i` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`),
  CONSTRAINT `FKs7a8s12182xe4bhalr8y4pnki` FOREIGN KEY (`id`) REFERENCES `electronic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ram_memory`
--

LOCK TABLES `ram_memory` WRITE;
/*!40000 ALTER TABLE `ram_memory` DISABLE KEYS */;
/*!40000 ALTER TABLE `ram_memory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage_device`
--

DROP TABLE IF EXISTS `storage_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage_device` (
  `architecture` int(11) NOT NULL,
  `size_inmb` double NOT NULL,
  `type` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `computer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmoym4ajgob79g1dhxeekjseb4` (`computer_id`),
  CONSTRAINT `FKcpym84qy9m3u9o3o0al8938cn` FOREIGN KEY (`id`) REFERENCES `electronic` (`id`),
  CONSTRAINT `FKmoym4ajgob79g1dhxeekjseb4` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage_device`
--

LOCK TABLES `storage_device` WRITE;
/*!40000 ALTER TABLE `storage_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `storage_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-25 17:34:21
