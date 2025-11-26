-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2025 at 05:48 PM
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
-- Database: `librarymanagmentssystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `auther` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `isbn` varchar(50) DEFAULT NULL,
  `publishDate` date DEFAULT NULL,
  `pageCount` int(11) DEFAULT NULL,
  `copyCount` int(11) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `profileImagepath` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `auther`, `category`, `isbn`, `publishDate`, `pageCount`, `copyCount`, `publisher`, `language`, `profileImagepath`) VALUES
(1, 'Java1', 'Ameer', 'edu', '12b1', '2015-01-15', 400, 1, 'sara', 'EN', '/Images/book2.jpg'),
(2, 'Java2', 'Amal', 'edu', '12b2', '2017-07-27', 400, 5, 'Toqa', 'EN', '/Images/book1.jpg'),
(3, 'Anne with an e', 'ali', 'story', '13b1', '2004-06-23', 300, 4, 'toqa', 'EN', '/Images/bookicon.jpg'),
(4, 'harry boter', 'ahmed', 'crime', '14b1', '2000-06-11', 200, 3, 'toqa', 'AR', '/Images/books_icon.png');

-- --------------------------------------------------------

--
-- Table structure for table `borrowbookdetails`
--

CREATE TABLE `borrowbookdetails` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `userImage` text DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `bookTitle` varchar(50) DEFAULT NULL,
  `bookImage` text DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrowbookdetails`
--

INSERT INTO `borrowbookdetails` (`id`, `userId`, `userName`, `userImage`, `bookId`, `bookTitle`, `bookImage`, `status`) VALUES
(7, 1, 'Sara Abu Daqqa', '/Images/image1.jpg', 1, 'Java1', '/Images/book2.jpg', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category`) VALUES
(1, 'edu'),
(2, 'story'),
(3, 'crime');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `profileImagePath` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullName`, `userName`, `password`, `email`, `phone`, `role`, `profileImagePath`) VALUES
(1, 'Sara Abu Daqqa', 'user', 'user123', 'sara@gmail.com', '056778945', 'User', '/Images/image1.jpg'),
(2, 'Toqa Abu daqqa', 'admin', 'admin123', 'toqa@gmail.com', '0566623456', 'Admin', '/Images/person1.jpg'),
(3, 'Ahmed Al Haj', 'lib', 'lib123', 'ahmed@gmail.com', '0566623456', 'Librerian', '/Images/person2.jpg'),
(5, 'Alaa Abu daqqa', 'admin2', 'admin123', 'alaa@gmail.com', '059876643', 'Admin', '/Images/image1.jpg'),
(6, 'ali', 'user3', 'user123', 'ssss', '00000', 'User', '/Images/person2.jpg'),
(7, 'ameer', 'lib2', 'lib123', 'dddfg', '0334567', 'Librerian', '/Images/Admin_icon.jpg'),
(8, 'waad', 'user4', 'user123', 'asdf', '1234567', 'User', '/Images/image1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `borrowbookdetails`
--
ALTER TABLE `borrowbookdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userId` (`userId`),
  ADD KEY `bookId` (`bookId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userName` (`userName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `borrowbookdetails`
--
ALTER TABLE `borrowbookdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowbookdetails`
--
ALTER TABLE `borrowbookdetails`
  ADD CONSTRAINT `borrowbookdetails_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `borrowbookdetails_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `books` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
