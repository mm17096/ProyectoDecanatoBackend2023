-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-09-2023 a las 04:44:12
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

CREATE TABLE `consulta` (
  `id_consulta` int(11) NOT NULL,
  `fecha_consulta` datetime(6) DEFAULT NULL,
  `hora_consulta` time(6) DEFAULT NULL,
  `num_consultorio` varchar(255) DEFAULT NULL,
  `id_especialidad` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`id_consulta`, `fecha_consulta`, `hora_consulta`, `num_consultorio`, `id_especialidad`, `id_medico`, `id_paciente`) VALUES
(1, '2023-05-29 14:14:12.000000', '14:14:20.000000', '2-1', 1, 2, 1),
(2, '2023-05-30 14:14:49.000000', '10:14:56.000000', '2-5', 1, 2, 2),
(3, '2023-05-31 14:15:21.000000', '08:15:28.000000', '2-4', 2, 3, 3),
(4, '2023-05-30 14:14:49.000000', '09:16:04.000000', '2-3', 1, 5, 1),
(6, '2023-06-29 14:16:32.000000', '14:16:37.000000', '2-1', 3, 4, 2),
(7, '2023-05-30 14:14:49.000000', '22:41:03.000000', '2-1', 1, 2, 4),
(8, '2023-05-30 22:41:03.000000', '22:41:03.000000', '2-10', 3, 3, 1),
(9, '2023-06-12 08:14:12.000000', '08:14:12.000000', '2-7', 2, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta_examanen`
--

CREATE TABLE `consulta_examanen` (
  `id_detalle_consulta` int(11) NOT NULL,
  `id_examen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `consulta_examanen`
--

INSERT INTO `consulta_examanen` (`id_detalle_consulta`, `id_examen`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(3, 5),
(4, 6),
(8, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_consulta`
--

CREATE TABLE `detalle_consulta` (
  `id_detalle_consulta` int(11) NOT NULL,
  `diagnostico` varchar(255) DEFAULT NULL,
  `tratamiento` varchar(255) DEFAULT NULL,
  `id_consulta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_consulta`
--

INSERT INTO `detalle_consulta` (`id_detalle_consulta`, `diagnostico`, `tratamiento`, `id_consulta`) VALUES
(1, 'ESTÁ CANSADO', 'REPOSO ABSOLUTO', 1),
(2, 'DX 2', 'REPOSO ALTERNADO', 1),
(3, 'DX 1', 'SIN REPOSO', 1),
(4, 'MUY CANSADO', 'REPOSO', 2),
(5, 'CANSADISIMO', 'DESCANSO', 3),
(6, 'DEMASIADO CANSADO', 'DESCANSAR', 4),
(7, 'SUPER CANSADO', 'REPOSAR MUCHO', 6),
(8, 'ESTÁ MUY AGOTADO', 'DESCANSO', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `id_especialidad` int(11) NOT NULL,
  `nombre_especiadad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`id_especialidad`, `nombre_especiadad`) VALUES
(1, 'OFTALMOLOGÍA '),
(2, 'PEDIATRIA'),
(3, 'GINECOLOGÍA'),
(4, 'CIRUGIA GENERAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `examen`
--

CREATE TABLE `examen` (
  `id_examen` int(11) NOT NULL,
  `lectura` varchar(255) DEFAULT NULL,
  `nombre_examen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `examen`
--

INSERT INTO `examen` (`id_examen`, `lectura`, `nombre_examen`) VALUES
(1, 'CON PROBLEMAS DE DIGESTION', 'HECES'),
(2, 'CON PROBLEMAS DE ORINA', 'ORINA'),
(3, 'PARASITOS', 'HECES'),
(4, 'PARASITOS INTESTINALES', 'HECES'),
(5, 'TRIGLICERIDOS ALTOS', 'SANGRE'),
(6, 'COLESTEROL MALO ALTO', 'SANGRE'),
(7, 'ARDOR AL ORINAR', 'ORINA'),
(8, 'GASES ', 'HECES'),
(9, 'FIEBRE', 'ANTIGENOS FEBRILES'),
(10, 'ORINA AMARILLA', 'ORINA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `id_medico` int(11) NOT NULL,
  `apellido_medico` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `jvpm` varchar(10) NOT NULL,
  `nombre_medico` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`id_medico`, `apellido_medico`, `foto`, `jvpm`, `nombre_medico`) VALUES
(1, 'PALACIOS MARINERO', NULL, '1233', 'JACINTO FRANCISCO'),
(2, 'PINEDA HENRRIQUEZ', NULL, '3232', 'JUANA DE JESUS'),
(3, 'ALVARADO DE PINEDA MODI', NULL, '6000', 'JOSEFINA ALEJANDRINA MODI'),
(4, 'FUENTES ALVARADO', NULL, '4654', 'MARIA DE LOS ANGELES'),
(5, 'DURAN ALEMÁN', NULL, '1244', 'MARINA ABIGAIL'),
(7, 'DEL CID ALVARADO', NULL, '5555', 'ILEANA PATRICIA'),
(8, 'MARIO', NULL, '1003', 'MIRNA ALEJA'),
(9, 'TEST APELLIDO', NULL, '1503', 'TEST NOMBRE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id_paciente` int(11) NOT NULL,
  `apellido_paciente` varchar(70) NOT NULL,
  `direccion_paciente` varchar(100) NOT NULL,
  `email_paciente` varchar(25) DEFAULT NULL,
  `ident_paciente` varchar(20) DEFAULT NULL,
  `nombre_paciente` varchar(70) NOT NULL,
  `telefono_paciente` varchar(10) NOT NULL,
  `fecha_nacimiento_paciente` date DEFAULT NULL,
  `fecha_vencimiento_dui_paciente` date DEFAULT NULL,
  `tiene_expediente_paciente` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id_paciente`, `apellido_paciente`, `direccion_paciente`, `email_paciente`, `ident_paciente`, `nombre_paciente`, `telefono_paciente`, `fecha_nacimiento_paciente`, `fecha_vencimiento_dui_paciente`, `tiene_expediente_paciente`) VALUES
(1, 'PONCE AGUILAR', 'SAN VICENTE', 'ponceaguilar@gmail.com', '00745693-4', 'MARIO ALEXIS', '2374-5555', '2000-07-08', '2025-07-08', b'1'),
(2, 'ROQUE MARTIR', 'SAN CAYETANO ISTEPEQUE', 'roquemartir@gmail.com', '00456923-7', 'JULIA ALEJANDRA', '7456-7777', '2010-07-08', '2025-07-08', b'1'),
(3, 'ANDRADE DE LA CRUZ', 'APASTEPEQUE', 'andradedelacruz@gmail.com', '00956823-8', 'FAUSTO ALEXIS', '6452-0000', '2000-07-08', '2025-07-08', b'0'),
(4, 'ROMERO FUNES', 'SAN SALVADOR', 'funes@gmail.com', '22241131-8', 'ADA YESICA', '7852-4444', '2000-07-08', '2025-07-08', b'0'),
(6, ' NAJERA', 'SAN SALVADOR', 'najera@gmail.com', '22222244-4', 'MARICELA', '7855-1111', '1990-02-02', '2026-02-03', b'1'),
(7, 'VIOLANTES', 'SAN VICENTE', NULL, '00745693-3', 'SEBASTIAN', '2374-7775', '2000-07-08', '2025-06-16', b'0'),
(8, 'FUENTES', 'SAN VICENTE', 'duran@gmail.com', '00745693-3', 'SEBASTIANA', '2374-7775', '1988-07-08', '2030-06-16', b'1'),
(9, 'Serrano Ortega', 'La Libertad', 'maria.serrano@gmail.com', '02034456', 'ANGELES RENDEROS Y GUERREROSD', '70984586', '1981-06-16', '2025-06-16', b'0'),
(10, 'Serrano Ortega', 'La Libertad', 'maria.serrano@gmail.com', '02034456', 'aa', '70984586', '1981-06-16', '2025-06-16', b'0'),
(11, 'Serrano Ortega', 'La Libertad', 'maria.serrano@gmail.com', '02034456', 'bb', '70984586', '1981-06-16', '2025-06-16', b'0'),
(12, 'Serrano Ortega', 'La Libertad', 'maria.serrano@gmail.com', '02034456', 'cc', '70984586', '1981-06-16', '2025-06-16', b'0'),
(13, 'duran', 'La Libertad', 'maria.serrano@gmail.com', '02034456', 'ana', '70984586', '1981-06-16', '2025-06-16', b'0'),
(14, 'duran', 'La Libertad', 'maria.serrano@gmail.com', '02034', 'anita', '70984586', '1981-06-16', '2025-06-16', b'0'),
(15, 'duran', 'La Libertad', 'maria.serrano@gmail.com', '020346666666666', 'anita', '70984586', '1981-06-16', '2025-06-16', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre_rol`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `email`, `enabled`, `password`, `username`) VALUES
(1, 'francovargas@ues.edu.sv', b'1', '$2a$10$NFoh5PqP68QYLvVFKImAbuCDBdTEFgVC311M00zyu9H.XpVdYcRN.', 'francovargas'),
(2, NULL, b'1', '$2a$10$TAvRTJK4Vjm.xQ96uJ1s/e8qI2LU2rxMNdlziN5EwCgiD2/sTvPzW', 'clemus'),
(3, NULL, b'1', '$2a$10$8WPzo9pgHLhYMaYZrPSvouvdeW8qQGF98rONE87an5m3cl2MGy.hi', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`id_usuario`, `id_rol`) VALUES
(1, 1),
(2, 1),
(2, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id_consulta`),
  ADD KEY `FK_consulta_especialidad` (`id_especialidad`),
  ADD KEY `FK_consulta_medico` (`id_medico`),
  ADD KEY `FK_consulta_paciente` (`id_paciente`);

--
-- Indices de la tabla `consulta_examanen`
--
ALTER TABLE `consulta_examanen`
  ADD KEY `FKfutmc71hsaweiwssh9p5wu0d0` (`id_examen`),
  ADD KEY `FKayrryqhhuewe53jsfde1a5qo4` (`id_detalle_consulta`);

--
-- Indices de la tabla `detalle_consulta`
--
ALTER TABLE `detalle_consulta`
  ADD PRIMARY KEY (`id_detalle_consulta`),
  ADD KEY `FK_consulta_detalle` (`id_consulta`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`id_especialidad`);

--
-- Indices de la tabla `examen`
--
ALTER TABLE `examen`
  ADD PRIMARY KEY (`id_examen`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`id_medico`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id_paciente`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `UK_l0qdsam7tunbtmxcmeeyfcifk` (`nombre_rol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD UNIQUE KEY `UKic3tbjpm5dum3n2xdrnns9gwm` (`id_usuario`,`id_rol`),
  ADD KEY `FKkxcv7htfnm9x1wkofnud0ewql` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consulta`
--
ALTER TABLE `consulta`
  MODIFY `id_consulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle_consulta`
--
ALTER TABLE `detalle_consulta`
  MODIFY `id_detalle_consulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `id_especialidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `examen`
--
ALTER TABLE `examen`
  MODIFY `id_examen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `id_medico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `FK_consulta_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id_especialidad`),
  ADD CONSTRAINT `FK_consulta_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  ADD CONSTRAINT `FK_consulta_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`);

--
-- Filtros para la tabla `consulta_examanen`
--
ALTER TABLE `consulta_examanen`
  ADD CONSTRAINT `FKayrryqhhuewe53jsfde1a5qo4` FOREIGN KEY (`id_detalle_consulta`) REFERENCES `detalle_consulta` (`id_detalle_consulta`),
  ADD CONSTRAINT `FKfutmc71hsaweiwssh9p5wu0d0` FOREIGN KEY (`id_examen`) REFERENCES `examen` (`id_examen`);

--
-- Filtros para la tabla `detalle_consulta`
--
ALTER TABLE `detalle_consulta`
  ADD CONSTRAINT `FK_consulta_detalle` FOREIGN KEY (`id_consulta`) REFERENCES `consulta` (`id_consulta`);

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `FK3ftpt75ebughsiy5g03b11akt` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FKkxcv7htfnm9x1wkofnud0ewql` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
