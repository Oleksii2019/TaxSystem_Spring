-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: users_db
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (59),(59),(59),(59),(59);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replacement_request`
--

DROP TABLE IF EXISTS `replacement_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `replacement_request` (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime NOT NULL,
  `taxofficer` bigint(20) DEFAULT NULL,
  `taxplayer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `taxplayer` (`taxplayer`),
  KEY `taxofficer_rr` (`taxofficer`),
  CONSTRAINT `taxofficer_rr` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`) ON DELETE SET NULL,
  CONSTRAINT `taxplayer` FOREIGN KEY (`taxplayer`) REFERENCES `taxpayers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replacement_request`
--

LOCK TABLES `replacement_request` WRITE;
/*!40000 ALTER TABLE `replacement_request` DISABLE KEYS */;
INSERT INTO `replacement_request` VALUES (53,'2019-08-07 12:36:49',2,4);
/*!40000 ALTER TABLE `replacement_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_alteration`
--

DROP TABLE IF EXISTS `report_alteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `report_alteration` (
  `id` bigint(20) NOT NULL,
  `accept_time` datetime DEFAULT NULL,
  `accepted` bit(1) DEFAULT b'0',
  `creation_time` datetime NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `report` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `report` (`report`),
  CONSTRAINT `report` FOREIGN KEY (`report`) REFERENCES `reports` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_alteration`
--

LOCK TABLES `report_alteration` WRITE;
/*!40000 ALTER TABLE `report_alteration` DISABLE KEYS */;
INSERT INTO `report_alteration` VALUES (12,'2019-07-15 21:14:13',_binary '','2019-07-15 12:31:35','Доопрацювати 1',11),(13,NULL,_binary '\0','2019-07-15 21:14:30','',11),(22,'2019-07-31 19:24:02',_binary '','2019-07-31 19:23:49','Доопрацювати',21),(36,NULL,_binary '\0','2019-08-01 19:22:59','Добавити',35),(49,NULL,_binary '\0','2019-08-06 18:27:49','Переробити 1',47);
/*!40000 ALTER TABLE `report_alteration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reports` (
  `id` bigint(20) NOT NULL,
  `accept_time` datetime DEFAULT NULL,
  `accepted` bit(1) DEFAULT b'0',
  `assessed` bit(1) DEFAULT b'0',
  `creation_time` datetime NOT NULL,
  `taxpayer` bigint(20) DEFAULT NULL,
  `taxofficer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `taxpayer` (`taxpayer`),
  KEY `taxofficer_r` (`taxofficer`),
  CONSTRAINT `taxofficer_r` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`) ON DELETE SET NULL,
  CONSTRAINT `taxpayer` FOREIGN KEY (`taxpayer`) REFERENCES `taxpayers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (5,NULL,_binary '\0',_binary '\0','2019-07-14 18:51:54',3,1),(6,NULL,_binary '\0',_binary '\0','2019-07-14 18:54:09',3,1),(11,NULL,_binary '\0',_binary '\0','2019-07-15 07:06:26',4,2),(14,NULL,_binary '\0',_binary '\0','2019-07-15 21:19:32',4,2),(21,'2019-07-31 19:24:17',_binary '',_binary '','2019-07-31 19:23:33',4,2),(35,NULL,_binary '\0',_binary '','2019-08-01 19:22:31',4,2),(38,NULL,_binary '\0',_binary '\0','2019-08-01 19:25:49',37,1),(47,NULL,_binary '\0',_binary '','2019-08-06 18:26:50',4,2),(56,NULL,_binary '\0',_binary '\0','2019-08-07 13:10:40',55,3);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxofficers`
--

DROP TABLE IF EXISTS `taxofficers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taxofficers` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxofficers`
--

LOCK TABLES `taxofficers` WRITE;
/*!40000 ALTER TABLE `taxofficers` DISABLE KEYS */;
INSERT INTO `taxofficers` VALUES (1,'Mike1','Олег Бондар','11',0),(2,'Mike2','Петро Писарчук','11',0),(3,'Mike3','Тарас Кравець','11',0);
/*!40000 ALTER TABLE `taxofficers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxpayers`
--

DROP TABLE IF EXISTS `taxpayers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taxpayers` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL,
  `taxofficer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `taxofficer` (`taxofficer`),
  CONSTRAINT `taxofficer` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxpayers`
--

LOCK TABLES `taxpayers` WRITE;
/*!40000 ALTER TABLE `taxpayers` DISABLE KEYS */;
INSERT INTO `taxpayers` VALUES (3,'Nike11','Василь Скляр','22',1,1),(4,'Nike22','Остап Швець','22',1,2),(37,'Nike21','ДП \"Сервис П\" ','22',1,2),(55,'Nike31','ПП \"Фотон +\"','22',1,3),(57,'Nike12','КП \"Зоряний Шлях\"','22',1,1),(58,'Nike32','ПП \"Зразкове\"','22',1,3);
/*!40000 ALTER TABLE `taxpayers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-07 16:21:56
