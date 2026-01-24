# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Entidad elegida (ej. Producto, Libro...):
Libro
## 2) Repositorio (fork) y gestión de versiones
Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
Enlace a MI fork: [PON AQUÍ EL ENLACE CUANDO LO CREES] //
Nº de commits realizados: (mínimo 5)

Ejemplo de commits recomendados:
- init proyecto + dependencias
- crear entidad Libro + repository
- service LibroService + métodos CRUD
- controller MVC + rutas
- vistas thymeleaf list/form + bootstrap
- validaciones + mensajes de error (opcional)


## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: LibroController
- Service: LibroService
- Repository: LibroRepository
- Entity:  Libro

## 4) Base de datos elegida (marca una)
- [X] H2
- [ ] MySQL
- [ ] PostgreSQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
(Indica la dependencia del driver que has usado)

### 5.2 application.properties / application.yml
(Pega aquí tu configuración SIN contraseñas reales si es necesario)

### 5.3 Pasos para crear la BD (si aplica)
- MySQL: CREATE DATABASE ...
- PostgreSQL: createdb ...

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica)
2. Comando de arranque:
   - ./mvnw spring-boot:run   (o equivalente)
3. URL de acceso:
   - http://localhost:8080/...

## 7) Pantallas / Rutas MVC
Entidad: Libro (ruta base /libros)
GET /libros → listar libros

GET /libros/nuevo → formulario alta
POST /libros → crear libro

GET /libros/{isbn}/editar → formulario editar

POST /libros/{isbn} → actualizar libro
POST /libros/{isbn}/borrar → eliminar libro


## 8) Mejoras extra (opcional)
Validaciones
@NotBlank en título
Validación de formato ISBN (regex simple o validador)
Estilos Bootstrap
Tablas, botones, alertas para mensajes
Búsqueda
Campo búsqueda por título/autor en /libros
Pruebas
Test unitarios de service con JUnit
Paginación
Pageable en listado de libros
