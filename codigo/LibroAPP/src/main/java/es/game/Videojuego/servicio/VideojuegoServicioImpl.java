package es.game.Videojuego.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import es.game.Videojuego.entidad.Videojuego;
import es.game.Videojuego.repositorio.VideojuegoRepositorio;
import jakarta.transaction.Transactional;

	@Service
	@Transactional 
	public class VideojuegoServicioImpl implements VideojuegoServicio {

	    private final VideojuegoRepositorio repositorio;

	    public VideojuegoServicioImpl(VideojuegoRepositorio repositorio) {
	        this.repositorio = repositorio;
	    }

	    @Override
	    public Videojuego crear (Videojuego videojuego) {
	        normalizar(videojuego);
	        if (videojuego.getId() != null) {
	            throw new IllegalArgumentException("Id ya existe");
	        }

	        return repositorio.save(videojuego);
	    }

	    @Override
	    public Videojuego actualizar(Integer id, Videojuego videojuego) {
	        normalizar(videojuego);

	        Videojuego existente = repositorio.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Videojuego no encontrado con id=" + id));

	        existente.setTitulo(videojuego.getTitulo());

	        return repositorio.save(existente);
	    }

	    @Override
	    @Transactional
	    public Optional<Videojuego> buscarPorId(Integer id) {
	        return repositorio.findById(id);
	    }

	    @Override
	    @Transactional
	    public List<Videojuego> listar() {
	        return repositorio.findAll();
	    }

	    @Override
	    public void eliminar(Integer id) {
	        if (!repositorio.existsById(id)) {
	            throw new IllegalArgumentException("Videojuego no encontrado con id=" + id);
	        }
	        repositorio.deleteById(id);
	    }


	    @Override
	    @Transactional
	    public List<Videojuego> buscar(String q) {
	        String texto = (q == null) ? "" : q.trim();
	        if (texto.isEmpty()) {
	            return repositorio.findAll();
	        }

	        return repositorio.findByTituloContainingIgnoreCase(texto);
	    }

	    // -------------------------
	    // Helpers
	    // -------------------------

	    private void normalizar(Videojuego videojuego) {
	        if (videojuego == null) return;

	        if (videojuego.getTitulo() != null) {
	        	videojuego.setTitulo(videojuego.getTitulo().trim());
	        }
	    }

	}


