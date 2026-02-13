package es.game.Videojuego.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import es.game.Videojuego.entidad.Videojuego;
import es.game.Videojuego.servicio.VideojuegoServicio;
import jakarta.validation.Valid;


@Controller
public class VideojuegoControlador {

	private final VideojuegoServicio videojuegoServicio;

    public VideojuegoControlador(VideojuegoServicio videojuegoServicio) {
        this.videojuegoServicio = videojuegoServicio;
    }
    
    
	 @GetMapping("/catalogo")
	    public String listado(Model model) {
		 List<Videojuego> videojuegos = videojuegoServicio.listar();
	        model.addAttribute("videojuegos", videojuegos);
	        return "catalogo";
	    }
	 
	 @GetMapping("/crear")
	    public String mostrarFormCrear(Model model) {
	        model.addAttribute("videojuego", new Videojuego());
	        return "crear";
	    }
	 
	 @GetMapping("/videojuego/editar/{id}")
	    public String obtenerProducto(@PathVariable Integer id, Model model) {
	        Videojuego videojuego = videojuegoServicio.buscarPorId(id);
	        model.addAttribute("videojuego", videojuego);
	        return "crear";
	    }
	 
	 @PostMapping("/crear")
	    public String guardarVideojuego(@Valid Videojuego videojuego,
	                                  BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
	            return "crear";
	        }

	        if (videojuego.getId() == null) {
	        	videojuegoServicio.crear(videojuego);
	        } else {
	        	videojuegoServicio.actualizar(videojuego);
	        }
	        return "redirect:/catalogo";
	    }
	 
	 
	 @GetMapping("/videojuegos/detalles/{id}")
	    public String obtenerVideojugo(@PathVariable Integer id, Model model) {
	        Videojuego videojuego = videojuegoServicio.buscarPorId(id);
	        model.addAttribute("videojuego", videojuego);
	        return "detalles";
	    }
	 
	 @GetMapping("/videojuegos/{id}/eliminar")
	    public String eliminarVideojuego(@PathVariable int id) {
	        videojuegoServicio.eliminar(id);
	        return "redirect:/catalogo";
	    }
	 
}
