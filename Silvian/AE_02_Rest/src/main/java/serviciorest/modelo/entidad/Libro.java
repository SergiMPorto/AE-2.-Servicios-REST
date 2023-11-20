package serviciorest.modelo.entidad;

import java.util.Objects;

public class Libro {
	
	/*
	 * LOS ATRIBUTOS DE LA CLASE
	 */
	private int idLibro;
	private String titulo;
	private String editorial;
	private String nota;
	
/*
 * CONSTRUCTOR VACIO
 */
	public Libro() {
		super();
	}
	
 /*
  * CONSTRUCTOR CON TODO 
  */
	public Libro(int idLibro, String titulo, String nota, String editorial) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.nota = nota;
		this.editorial = editorial;
	}
	
/*
 * GETTER AND SETTER
 */
	public int getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

 /*
  * SOBREESCRITURA DE toString 
  */
	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", nota=" + nota + ", editorial=" + editorial + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(editorial, idLibro, nota, titulo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(editorial, other.editorial) && idLibro == other.idLibro
				&& Objects.equals(nota, other.nota) && Objects.equals(titulo, other.titulo);
	}


}

	

    
    
    
