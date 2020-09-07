CREATE DATABASE  IF NOT EXISTS `wire_payment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wire_payment`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: wire_payment
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_user_bank_details`
--

DROP TABLE IF EXISTS `t_user_bank_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_bank_details` (
  `record_id` int(11) NOT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `bank_acc_id` bigint(15) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `bank_balance` decimal(10,2) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `user_id` (`user_id`),
  KEY `user_type_id` (`user_type_id`),
  KEY `bank_id` (`bank_id`),
  CONSTRAINT `t_user_bank_details_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user_details` (`user_id`),
  CONSTRAINT `t_user_bank_details_ibfk_2` FOREIGN KEY (`user_type_id`) REFERENCES `t_user_type` (`user_type_id`),
  CONSTRAINT `t_user_bank_details_ibfk_3` FOREIGN KEY (`bank_id`) REFERENCES `t_bank` (`bank_id`),
  CONSTRAINT `t_user_bank_details_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `t_user_details` (`user_id`),
  CONSTRAINT `t_user_bank_details_ibfk_5` FOREIGN KEY (`user_type_id`) REFERENCES `t_user_type` (`user_type_id`),
  CONSTRAINT `t_user_bank_details_ibfk_6` FOREIGN KEY (`bank_id`) REFERENCES `t_bank` (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_bank_details`
--

LOCK TABLES `t_user_bank_details` WRITE;
/*!40000 ALTER TABLE `t_user_bank_details` DISABLE KEYS */;
INSERT INTO `t_user_bank_details` VALUES (2,202,212345345674,101,0.00,1),(6,202,212345345675,102,8000.00,1),(7,201,786545345673,103,10005000.00,2),(8,202,994555495674,103,1000.00,2),(9,202,212345347777,101,8000.00,1);
/*!40000 ALTER TABLE `t_user_bank_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-28 10:13:39
