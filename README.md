PORTAL WEB INTEGRAL PARA LA UNIVERSIDAD CATÓLICA BOLIVIANA

Descripción General

Su propósito es servir como centro de información, interacción y servicios para la comunidad universitaria, incluyendo estudiantes, profesores, personal administrativo y visitantes. El portal ofrece una amplia gama de características y funcionalidades para satisfacer las necesidades de la universidad.

Objetivo

El objetivo principal de este proyecto es proporcionar a la Universidad Católica Boliviana una plataforma moderna y completa que abarque desde la identidad institucional hasta la gestión académica, pasando por la comunicación eficiente y la interacción con la comunidad universitaria. Los objetivos clave incluyen:

  - Diseño e Identidad Institucional: Crear un diseño atractivo y coherente con la identidad visual de la universidad, utilizando una paleta de colores y elementos gráficos que reflejen los valores y la historia de la institución.

  - Secciones Académicas: Ofrecer información detallada sobre facultades, programas académicos y cursos, incluyendo detalles sobre el personal académico de cada departamento.

  - Admisión y Becas: Proporcionar requisitos de admisión, fechas importantes y procedimientos de solicitud, así como información sobre oportunidades de becas y ayudas financieras.

  - Plataforma de Aprendizaje en Línea: Integrar un sistema de gestión del aprendizaje para estudiantes y profesores.

  - Investigación y Proyectos: Destacar investigaciones, proyectos y publicaciones de la universidad, y proporcionar acceso a bases de datos y recursos de investigación.

  - Gestión de Campus: Permitir a los estudiantes y profesores acceder a horarios de clases, calendarios académicos y eventos universitarios.

  - Comunicación y Noticias: Publicar noticias y anuncios institucionales, y ofrecer un sistema de boletines y suscripciones.

  - Recursos para Estudiantes y Personal: Proporcionar información sobre servicios de apoyo, bibliotecas, deportes y actividades culturales, además de ofrecer acceso a herramientas de gestión académica y recursos administrativos.

  - Portal de Egresados: Mantener un contacto continuo con los egresados y ofrecer noticias sobre sus logros y oportunidades de networking.

  - Diseño Responsivo: Asegurar que el portal sea accesible y funcional en dispositivos móviles, tablets y computadoras de escritorio.

  - Seguridad y Privacidad: Implementar medidas de seguridad para proteger la información personal y académica de los usuarios.

  - Actualizaciones Continuas: Planificar actualizaciones regulares para agregar nuevas características y mejorar la experiencia del usuario.

Tecnologías Utilizadas

El proyecto se desarrolló utilizando las siguientes tecnologías y metodologías:

    Lenguaje de Programación: Java (Spring Boot)
    Metodología de Desarrollo: Scrum
    Control de Versiones: Git

Cómo Usar la API:

## Endpoints de la API

### Obtener todos los registros

- **URL**: `/api/v1/{nombre_de_la_tabla}/`
- **Método**: `GET`
- **Descripción**: Obtiene una lista de todos los registros de la tabla `{nombre_de_la_tabla}`.
- **Uso**: Envía una solicitud GET a esta URL para recuperar todos los registros disponibles en la tabla.

### Crear un nuevo registro

- **URL**: `/api/v1/{nombre_de_la_tabla}/`
- **Método**: `POST`
- **Descripción**: Crea un nuevo registro en la tabla `{nombre_de_la_tabla}`.
- **Uso**: Envía una solicitud POST a esta URL con los datos del registro en el cuerpo de la solicitud.

### Actualizar un registro existente

- **URL**: `/api/v1/{nombre_de_la_tabla}/`
- **Método**: `PUT`
- **Descripción**: Actualiza un registro existente en la tabla `{nombre_de_la_tabla}`.
- **Uso**: Envía una solicitud PUT a esta URL con los datos actualizados del registro en el cuerpo de la solicitud.

### Eliminar un registro por ID

- **URL**: `/api/v1/{nombre_de_la_tabla}/{id}`
- **Método**: `DELETE`
- **Descripción**: Elimina un registro específico de la tabla `{nombre_de_la_tabla}` por su ID.
- **Uso**: Reemplace `{id}` en la URL con el ID del registro que desea eliminar.

### Obtener un registro por ID

- **URL**: `/api/v1/{nombre_de_la_tabla}/{id}`
- **Método**: `GET`
- **Descripción**: Obtiene un registro específico de la tabla `{nombre_de_la_tabla}` por su ID.
- **Uso**: Reemplace `{id}` en la URL con el ID del registro que desea obtener.

## Tablas Disponibles

Las siguientes son las tablas disponibles en la API:

- `asistencia_eventos`
- `comentarios_noticias`
- `cursos`
- `eventos`
- `facultades`
- `noticias`
- `programas_academicos`
- `usuarios`
- `usuarios_facultades`

Puedes utilizar estos endpoints siguiendo la estructura mencionada para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en la base de datos de acuerdo a la tabla que desees gestionar.

# Cuerpos de Consulta para API

Recuerda que necesitas enviar los cuerpos de consulta en formato JSON en consultas POST o PUT. A continuación se muestra el cuerpo de consulta de cada tabla:


### Usuarios

```json
{
  "usuarioId": 1,
  "nombre": "Nombre del usuario",
  "apellido": "Apellido del usuario",
  "correoElectrónico": "correo@example.com",
  "contraseña": "contraseña123",
  "tipoUsuario": 1
}
```
### Programas Académicos

```json
{
  "programaId": 1,
  "nombre": "Nombre del programa",
  "facultadId": 1,
  "descripción": "Descripción del programa"
}
```
### Noticias

```json
{
  "noticiaId": 1,
  "titulo": "Título de la noticia",
  "contenido": "Contenido de la noticia",
  "fechaPublicación": "2023-10-28"
}
```
### Facultades

```json
{
  "facultadId": 1,
  "nombre": "Nombre de la facultad",
  "descripción": "Descripción de la facultad"
}
```

### Usuarios Facultades

```json
{
  "usuarioFacultadId": 1,
  "usuario": {
    "usuarioId": 1
  },
  "facultad": {
    "facultadId": 2
  }
}
```

### Eventos

```json
{
  "eventoId": 1,
  "nombre": "Nombre del evento",
  "fechaInicio": "2023-11-01",
  "fechaFin": "2023-11-03",
  "descripción": "Descripción del evento"
}
```
### Cursos

```json
{
  "cursoId": 1,
  "nombre": "Nombre del curso",
  "programaId": 1,
  "descripción": "Descripción del curso"
}
```
### Comentarios en Noticias

```json
{
  "comentarioId": 1,
  "noticia": {
    "noticiaId": 1
  },
  "usuario": {
    "usuarioId": 2
  },
  "comentario": "Este es un comentario en la noticia",
  "fechaComentario": "2023-10-30"
}
```
### Asistencia a Eventos

```json
{
  "asistenciaId": 1,
  "eventoId": 1,
  "usuarioId": 2,
  "fechaAsistencia": "2023-10-30"
}
```
Recuerda que el campo "id" debe estar presente en  PUT para identificar el registro, pero en las solicitudes POST, el campo "id" se ignora.