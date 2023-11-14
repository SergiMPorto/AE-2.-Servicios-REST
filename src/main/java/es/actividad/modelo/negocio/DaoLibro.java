package es.actividad.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.actividad.modelo.entidad.Libro;




@Component
public class DaoLibro {
	
	public List<Libro> listaLibros;
	
	public DaoLibro() {
		System.out.println(" DaoLibro ->Creando la lista de personas");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(12,"Mareas Vivas", "Libro muy interesante", "Follas novas");
		Libro l2 = new Libro(13,"The witcher", "Inicios del brujo", "Mundo lector");
		Libro l3 = new Libro(14,"La sombra de la espada", "Libro bélico", "Eu que sei");
		Libro l4 = new Libro(15,"Arthas", "Aun no me lo he acabado", "Litch King");
		Libro l5 = new Libro(16,"Dragones y mazmorras", "Un ladrillo de la hostia", "Dungeon master");
		
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
		listaLibros.add(l1);
		
	}
	
	/**
	 * Metodo que introduce un libro en la lista
	 * @param l el libro que queremos introducir
	 */
	public void addLibro(Libro l) {
		listaLibros.add(l);
	}
	
	/**
	 * Borramos el libro del id que le pasemos al método
	 * @return devolvemos el libro que hemos quitado del array, 
	 * o null en caso de que no exista o esté fuera del rango.
	 */
	public Libro deleteLibro(int idLibro) {
	    try {
	        for (Libro libro : listaLibros) {
	            if (libro.getIdLibro() == idLibro) {
	                listaLibros.remove(libro); 
	                return libro;
	            }
	        }
	    } catch (IndexOutOfBoundsException e) {
	        System.out.println("deleteLibro -> Libro fuera de rango");
	        return null;
	    }
	    return null; 
	}
	
	/**
	 * Metodo que modifica un libro según su id
	 * @param l contiene todos los datos que queremos modificar, pero 
	 * p.getIdLibro() contiene el identificador que nos muestra el libro que
	 * queremos modificar.
	 * @return el libo modificado en caso de que exista, null en caso
	 * contrario
	 */
	public Libro updateLibro(Libro l) {
		try {
			Libro libro = listaLibros.get(l.getIdLibro());
			libro.setTitulo(l.getTitulo());
			libro.setNota(l.getNota());
			libro.setEditorial(l.getEditorial());

			return libro;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("updateLibro -> Libro fuera de rango");
			return null;
		}
	}
	
	/**
	 * Método que nos devuelve el libro del id que hemos introducido
	 * @param idLibro
	 * @return el libro del id introducido o null si no existe
	 */
	public Libro getLibro(int idLibro) {
		try {
			for(Libro libro : listaLibros) {
				if(libro.getIdLibro()== idLibro) {
					return libro;
				}
			}
		}catch (IndexOutOfBoundsException iobe) {
			System.out.println("getLibro -> Libro fuera de rango");
			return null;
		}
		
		return null;
		
	}
	/**
	 * Este método nos devuelve todos los libros de nuestra lista
	 * @return los libros de nuestra lista
	 */
	public List<Libro> getAllLibros() {
		return listaLibros;
	}
	
	/**
	 * Metodo que devuelve todos los libros por titulo. Guardamos los libros en una lista
	 * por el hipotético caso de que se de la coincidencia de dos libros con el mismo
	 * título.
	 * @param titulo es la variable que recoge el titulo del libro por el cual vamos
	 * a hacer la busqueda.
	 * @return los libros que tenga el titulo que hemos introducido, si no hay libros
	 * con ese titulo devolverá una lista vacía.
	 */
	public List<Libro> LibrosPorTitulo(String titulo){
		ArrayList<Libro> listaLibrosAux = new ArrayList<Libro>();
		for(Libro l : listaLibros) {
			if(l.getTitulo().equalsIgnoreCase(titulo)) {//
				listaLibrosAux.add(l);
				
			}
		}
		return listaLibrosAux;
	}
}
