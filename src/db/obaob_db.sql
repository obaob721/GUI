-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2025 at 06:28 PM
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
(4, 1, 'Jaylon Mantillas', 'Scam', 'Sport\'s Complex, Minglanilla, Cebu.', 'Pending', '2025-03-30 22:35:03', 'David Vergara', 'Dustin Collins'),
(5, 11, 'Lelouch Takanashi', 'kusog pa sound bisag gabie na kaayo', 'Minglanilla, Cebu.', 'Pending', '2025-04-01 00:08:06', 'Ren Takahashi', 'Hakuie Takahashi'),
(6, 16, 'Mark Sanchez', 'Lumay', 'Minglanilla, Cebu.', 'Settled', '2025-04-01 01:03:54', 'Dhyll Roco', 'Angel Amaro'),
(7, 8, 'Michael Frodo', 'Thief', 'Lungsod, Minglanilla, Cebu.', 'Pending', '2025-04-18 15:58:16', 'Anney Capta', 'Jerolle Undas'),
(8, 10, 'Maria Santos', 'Robbery', 'Lungsod, Minglanilla, Cebu.', 'Pending', '2025-05-24 23:31:41', 'N/A', 'N/A'),
(9, 1, 'Juan Dela Cruz', 'Scam', 'Minglanilla', 'Pending', '2025-05-24 23:36:34', 'N/A', 'N/A'),
(10, 1, 'Justin Valen', 'Scam', 'Minglanilla', 'Pending', '2025-05-24 23:39:31', 'N/A', 'N/A'),
(11, 7, 'Juan Dela Cruz', 'Robbery', 'Minglanilla', 'Pending', '2025-05-24 23:43:58', 'N/A', 'N/A');

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
(7, 'Jaymaica', 'Narvasa', 22, 'Minglanilla, Cebu.', '09203247795'),
(8, 'Juan', 'Dela Cruz', 34, 'Minglanilla, Cebu.', '09171234567'),
(9, 'Maria', 'Santos', 28, 'Minglanilla, Cebu.', '09283456789'),
(10, 'Jose', 'Reyes', 45, 'Minglanilla, Cebu.', '09394567890'),
(11, 'Pedro', 'Garcia', 52, 'Minglanilla, Cebu.', '09406789012'),
(12, 'Clara', 'Fernandez', 26, 'Minglanilla, Cebu', '09517890123'),
(13, 'Rosa', 'Bautista', 29, 'Minglanilla, Cebu.', '09739012345'),
(14, 'Daniel', 'Ramos', 41, 'Minglanilla, Cebu.', '09840123456'),
(15, 'Ernesto', 'Castillo', 50, 'Minglanilla, Cebu.', '09162345678'),
(16, 'Sofia', 'Villanueva', 22, 'Minglanilla, Cebu.', '09273456789'),
(19, 'Ramon', 'Navarro', 44, 'Minglanilla, Cebu.', '09384567890'),
(20, 'Teresa', 'Guzman', 37, 'Minglanilla, Cebu.', '09495678901'),
(21, 'Victor', 'Salazar', 47, 'Minglanilla, Cebu.', '09506789012'),
(22, 'Carla', 'Dominguez', 32, 'Minglanilla, Cebu.', '09617890123'),
(23, 'Samuel', 'Roldan', 39, 'Minglanilla, Cebu.', '09728901234'),
(27, 'King James', 'Ravina', 19, 'Minglanilla, Cebu.', '09839012345'),
(28, 'John', 'Rome', 31, 'Minglanilla, Cebu.', '09940123456'),
(29, 'Angelica', 'Mendoza', 25, 'Minglanilla, Cebu.', '09180987654'),
(33, 'Isabella', 'Torres', 24, 'Minglanilla, Cebu.', '09290123456'),
(34, 'Nathan', 'Cortez', 33, 'Minglanilla, Cebu.', '09301234567'),
(35, 'Elena', 'Rosales', 27, 'Minglanilla, Cebu.', '09412345678'),
(36, 'Leo', 'Marquez', 35, 'Minglanilla, Cebu.', '09523456789'),
(37, 'Camille', 'Andrada', 21, 'Minglanilla, Cebu.', '09634567890'),
(38, 'Zandro', 'Reyes', 29, 'Minglanilla, Cebu.', '09745678901'),
(39, 'Mika', 'Soriano', 26, 'Minglanilla, Cebu.', '09856789012'),
(40, 'Gabriel', 'Lopez', 42, 'Minglanilla, Cebu.', '09967890123'),
(41, 'Bea', 'Villamor', 31, 'Minglanilla, Cebu.', '09178901234'),
(42, 'Tristan', 'Delos Reyes', 38, 'Minglanilla, Cebu.', '09289012345'),
(43, 'Andrea', 'Montes', 23, 'Minglanilla, Cebu.', '09390123456'),
(44, 'Dominic', 'Salvador', 36, 'Minglanilla, Cebu.', '09401234567');

