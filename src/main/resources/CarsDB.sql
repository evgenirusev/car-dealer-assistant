-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.12 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for dealership_db
CREATE DATABASE IF NOT EXISTS `dealership_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `dealership_db`;

-- Dumping structure for table dealership_db.cars
CREATE TABLE IF NOT EXISTS `cars` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `traveled_distance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `is_driver_young` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.logs
CREATE TABLE IF NOT EXISTS `logs` (
  `id` varchar(255) NOT NULL,
  `modifying_date` datetime DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.parts
CREATE TABLE IF NOT EXISTS `parts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKitcfa74ce7tvj2n1bup6s3myq` (`supplier_id`),
  CONSTRAINT `FKitcfa74ce7tvj2n1bup6s3myq` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.parts_cars
CREATE TABLE IF NOT EXISTS `parts_cars` (
  `part_id` bigint(20) NOT NULL,
  `car_id` bigint(20) NOT NULL,
  PRIMARY KEY (`car_id`,`part_id`),
  KEY `FK8erocm1fr7d2epdxroh96a3lt` (`part_id`),
  CONSTRAINT `FK8erocm1fr7d2epdxroh96a3lt` FOREIGN KEY (`part_id`) REFERENCES `parts` (`id`),
  CONSTRAINT `FK9bkl7o8lnx1ykan7pqk0ww0ar` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.sales
CREATE TABLE IF NOT EXISTS `sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhb80sy23hk4kk9s4g271uw9ci` (`car_id`),
  KEY `FKd94vrikapjd2ews1k4lb71sfg` (`customer_id`),
  CONSTRAINT `FKd94vrikapjd2ews1k4lb71sfg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `FKhb80sy23hk4kk9s4g271uw9ci` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.suppliers
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_importer` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
-- Dumping structure for table dealership_db.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
