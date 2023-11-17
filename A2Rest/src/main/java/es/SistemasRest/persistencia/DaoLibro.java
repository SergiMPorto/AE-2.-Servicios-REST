package es.SistemasRest.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.SistemasRest.entidad.Libro;

@Component
public class DaoLibro {

    public static List<Libro> listaLibros;

    static {
        listaLibros = new ArrayList<Libro>();

        Libro l1 = new Libro(01, "La Vida Sexual de Catherine MIller","Catherine Miller", "Galerista de arte que narra sus experiencias sexuales");
        Libro l2 = new Libro(02,"Corazón tan blanco", "Javier Marías", "Primer éxito internacional de Javier Marías");
        Libro l3 = new Libro(03, "American Psycho", "Breat Easton Ellis", "Las hazañas de Patrick Bateman ");
        Libro l4 = new Libro(04, "La mancha Humana", "Philip Roth", "Philip Roth se adelantó a la generacion Woke");
        Libro l5 = new Libro(05, "2666", "Roberto Bolaño", "Obra monumental de la literatura latinoamericana");
        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);
    }

    public Libro getLibroByID(int id) {
    	{
            try {
                for(Libro libro : listaLibros) {
                    if(libro.getId()== id) {
                        return libro;
                    }
                }
            }catch (IndexOutOfBoundsException iobe) {
                System.out.println("getLibro -> Libro fuera de rango");
                return null;
            }

            return null;

        }

    }

  

    public List<Libro> list() {
        return listaLibros;
    }

    public void añadirLibro(Libro l) {
        boolean existeLibros = false;

        for (Libro existeLibro : listaLibros) {
            if (existeLibro.getId() == l.getId() ) {
                existeLibros = true;
                break;
            }
        }

        if (!existeLibros) {
            listaLibros.add(l);
        }
    }


    public boolean eliminar(int id) {
    	for (Libro libro : listaLibros) {
            if (libro.getId() == id) {
                listaLibros.remove(libro);
                return true; 
            }
        }
        return false; 
    }
    public Libro modificar(Libro l) {
		try {
			Libro lAux = listaLibros.get(l.getId());
			lAux.setTitulo(l.getTitulo());
			lAux.setAutor(l.getAutor());
			lAux.setNota(l.getNota());

			return lAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("actualizacion  -> Libro no existe");
			return null;
		}
	}
}
