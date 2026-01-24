package es.biblioteca.LibroAPP.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.biblioteca.LibroAPP.modelo.entidad.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

    // Para comprobar duplicados y buscar por ISBN (muy típico en el CRUD)
    boolean existsByIsbn(String isbn);
    Optional<Libro> findByIsbn(String isbn);
    // Para búsquedas sencillas en Thymeleaf (listado con filtro)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
