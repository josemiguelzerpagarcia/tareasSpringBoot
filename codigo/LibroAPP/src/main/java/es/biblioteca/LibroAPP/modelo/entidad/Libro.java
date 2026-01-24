package es.biblioteca.LibroAPP.modelo.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Nombre no puede estar en blanco")
	@Column(nullable = false)
	private String titulo;
	@NotBlank(message = "isbn no puede estar en blanco")
	@Pattern(regexp="^(?:97[89][ -]?)?(?:\\d[ -]?){9,12}\\d$", message="ISBN no valido")
	private String isbn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
