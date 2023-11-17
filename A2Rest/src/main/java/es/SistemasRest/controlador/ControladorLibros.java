package es.SistemasRest.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.SistemasRest.entidad.Libro;
import es.SistemasRest.persistencia.DaoLibro;

@RestController
  
public class ControladorLibros {

    @Autowired
    private DaoLibro daoLibro;

    @GetMapping(path = "libros/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") int id) {
        System.out.println("Buscando libro con id: " + id);
        Libro l = daoLibro.getLibroByID(id);

        if (l != null) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
  @PostMapping(path="libros",consumes =  MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Libro>altaLibro(@RequestBody Libro l){
	  System.out.println("Damo del alta el libro: "+ l);
	  daoLibro.añadirLibro(l);
	  return new ResponseEntity<Libro>(l, HttpStatus.CREATED);
  }
  
  
  @GetMapping(path = "/libros", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Libro>> listaLibros() {
      return new ResponseEntity<>(daoLibro.list(), HttpStatus.OK);


  }
  
  @PutMapping(path="libros/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<Libro> modificarLibro(@PathVariable("id") int id, @RequestBody Libro l){
	  System.out.println("ID a modificar: " + id);
	  System.out.println("Datos a modificar: "+ l);
	  Libro lm = daoLibro.modificar(l);
	  if(lm !=null) {
		  return new ResponseEntity<Libro>(HttpStatus.OK); 
	  }else {
		  return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
	  }
  }
  
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Libro> eliminarLibro(@PathVariable("id") int id) {
      return daoLibro.eliminar(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
    
}
