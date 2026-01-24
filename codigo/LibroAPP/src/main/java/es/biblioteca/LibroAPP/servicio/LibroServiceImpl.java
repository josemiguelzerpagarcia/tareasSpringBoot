package es.biblioteca.LibroAPP.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.biblioteca.LibroAPP.modelo.entidad.Libro;
import es.biblioteca.LibroAPP.repositorio.LibroRepository;
import jakarta.transaction.Transactional;

	@Service
	@Transactional //sirve para que Spring ejecute un método como una transacción de base de datos.
	public class LibroServiceImpl implements LibroService {

	    private final LibroRepository repositorio;

	    public LibroServiceImpl(LibroRepository repositorio) {
	        this.repositorio = repositorio;
	    }

	    @Override
	    public Libro crear(Libro libro) {
	        normalizar(libro);

	        // Evitar duplicado por ISBN
	        if (repositorio.existsByIsbn(libro.getIsbn())) {
	            throw new IllegalArgumentException("ISBN ya existe");
	        }

	        return repositorio.save(libro);
	    }

	    @Override
	    public Libro actualizar(Integer id, Libro libro) {
	        normalizar(libro);

	        Libro existente = repositorio.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con id=" + id));

	        // Si cambia el ISBN, comprobar duplicado
	        String nuevoIsbn = libro.getIsbn();
	        String isbnActual = existente.getIsbn();

	        if (nuevoIsbn != null && !nuevoIsbn.equals(isbnActual) && repositorio.existsByIsbn(nuevoIsbn)) {
	            throw new IllegalArgumentException("ISBN ya existe");
	        }

	        existente.setTitulo(libro.getTitulo());
	        existente.setIsbn(nuevoIsbn);

	        return repositorio.save(existente);
	    }

	    @Override
	    @Transactional
	    public Optional<Libro> buscarPorId(Integer id) {
	        return repositorio.findById(id);
	    }

	    @Override
	    @Transactional
	    public List<Libro> listar() {
	        return repositorio.findAll();
	    }

	    @Override
	    public void eliminar(Integer id) {
	        if (!repositorio.existsById(id)) {
	            throw new IllegalArgumentException("Libro no encontrado con id=" + id);
	        }
	        repositorio.deleteById(id);
	    }

	    @Override
	    @Transactional
	    public Optional<Libro> buscarPorIsbn(String isbn) {
	        String norm = normalizarIsbn(isbn);
	        if (norm == null) return Optional.empty();
	        return repositorio.findByIsbn(norm);
	    }

	    @Override
	    @Transactional
	    public boolean existeIsbn(String isbn) {
	        String norm = normalizarIsbn(isbn);
	        return norm != null && repositorio.existsByIsbn(norm);
	    }

	    @Override
	    @Transactional
	    public List<Libro> buscar(String q) {
	        String texto = (q == null) ? "" : q.trim();
	        if (texto.isEmpty()) {
	            return repositorio.findAll();
	        }

	        // Búsqueda simple por título (suficiente para MVP)
	        return repositorio.findByTituloContainingIgnoreCase(texto);
	    }

	    // -------------------------
	    // Helpers
	    // -------------------------

	    private void normalizar(Libro libro) {
	        if (libro == null) return;

	        if (libro.getTitulo() != null) {
	            libro.setTitulo(libro.getTitulo().trim());
	        }
	        libro.setIsbn(normalizarIsbn(libro.getIsbn()));
	    }

	    private String normalizarIsbn(String isbn) {
	        if (isbn == null) return null;
	        String x = isbn.replace("-", "").replace(" ", "").trim();
	        return x.isEmpty() ? null : x;
	    }
	}


