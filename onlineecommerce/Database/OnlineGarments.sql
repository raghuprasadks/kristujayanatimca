/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - onlinegarmentsdeal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinegarmentsdeal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `onlinegarmentsdeal`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(225) DEFAULT NULL,
  `Age` varchar(225) DEFAULT NULL,
  `Address` varchar(225) DEFAULT NULL,
  `ContactNo` varchar(225) DEFAULT NULL,
  `ProductChoice` varchar(225) DEFAULT NULL,
  `ItemCode` varchar(225) DEFAULT NULL,
  `MultipleItems` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Login` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`ID`,`Name`,`Age`,`Address`,`ContactNo`,`ProductChoice`,`ItemCode`,`MultipleItems`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`Login`) values (1,'alexa kinner','21','asc','9165415597',NULL,'200103',NULL,'root','root','2019-10-22 12:13:57','2019-10-22 12:13:57','Customer123'),(2,'Lila Bowman','29','csadc','9165415599',NULL,'200102',NULL,'Customer123','Customer123','2019-10-22 12:15:11','2019-10-22 12:15:11','Customer123'),(3,'AIR INDIAN','18','ddd','9165415596',NULL,'200102',NULL,'root','root','2019-10-22 12:18:59','2019-10-22 12:18:59','Hariom123');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `ID` bigint(20) NOT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `login` */

insert  into `login`(`ID`,`login`,`password`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`role`) values (1,'Admin123','Admin@123','root','root','2019-10-02 09:41:47','2019-10-02 09:41:51',1),(2,'Manager123','Man@123',NULL,NULL,'2019-10-02 09:53:47','2019-10-02 09:51:44',2),(3,'Customer123','123',NULL,NULL,'2019-10-22 12:14:04','2019-10-22 12:14:04',3),(4,'Hariom123','123',NULL,NULL,'2019-10-22 12:19:07','2019-10-22 12:19:07',3);

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(225) DEFAULT NULL,
  `emailId` varchar(225) DEFAULT NULL,
  `contactNo` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `Login` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`ID`,`Name`,`emailId`,`contactNo`,`address`,`Login`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Manager','Manager@gmail.com','9165415596','Indore','Manager123','Admin123','Admin123','2019-10-02 09:53:39','2019-10-02 09:51:44');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `ID` bigint(20) NOT NULL,
  `paymentId` bigint(20) DEFAULT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  `productId` bigint(20) DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `login` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

insert  into `payment`(`ID`,`paymentId`,`customerId`,`productId`,`paymentDate`,`amount`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`login`) values (1,1,1,2,'2019-10-22',1500.9,NULL,NULL,'2019-10-22 12:14:05','2019-10-22 12:14:05','Customer123'),(2,2,2,1,'2019-10-22',166.2,NULL,NULL,'2019-10-22 12:15:26','2019-10-22 12:15:26','Customer123'),(3,3,3,1,'2019-10-22',166.2,NULL,NULL,'2019-10-22 12:19:08','2019-10-22 12:19:08','Hariom123');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `ID` bigint(20) NOT NULL,
  `productName` varchar(225) DEFAULT NULL,
  `productQuantity` varchar(225) DEFAULT NULL,
  `productChoice` varchar(225) DEFAULT NULL,
  `itemCode` varchar(225) DEFAULT NULL,
  `Image` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `price` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`ID`,`productName`,`productQuantity`,`productChoice`,`itemCode`,`Image`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`price`) values (1,'alexa kinnerr','2','Non','200102','127494.jpg','Admin123','Admin123','2019-10-02 11:28:07','2019-10-02 11:48:45',166.2),(2,'Lila Bowman','2','Non','200103','12a6fc3c07019473b2f34fe207aaecdf.jpg','Admin123','Admin123','2019-10-06 07:52:58','2019-10-06 07:55:15',1500.9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
