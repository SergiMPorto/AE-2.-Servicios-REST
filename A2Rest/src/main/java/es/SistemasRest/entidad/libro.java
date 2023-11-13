package es.SistemasRest.entidad;

public class libro {
	
	private int id;
	private String titulo;
	private String editorial;
	private String nota;
	
	
	public libro(int id, String titulo, String editorial, String nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
	

}
