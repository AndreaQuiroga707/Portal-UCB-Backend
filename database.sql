-- Crea la base de datos
create database portal_ucb_db;

-- Utiliza la base de datos recién creada
use portal_ucb_db;

-- Definición de la tabla Usuarios
create table usuarios (
  usuario_id int auto_increment primary key,
  nombre varchar(50),
  apellido varchar(50),
  correo_electronico varchar(100) unique,
  fecha_registro timestamp default current_timestamp
);


-- Definición de la tabla Facultades
create table facultades (
  facultad_id int auto_increment primary key,
  nombre varchar(100),
  descripcion text
);

-- Definición de la tabla ProgramasAcademicos
create table programas_academicos (
  programa_id int auto_increment primary key,
  nombre varchar(100),
  facultad_id int,
  descripcion text
);

-- Definición de la tabla Cursos
create table cursos (
  curso_id int auto_increment primary key,
  nombre varchar(100),
  programa_id int,
  descripcion text
);
-- cambios 25/11/2023
-- Definición de la tabla Noticias
create table noticias (
  noticia_id int auto_increment primary key,
  enlace_imagen text,
  titulo varchar(200),
  contenido text,
  fecha_publicacion date
);



-- Definición de la tabla Eventos
create table eventos (
  evento_id int auto_increment primary key,
  enlace_imagen varchar(200),
  nombre varchar(200),
  fecha_inicio date,
  fecha_fin date,
  descripcion text
);


-- CAMBIOS DE BD 03/11/2023
-- Definición de la tabla Carreras
create table carreras (
  carrera_id int auto_increment primary key,
  nombre varchar(100),
  siglas varchar(3),
  acreditacion text, -- las acreditaciones se guardan usando el token ; para separar las acreditaciones
  duracion_semestres int,
  areas_estudio text, -- las areas de estudio se guardan usando el token ; para separar las areas de estudio
  modalidades_graduacion text, -- las modalidades de graduacion se guardan usando el token ; para separar las modalidades de graduacion
  beneficios text, -- los beneficios se guardan usando el token ; para separar los beneficios
  malla_curricular_pdf varchar(200), -- ruta del archivo pdf de la malla curricular
  video_promocional varchar(200), -- ruta del video promocional
  donde_trabajar text, -- donde trabajar se guardan usando el token ; para separar los lugares de trabajo
  docentes_tiempo_horario varchar(200), -- ruta del archivo pdf de los horarios de los docentes
  facultad_id int,
  contacto_id int
);

-- Definición de la tabla Contactos
create table contactos (
    contacto_id int auto_increment primary key,
    ci varchar(20) unique not null,
    nombre varchar(100),
    correo varchar(100),
    telefono varchar(20),
    movil varchar(20)
);

-- Definición de la tabla InstitutosInvestigacion
create table institutos_investigacion (
    instituto_id int auto_increment primary key,
    enlace_imagen text,
    nombre varchar(100),
    enlace_web varchar(200),
    lineas_investigacion varchar(200),
    descripcion text, -- pensado para que guarde usando el token ; para separar las lineas de investigacion
    carrera_id int,
    contacto_id int
);

-- Definicion de la tabla CentrosInvestigacion
create table centros_investigacion (
    centro_id int auto_increment primary key,
    enlace_imagen varchar(200),
    nombre varchar(100),
    enlace_web varchar(200),
    descripcion text, -- pensado para que guarde usando el token ; para separar las lineas de investigacion
    carrera_id int,
    contacto_id int
);


-- Definicion de la tabla SociedadesCientificas
create table sociedades_cientificas (
    sociedad_id int auto_increment primary key,
    enlace_imagen text,
    nombre varchar(100),
    enlace_web varchar(200),
    carrera_id int,
    contacto_id int
);

-- Definicion de la tabla GruposInvestigacion
create table grupos_investigacion (
    grupo_id int auto_increment primary key,
    enlace_imagen text,
    nombre varchar(100),
    enlace_web varchar(200),
    carrera_id int,
    contacto_id int
);


-- CAMBIOS DE BD 12/11/2023

-- Definición de la tabla Docentes
create table docentes (
  docente_id int auto_increment primary key,
  nombre varchar(100),
  apellido varchar(100),
  trayectoria text, -- (luego vemos si quitarlo o no) Es como la biografia del docente de su trayectoria
  foto varchar(200) -- ruta de la foto del docente
);

-- Definición de la tabla DocentesDeCarrera
create table docentes_de_carrera (
  docente_de_carrera_id int auto_increment primary key,
  docente_id int,
  carrera_id int
);

-- Definicion de tabla suscripciones
create table suscripciones (
  suscripcion_id int auto_increment primary key,
  correo varchar(100)
);

insert into suscripciones (correo) values ('ardayaalfaro@gmail.com'), ('guido.alfaro@ubc.edu.bo');

-- Definición de las claves foráneas

alter table programas_academicos
add foreign key (facultad_id)
references facultades(facultad_id);

alter table cursos
add foreign key (programa_id)
references programas_academicos(programa_id);

# alter table usuarios_facultades
# add foreign key (usuario_id)
# references usuarios(usuario_id);
#
# alter table usuarios_facultades
# add foreign key (facultad_id)
# references facultades(facultad_id);

-- CAMBIOS DE BD 03/11/2023

alter table institutos_investigacion
add foreign key (carrera_id)
references carreras(carrera_id);

alter table institutos_investigacion
add foreign key (contacto_id)
references contactos(contacto_id);

alter table centros_investigacion
add foreign key (carrera_id)
references carreras(carrera_id);

alter table centros_investigacion
add foreign key (contacto_id)
references contactos(contacto_id);

alter table sociedades_cientificas
add foreign key (carrera_id)
references carreras(carrera_id);

alter table sociedades_cientificas
add foreign key (contacto_id)
references contactos(contacto_id);

alter table grupos_investigacion
add foreign key (carrera_id)
references carreras(carrera_id);

alter table grupos_investigacion
add foreign key (contacto_id)
references contactos(contacto_id);

alter table carreras
add foreign key (facultad_id)
references facultades(facultad_id);

alter table carreras
add foreign key (contacto_id)
references contactos(contacto_id);
-- CAMBIOS DE BD 12/11/2023
alter table docentes_de_carrera
add foreign key (docente_id)
references docentes(docente_id);

alter table docentes_de_carrera
add foreign key (carrera_id)
references carreras(carrera_id);

ALTER TABLE usuarios
    ADD COLUMN password VARCHAR(255) NOT NULL;