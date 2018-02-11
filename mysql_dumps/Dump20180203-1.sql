-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: back
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `consultant_group`
--

DROP TABLE IF EXISTS `consultant_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultant_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `video_tarif` int(11) NOT NULL,
  `conversation_tarif` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultant_group`
--

LOCK TABLES `consultant_group` WRITE;
/*!40000 ALTER TABLE `consultant_group` DISABLE KEYS */;
INSERT INTO `consultant_group` VALUES (1,'name test','Description test',455,10),(2,'name test','Description test',455,10),(3,'name test','Description test',455,10),(4,'name test','Description test',455,10),(5,'name test','Description test',455,10);
/*!40000 ALTER TABLE `consultant_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultant_group_user`
--

DROP TABLE IF EXISTS `consultant_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultant_group_user` (
  `user_id` bigint(20) NOT NULL,
  `consultant_grout_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) DEFAULT '0',
  `video_tarif` int(11) DEFAULT NULL,
  `conversation_tarif` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `consultant__group_user_consultant_group_id_fk` (`consultant_grout_id`),
  KEY `consultant__group_user_user_id_fk` (`user_id`),
  CONSTRAINT `consultant__group_user_consultant_group_id_fk` FOREIGN KEY (`consultant_grout_id`) REFERENCES `consultant_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `consultant__group_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultant_group_user`
--

LOCK TABLES `consultant_group_user` WRITE;
/*!40000 ALTER TABLE `consultant_group_user` DISABLE KEYS */;
INSERT INTO `consultant_group_user` VALUES (6,4,1,0,455,NULL),(6,4,2,0,455,20),(6,4,3,0,455,20),(6,4,4,0,455,20);
/*!40000 ALTER TABLE `consultant_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultant_information`
--

DROP TABLE IF EXISTS `consultant_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultant_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `education` varchar(50) NOT NULL,
  `degree` varchar(50) NOT NULL,
  `license_number` varchar(50) NOT NULL,
  `license_file` varchar(50) DEFAULT NULL,
  `license_until` date NOT NULL,
  `available_from` time NOT NULL,
  `available_until` time NOT NULL,
  `consultant_group_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `consultant_information_consultant__group_user_user_id_fk` (`consultant_group_user_id`),
  CONSTRAINT `consultant_information_consultant__group_user_user_id_fk` FOREIGN KEY (`consultant_group_user_id`) REFERENCES `consultant_group_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultant_information`
--

LOCK TABLES `consultant_information` WRITE;
/*!40000 ALTER TABLE `consultant_information` DISABLE KEYS */;
INSERT INTO `consultant_information` VALUES (2,'MIT','PhD','License by NY Government','file.png','2017-12-30','09:00:00','16:00:00',2),(3,'MIT','PhD','License by NY Government','file.png','2017-12-30','09:00:00','16:00:00',2),(4,'MIT','PhD','License by NY Government','file.png','2017-12-30','09:00:00','16:00:00',2),(5,'MIT','PhD','License by NY Government','file.png','2017-12-30','09:00:00','16:00:00',2),(6,'MIT','PhD','License by NY Government','xcgxsdf.docx','2017-12-30','09:00:00','16:00:00',2),(7,'MIT','PhD','License by NY Government','xcgxsdf.docx','2017-12-30','09:00:00','16:00:00',2),(8,'MIT','PhD','License by NY Government','xcgxsdf.docx','2017-12-30','09:00:00','16:00:00',2),(9,'MIT','PhD','License by NY Government','xcgxsdf.docx','2017-12-30','09:00:00','16:00:00',2),(10,'MIT','PhD','License by NY Government','xcgxsdf.docx','2017-12-30','09:00:00','16:00:00',2);
/*!40000 ALTER TABLE `consultant_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consultant_group_user_id` bigint(20) NOT NULL,
  `customer_information_id` bigint(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `conversation_customer_information_id_fk` (`customer_information_id`),
  KEY `conversation_consultant__group_user_id_fk` (`consultant_group_user_id`),
  CONSTRAINT `conversation_consultant__group_user_id_fk` FOREIGN KEY (`consultant_group_user_id`) REFERENCES `consultant_group_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conversation_customer_information_id_fk` FOREIGN KEY (`customer_information_id`) REFERENCES `customer_information` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_message`
--

DROP TABLE IF EXISTS `conversation_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` bigint(20) NOT NULL,
  `message` text NOT NULL,
  `is_consultant_message` tinyint(1) DEFAULT '0',
  `date_time` datetime NOT NULL,
  `attached_file` varchar(50) DEFAULT NULL,
  `video_duration` time DEFAULT NULL,
  `video_external_link` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_message_conversation_id_fk` (`conversation_id`),
  KEY `conversation_message_conversation_video_id_fk` (`video_duration`),
  CONSTRAINT `conversation_message_conversation_id_fk` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_message`
--

LOCK TABLES `conversation_message` WRITE;
/*!40000 ALTER TABLE `conversation_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_status`
--

DROP TABLE IF EXISTS `conversation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_status`
--

LOCK TABLES `conversation_status` WRITE;
/*!40000 ALTER TABLE `conversation_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_status_history`
--

DROP TABLE IF EXISTS `conversation_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation_status_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` bigint(20) NOT NULL,
  `conversation_status_id` bigint(20) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_status_history_conversation_id_fk` (`conversation_id`),
  KEY `conversation_status_history_conversation_status_id_fk` (`conversation_status_id`),
  CONSTRAINT `conversation_status_history_conversation_id_fk` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conversation_status_history_conversation_status_id_fk` FOREIGN KEY (`conversation_status_id`) REFERENCES `conversation_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_status_history`
--

LOCK TABLES `conversation_status_history` WRITE;
/*!40000 ALTER TABLE `conversation_status_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation_status_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_information`
--

DROP TABLE IF EXISTS `customer_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `birth_data` date DEFAULT NULL,
  `additional_information` text,
  `is_primary` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_information_user_id_fk` (`user_id`),
  CONSTRAINT `user_information_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_information`
--

LOCK TABLES `customer_information` WRITE;
/*!40000 ALTER TABLE `customer_information` DISABLE KEYS */;
INSERT INTO `customer_information` VALUES (1,2,'2017-10-10','sd',0),(2,2,'2017-10-10','sd',0),(3,2,'2017-10-10','sd',0),(4,NULL,'2017-10-10','sd',0),(5,NULL,'2017-10-10','sd',0),(6,2,'2017-10-10','sd',0),(8,7,'2017-10-10','sdfsdfsdf',0),(9,7,'2017-10-10','sdfsdfsdf',0),(10,7,'2017-10-10','sdfsdfsdf',0),(12,7,'2017-10-10','sdfsdfsdf',0),(13,7,'2017-10-10','sdfsdfsdf',0),(14,7,'2017-10-10','sdfsdfsdf',0),(15,7,'2017-10-10','sdfsdfsdf',0),(16,7,'2017-10-10','sdfsdfsdf',0),(17,NULL,'2018-02-14','asd',1);
/*!40000 ALTER TABLE `customer_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_payment`
--

DROP TABLE IF EXISTS `customer_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_time` datetime NOT NULL,
  `conversation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_payment_conversation_id_fk` (`conversation_id`),
  CONSTRAINT `customer_payment_conversation_id_fk` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_payment`
--

LOCK TABLES `customer_payment` WRITE;
/*!40000 ALTER TABLE `customer_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'CUSTOMER','CUSTOMER'),(2,'CONSULTANT','CONSULTANT'),(3,'ADMIN_USER','ADMIN_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a','customer','gmail@gmail.com','',''),(2,'821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a','consultant','gmail@gmail.com','',''),(3,'821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a','admin','gmail@gmail.com','',''),(4,'341f24f767aeabdf39c01c2b5ff9513e6030b093fc234cb0d2b790c27d9b103b','username','examle@gmail.com','',''),(5,'341f24f767aeabdf39c01c2b5ff9513e6030b093fc234cb0d2b790c27d9b103b','username','examle@gmail.com','',''),(6,'341f24f767aeabdf39c01c2b5ff9513e6030b093fc234cb0d2b790c27d9b103b','username','examle@gmail.com','',''),(7,'341f24f767aeabdf39c01c2b5ff9513e6030b093fc234cb0d2b790c27d9b103b','username','examle@gmail.com','firstName','lastName'),(8,'341f24f767aeabdf39c01c2b5ff9513e6030b093fc234cb0d2b790c27d9b103b','username','examle@gmail.com','firstName','lastName'),(9,'149446a191ab8713f6abd627e23df16fa60391c0cba6870471ccc1e1bdc5cd76','client','gmail@gmail.com','client','clientlastname'),(10,'d4a6e18ad0c18de53488f764fac17784244bec0a95e02c881ece820c3d84d979','cl','cl@cl.cl','cl','cl'),(11,'03042cf8100db386818cee4ff0f2972431a62ed78edbd09ac08accfabbefd818','sd','sd','sd','sd'),(12,'f9c7af7ebcbf098b9f5f37361d1b168bb2e5b98d930ceef0f055377a8c94db61','ds','ds','sdsd','sd'),(13,'d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','client2','cl','name','sdsdf');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(2,2),(3,2),(3,3),(4,3),(4,1),(5,3),(5,1),(6,3),(6,1),(7,3),(7,1),(8,3),(8,1),(9,3),(9,1),(10,3),(10,1),(11,3),(11,1),(12,3),(12,1),(13,3),(13,2),(13,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-03 13:56:48
