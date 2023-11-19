package es.SistemasRest.persistencia;

import org.springframework.stereotype.Component;
import es.SistemasRest.entidad.Libro;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoLibro {

    public static List<Libro> listaLibros;

    static {
        listaLibros = new ArrayList<>();
        
        Libro l1 = new Libro(1, "La Vida Sexual de Catherine Miller", "Catherine Miller", "Galerista de arte que narra sus experiencias sexuales");
        Libro l2 = new Libro(2, "Corazón tan blanco", "Javier Marías", "Primer éxito internacional de Javier Marías");
        Libro l3 = new Libro(3, "American Psycho", "Breat Easton Ellis", "Las hazañas de Patrick Bateman");
        Libro l4 = new Libro(4, "La mancha Humana", "Philip Roth", "Philip Roth se adelantó a la generación Woke");
        Libro l5 = new Libro(5, "2666", "Roberto Bolaño", "Obra monumental de la literatura latinoamericana");

        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);
    }
    public Libro getLibroByID(int id) {
        try {
            for (Libro libro : listaLibros) {
                if (libro.getId() == id) {
                    return libro;
                }
            }
        } catch (IndexOutOfBoundsException iobe) {
            System.out.println("getLibro -> Libro fuera de rango");
            return null;
        }

        return null;
    }

    public List<Libro> list() {
        return listaLibros;
    }
// Método para añadir un libro y viendo que no se duplique ni por id ni por titulo
    public boolean añadirLibro(Libro l) {
        // Verificar duplicados por ID
        for (Libro libro : listaLibros) {
            if (libro.getId() == l.getId()) {
                System.out.println("Ya existe un libro con el mismo ID: " + l.getId());
                return false;
            }
        }

        // Verificar duplicados por título
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equals(l.getTitulo())) {
                System.out.println("Ya existe un libro con el mismo título: " + l.getTitulo());
                return false;
            }
        }

        // Si no hay duplicados, añadir el libro y retornar verdadero
        listaLibros.add(l);
        System.out.println("alta -> Codigo de respuesta 201 CREATED");
        System.out.println("Libro añadido correctamente: " + l);
        return true;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            if (libro.getId() == id) {
                listaLibros.remove(i);
                return true;
            }
        }
        return false;
    }

    public Libro modificar(Libro l) {
        try {
            Libro lAux = listaLibros.get(l.getId() - 1);
            lAux.setTitulo(l.getTitulo());
            lAux.setAutor(l.getAutor());
            lAux.setNota(l.getNota());

            return lAux;
        } catch (IndexOutOfBoundsException iobe) {
            System.out.println("actualizacion -> Libro no existe");
            return null;
        }
    }
}
