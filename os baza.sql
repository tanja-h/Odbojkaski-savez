/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 10.1.36-MariaDB : Database - os baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`os baza` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `os baza`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `zip_code` varchar(5) NOT NULL,
  `city_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`zip_code`,`city_name`) values 
('11000','Beograd'),
('14000','Valjevo'),
('18101','Niš'),
('19000','Zaječar'),
('21000','Novi Sad'),
('24000','Subotica'),
('34000','Kragujevac'),
('36000','Kraljevo');

/*Table structure for table `club` */

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `club_registration_number` varchar(7) NOT NULL,
  `club_name` varchar(50) NOT NULL,
  `club_adress` varchar(50) NOT NULL,
  `number_of_wins_match` int(11) DEFAULT '0',
  `number_of_wins_tournament` int(11) DEFAULT '0',
  `city` varchar(5) NOT NULL,
  PRIMARY KEY (`club_registration_number`),
  KEY `fk_grad-klub` (`city`),
  CONSTRAINT `fk_grad-klub` FOREIGN KEY (`city`) REFERENCES `city` (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `club` */

insert  into `club`(`club_registration_number`,`club_name`,`club_adress`,`number_of_wins_match`,`number_of_wins_tournament`,`city`) values 
('0000010','Blok Aut','Jurija Gagarina 168',2,1,'11000'),
('0000011','Roda','Mirijevski Bulevar bb',0,0,'11000'),
('0000022','As Batajnica','Ulica SG Mitraljete 57',3,1,'11000'),
('0000033','Crvena Zvezda','Marka Kraljevića 27',5,2,'11000'),
('0000034','Šumadija 34','Šumadijska 4',0,0,'34000'),
('0000044','Radnički Kragujevac','Save Kovačevića 33',0,0,'34000'),
('0000055','Partizan','Partizanske Avijacije 136',3,1,'11000'),
('0000066','Vojvodina NS','Zmaj Jovina 65',0,0,'21000'),
('0000077','Srbijanka 014','Valjevske kapije 128',0,0,'14000'),
('0000088','Valjevo','Vuka Karadžića 42',2,1,'14000'),
('0000099','Spartak','Bulevar Laze Kostića 18',1,1,'24000'),
('0000100','Ribnica','Kraljevačka 89',0,0,'36000');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`username`,`password`,`name`) values 
('laza','laza','Lazar Lazic'),
('pera','pera','Petar Petrovic'),
('zika','zika','Zika Zikic');

/*Table structure for table `game` */

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `matchID` int(11) NOT NULL,
  `tournamentID` int(3) NOT NULL,
  `host` varchar(7) NOT NULL,
  `guest` varchar(7) NOT NULL,
  `score` varchar(3) NOT NULL,
  `winner` varchar(7) NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`matchID`,`tournamentID`),
  KEY `fk_utakmica_domacin` (`host`),
  KEY `fk_utakmica_gost` (`guest`),
  KEY `fk_utakmica_turnir` (`tournamentID`),
  CONSTRAINT `fk_utakmica_domacin` FOREIGN KEY (`host`) REFERENCES `club` (`club_registration_number`) ON UPDATE CASCADE,
  CONSTRAINT `fk_utakmica_gost` FOREIGN KEY (`guest`) REFERENCES `club` (`club_registration_number`) ON UPDATE CASCADE,
  CONSTRAINT `fk_utakmica_turnir` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `game` */

insert  into `game`(`matchID`,`tournamentID`,`host`,`guest`,`score`,`winner`,`time`) values 
(1,1,'0000011','0000022','1:3','GOST','01:45:00'),
(1,2,'0000099','0000066','3:2','DOMAĆIN','02:10:00'),
(1,3,'0000088','0000100','3:2','DOMAĆIN','02:40:00'),
(1,4,'0000022','0000011','3:2','DOMAĆIN','03:00:00'),
(1,5,'0000088','0000055','2:3','GOST','02:15:00'),
(1,6,'0000010','0000011','3:0','DOMAĆIN','02:10:00'),
(1,7,'0000022','0000010','3:1','DOMAĆIN','01:35:00'),
(2,1,'0000033','0000034','3:0','DOMAĆIN','01:20:00'),
(2,5,'0000033','0000066','3:1','DOMAĆIN','02:00:00'),
(2,6,'0000022','0000033','1:3','GOST','02:25:00'),
(2,7,'0000033','0000022','3:2','DOMAĆIN','02:20:00'),
(3,1,'0000022','0000033','1:3','GOST','01:50:00'),
(3,5,'0000099','0000088','0:3','GOST','01:35:00'),
(3,6,'0000033','0000010','2:3','GOST','02:40:00'),
(4,5,'0000055','0000033','3:2','DOMAĆIN','03:25:00'),
(5,5,'0000055','0000088','3:1','DOMAĆIN','02:20:00');

/*Table structure for table `participation` */

DROP TABLE IF EXISTS `participation`;

CREATE TABLE `participation` (
  `tournamentID` int(3) NOT NULL,
  `club_registration_number` varchar(7) NOT NULL,
  PRIMARY KEY (`tournamentID`,`club_registration_number`),
  KEY `fk_ucesce_klub` (`club_registration_number`),
  CONSTRAINT `fk_ucesce_klub` FOREIGN KEY (`club_registration_number`) REFERENCES `club` (`club_registration_number`) ON UPDATE CASCADE,
  CONSTRAINT `fk_ucesce_turnir` FOREIGN KEY (`tournamentID`) REFERENCES `tournament` (`tournamentID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `participation` */

