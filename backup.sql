mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: portal_ucb_db
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `carrera_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `siglas` varchar(3) DEFAULT NULL,
  `acreditacion` text,
  `duracion_semestres` int DEFAULT NULL,
  `areas_estudio` text,
  `modalidades_graduacion` text,
  `beneficios` text,
  `malla_curricular_pdf` varchar(200) DEFAULT NULL,
  `video_promocional` varchar(200) DEFAULT NULL,
  `donde_trabajar` text,
  `docentes_tiempo_horario` varchar(200) DEFAULT NULL,
  `facultad_id` int DEFAULT NULL,
  `contacto_id` int DEFAULT NULL,
  PRIMARY KEY (`carrera_id`),
  KEY `facultad_id` (`facultad_id`),
  KEY `contacto_id` (`contacto_id`),
  CONSTRAINT `carreras_ibfk_1` FOREIGN KEY (`facultad_id`) REFERENCES `facultades` (`facultad_id`),
  CONSTRAINT `carreras_ibfk_2` FOREIGN KEY (`contacto_id`) REFERENCES `contactos` (`contacto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (1,'Ingeniera de Sistemas','IS','Acreditado por el Comit de Acreditacin de Ingeniera',10,'Desarrollo de software; Redes de computadoras; Inteligencia artificial; Seguridad informtica','Examen final; Tesis de grado; Trabajo profesional','Alta empleabilidad; Salarios competitivos; Investigacin aplicada','https://www.example.com/malla_curricular.pdf','https://www.example.com/video_promocional.mp4','Empresas de software; Consultoras tecnolgicas; Instituciones educativas','https://www.example.com/horarios_docentes.pdf',1,1);
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centros_investigacion`
--

DROP TABLE IF EXISTS `centros_investigacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centros_investigacion` (
  `centro_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` varchar(200) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `enlace_web` varchar(200) DEFAULT NULL,
  `descripcion` text,
  `carrera_id` int DEFAULT NULL,
  `contacto_id` int DEFAULT NULL,
  PRIMARY KEY (`centro_id`),
  KEY `carrera_id` (`carrera_id`),
  KEY `contacto_id` (`contacto_id`),
  CONSTRAINT `centros_investigacion_ibfk_1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`),
  CONSTRAINT `centros_investigacion_ibfk_2` FOREIGN KEY (`contacto_id`) REFERENCES `contactos` (`contacto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centros_investigacion`
--

LOCK TABLES `centros_investigacion` WRITE;
/*!40000 ALTER TABLE `centros_investigacion` DISABLE KEYS */;
INSERT INTO `centros_investigacion` VALUES (1,'https://upload.wikimedia.org/wikipedia/commons/a/a9/Logo_UCB.png','Centro de Investigacin en Ciencias de la Computacin','https://www.ucb.edu.bo/centro-ciencias-computacion','Centro dedicado a la investigacin avanzada en reas de la computacin',1,1);
/*!40000 ALTER TABLE `centros_investigacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactos`
--

DROP TABLE IF EXISTS `contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactos` (
  `contacto_id` int NOT NULL AUTO_INCREMENT,
  `ci` varchar(20) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `movil` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`contacto_id`),
  UNIQUE KEY `ci` (`ci`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactos`
--

