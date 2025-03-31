-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 30, 2025 at 06:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `obaob_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `blotter_table`
--

CREATE TABLE `blotter_table` (
  `b_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  `b_fname` varchar(50) NOT NULL,
  `b_incident` varchar(50) NOT NULL,
  `b_location` varchar(50) NOT NULL,
  `b_status` varchar(50) NOT NULL,
  `b_date` datetime NOT NULL DEFAULT current_timestamp(),
  `b_witness1` varchar(50) NOT NULL,
  `b_witness2` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blotter_table`
--

INSERT INTO `blotter_table` (`b_id`, `c_id`, `b_fname`, `b_incident`, `b_location`, `b_status`, `b_date`, `b_witness1`, `b_witness2`) VALUES
(3, 2, 'Mae Anne Amante', 'Robbery', 'St.Cecilia\'s, Minglanilla,Cebu.', 'Pending', '2025-03-30 22:20:34', 'Pat Obaob', 'Mary Obaob'),
(4, 1, 'Jaylon Mantillas', 'Scam', 'Sport\'s Complex, Minglanilla, Cebu.', 'Pending', '2025-03-30 22:35:03', 'David Vergara', 'Dustin Collins');

-- --------------------------------------------------------

--
-- Table structure for table `citizen_table`
--

CREATE TABLE `citizen_table` (
  `c_id` int(11) NOT NULL,
  `c_fname` varchar(50) NOT NULL,
  `c_lname` varchar(50) NOT NULL,
  `c_age` int(3) NOT NULL,
  `c_address` varchar(100) NOT NULL,
  `c_pnumber` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `citizen_table`
--

INSERT INTO `citizen_table` (`c_id`, `c_fname`, `c_lname`, `c_age`, `c_address`, `c_pnumber`) VALUES
(1, 'Diovely', 'Campo', 22, 'Minglanilla, Cebu.', '09334178140'),
(2, 'Karl', 'Campoy', 20, 'Minglanilla, Cebu.', '09059641855'),
(3, 'Justin', 'Valen', 22, 'Minglanilla, Cebu.', '09992345768'),
(5, 'Patricia', 'Obaob', 19, 'Minglanilla, Cebu.', '09223198120'),
(6, 'Lawrence', 'Sumbi', 20, 'Minglanilla, Cebu.', '09167892345'),
(7, 'Jaymaica', 'Narvasa', 22, 'Minglanilla, Cebu.', '09203247795');

-- --------------------------------------------------------

--
-- Table structure for table `system_logs`
--

CREATE TABLE `system_logs` (
  `logs_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `logs_action` varchar(200) NOT NULL,
  `logs_date` datetime(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `user_id` int(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `use_type` varchar(50) NOT NULL,
  `user_status` varchar(50) NOT NULL,
  `u_image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`user_id`, `firstName`, `lastName`, `email`, `password`, `use_type`, `user_status`, `u_image`) VALUES
(1, 'Lawrence', 'Sumbi', 'guian@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'User	', 'Active', 'src/usersImagesguian@gmail.com.jpg'),
(2, 'Patricia', 'Obaob', 'pat@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'Admin', 'Active', 'src/usersImagespat@gmail.com.jpg'),
(5, 'Mary', 'Largo', 'largo@gmail.com', 'uglymary', 'User	', 'Pending', ''),
(6, 'Risa', 'Ravina', 'risahon@gmail.com', 'mamarisa', 'User	', 'Pending', ''),
(7, 'Jaymaica', 'Narvasa', 'maica@gmail.com', 'maicamaica', 'User	', 'Pending', ''),
(11, 'sample', 'sample', 'sample@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'User	', 'Pending', ''),
(12, 'sample2', 'sample2', 'sample2@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'User	', 'Pending', ''),
(14, 'Ann', 'Obaob', 'obaob@gmail.com', 'c07eb5a8c0dc7bb81c217b67f11c3b7a5e95ffd7', 'Admin', 'Deactivated', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blotter_table`
--
ALTER TABLE `blotter_table`
  ADD PRIMARY KEY (`b_id`),
  ADD KEY `c_id` (`c_id`);

--
-- Indexes for table `citizen_table`
--
ALTER TABLE `citizen_table`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `system_logs`
--
ALTER TABLE `system_logs`
  ADD PRIMARY KEY (`logs_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blotter_table`
--
ALTER TABLE `blotter_table`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `citizen_table`
--
ALTER TABLE `citizen_table`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `system_logs`
--
ALTER TABLE `system_logs`
  MODIFY `logs_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `user_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blotter_table`
--
ALTER TABLE `blotter_table`
  ADD CONSTRAINT `blotter_table_c_id_fr` FOREIGN KEY (`c_id`) REFERENCES `citizen_table` (`c_id`);

--
-- Constraints for table `system_logs`
--
ALTER TABLE `system_logs`
  ADD CONSTRAINT `user_table_user_id_fr` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