insert  into `participation`(`tournamentID`,`club_registration_number`) values 
(1,'0000011'),
(1,'0000022'),
(1,'0000033'),
(1,'0000034'),
(2,'0000066'),
(2,'0000099'),
(3,'0000088'),
(3,'0000100'),
(4,'0000011'),
(4,'0000022'),
(5,'0000033'),
(5,'0000055'),
(5,'0000066'),
(5,'0000088'),
(5,'0000099'),
(6,'0000010'),
(6,'0000011'),
(6,'0000022'),
(6,'0000033'),
(7,'0000010'),
(7,'0000022'),
(7,'0000033');

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `player_registration_number` varchar(5) NOT NULL,
  `name` varchar(40) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `player_adress` varchar(40) NOT NULL,
  `jersey_number` int(2) DEFAULT '0',
  `city` varchar(5) NOT NULL,
  `positionID` int(1) NOT NULL,
  `club_registration_number` varchar(7) NOT NULL,
  PRIMARY KEY (`player_registration_number`),
  KEY `fk_igrac-klub` (`club_registration_number`),
  KEY `fk_grad-igrac` (`city`),
  KEY `fk_pozicija` (`positionID`),
  CONSTRAINT `fk_grad-igrac` FOREIGN KEY (`city`) REFERENCES `city` (`zip_code`) ON UPDATE CASCADE,
  CONSTRAINT `fk_igrac-klub` FOREIGN KEY (`club_registration_number`) REFERENCES `club` (`club_registration_number`) ON UPDATE CASCADE,
  CONSTRAINT `fk_pozicija` FOREIGN KEY (`positionID`) REFERENCES `position` (`positionID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player` */

insert  into `player`(`player_registration_number`,`name`,`gender`,`date_of_birth`,`player_adress`,`jersey_number`,`city`,`positionID`,`club_registration_number`) values 
('00001','Milica Vujović','Ž','1994-04-23','Bulevar Mihajla Pupina 24',5,'21000',2,'0000066'),
('00009','Milica Petrović','Ž','1993-07-07','Isidore Sekulić 64',2,'21000',3,'0000055'),
('11111','Ana Jovanović','Ž','1990-12-12','Milutina Milankovića 134b',0,'36000',1,'0000011'),
('11133','Ivan Krstić','M','1992-03-15','Zmaja od noćaja 18',13,'21000',3,'0000099'),
('11222','Nikola Jovanović','M','1994-11-10','Omladinskih Brigada',8,'11000',4,'0000033'),
('12344','Marko Markovic','M','1990-01-12','Jovana Ristića 85',1,'18101',5,'0000055');

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `positionID` int(1) NOT NULL AUTO_INCREMENT,
  `position_name` varchar(20) NOT NULL,
  PRIMARY KEY (`positionID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `position` */

insert  into `position`(`positionID`,`position_name`) values 
(1,'DIZAC'),
(2,'PRIMAC'),
(3,'SREDNJI BLOKER'),
(4,'KOREKTOR'),
(5,'LIBERO');

/*Table structure for table `tournament` */

DROP TABLE IF EXISTS `tournament`;

CREATE TABLE `tournament` (
  `tournamentID` int(3) NOT NULL,
  `tournament_name` varchar(40) NOT NULL,
  `date` datetime NOT NULL,
  `winner` varchar(7) NOT NULL,
  `city` varchar(5) NOT NULL,
  PRIMARY KEY (`tournamentID`),
  KEY `fk_turnir_pobednik` (`winner`),
  KEY `fk_turnir_postanski_broj` (`city`),
  CONSTRAINT `fk_turnir_pobednik` FOREIGN KEY (`winner`) REFERENCES `club` (`club_registration_number`) ON UPDATE CASCADE,
  CONSTRAINT `fk_turnir_postanski_broj` FOREIGN KEY (`city`) REFERENCES `city` (`zip_code`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tournament` */

insert  into `tournament`(`tournamentID`,`tournament_name`,`date`,`winner`,`city`) values 
(1,'Uskršnji turnir','2018-10-18 20:26:01','0000033','18101'),
(2,'Humanitarni','2019-02-16 20:26:47','0000099','21000'),
(3,'Liga seniora','2018-07-07 20:27:24','0000088','36000'),
(4,'aaaa Turnir','2002-12-12 00:00:00','0000022','11000'),
(5,'Liga kadeta','2018-05-10 00:00:00','0000055','11000'),
(6,'Mini volley na Adi','2019-02-22 00:00:00','0000010','11000'),
(7,'NAziv neki','2019-12-12 00:00:00','0000033','11000');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