LOCK TABLES `contactos` WRITE;
/*!40000 ALTER TABLE `contactos` DISABLE KEYS */;
INSERT INTO `contactos` VALUES (1,'12345678','Juan Prez','juan.perez@ucb.edu.bo','123456789','987654321');
/*!40000 ALTER TABLE `contactos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `curso_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `programa_id` int DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`curso_id`),
  KEY `programa_id` (`programa_id`),
  CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`programa_id`) REFERENCES `programas_academicos` (`programa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docentes`
--

DROP TABLE IF EXISTS `docentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docentes` (
  `docente_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `trayectoria` text,
  `foto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`docente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docentes`
--

LOCK TABLES `docentes` WRITE;
/*!40000 ALTER TABLE `docentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `docentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docentes_de_carrera`
--

DROP TABLE IF EXISTS `docentes_de_carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docentes_de_carrera` (
  `docente_de_carrera_id` int NOT NULL AUTO_INCREMENT,
  `docente_id` int DEFAULT NULL,
  `carrera_id` int DEFAULT NULL,
  PRIMARY KEY (`docente_de_carrera_id`),
  KEY `docente_id` (`docente_id`),
  KEY `carrera_id` (`carrera_id`),
  CONSTRAINT `docentes_de_carrera_ibfk_1` FOREIGN KEY (`docente_id`) REFERENCES `docentes` (`docente_id`),
  CONSTRAINT `docentes_de_carrera_ibfk_2` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docentes_de_carrera`
--

LOCK TABLES `docentes_de_carrera` WRITE;
/*!40000 ALTER TABLE `docentes_de_carrera` DISABLE KEYS */;
/*!40000 ALTER TABLE `docentes_de_carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `evento_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` text,
  `nombre` varchar(200) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`evento_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (1,'http://127.0.0.1:9000/academico-bucket/UCB-Medicina-Post-Fb-Ig-1200x1200_Primer-Preuniversitario','Conferencia de IA Avanzadadisima','2025-06-15','2025-06-17','Aoonn del evento con nuevas ponencias sobre inteligencia artificial y su impacto en la sociedad.'),(2,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T224124Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=81f2be970b302dbd82c7c4a0c317b7de6b1f376a4fcd41276eaf0bd25bc988c8','Preuniversitario General','2025-01-01','2025-01-31','Clases: Matem├íticas B├ísicas, Lenguaje y Bases de la Programaci├│n.\n\nModalidad: Presencial\n\nCosto: Bs. 800.\n\nPaso 1: Dir├¡gete al Departamento de Admisi├│n y Orientaci├│n con tu carnet de identidad para ser ingresado al SIAAN.\nPaso 2: Realiza el pago del preuniversitario (Bs. 800) en cajas.\nPaso 3: Visita la Unidad de Enlaces Educativos (UNEE) para finalizar tu inscripci├│n en el bloque T ÔÇô Av. 14 de Septiembre No 4770, entre Calles 1 y 2, Obrajes.'),(3,'http://127.0.0.1:9000/academico-bucket/UCB-Medicina-Post-Fb-Ig-1200x1200_Primer-Preuniversitario-A-copia.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T230737Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=3bd1eb0e7d987f2c4b660f153f48bb069e03f3f805a1ff80b3ab32ddf22a2a71','Preuniversitario de Medicina ','2024-11-01','2025-01-31','La Carrera de Medicina tiene como requisito obligatorio cursar y aprobar el Preuniversitario. El curso preuniversitario incluye la Prueba de Aptitud Acad├®mica (PAA) sin costo adicional.\nInscripciones para el 2025: noviembre 2024 ÔÇô  diciembre 2024 ÔÇô enero 2025\n\nMaterias:\nIntroducci├│n a la Medicina.\nAprender a Aprender.\nInversi├│n: Bs. 1.000.-\n\nPrimer preuniversitario ÔÇô Presencial\nDel 18 de noviembre al 19 de diciembre.\n\nHorario de clases: De lunes a s├íbado de 16:45 a 20:15.\n\nInscripciones: Hasta el viernes 15 de noviembre.\n\nSegundo preuniversitario ÔÇô Presencial\nDel 09 al 19 de diciembre de 2024 y del 06 al 18 de enero de 2025.\n\nHorario de clases: De lunes a s├íbado de 08:00 a 13:15.\n\nInscripciones: Hasta el viernes 6 de diciembre de 2024.\n\nTercer preuniversitario ÔÇô Presencial\nDel 13 de enero al 1ro. de febrero de 2025.\n\nHorario de clases: De lunes a s├íbado de 14:15 a 19:15\n\nInscripciones: Hasta el viernes 10 de enero de 2025.\n\nCuarto preuniversitario ÔÇô Virtual\n\nVirtual intensivo sincr├│nico (para personas que viven en otros departamentos de Bolivia y extranjeros).\n\nDel 13 de enero al 1ro. de febrero 2025.\n\nHorario de clases: De lunes a s├íbado de 14:15 a 19:15\n\nInscripciones: Hasta el viernes 10 de enero de 2025.\n\nPasos para inscribirte\nRequisitos:\n\nPresentar fotocopia simple del CI.\nDocumento que acredite que el estudiante es bachiller.\nPaso 1: Dir├¡gete al Departamento de Admisi├│n y Orientaci├│n con los documentos para ser ingresado al Sistema Acad├®mico Nacional ÔÇô SIAAN.\nPaso 2: Realiza el pago del preuniversitario en cajas de la universidad (Bs. 1.000).\nInscripci├│n en l├¡nea:\n\nSolicitar formulario al n├║mero de WhatsApp: 69934380 o al correo electr├│nico: mugarte@ucb.edu.bo para env├¡ar los datos solicitados con la siguiente documentaci├│n adjunta:\n\nFotocopia simple del CI.\nDocumento que acredite que el estudiante es bachiller.');
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultades`
--

DROP TABLE IF EXISTS `facultades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facultades` (
  `facultad_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`facultad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultades`
--

LOCK TABLES `facultades` WRITE;
/*!40000 ALTER TABLE `facultades` DISABLE KEYS */;
INSERT INTO `facultades` VALUES (1,'Facultad de Ingenier├¡a','Esta facultad ofrece programas en ingenier├¡a de sistemas, civil y electr├│nica.');
/*!40000 ALTER TABLE `facultades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos_investigacion`
--

DROP TABLE IF EXISTS `grupos_investigacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos_investigacion` (
  `grupo_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` text,
  `nombre` varchar(100) DEFAULT NULL,
  `enlace_web` varchar(200) DEFAULT NULL,
  `carrera_id` int DEFAULT NULL,
  `contacto_id` int DEFAULT NULL,
  PRIMARY KEY (`grupo_id`),
  KEY `carrera_id` (`carrera_id`),
  KEY `contacto_id` (`contacto_id`),
  CONSTRAINT `grupos_investigacion_ibfk_1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`),
  CONSTRAINT `grupos_investigacion_ibfk_2` FOREIGN KEY (`contacto_id`) REFERENCES `contactos` (`contacto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos_investigacion`
--

LOCK TABLES `grupos_investigacion` WRITE;
/*!40000 ALTER TABLE `grupos_investigacion` DISABLE KEYS */;
INSERT INTO `grupos_investigacion` VALUES (1,'https://upload.wikimedia.org/wikipedia/commons/a/a9/Logo_UCB.png','Grupo de Investigacin en Inteligencia Artificial','https://www.ucb.edu.bo/grupo-investigacion-ia',1,1);
/*!40000 ALTER TABLE `grupos_investigacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutos_investigacion`
--

DROP TABLE IF EXISTS `institutos_investigacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institutos_investigacion` (
  `instituto_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` text,
  `nombre` varchar(100) DEFAULT NULL,
  `enlace_web` varchar(200) DEFAULT NULL,
  `lineas_investigacion` varchar(200) DEFAULT NULL,
  `descripcion` text,
  `carrera_id` int DEFAULT NULL,
  `contacto_id` int DEFAULT NULL,
  PRIMARY KEY (`instituto_id`),
  KEY `carrera_id` (`carrera_id`),
  KEY `contacto_id` (`contacto_id`),
  CONSTRAINT `institutos_investigacion_ibfk_1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`),
  CONSTRAINT `institutos_investigacion_ibfk_2` FOREIGN KEY (`contacto_id`) REFERENCES `contactos` (`contacto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutos_investigacion`
--

LOCK TABLES `institutos_investigacion` WRITE;
/*!40000 ALTER TABLE `institutos_investigacion` DISABLE KEYS */;
INSERT INTO `institutos_investigacion` VALUES (4,'https://upload.wikimedia.org/wikipedia/commons/a/a9/Logo_UCB.png','Instituto de Investigacin en Tecnologa','https://www.ucb.edu.bo/instituto-tecnologia','Desarrollo de software; Inteligencia artificial; Ciberseguridad','Centro de investigacin especializado en innovacin tecnolgica',1,1),(5,'http://127.0.0.1:9000/academico-bucket/Logo-IICC.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250126%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250126T162735Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d8f3f74a426b92ff0071dc185b0e659fe02f3cc717e13177d387f48a6ab54ff2','Instituto de Investigaciones en Ciencias del Comportamiento (IICC-UCB)','https://www.iicc.ucb.edu.bo/','Familia, Ni├▒ez y Adolescencia en situaci├│n de vulnerabilidad social.  Pensamiento Moral. Gesti├│n Social Ambiental.','rwe',1,1),(7,NULL,'Instituto de Investigaciones en Ciencias del Comportamiento (IICC-UCB)','https://www.iicc.ucb.edu.bo/','w','a',1,1),(8,NULL,'Instituto de Investigaciones en Ciencias del Comportamiento (IICC-UCB)','https://www.iicc.ucb.edu.bo/','adsd','a',1,1),(9,'http://127.0.0.1:9000/academico-bucket/1.25-web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T063240Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d14f0e48f8834e2cd78fe196b26b8298872153616d8930281d39c937bec58967','hoal','https://www.iicc.ucb.edu.bo/','adsd','a',1,1),(10,NULL,'Instituto de Investigaciones en Ciencias del Comportamiento (IICC-UCB)','https://www.iicc.ucb.edu.bo/','adsd','a',1,1),(11,'http://127.0.0.1:9000/academico-bucket/1.25-web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T064327Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d0f9e5cd62446e89b4b1d38987788a03463b9f89b5095f72e7280e387dfadc7b','wre','https://www.iicc.ucb.edu.bo/','w','s',1,1),(12,'http://127.0.0.1:9000/academico-bucket/1.25-web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T064642Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=6a86ac00033f80c81faf6080eab65e9b1c8f566680d362ff037cb75b34727b8f','wre','https://www.iicc.ucb.edu.bo/','adsd','sfsa',1,1),(13,'http://127.0.0.1:9000/academico-bucket/1.25-web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T064829Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=559d308dfe29614f19ed6a4f33c7f24c7876704d701572084fb8ba95f6e87299','waaaa','https://www.ucb.edu.bo/','Familia, Ni├▒ez y Adolescencia en situaci├│n de vulnerabilidad social.  Pensamiento Moral. Gesti├│n Social Ambiental.','a',1,1),(14,NULL,'sad','a.com','adsd','a',1,1),(15,'http://127.0.0.1:9000/academico-bucket/UCB-Medicina-Post-Fb-Ig-1200x1200_Primer-Preuniversitario-A-copia.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T070505Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=17cd62d859a2bbd1e9223654cfaeac1906f31cbd7079baf9cb1dbdd5f2b03fa6','wre','ad','Familia, Ni├▒ez y Adolescencia en situaci├│n de vulnerabilidad social.  Pensamiento Moral. Gesti├│n Social Ambiental.','fs',1,1),(16,'http://127.0.0.1:9000/academico-bucket/UCB-Prueba-Aptitud-Academica_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T071443Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=f11e6152b865056c4dfdfa6aa3f3431366bcb8426a29ec9b50b1e1504b017200','wre','ad','Familia, Ni├▒ez y Adolescencia en situaci├│n de vulnerabilidad social.  Pensamiento Moral. Gesti├│n Social Ambiental.','adfdasf',1,1),(17,'http://127.0.0.1:9000/academico-bucket/UCB-Prueba-Aptitud-Academica_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T071502Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=63602f1f0193025d152dffe7dcbb265c810a74880241ba3e24002dca88403ed7','wre','ad','Familia, Ni├▒ez y Adolescencia en situaci├│n de vulnerabilidad social.  Pensamiento Moral. Gesti├│n Social Ambiental.','adfdasf',1,1),(18,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T071548Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=658804def96b16b86d53b7ba2cea6df7dff547ec0922690575c06609d55f8ec5','sd','dfsa','sf','fasd',1,1),(19,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T071551Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=721851b4d6b499642d922b2d44739579f78dd1057acd3b6692f23f36fd01a650','sd','dfsa','sf','fasd',1,1),(20,'http://127.0.0.1:9000/academico-bucket/DSC_8223.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T072546Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=92eba642cafb7a10c7a0aa41dd1378dcdba532badfb682d30995d98720620856','1545564','https://www.ucb.edu.bo/','45646545','5644654',1,1),(21,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T073900Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=1d36861fc0d08f79e4011c632c3f8e32d3fb0189ffe109299f7dc201e357c9cc','afafs2222222','https://www.ucb.edu.bo/','afsdf','adfsfs',1,1),(22,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T074139Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d4df6b5e793e8bb46f11c53d2ea4e83f210d8c6f09bc00ca28ad0e6704910b94','sdaff','https://www.ucb.edu.bo/','afsdf','asfdfas',1,1),(23,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T074335Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=90e7dbe003d215d07b1b3e8e6d002a7a66f30645c28fb08e58978baaa0de2f76','sdaff','https://www.ucb.edu.bo/','afsdf','asfdfas',1,1),(24,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075113Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a519805511f561f992ad670b197f3f0cb29a2abff18ddc9bfeaa20bb316b4bef','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(25,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075116Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d8f6950be6f985b2e998e7e04a52815cfaa3e0d72dbe6643f97de015c85dba91','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(26,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075117Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=fcccb006512206c75f61c532a92150c5a686a3cfae3cb69c4d7bbdc6f22cdc30','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(27,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075117Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=fcccb006512206c75f61c532a92150c5a686a3cfae3cb69c4d7bbdc6f22cdc30','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(28,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075117Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=fcccb006512206c75f61c532a92150c5a686a3cfae3cb69c4d7bbdc6f22cdc30','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(29,'http://127.0.0.1:9000/academico-bucket/UCB-Preuniversitario-Generico_Post_web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250129T075117Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=fcccb006512206c75f61c532a92150c5a686a3cfae3cb69c4d7bbdc6f22cdc30','777','https://www.ucb.edu.bo/','afsdf','adasfdf',1,1),(30,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(31,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(32,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(33,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(34,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(35,NULL,'11','https://www.ucb.edu.bo/','afsdf','fads',1,1),(36,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(37,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(38,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(39,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(40,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(41,NULL,'ads','https://www.ucb.edu.bo/','afsdf','v',1,1),(42,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(43,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(44,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(45,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(46,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(47,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(48,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(49,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(50,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(51,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(52,NULL,'wre','https://www.ucb.edu.bo/','afsdf','.',1,1),(53,NULL,'8888','https://www.ucb.edu.bo/','afsdf','m',1,1),(54,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(55,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(56,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(57,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(58,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(59,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(60,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(61,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(62,NULL,'ads','https://www.iicc.ucb.edu.bo/','a','a',1,1),(63,NULL,'894894','https://www.ucb.edu.bo/','afsdf','.',1,1);
/*!40000 ALTER TABLE `institutos_investigacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticias`
--

DROP TABLE IF EXISTS `noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticias` (
  `noticia_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` text,
  `titulo` varchar(200) DEFAULT NULL,
  `contenido` text,
  `fecha_publicacion` date DEFAULT NULL,
  PRIMARY KEY (`noticia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticias`
--

LOCK TABLES `noticias` WRITE;
/*!40000 ALTER TABLE `noticias` DISABLE KEYS */;
INSERT INTO `noticias` VALUES (1,'http://127.0.0.1:9000/academico-bucket/DSC_8223.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250114%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250114T040354Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=03f3ee48e7ab78900d159b27503ff0d050e8c385a9dc1144849318be629659b4','UCB: sede del an├ílisis CRIDA para la gesti├│n colaborativa del agua frente al cambio clim├ítico en la Cuenca Katari','El pasado 6 de diciembre, se llev├│ a cabo el taller ÔÇ£An├ílisis CRIDA de la Cuenca KatariÔÇØ en el Paraninfo de la Universidad Cat├│lica Boliviana ┬½San Pablo┬╗, un evento que reuni├│ a representantes de diversas instituciones, comunidades y expertos en gesti├│n h├¡drica. Esta iniciativa es financiada por la UNESCO y organizada en colaboraci├│n con entidades acad├®micas y no acad├®micas, tanto nacionales como internacionales -que incluye a la principal instancia del Estado boliviano para el medio ambiente y el agua-. Su objetivo es  fortalecer la planificaci├│n participativa para enfrentar los retos del cambio clim├ítico en la regi├│n. Los participantes destacaron el papel de la UCB como sede de este encuentro y como una de las entidades que impulsan esta iniciativa para mejorar las condiciones socio ambientales de la Cuenca Katari.\n\nLogros clave: una colaboraci├│n multiactoral exitosa\n\nDurante el taller se destac├│ la creaci├│n de un espacio de di├ílogo inclusivo en el que participaron actores gubernamentales, comunitarios y acad├®micos. Leonardo Villafuerte, del Instituto para la Democracia de la UCB, resalt├│ que este tipo de encuentros permiten construir relaciones s├│lidas entre las comunidades de la cuenca y las instituciones, algo fundamental para garantizar soluciones compartidas. Adem├ís, subray├│ que la diversidad de perspectivas presentes, desde comunidades locales hasta actores gubernamentales, marc├│ un paso decisivo hacia un modelo de gesti├│n h├¡drica m├ís equitativo y colaborativo.\n\nPor su parte, la miembro del Subproyecto Transversal, Guadalupe Peres Caj├¡as, se├▒al├│ que uno de los mayores retos fue mantener un di├ílogo fluido y participativo a lo largo de una jornada completa, algo que se logr├│ gracias a la preparaci├│n previa y al compromiso de todos los asistentes. Destac├│ que la representaci├│n diversa de actores, incluyendo comunidades de la cuenca alta, media y baja, fue esencial para identificar problemas y propuestas que reflejen las realidades de todos los involucrados.\n\nImpacto en la planificaci├│n adaptativa\n\nEn el taller se desarroll├│ la metodolog├¡a CRIDA (An├ílisis de Decisiones Basadas en el Riesgo Clim├ítico), una herramienta que facilita la toma de decisiones frente a escenarios de incertidumbre clim├ítica. Afnan Agramont, coordinador del proyecto, explic├│ que esta metodolog├¡a se basa en cuatro momentos clave: el mapeo de actores, la identificaci├│n de problemas relacionados con el agua, el an├ílisis de los impactos del cambio clim├ítico y la planificaci├│n de estrategias adaptativas. Este marco permiti├│ a los participantes analizar las vulnerabilidades de la cuenca Katari y desarrollar propuestas pr├ícticas y escalables para enfrentar los retos clim├íticos.\n\nAgramont destac├│ que el enfoque participativo de CRIDA permiti├│ integrar conocimientos t├®cnicos y locales, garantizando que las estrategias propuestas sean s├│lidas y flexibles ante diversos escenarios clim├íticos. Seg├║n el coordinador, el producto final de este proceso ser├í una planificaci├│n que no solo reconozca los desaf├¡os del cambio clim├ítico, sino que tambi├®n incorpore medidas concretas para abordarlos.\n\nImportancia de la colaboraci├│n\n\nEn el taller tambi├®n se subray├│ la importancia de las relaciones multiactorales. Peres Caj├¡as enfatiz├│ que las reuniones previas entre los diferentes actores, incluyendo representantes comunitarios, fueron decisivas para establecer objetivos comunes y preparar un espacio din├ímico y constructivo durante la jornada. Estas interacciones permitieron conocer los intereses y perspectivas de cada parte, fomentando un ambiente de cooperaci├│n y confianza.\n\nVillafuerte a├▒adi├│ que la participaci├│n activa de las comunidades no solo enriquece el proceso, sino que tambi├®n es crucial para garantizar que las soluciones sean inclusivas y representen las realidades locales. Adem├ís, valor├│ que este evento marca un cambio hacia un enfoque m├ís participativo en la gesti├│n de recursos h├¡dricos, donde todas las voces tienen un rol importante.\n\nEste enfoque colaborativo y adaptativo posiciona a la cuenca Katari como un modelo de resiliencia clim├ítica, demostrando que el trabajo conjunto entre comunidades, instituciones y expertos es clave para enfrentar los desaf├¡os globales del cambio clim├ítico.','2025-01-09'),(2,'http://127.0.0.1:9000/academico-bucket/1.25-web.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250114%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250114T141746Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2fa90b0c2f637e030b18bce1d8ae108369764f48b25faedc8282dcdac0991598','ConvocatoriaAsistentes para Laboratorios de Computaci├│n','El Centro de C├│mputo de la Universidad Cat├│lica Boliviana invita a los estudiantes interesados en colaborar como asistentes en los Laboratorios de Computaci├│n durante el semestre 1-2025 a presentar su postulaci├│n.\n\nRequisitos:\n\nConocimientos b├ísicos en inform├ítica, electr├│nica, redes e instalaci├│n de aplicaciones.\nHabilidad para instalar, configurar y reparar computadoras.\nVocaci├│n de servicio e inter├®s en aprender e implementar nuevas tecnolog├¡as.\nDisponibilidad para trabajar en turnos de 4 horas continuas.\nConocimiento b├ísico de ingl├®s t├®cnico.\nSer estudiante regular durante el semestre 1-2025.\nProceso de postulaci├│n:\n\nEnviar su hoja de vida a las oficinas del Centro de C├│mputo (Anexo Bloque D, piso 2) o al correo electr├│nico: cc.lpz@ucb.edu.bo.\nPlazo m├íximo: 21 de enero de 2025.','2025-01-08'),(3,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250114%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250114T141935Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=b5cd045955e8a0a097a0ce9e791d3e21ff22a3a40975e66169c5ebcf6f6d95e2','Estudiantes de Ingenier├¡a Industrial accedieron a la doble titulaci├│n con la Pontificia Universidad Javeriana de Colombia','La doble titulaci├│n de los estudiantes de la Universidad Cat├│lica Boliviana en la Pontificia Universidad Javeriana de Colombia se hace realidad a partir del 27 de enero de 2025, fecha en la que tres estudiantes de Ingenier├¡a Industrial de la Sede La Paz, iniciar├ín clases de sus ├║ltimos semestres en Bogot├í Colombia y lograr├ín la titulaci├│n en Ingenier├¡a Industrial v├ílida para Colombia como para Bolivia.\n\nIngenier├¡a Industrial de la Sede La Paz, manifiesta su orgullo por este nuevo paso hacia la excelencia acad├®mica, que dan nuestros estudiantes y agradece a los promotores de la Doble Titulaci├│n UCB-PUJ en las personas de: Mgr. Jos├® Loaiza Torrez, Rector UCB de la Sede Tarija, Dr. Lope Hugo Barrero Solano, Decano de la Facultad de Ingenier├¡a de la PUJ ÔÇô Bogot├í, Mgr. Ren├ín Laguna Vargas, Decano de la Facultad de Ingenier├¡a de la UCB ÔÇô La Paz y el Mgr. Cesar Augusto Mend├¡vil Collazos, Director de Ingenier├¡a Industrial UCB ÔÇô Santa Cruz\n\n┬íMuchas felicidades a nuestros destacados estudiantes de Ingenier├¡a Industrial por haber optado por la doble titulaci├│n con la Pontificia Universidad Javeriana de Bogot├í Colombia!\n\nCelebramos sus logros y deseamos mucho ├®xito en sus estudios en Colombia a:\n\nLorena Ayala Altamirano\nMateo Mendoza Villegas\nHenry Jhon Apaza Balboa\nLa carrera de Ingenier├¡a Industrial de la Universidad Cat├│lica Boliviana, programa acreditado por el Sistema Educativo del MERCOSUR, promueve la mejora continua en su gesti├│n acad├®mica y la doble titulaci├│n que seguir├ín los estudiantes, revela la excelencia formativa y oportunidades que brinda el programa a los estudiantes de Bolivia.','2025-01-08'),(5,'http://127.0.0.1:9000/academico-bucket/DSC_0616.webp?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=academico-access-key%2F20250126%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250126T162120Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=9fec27c7ee3b49ceaa36ab70b00c2ee0daef5bdf5f2827df5898a130697027cc','asdfdds','dasfdsf','2024-12-31');
/*!40000 ALTER TABLE `noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_history`
--

DROP TABLE IF EXISTS `password_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_history` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `password_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_history`
--

LOCK TABLES `password_history` WRITE;
/*!40000 ALTER TABLE `password_history` DISABLE KEYS */;
INSERT INTO `password_history` VALUES (1,3,'3ca07f73da55365f3e3738f3ee1a8317ea599d3e712f9e56c6972e08f3708702','2025-01-27 01:36:54'),(2,4,'89ba8a1cd0caaa5306e1d6b0d3912a149adfaa59772bb2dc10f2b59d51077b76','2025-01-27 16:46:23'),(3,5,'28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521','2025-01-27 17:53:12'),(4,6,'7ade4009723cebef9e76657f41b5125f3638500388ccbdaf1e523ec88719a8f4','2025-01-27 18:09:22'),(5,1,'28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521','2025-01-27 18:35:57'),(6,1,'28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521','2025-01-27 18:35:57'),(8,7,'c9d89560582da2b624d2ba408bc2d834ff7e0e8a4236bae84f4bed71d46312fa','2025-01-27 19:34:56'),(9,1,'1ffc92b2484a76955eebfd05266f9073cf72ca7653c89dba3be18b8ee33b07a5','2025-01-27 19:51:36'),(10,8,'28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521','2025-01-28 00:59:06'),(11,1,'4fcefe4a89986fb403f34ffd8842eac863f5999a326896a7ede4811d90b06f34','2025-01-28 09:40:21'),(12,9,'28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521','2025-01-28 10:29:53');
/*!40000 ALTER TABLE `password_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programas_academicos`
--

DROP TABLE IF EXISTS `programas_academicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programas_academicos` (
  `programa_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `facultad_id` int DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`programa_id`),
  KEY `facultad_id` (`facultad_id`),
  CONSTRAINT `programas_academicos_ibfk_1` FOREIGN KEY (`facultad_id`) REFERENCES `facultades` (`facultad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas_academicos`
--

LOCK TABLES `programas_academicos` WRITE;
/*!40000 ALTER TABLE `programas_academicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `programas_academicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`rol_id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN','Rol de administrador'),(2,'GESTOR DE USUARIOS','Rol para gestores de cuentas');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sociedades_cientificas`
--

DROP TABLE IF EXISTS `sociedades_cientificas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sociedades_cientificas` (
  `sociedad_id` int NOT NULL AUTO_INCREMENT,
  `enlace_imagen` text,
  `nombre` varchar(100) DEFAULT NULL,
  `enlace_web` varchar(200) DEFAULT NULL,
  `carrera_id` int DEFAULT NULL,
  `contacto_id` int DEFAULT NULL,
  PRIMARY KEY (`sociedad_id`),
  KEY `carrera_id` (`carrera_id`),
  KEY `contacto_id` (`contacto_id`),
  CONSTRAINT `sociedades_cientificas_ibfk_1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`),
  CONSTRAINT `sociedades_cientificas_ibfk_2` FOREIGN KEY (`contacto_id`) REFERENCES `contactos` (`contacto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sociedades_cientificas`
--

LOCK TABLES `sociedades_cientificas` WRITE;
/*!40000 ALTER TABLE `sociedades_cientificas` DISABLE KEYS */;
INSERT INTO `sociedades_cientificas` VALUES (1,'https://upload.wikimedia.org/wikipedia/commons/a/a9/Logo_UCB.png','Sociedad Cientfica de Ingeniera de Sistemas','https://www.ucb.edu.bo/sociedad-ingenieria-sistemas',1,1);
/*!40000 ALTER TABLE `sociedades_cientificas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripciones`
--

DROP TABLE IF EXISTS `suscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripciones` (
  `suscripcion_id` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`suscripcion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripciones`
--

LOCK TABLES `suscripciones` WRITE;
/*!40000 ALTER TABLE `suscripciones` DISABLE KEYS */;
INSERT INTO `suscripciones` VALUES (3,'andrea.712585@gmail.com'),(4,'yameltorrezvaldez@gmail.com');
/*!40000 ALTER TABLE `suscripciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `correo_electronico` varchar(100) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(255) NOT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `reset_token_expiration` timestamp NULL DEFAULT NULL,
  `failed_attempts` int DEFAULT '0',
  `is_locked` tinyint(1) DEFAULT '0',
  `lock_time` timestamp NULL DEFAULT NULL,
  `last_password_update` timestamp NULL DEFAULT NULL,
  `rol_id` int DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`),
  KEY `fk_rol` (`rol_id`),
  CONSTRAINT `fk_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Andreaasfdsfasdf','Quirogafsdaf','andrea.quiroga@ucb.edu.bo','2025-01-25 16:56:01','4fcefe4a89986fb403f34ffd8842eac863f5999a326896a7ede4811d90b06f34',NULL,NULL,0,0,NULL,'2025-01-28 09:40:21',2),(2,'Pepe','Tercero','andrea.quiroga.p@ucb.edu.bo','2025-01-26 19:09:01','2f21eda65ee18075e7aa088ab896d852ad499d87a6bd9eeb47912b3d72c3fb65',NULL,NULL,4,1,'2025-01-27 20:13:55',NULL,1),(3,'Juan','Perez','juan.perez@ucb.edu.bo','2025-01-27 01:36:53','3ca07f73da55365f3e3738f3ee1a8317ea599d3e712f9e56c6972e08f3708702',NULL,NULL,3,1,'2025-01-27 18:54:54','2025-01-27 01:36:53',2),(4,'Mayra','Valdez','mayra.valdez.t@ucb.edu.bo','2025-01-27 16:46:23','89ba8a1cd0caaa5306e1d6b0d3912a149adfaa59772bb2dc10f2b59d51077b76',NULL,NULL,0,0,'2025-01-27 19:01:57','2025-01-27 16:46:23',2),(5,'Roberto','Alanoca','roberto.alanoca@ucb.edu.bo','2025-01-27 17:53:12','28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521',NULL,NULL,0,0,NULL,'2025-01-27 17:53:12',2),(6,'pruebaaa ','comprobar','prueba.c@ucb.edu.bo','2025-01-27 18:09:22','7ade4009723cebef9e76657f41b5125f3638500388ccbdaf1e523ec88719a8f4',NULL,NULL,0,0,NULL,'2025-01-27 18:09:22',1),(7,'pruebaaa2','comprobar','prueba2.c@ucb.edu.bo','2025-01-27 19:34:56','c9d89560582da2b624d2ba408bc2d834ff7e0e8a4236bae84f4bed71d46312fa',NULL,NULL,0,0,NULL,'2025-01-27 19:34:56',2),(8,'Administrador','Eventos','administrador.eventos@ucb.edu.bo','2025-01-28 00:59:05','28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521',NULL,NULL,0,0,NULL,'2025-01-28 00:59:05',1),(9,'Mayra2','Valdez','mayra.valdez.torrez@ucb.edu.bo','2025-01-28 10:29:52','28d0c028cbbe231efcd7046863420b85ca6978c683ca27aa066e4e78edb23521',NULL,NULL,0,0,NULL,'2025-01-28 10:29:52',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-30  4:31:51
