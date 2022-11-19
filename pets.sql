-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 06-06-2022 a las 02:57:37
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pets`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`, `nombre`, `rol`) VALUES
(1, 'erik@correo.com', '$2b$10$ubqpBm5No2N2qopuoD4xJeMX/iRVJg.W67PtqoiPEG3H7QXFGNVwC', 'erik', 'ADMIN'),
(2, 'tona@correo.com', '$2b$10$ZjBkqLjetm4DgaVZdIUBm.THyetxw7fHHrIRXV3TEPbha50s80l1y', 'tonatiuh', 'ADMIN'),
(12, 'fer@correo.com', '$2a$10$tD0GhGldcMGl6OHwPszIuO7CNgblVgtplnxFdRErdbSFSq6rPAFOS', 'fernando', 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE IF NOT EXISTS `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `edad` varchar(50) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre_mascota` varchar(255) DEFAULT NULL,
  `ruta_imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `report`
--

INSERT INTO `report` (`id`, `edad`, `estado`, `nombre_mascota`, `ruta_imagen`) VALUES
(11, '19', b'1', 'negro', 'bulldog.jpg'),
(12, '0.5', b'1', 'pelusa', 'cat3.jpg'),
(13, '1.2', b'1', 'gary', 'cat2.jpg'),
(14, '1', b'1', 'leon', 'cat4.jpg'),
(15, '3.2', b'1', 'canelo1', 'puppy.jpg'),
(17, '21', b'1', 'puug1', 'pug.jpg'),
(21, '4', b'1', 'pupy', 'lazy.jpg'),
(22, '1', b'1', 'kike', 'guinea-pig-gbfaeebc05_640.jpg'),
(23, '6', b'1', 'mafu', 'lemur-ge2aa495e9_640.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
