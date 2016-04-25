-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ckgj
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

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

CREATE DATABASE `ckgj` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE ckgj;
--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `employee_cnt` int(11) DEFAULT NULL,
  `institution_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `statement`
--

DROP TABLE IF EXISTS `statement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `asset` float(25,2) DEFAULT NULL,
  `bank_account` float(25,2) DEFAULT NULL,
  `cash` float(25,2) DEFAULT NULL,
  `cost` float(25,2) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `debt` float(25,2) DEFAULT NULL,
  `equity` float(25,2) DEFAULT NULL,
  `income` float(25,2) DEFAULT NULL,
  `liability` float(25,2) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  `receivable` float(25,2) DEFAULT NULL,
  `revenue` float(25,2) DEFAULT NULL,
  `salary_cost` float(25,2) DEFAULT NULL,
  `salary_debt` float(25,2) DEFAULT NULL,
  `supplier_cost` float(25,2) DEFAULT NULL,
  `supplier_debt` float(25,2) DEFAULT NULL,
  `tax_cost` float(25,2) DEFAULT NULL,
  `tax_debt` float(25,2) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nitar4foogg4tqvruxrl5qya8` (`company_id`),
  CONSTRAINT `FK_nitar4foogg4tqvruxrl5qya8` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `wxuser_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`),
  KEY `FK_bteyn2vjkuydkfqefgaje2rhr` (`company_id`),
  KEY `FK_r2k6nuqhqeb3utvm72sgo8dby` (`wxuser_id`),
  CONSTRAINT `FK_bteyn2vjkuydkfqefgaje2rhr` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK_r2k6nuqhqeb3utvm72sgo8dby` FOREIGN KEY (`wxuser_id`) REFERENCES `wx_user` (`id`)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `wx_user`
--

DROP TABLE IF EXISTS `wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o9u8f24qhj9k3xscgr6lhm2w1` (`open_id`),
  KEY `FK_qo17icmffu76p6c7u349om89h` (`user_id`),
  CONSTRAINT `FK_qo17icmffu76p6c7u349om89h` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-25  2:26:00