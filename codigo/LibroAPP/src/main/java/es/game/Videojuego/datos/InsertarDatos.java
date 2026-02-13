package es.game.Videojuego.datos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import net.datafaker.Faker;
import es.game.Videojuego.entidad.Videojuego;
import es.game.Videojuego.servicio.VideojuegoServicio;
import jakarta.annotation.PostConstruct;

@Component
public class InsertarDatos {
	
	private final int TOTAL_VIDEOJUEGOS = 20;

	@Autowired
	private VideojuegoServicio servicio;
	
	
	
	@PostConstruct
	void init() {
		
		for(int i=0; i<TOTAL_VIDEOJUEGOS; i++) {
			Videojuego v = new Videojuego();
		v.setDesarrollador(fake().dog().name());
		v.setDescripcion(fake().chuckNorris().fact());
		v.setPrecio(fake().number().randomDigit());
		v.setTitulo(fake().cat().breed());
		
		servicio.crear(v);
		}
	}
	
	@Bean
	public Faker fake() {
		return new Faker();
	}
}
