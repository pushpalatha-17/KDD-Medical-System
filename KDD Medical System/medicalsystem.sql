-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.58-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for medicalsystem
CREATE DATABASE IF NOT EXISTS `medicalsystem` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `medicalsystem`;


-- Dumping structure for table medicalsystem.diseaseinfo
CREATE TABLE IF NOT EXISTS `diseaseinfo` (
  `Disease` varchar(50) DEFAULT NULL,
  `Sympt` varchar(500) DEFAULT NULL,
  `Class` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table medicalsystem.diseaseinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `diseaseinfo` DISABLE KEYS */;
INSERT INTO `diseaseinfo` (`Disease`, `Sympt`, `Class`) VALUES
	('Sinusitis', 'Sneezing#Drycough#Headache#Tiredness', '-'),
	('Acutelobarpnemonia', 'Fever#Coughwithexp#Breathlessness#Chestpain#Lossofappetite#Chills', '-'),
	('Acutegastroentites', 'Fever#Painabdomen#Loosemotion#Nausea', '-'),
	('Pulmonarytuberclosis', 'Fever#Coughwithexp#Breathlessness#Lossofappetite#Lossofweight#Sweating', '-'),
	('Influenza', 'Fever#Bodypain#Runningnose#Sorethroat', '-'),
	('Pepticulcer', 'Vomiting#Painabdomen#Nausea#Heartburns', '-'),
	('Hypertension', 'Headache#Chestpain#Vomiting#Giddiness', '-'),
	('Diabetusmelitus', 'Lossofweight#Frequentmicturation#Excessivethirst#Weakness', '-'),
	('Malaria', 'Fever#Headache#Chills#Weakness', '-'),
	('Bascillarydysentry', 'Fever#Lossofappetite#Vomiting#Painabdomen#Dysentry', '-'),
	('Bronchicalashma', 'Coughwithexp#Breathlessness#Chestpain', '-'),
	('Migrane', 'Headache#Vomiting#Diminessofvision', '-'),
	('UpperRespiratory', 'Fever#Bodypain#Coughwithexp', '-');
/*!40000 ALTER TABLE `diseaseinfo` ENABLE KEYS */;


-- Dumping structure for table medicalsystem.symptoms
CREATE TABLE IF NOT EXISTS `symptoms` (
  `Symptom` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table medicalsystem.symptoms: ~0 rows (approximately)
/*!40000 ALTER TABLE `symptoms` DISABLE KEYS */;
INSERT INTO `symptoms` (`Symptom`) VALUES
	('Headache'),
	('Fever'),
	('Bodypain'),
	('Coughwithexp'),
	('Backache'),
	('Loosemotion'),
	('Weakness'),
	('Jointpain'),
	('Swellingofjoints'),
	('Runningnose'),
	('Chills'),
	('Drycough'),
	('Breathlessness'),
	('Sorethroat'),
	('Diifficulttoswallow'),
	('Lymphnodalenlarge'),
	('Chestpain'),
	('Lossofappetite'),
	('Vomiting'),
	('Painabdomen'),
	('Dysentry'),
	('Sneezing'),
	('Yellowcoloredurine'),
	('Lossofweight'),
	('Giddiness'),
	('Sweating'),
	('Frequentmicturation'),
	('Excessivethirst'),
	('Diminessofvision'),
	('Nausea'),
	('Heartburns'),
	('Rashes'),
	('Tiredness');
/*!40000 ALTER TABLE `symptoms` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
