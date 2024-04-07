-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.27 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for meter_readings
CREATE DATABASE IF NOT EXISTS `meter_readings` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `meter_readings`;

-- Dumping structure for table meter_readings.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `consumer_address` varchar(255) DEFAULT NULL,
  `consumer_district` varchar(255) DEFAULT NULL,
  `consumer_email` varchar(255) DEFAULT NULL,
  `consumer_first_name` varchar(255) DEFAULT NULL,
  `consumer_last_name` varchar(255) DEFAULT NULL,
  `consumer_mobile` varchar(255) DEFAULT NULL,
  `consumer_nic` varchar(255) DEFAULT NULL,
  `outstanding_amount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_66gkcp94endmotfwb8r4ocxm9` (`account_number`),
  UNIQUE KEY `UK_er5m38mpaxa0ks63jgx4w3l9e` (`consumer_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.account: ~3 rows (approximately)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `account_number`, `consumer_address`, `consumer_district`, `consumer_email`, `consumer_first_name`, `consumer_last_name`, `consumer_mobile`, `consumer_nic`, `outstanding_amount`) VALUES
	(1, 'Administrator', '2021-11-21 08:48:10.162000', 'Administrator', '2021-11-24 18:11:16.901000', '10009', 'Kongahagedara', 'Kurunegala', 'chanaka.bandara00@gmail.com', 'Chanaka', 'Karunanayaka', '071123456789', '910460269v', -34.25),
	(3, 'Administrator', '2021-11-21 11:59:13.515000', 'Administrator', '2021-11-21 11:59:13.515000', '10010', 'Kongahagedara', 'Kurunegala', 'chanaka.bandara00@gmail.com', 'Chanaka', 'Karunanayaka', '071123456256', '910460269v', 5.5),
	(4, 'Administrator', '2021-11-21 22:14:00.801000', 'Administrator', '2021-11-21 22:14:00.801000', '10012', 'Kongahagedara', 'Kurunegala', 'chanaka.bandara00@gmail.com', 'Chanaka', 'Karunanayaka', '071123456262', '910460269v', 5.5),
	(6, 'Administrator', '2021-11-27 15:34:44.544000', 'Administrator', '2021-11-27 15:34:44.544000', '10013', 'Kongahagedara, Kuliyapitiya, Sri Lanka', 'kuruegala', 'chanaka.bandara00@gmail.com', 'Chanaka', 'Karuananyaka', '+10770256762', '910460269v', NULL),
	(8, 'Administrator', '2021-11-27 18:58:25.275000', 'Administrator', '2021-11-27 18:58:25.275000', '10014', 'Kongahagedara', 'kurunegala', 'chanaka.bandara@mcmedisoft.com', 'Chanaka', 'Bandara', '+94770256762', '910460269v', NULL),
	(10, 'Administrator', '2021-11-27 19:07:43.815000', 'Administrator', '2021-11-27 19:07:43.815000', '10015', 'Kongahagedara', 'Kurunegala', 'chanaka.bandara@mcmedisoft.com', 'Chanaka', 'Bandara', '+94770500000', '910460269V', NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table meter_readings.consumer_account
CREATE TABLE IF NOT EXISTS `consumer_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrjt7cgpaii497o4q9b0xr34wi` (`account_id`),
  KEY `FKaklvt6wurh5fqt70c8tct8bt9` (`user_id`),
  CONSTRAINT `FKaklvt6wurh5fqt70c8tct8bt9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrjt7cgpaii497o4q9b0xr34wi` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.consumer_account: ~3 rows (approximately)
DELETE FROM `consumer_account`;
/*!40000 ALTER TABLE `consumer_account` DISABLE KEYS */;
INSERT INTO `consumer_account` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `account_id`, `user_id`) VALUES
	(1, 'Administrator', '2021-11-21 08:48:10.185000', 'Administrator', '2021-11-21 08:48:10.185000', 1, 1),
	(2, 'Administrator', '2021-11-21 11:59:13.551000', 'Administrator', '2021-11-21 11:59:13.551000', 3, 1),
	(3, 'Administrator', '2021-11-21 22:14:00.840000', 'Administrator', '2021-11-21 22:14:00.840000', 4, 1),
	(4, 'Administrator', '2021-11-27 15:34:44.665000', 'Administrator', '2021-11-27 15:34:44.665000', 6, NULL),
	(5, 'Administrator', '2021-11-27 18:58:25.304000', 'Administrator', '2021-11-27 18:58:25.304000', 8, NULL),
	(6, 'Administrator', '2021-11-27 19:07:43.828000', 'Administrator', '2021-11-27 19:07:43.828000', 10, NULL);
/*!40000 ALTER TABLE `consumer_account` ENABLE KEYS */;

-- Dumping structure for table meter_readings.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o61w8sj1xunllw9hlqiihm6qf` (`transaction_id`),
  KEY `FKoevv8h8t2qgym9s0cn7oh069b` (`account_id`),
  KEY `FKjunvl5maki3unqdvljk31kns3` (`user_id`),
  CONSTRAINT `FKjunvl5maki3unqdvljk31kns3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKoevv8h8t2qgym9s0cn7oh069b` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.invoice: ~3 rows (approximately)
DELETE FROM `invoice`;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `amount`, `transaction_id`, `account_id`, `user_id`, `method`) VALUES
	(1, 'Administrator', '2021-11-21 11:09:29.737000', 'Administrator', '2021-11-21 11:09:29.737000', 100.5, '100001', 1, 1, 'visa'),
	(2, 'Administrator', '2021-11-21 19:49:00.365000', 'Administrator', '2021-11-21 19:49:00.365000', 1000, '1000100', 1, 1, 'visa'),
	(3, 'Administrator', '2021-11-24 18:11:16.802000', 'Administrator', '2021-11-24 18:11:16.802000', 100, '1000101', 1, 1, 'visa');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table meter_readings.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.role: ~0 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `role_name`) VALUES
	(1, 'Administrator', '2021-11-21 08:47:22.015000', 'Administrator', '2021-11-21 08:47:22.015000', 'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table meter_readings.unit
CREATE TABLE IF NOT EXISTS `unit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `unit` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqgbxyuc2edjkvmhh4gnxpj7wi` (`user_id`),
  KEY `FKlknsp91jal1w83yx9x23lklrs` (`account_id`),
  CONSTRAINT `FKlknsp91jal1w83yx9x23lklrs` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKqgbxyuc2edjkvmhh4gnxpj7wi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.unit: ~4 rows (approximately)
DELETE FROM `unit`;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `amount`, `month`, `unit`, `user_id`, `account_id`) VALUES
	(3, 'Administrator', '2021-11-22 21:55:15.313000', 'Administrator', '2021-11-22 21:55:15.313000', 12.05, 'FEB', 10, 1, 1),
	(4, 'Administrator', '2021-11-22 22:16:47.829000', 'Administrator', '2021-11-22 22:16:47.829000', 12.05, 'FEB', 10, 1, 1),
	(9, 'Administrator', '2021-11-23 11:49:33.199000', 'Administrator', '2021-11-23 11:49:33.199000', 12.05, 'November', 1000, 1, 1),
	(10, 'Administrator', '2021-11-23 11:54:42.323000', 'Administrator', '2021-11-23 11:54:42.323000', 12.05, 'November', 1000, 1, 1);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;

-- Dumping structure for table meter_readings.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.user: ~0 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `email`, `first_name`, `last_name`, `mobile`, `username`) VALUES
	(1, 'Administrator', '2021-11-21 08:47:22.130000', 'Administrator', '2021-11-21 08:47:22.130000', 'vihanga@wso2.com', 'Vihanga', 'Liyanage', '071123456789', 'vihanga');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table meter_readings.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table meter_readings.user_role: ~0 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `role_id`, `user_id`) VALUES
	(1, 'Administrator', '2021-11-21 08:47:22.178000', 'Administrator', '2021-11-21 08:47:22.178000', 1, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
