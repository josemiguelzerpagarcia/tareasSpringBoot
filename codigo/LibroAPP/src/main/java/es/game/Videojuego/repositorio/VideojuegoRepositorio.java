package es.game.Videojuego.repositorio;

import es.game.Videojuego.entidad.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepositorio extends JpaRepository<Videojuego, Integer> {


    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);
    List<Videojuego> findByTituloIgnoreCase(String titulo);

    List<Videojuego> findByDesarrolladorContainingIgnoreCase(String desarrollador);

    List<Videojuego> findAllByOrderByTituloAsc();

}