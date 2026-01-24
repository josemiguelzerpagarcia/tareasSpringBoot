package es.biblioteca.LibroAPP.modelo.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.biblioteca.LibroAPP.modelo.entidad.Libro;
import es.biblioteca.LibroAPP.servicio.LibroService;
@Component
public class DatosFakeDesarrollo  implements CommandLineRunner{

	@Autowired
	private LibroService servicio;
	
	
	@Override
	public void run(String... args) throws Exception {
		Libro libro = new Libro();
		libro.setTitulo("Don Quijote de la Mancha");
		libro.setIsbn("9788432225005");
		servicio.crear(libro);
		
	}

}
