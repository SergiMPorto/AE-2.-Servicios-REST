package serviciorest.modelo.libro.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Libro;

@Component
public class DaoLibro {
	
	public List<Libro> listaLibros;
	
	public DaoLibro() {
		
		System.out.println(" DAOLIBRO ->CREANDO LA LISTA DE LIBROS");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(6,"NADA", "PLANETA DE LIBROS", 
				               "NACIMOS A LA VIDA REAL POR UN PRODIGIO DE LA CREACION ARTISTICA");
		
		Libro l2 = new Libro(7,"CINO HORAS CON MARIO", "PLANETA DE LIBROS", 
				              "LA POBRE VIDA LLENA DE ERRORES Y TORPEZA, DE PEQUEÑOS GOCES E INCOMPRESIONES");
		
		Libro l3 = new Libro(8,"EL NIÑO CON EL PIJAMA DE RAYAS", "SALAMANDRA", 
				   "SI DECIDES EMBARCARTE EN LA AVENTURA, DEBES CONOCER A LOS ACMPAÑIANTES QUE VAS A TENER");
		
		Libro l4 = new Libro(9,"INSOLACIÓN", "PENGUIN", "LO QUE  PASA ES QUE ME GUSTA");
		
		Libro l5 = new Libro(10,"UNA EDUCACIÓN", "BBC NEWS", "UNA NOVELA UNICA Y INSPIRADORA");
		
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
		
	}
	/*
	 * REQUERIMIENTO NUMERO 1:
	 */
	
	/*
	 * EL METODO QUE NOS AYUDA A AÑADIR UN LIBRO
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
		
	