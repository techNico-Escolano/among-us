-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-04-2026 a las 03:16:05
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nave_espacial`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `id_sala` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `saboteada` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`id_sala`, `nombre`, `saboteada`) VALUES
(1, 'Cafetería', 0),
(2, 'Electricidad', 0),
(3, 'Motores', 0),
(4, 'Armería', 0),
(5, 'Navegación', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

CREATE TABLE `tarea` (
  `id_tarea` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `completada` tinyint(1) DEFAULT 0,
  `id_tripulante` int(11) DEFAULT NULL,
  `id_sala` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarea`
--

INSERT INTO `tarea` (`id_tarea`, `descripcion`, `completada`, `id_tripulante`, `id_sala`) VALUES
(1, 'Vaciar el basurero', 0, 1, 1),
(2, 'Arreglar el cableado eléctrico', 0, 2, 2),
(3, 'Alinear la salida del motor principal', 0, 3, 3),
(4, 'Calibrar el distribuidor', 0, 4, 2),
(5, 'Descargar datos de navegación', 0, 1, 5),
(6, 'Destruir asteroides', 0, 2, 4),
(7, 'Llenar los botes de gasolina', 0, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tripulante`
--

CREATE TABLE `tripulante` (
  `id_tripulante` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `vivo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tripulante`
--

INSERT INTO `tripulante` (`id_tripulante`, `nombre`, `rol`, `vivo`) VALUES
(1, 'Rojo', 'capitan', 1),
(2, 'Azul', 'medico', 1),
(3, 'Verde', 'ingeniero', 1),
(4, 'Amarillo', 'tripulante', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id_sala`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id_tarea`),
  ADD KEY `id_tripulante` (`id_tripulante`),
  ADD KEY `id_sala` (`id_sala`);

--
-- Indices de la tabla `tripulante`
--
ALTER TABLE `tripulante`
  ADD PRIMARY KEY (`id_tripulante`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `id_sala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id_tarea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tripulante`
--
ALTER TABLE `tripulante`
  MODIFY `id_tripulante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD CONSTRAINT `tarea_ibfk_1` FOREIGN KEY (`id_tripulante`) REFERENCES `tripulante` (`id_tripulante`),
  ADD CONSTRAINT `tarea_ibfk_2` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