-- --------------------------------------------------------

--
-- Table structure for table `reports_table`
--

CREATE TABLE `reports_table` (
  `r_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `r_datesettled` timestamp NOT NULL DEFAULT current_timestamp(),
  `r_description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports_table`
--

INSERT INTO `reports_table` (`r_id`, `b_id`, `r_datesettled`, `r_description`) VALUES
(3, 3, '2025-03-31 08:32:45', 'imissyou guiannn'),
(5, 4, '2025-03-31 09:29:38', 'Fist Warning, If done again the suspect\'s may suffers or be punished.'),
(9, 5, '2025-03-31 16:08:42', 'skskskskskksks'),
(10, 6, '2025-03-31 17:04:01', 'sdsds'),
(11, 7, '2025-04-18 07:58:22', 'Second Warning or else might send a warrant of arrest.'),
(12, 8, '2025-05-24 15:31:55', 'SAMPLE'),
(13, 11, '2025-05-24 15:44:00', 'SAMPLE AGAIN');

-- --------------------------------------------------------

--
-- Table structure for table `system_logs`
--

CREATE TABLE `system_logs` (
  `logs_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `logs_action` varchar(255) NOT NULL,
  `logs_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `system_logs`
--

INSERT INTO `system_logs` (`logs_id`, `user_id`, `logs_action`, `logs_date`) VALUES
(1, 2, 'Admin logged in', '2025-04-18 03:16:28'),
(2, 1, 'Updated user profile for Lawrence Sumbi', '2025-04-18 03:16:41'),
(3, 2, 'Admin logged in', '2025-04-18 03:24:31'),
(4, 2, 'Admin logged in', '2025-04-18 05:57:49'),
(5, 2, 'Admin logged in', '2025-04-18 06:06:34'),
(7, 2, 'Patricia logged in', '2025-04-18 06:30:58'),
(8, 2, 'Admin logged in', '2025-04-18 06:34:42'),
(9, 2, 'Admin logged in', '2025-04-18 06:37:29'),
(10, 2, 'Admin logged in', '2025-04-18 06:37:59'),
(11, 2, 'Logged out', '2025-04-18 06:38:02'),
(12, 2, 'Admin logged in', '2025-04-18 06:41:48'),
(13, 2, 'Logged out', '2025-04-18 06:41:57'),
(14, 2, 'Admin logged in', '2025-04-18 06:54:57'),
(15, 2, 'Updated user profile for Guian Sumbi', '2025-04-18 06:55:13'),
(16, 2, 'Deleted user account for sample sample', '2025-04-18 06:55:22'),
(17, 2, 'Logged out', '2025-04-18 06:56:54'),
(18, 2, 'Admin logged in', '2025-04-18 07:00:30'),
(19, 2, 'Registered new user: Pancit Cantoon', '2025-04-18 07:01:02'),
(20, 2, 'Admin logged in', '2025-04-18 07:10:04'),
(24, 2, 'Admin logged in', '2025-04-18 07:29:53'),
(25, 2, 'Added a new Citizen: sample sample', '2025-04-18 07:30:29'),
(26, 2, 'Admin logged in', '2025-04-18 07:36:08'),
(27, 2, 'Admin Updated Citizen: samples samples', '2025-04-18 07:36:42'),
(28, 2, 'Admin Deleted a Citizen with phone number: 0000000000', '2025-04-18 07:36:55'),
(29, 2, 'Admin Logged out', '2025-04-18 07:36:59'),
(30, 2, 'Admin Logged in', '2025-04-18 07:55:44'),
(31, 2, 'Admin Added a new Blotter entry (ID: 7) for Suspect: Juan Dela Cruz', '2025-04-18 07:58:19'),
(32, 2, 'Admin Updated Report (r_id: 11) with description.', '2025-04-18 07:59:36'),
(33, 2, 'Admin Logged in', '2025-04-18 12:19:29'),
(34, 2, 'Admin Updated Report (r_id: 11) with description.', '2025-04-18 12:20:29'),
(35, 2, 'Admin Logged in', '2025-04-18 12:40:10'),
(36, 2, 'Admin Updated Report (r_id: 11) with description.', '2025-04-18 12:40:36'),
(37, 2, 'Admin Logged in', '2025-04-19 04:15:14'),
(38, 2, 'Admin Logged in', '2025-04-19 04:17:38'),
(39, 2, 'Admin Added a new Citizen: Isabella Torres', '2025-04-19 04:25:32'),
(40, 2, 'Admin Added a new Citizen: Nathan Cortez', '2025-04-19 04:26:22'),
(41, 2, 'Admin Added a new Citizen: Elena Rosales', '2025-04-19 04:27:03'),
(42, 2, 'Admin Added a new Citizen: Leo Marquez', '2025-04-19 04:28:04'),
(43, 2, 'Admin Added a new Citizen: Camille Andrada', '2025-04-19 04:28:52'),
(44, 2, 'Admin Added a new Citizen: Zandro Reyes', '2025-04-19 04:29:40'),
(45, 2, 'Admin Added a new Citizen: Mika Soriano', '2025-04-19 04:30:16'),
(46, 2, 'Admin Added a new Citizen: Gabriel Lopez', '2025-04-19 04:30:58'),
(47, 2, 'Admin Added a new Citizen: Bea Villamor', '2025-04-19 04:31:40'),
(48, 2, 'Admin Added a new Citizen: Tristan Delos Reyes', '2025-04-19 04:32:41'),
(49, 2, 'Admin Added a new Citizen: Andrea Montes', '2025-04-19 04:33:21'),
(50, 2, 'Admin Added a new Citizen: Dominic Salvador', '2025-04-19 04:34:22'),
(51, 2, 'Admin Logged out', '2025-04-19 04:35:15'),
(52, 2, 'Admin Logged in', '2025-05-01 12:09:33'),
(53, 2, 'Admin Logged in', '2025-05-01 12:37:24'),
(54, 2, 'Admin Logged in', '2025-05-01 12:43:24'),
(55, 2, 'Admin Logged in', '2025-05-01 12:54:00'),
(56, 2, 'Logged out', '2025-05-01 12:54:58'),
(57, 2, 'Admin Logged in', '2025-05-01 12:56:28'),
(58, 2, 'Logged out', '2025-05-01 12:57:20'),
(59, 2, 'Admin Logged in', '2025-05-04 02:23:11'),
(60, 2, 'Logged out', '2025-05-04 02:24:14'),
(61, 2, 'Admin Logged in', '2025-05-04 02:39:11'),
(62, 2, 'Logged out', '2025-05-04 02:47:01'),
(63, 2, 'Admin Logged in', '2025-05-04 02:47:15'),
(64, 2, 'Logged out', '2025-05-04 02:49:50'),
(65, 2, 'Admin Logged in', '2025-05-04 02:50:05'),
(66, 2, 'Logged out', '2025-05-04 02:52:01'),
(67, 2, 'Admin Logged in', '2025-05-04 02:52:15'),
(68, 2, 'Admin Logged in', '2025-05-04 02:54:14'),
(69, 2, 'Admin Logged in', '2025-05-04 02:54:53'),
(70, 2, 'Logged out', '2025-05-04 02:55:13'),
(71, 2, 'Admin Logged in', '2025-05-04 02:56:23'),
(72, 2, 'Admin Logged in', '2025-05-04 03:00:20'),
(73, 2, 'Admin Logged in', '2025-05-04 03:01:29'),
(74, 2, 'Logged out', '2025-05-04 03:01:43'),
(75, 2, 'Admin Logged in', '2025-05-04 03:12:04'),
(76, 2, 'Logged out', '2025-05-04 03:13:17'),
(77, 2, 'Admin Logged in', '2025-05-04 03:13:39'),
(78, 2, 'Logged out', '2025-05-04 03:13:50'),
(79, 2, 'Admin Logged in', '2025-05-04 03:25:36'),
(80, 2, 'Admin Logged in', '2025-05-20 14:10:16'),
(81, 2, 'Admin Logged out', '2025-05-20 14:10:28'),
(82, 2, 'Admin Logged in', '2025-05-20 14:16:29'),
(83, 2, 'Admin Logged out', '2025-05-20 14:16:46'),
(84, 2, 'Admin Logged in', '2025-05-20 14:18:30'),
(85, 2, 'Admin Logged in', '2025-05-20 14:21:15'),
(86, 2, 'Admin Logged out', '2025-05-20 14:22:25'),
(87, 2, 'Admin Logged in', '2025-05-20 14:22:43'),
(88, 2, 'Admin Logged out', '2025-05-20 14:22:48'),
(89, 2, 'Admin Logged in', '2025-05-20 14:24:56'),
(90, 2, 'Admin Logged out', '2025-05-20 14:25:11'),
(91, 2, 'Admin Logged in', '2025-05-20 14:26:37'),
(92, 2, 'Admin Logged in', '2025-05-24 11:17:45'),
(93, 2, 'Admin Logged in', '2025-05-24 11:26:24'),
(94, 2, 'Admin Logged in', '2025-05-24 11:43:07'),
(95, 2, 'Admin Updated Report (r_id: 10) with description.', '2025-05-24 11:43:24'),
(96, 2, 'Admin Logged in', '2025-05-24 11:57:03'),
(97, 2, 'Admin Logged in', '2025-05-24 11:58:47'),
(98, 2, 'Admin Logged in', '2025-05-24 12:01:01'),
(99, 2, 'Admin Updated Report (r_id: 10) with description.', '2025-05-24 12:01:09'),
(100, 2, 'Printed a report from the admin panel.', '2025-05-24 12:01:27'),
(101, 2, 'Admin Logged in', '2025-05-24 12:01:46'),
(102, 2, 'Admin Logged in', '2025-05-24 14:16:48'),
(103, 2, 'Admin Logged in', '2025-05-24 14:41:11'),
(104, 2, 'Admin Logged out', '2025-05-24 14:43:24'),
(105, 2, 'Admin Logged in', '2025-05-24 14:43:43'),
(106, 2, 'Admin Logged out', '2025-05-24 14:44:25'),
(107, 2, 'Admin Logged in', '2025-05-24 14:54:37'),
(108, 2, 'Admin Logged out', '2025-05-24 14:55:04'),
(109, 2, 'Admin Logged in', '2025-05-24 14:56:49'),
(110, 2, 'Admin Logged out', '2025-05-24 14:57:02'),
(111, 2, 'Admin Logged in', '2025-05-24 14:58:14'),
(112, 2, 'Admin Logged out', '2025-05-24 14:59:35'),
(113, 2, 'Admin Logged in', '2025-05-24 14:59:47'),
(114, 2, 'Admin Logged out', '2025-05-24 15:00:09'),
(115, 2, 'Admin Logged in', '2025-05-24 15:00:53'),
(116, 2, 'Admin Logged out', '2025-05-24 15:01:10'),
(117, 2, 'Admin Logged in', '2025-05-24 15:01:52'),
(118, 2, 'Admin Logged in', '2025-05-24 15:05:52'),
(119, 2, 'Admin Logged out', '2025-05-24 15:07:11'),
(120, 2, 'Admin Logged in', '2025-05-24 15:07:50'),
(121, 2, 'Admin Logged out', '2025-05-24 15:08:46'),
(122, 2, 'Admin Logged in', '2025-05-24 15:26:42'),
(123, 2, 'Admin Logged out', '2025-05-24 15:30:27'),
(124, 2, 'Admin Logged in', '2025-05-24 15:30:42'),
(125, 2, 'Admin Updated Report (r_id: 12) with description.', '2025-05-24 15:33:13'),
(126, 2, 'Admin Logged in', '2025-05-24 15:35:59'),
(127, 2, 'Admin Logged in', '2025-05-24 15:39:02'),
(128, 2, 'Admin Logged in', '2025-05-24 15:43:19'),
(129, 2, 'Admin Updated Report (r_id: 13) with description.', '2025-05-24 15:44:11'),
(130, 2, 'Admin Logged in', '2025-05-24 15:57:06'),
(131, 2, 'Admin Logged in', '2025-05-24 15:59:29'),
(132, 2, 'Admin Logged in', '2025-05-24 16:25:12');

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
  `u_image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`user_id`, `firstName`, `lastName`, `email`, `password`, `use_type`, `user_status`, `u_image`) VALUES
