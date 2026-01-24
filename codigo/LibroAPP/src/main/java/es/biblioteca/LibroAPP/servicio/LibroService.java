package es.biblioteca.LibroAPP.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.biblioteca.LibroAPP.modelo.entidad.Libro;

public interface LibroService {
	  // CRUD
    Libro crear(Libro libro);
    Libro actualizar(Integer id, Libro libro);
    Optional<Libro> buscarPorId(Integer id);
    List<Libro> listar();
    void eliminar(Integer id);

    // Operaciones por ISBN (muy útiles para tu caso)
    Optional<Libro> buscarPorIsbn(String isbn);
    boolean existeIsbn(String isbn);

    // Búsqueda simple para el listado (q)
    List<Libro> buscar(String q);
}
