-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-09-2023 a las 23:04:55
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bonati_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'walter', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IdUsuario` int(11) NOT NULL,
  `ApeYNom` varchar(50) NOT NULL,
  `Dni` int(11) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `TelefonoAux` int(11) NOT NULL,
  `ObraSocial` varchar(100) NOT NULL,
  `Domicilio` varchar(200) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `YaPago` bit(1) NOT NULL DEFAULT b'0',
  `image` varchar(100) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`IdUsuario`, `ApeYNom`, `Dni`, `Telefono`, `TelefonoAux`, `ObraSocial`, `Domicilio`, `FechaNacimiento`, `YaPago`, `image`) VALUES
(1, 'Jesus Colmenares', 96030909, 2147483647, 1, '1', 'Padre Arce 190 dep 7', '1998-07-16', b'0', 'C:\\\\Users\\\\AMD Ryzen 5 5500\\\\Downloads\\\\356635312_10223698741358274_6536068915640769722_n.jpg'),
(12, 'Rocio Paez', 1234, 1234, 1234, 'No', 'Supe', '2000-03-21', b'0', 'C:\\\\Users\\\\AMD Ryzen 5 5500\\\\Pictures\\\\DSC_36291.jpg'),
(13, 'fdas', 2321, 321, 321, 'fda', 'fads', '2023-09-01', b'0', 'C:\\\\Users\\\\AMD Ryzen 5 5500\\\\Pictures\\\\Bastian3.png'),
(14, 'fadsfdas', 132321, 321, 321321, 'fdsafda', 'fsafdas', '2023-08-31', b'0', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IdPago` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL DEFAULT 0,
  `FechaPago` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`IdPago`, `IdUsuario`, `Cantidad`, `FechaPago`) VALUES
(1, 1, 5000, '2023-05-18 13:44:39'),
(2, 1, 3000, '2023-09-17 15:07:54'),
(3, 12, 10000, '2023-09-17 16:06:15'),
(4, 12, 1000, '2023-09-17 16:09:46'),
(5, 12, 15000, '2023-09-17 16:22:30');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IdUsuario`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IdPago`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `IdPago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
