package es.SistemasRest.persistencia;

import java.util.ArrayList;
import java.util.List;

import es.SistemasRest.entidad.libro;

public class DaolLibro {

	public static List<libro> listaLibros;
	
	static {
		listaLibros = new ArrayList<libro>();
		
		libro l1 = new libro(01, "La Vida Sexual de Catherine MIller","Catherine Miller", "Galerista de arte que narra sus experiencias sexuales");
	    libro l2 = new libro(01,"Corazón tan blanco", "Javier Marías", "Primer éxito internacional de Javiar Marías");
		libro l3 = new libro(03, "American Psycho", "Breat Easton Ellis", "Las hazañas de Patrick Bateman ");
		libro l4 = new libro(04, "La mancha Humana", "Philip Roth", "Philip Roth se adelantó a la generacion Woke");
		libro l5 = new libro(05, "2666", "Roberto Bolaño", "Obra monumental de la literatura latinoamericana");
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
	}
}