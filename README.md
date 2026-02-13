CRUD MVC con Thymeleaf — RA3
1) Datos del alumno/a
  Entidad elegida Videojuego
2) Repositorio (fork) y gestión de versiones
  Repositorio base: https://github.com/josemiguelzerpagarcia/tareasSpringBoot
  Enlace a MI fork: (https://github.com/josemiguelzerpagarcia/tareasSpringBoot/new/LibrosApp)
  Nº de commits realizados: (mínimo 5)
  5
4) Arquitectura
Explica brevemente cómo has organizado:

  Controller: es.game.Videojuego.controlador --> VideojuegoControlador
  Service: es.game.Videojuego.servicio --> VideojuegoServicio/VideojuegoServicioImpl
  Repository: es.game.Videojuego.repositorio --> VideojuegoRepositorio
  Entity: es.game.Videojuego.entidad --> Videojuego
4) Base de datos elegida (marca una)
   *H2*
 
5) Configuración de la base de datos
5.1 Dependencias añadidas
  <dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
5.2 application.properties / application.yml
  spring.application.name=Videojuego
  server.port=9092

  spring.h2.console.enabled=true
  spring.h2.console.path=/h2

  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.username=sa
  spring.datasource.password=
  spring.datasource.driver-class-name=org.h2.Driver
  spring.jpa.hibernate.ddl-auto=update

6) Cómo ejecutar el proyecto
Comando de arranque:
  ./mvnw spring-boot:run
URL de acceso:
  http://localhost:9092
