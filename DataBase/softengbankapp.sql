-- phpMyAdmin SQL Dump
-- version 4.4.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 14, 2015 at 03:20 PM
-- Server version: 5.6.24
-- PHP Version: 5.5.9-1ubuntu4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `softengbankapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `accountid` int(12) NOT NULL,
  `bcid` int(12) NOT NULL,
  `accounttype` varchar(255) NOT NULL,
  `balance` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountid`, `bcid`, `accounttype`, `balance`) VALUES
(162, 1000, 'Checking', 54171),
(163, 1001, 'Savings', 6153),
(164, 1002, 'Checking', 45462),
(165, 1003, 'Savings', 54320),
(166, 1004, 'Checking', 71354);

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `bankid` int(12) NOT NULL,
  `bankname` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`bankid`, `bankname`) VALUES
(19, 'Royal Bank'),
(20, 'Republic Bank'),
(21, 'Scotia Bank');

-- --------------------------------------------------------

--
-- Table structure for table `bankclient`
--

CREATE TABLE `bankclient` (
  `bcid` int(12) NOT NULL,
  `bankid` int(12) NOT NULL,
  `clientid` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bankclient`
--

INSERT INTO `bankclient` (`bcid`, `bankid`, `clientid`) VALUES
(1000, 19, 23),
(1001, 19, 26),
(1002, 20, 26),
(1003, 20, 26),
(1004, 21, 26);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `clientid` int(12) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientid`, `firstname`, `lastname`, `username`, `password`) VALUES
(23, 'Mitra', 'Kalloo', 'MK', 1216985755),
(24, 'Will', 'Smith', 'WillSmith', 83581601),
(25, 'Harry', 'Potter', 'HP', 2225389),
(26, 'Amit', 'Maraj', 'AM', 1216985755);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `logid` int(11) NOT NULL,
  `account` int(11) NOT NULL COMMENT 'account bcid',
  `description` varchar(255) NOT NULL,
  `old_balance` double NOT NULL,
  `new_balance` double DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`logid`, `account`, `description`, `old_balance`, `new_balance`, `date`) VALUES
(115, 1000, 'transfer $574.0 to account 1001', 54745, 54171, '2015-04-13 14:06:52'),
(116, 1001, 'received $574.0 from account 1000', 9574, 10148, '2015-04-13 14:06:53'),
(117, 1002, 'transfer $1000.0 to account 1003', 45643, 44643, '2015-04-13 14:06:54'),
(118, 1003, 'received $1000.0 from account 1002', 53635, 54635, '2015-04-13 14:06:55'),
(119, 1003, 'transfer $69.0 to account 1004', 54635, 54566, '2015-04-14 01:58:47'),
(120, 1004, 'received $69.0 from account 1003', 67863, 67932, '2015-04-14 01:58:48'),
(121, 1001, 'transfer $420.0 to account 1003', 10148, 9728, '2015-04-14 01:59:09'),
(122, 1003, 'received $420.0 from account 1001', 54566, 54986, '2015-04-14 01:59:10'),
(123, 1001, 'transfer $4567.0 to account 1002', 9728, 5161, '2015-04-14 02:02:01'),
(124, 1002, 'received $4567.0 from account 1001', 44643, 49210, '2015-04-14 02:02:02'),
(125, 1003, 'transfer $666.0 to account 1002', 54986, 54320, '2015-04-14 03:55:01'),
(126, 1002, 'received $666.0 from account 1003', 49210, 49876, '2015-04-14 03:55:02'),
(127, 1004, 'transfer $12.0 to account 1001', 67932, 67920, '2015-04-14 04:22:55'),
(128, 1001, 'received $12.0 from account 1004', 5161, 5173, '2015-04-14 04:22:56'),
(129, 1002, 'transfer $980.0 to account 1001', 49876, 48896, '2015-04-14 05:19:21'),
(130, 1001, 'received $980.0 from account 1002', 5173, 6153, '2015-04-14 05:19:22'),
(131, 1002, 'transfer $3434.0 to account 1004', 48896, 45462, '2015-04-14 05:49:39'),
(132, 1004, 'received $3434.0 from account 1002', 67920, 71354, '2015-04-14 05:49:39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accountid`),
  ADD KEY `accountid` (`accountid`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`bankid`),
  ADD KEY `bankid` (`bankid`);

--
-- Indexes for table `bankclient`
--
ALTER TABLE `bankclient`
  ADD PRIMARY KEY (`bcid`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientid`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`logid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `accountid` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=167;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `bankid` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientid` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `logid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=133;