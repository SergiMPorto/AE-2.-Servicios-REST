package es.actividad.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.actividad.modelo.entidad.Libro;
import es.actividad.modelo.negocio.DaoLibro;

@RestController
public class ControlLibro {
		
	@Autowired
	private DaoLibro dl;
	
	@GetMapping(path="libros/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> getLibro(@PathVariable("id") int idLibro) {
		System.out.println("Buscando libro con id: " + idLibro);
		Libro l = dl.getLibro(idLibro);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> borrarLibro(@PathVariable("id") int idLibro) {
		System.out.println("ID a borrar: " + idLibro);
		Libro l = dl.deleteLibro(idLibro);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(
			@PathVariable("id") int id, 
			@RequestBody Libro l) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + l);
		l.setIdLibro(id);
		Libro libro = dl.updateLibro(l);
		if(libro != null) {
			return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
		System.out.println("altaPersona: objeto persona: " + l);
		boolean bandera = dl.addLibro(l);
		
		if(bandera == true) {
			return new ResponseEntity<Libro>(l,HttpStatus.CREATED);//201 CREATED
		} else {
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);//400
		}
	}
	
	@GetMapping(path="libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listarLibros(
			@RequestParam(name="titulo",required=false) String titulo) {
		List<Libro> listaPersonas = null;
		//Si no me viene el titulo, devolvemos toda la lista
		if(titulo == null) {
			System.out.println("Listando las personas");
			listaPersonas = dl.getAllLibros();			
		}else {
			System.out.println("Listando de libros por titulo: " + titulo);
			listaPersonas = dl.LibrosPorTitulo(titulo);
		}
		System.out.println(listaPersonas);
		return new ResponseEntity<List<Libro>>(listaPersonas,HttpStatus.OK);
	}
}
