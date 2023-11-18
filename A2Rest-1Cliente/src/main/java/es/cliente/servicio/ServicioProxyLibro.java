package es.cliente.servicio;
import es.cliente.entidad.*;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.cliente.entidad.Libro;


@Service
public class ServicioProxyLibro {
	
	public static final String URL = "http://localhost:8080/libros";
	
	@Autowired
	private RestTemplate restTemplate;

public Libro agregarLibro(Libro l) {
	try {
		
		ResponseEntity<Libro> re = restTemplate.postForEntity(URL , l, Libro.class);
		System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
		return re.getBody();
	} catch (HttpClientErrorException e) {
		System.out.println("alta -> Al libro NO se ha dado de alta, id: " + l);
	    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
	    return null;
	}
}


   public boolean  eliminarLibro(int id) {
	   try {
		   restTemplate.delete(URL+id);;
		   return true;
	   }catch (HttpClientErrorException e) {
			System.out.println("modificar->  El libro no se ha borrado, id: " + id);
			System.out.println("modificar-> Codiggo de respuesta: "+e.getStatusCode());
			return false;
			}
   }
     
public boolean modificarLibro(Libro l) {
    try {
        restTemplate.put(URL + "/" + l.getId(), l, Libro.class);
        return true;
    } catch (HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println("modificar -> El libro con ID " + l.getId() + " no se encontró.");
        } else {
            System.out.println("modificar -> Error al modificar el libro con ID " + l.getId() + ": " + e.getStatusCode());
        }
        return false;
    }
}

   public Libro obtenerLibro(int id) {
    
try {

	   ResponseEntity<Libro> responseEntity = restTemplate.getForEntity(URL + "/" + id, Libro.class);
	   HttpStatusCode hs=responseEntity.getStatusCode();
	    if(hs==HttpStatus.OK) {
	    	return responseEntity.getBody();
	    }else { System.out.println("obtener -> Respuesta no contemplada");
return null;
	    }
	    
}catch (HttpClientErrorException e) {
	System.out.println("obtener -> El libro no se ha encontrado , id: " + id);
    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
    return null;
}
}

	 


   public List<Libro> listarLibros() {
       try {
           ResponseEntity<Libro[]> responseEntity = restTemplate.getForEntity(URL, Libro[].class);
           if (responseEntity.getStatusCode() == HttpStatus.OK) {
               return List.of(responseEntity.getBody());
           } else {
               System.out.println("listar -> Fallo al obtener la lista de libros. Código de respuesta: " + responseEntity.getStatusCode());
               return List.of();
           }
       } catch (HttpClientErrorException e) {
           System.out.println("listar -> Fallo al obtener la lista de libros. Detalles: " + e.getMessage());
           return List.of();
       }
   }
}
