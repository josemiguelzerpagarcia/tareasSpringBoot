package es.game.Videojuego.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import es.game.Videojuego.entidad.Videojuego;

public interface VideojuegoServicio {
	Videojuego crear(Videojuego libro);
	Videojuego actualizar(Integer id, Videojuego libro);
    Optional<Videojuego> buscarPorId(Integer id);
    List<Videojuego> listar();
    void eliminar(Integer id);

    List<Videojuego> buscar(String q);
}
