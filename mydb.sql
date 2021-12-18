-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 18, 2021 at 01:07 PM
-- Server version: 8.0.27
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `code` varchar(6) NOT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`code`, `name`) VALUES
('CSC101', 'Object Oriented Programming'),
('CSC301', 'Assembly Language'),
('CSC302', 'Database Managements Systems'),
('CSC501', 'Computer Architecture'),
('MTH101', 'Linear Algebra'),
('MTH201', 'Multivariable Calculus'),
('MTH302', 'Statistics and Probability'),
('PHY202', 'Modern Physics');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
CREATE TABLE IF NOT EXISTS `section` (
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course1` varchar(6) NOT NULL,
  `course2` varchar(6) NOT NULL,
  `course3` varchar(6) NOT NULL,
  `course4` varchar(6) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `course1` (`course1`),
  KEY `course2` (`course2`),
  KEY `course3` (`course3`),
  KEY `course4` (`course4`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`name`, `course1`, `course2`, `course3`, `course4`) VALUES
('FA19-BCS-B', 'CSC501', 'CSC301', 'CSC302', 'PHY202'),
('FA20-BCS-A', 'CSC101', 'MTH101', 'MTH302', 'PHY202'),
('FA20-BCS-B', 'CSC101', 'MTH101', 'MTH302', 'PHY202');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `regNo` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(30) NOT NULL,
  `section` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gpa` varchar(4) NOT NULL,
  PRIMARY KEY (`regNo`),
  KEY `FKsection` (`section`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`regNo`, `name`, `section`, `gpa`) VALUES
('FA19-BCS-015', 'Arose Niazi', 'FA19-BCS-B', '4.0'),
('FA19-BCS-018', 'Foo Bar', 'FA19-BCS-B', '3.5'),
('FA20-BCS-001', 'Albert Camus', 'FA20-BCS-B', '2.7'),
('FA20-BCS-003', 'Jean Sartre', 'FA20-BCS-A', '1.7'),
('FA20-BCS-008', 'Ammar Amir', 'FA20-BCS-B', '3.7'),
('FA20-BCS-027', 'Ahmed Anwar', 'FA20-BCS-B', '3.3'),
('FA20-BCS-055', 'Muhammad Ibrahim', 'FA20-BCS-B', '3.3'),
('FA20-BCS-077', 'Arthur Schopenhauer', 'FA20-BCS-B', '4.0'),
('FA20-BCS-087', 'Hammad Ali', 'FA20-BCS-A', '4.0');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `course1` FOREIGN KEY (`course1`) REFERENCES `courses` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `course2` FOREIGN KEY (`course2`) REFERENCES `courses` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `course3` FOREIGN KEY (`course3`) REFERENCES `courses` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `course4` FOREIGN KEY (`course4`) REFERENCES `courses` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `FKsection` FOREIGN KEY (`section`) REFERENCES `section` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
