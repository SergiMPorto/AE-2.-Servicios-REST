package es.cliente.entidad;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String nota;

    public Libro() {
        // Constructor sin argumentos requerido para la deserialización
    }

    public Libro(int id, String titulo, String autor, String nota) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", nota=" + nota + "]";
    }
}