(1, 'Guian', 'Sumbi', 'guian@gmail.com', 'c07eb5a8c0dc7bb81c217b67f11c3b7a5e95ffd7', 'User	', 'Deactivated', 'src/usersImagesguian@gmail.com.jpg'),
(2, 'Patricia', 'Obaob', 'pat@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'Admin', 'Active', 'src/usersImagespat@gmail.com.jpg'),
(5, 'Mary', 'Largo', 'largo@gmail.com', 'uglymary', 'User	', 'Pending', ''),
(6, 'Risa', 'Ravina', 'risahon@gmail.com', 'mamarisa', 'User	', 'Pending', ''),
(7, 'Jaymaica', 'Narvasa', 'maica@gmail.com', 'maicamaica', 'User	', 'Pending', ''),
(15, 'Pancit', 'Cantoon', 'pan@gmail.com', '7c222fb2927d828af22f592134e8932480637c0d', 'User	', 'Pending', NULL);

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
-- Indexes for table `reports_table`
--
ALTER TABLE `reports_table`
  ADD PRIMARY KEY (`r_id`),
  ADD KEY `b_id` (`b_id`);

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
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `citizen_table`
--
ALTER TABLE `citizen_table`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `reports_table`
--
ALTER TABLE `reports_table`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `system_logs`
--
ALTER TABLE `system_logs`
  MODIFY `logs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `user_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blotter_table`
--
ALTER TABLE `blotter_table`
  ADD CONSTRAINT `blotter_table_c_id_fr` FOREIGN KEY (`c_id`) REFERENCES `citizen_table` (`c_id`);

--
-- Constraints for table `reports_table`
--
ALTER TABLE `reports_table`
  ADD CONSTRAINT `reports_table_b_id_fr` FOREIGN KEY (`b_id`) REFERENCES `blotter_table` (`b_id`);

--
-- Constraints for table `system_logs`
--
ALTER TABLE `system_logs`
  ADD CONSTRAINT `user_table_user_id_fr` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
