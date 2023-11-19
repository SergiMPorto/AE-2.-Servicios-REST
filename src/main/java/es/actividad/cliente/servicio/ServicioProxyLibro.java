package es.actividad.cliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.actividad.cliente.entidad.Libro;


@Service
public class ServicioProxyLibro {
	
	public static final String URL = "http://localhost:8080/libros";
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Método que obtiene un libro del servicio REST a partir de un id
	 * En caso de que el id no exita arrojaria una expcepción que se captura
	 * para sacar el codigo de respuesta
	 * 
	 * @param id que queremos obtener
	 * @return retorna el libro que estamos buscando, null en caso de que el libro
	 * no se encuentre en el servidor (devuelva 404) o haya habido algún
	 * otro error.
	 */
	public Libro obtener(int id){
		try {
			
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + "/" + id, Libro.class);
			HttpStatus hs= (HttpStatus) re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				
				return re.getBody();
			}else {
				System.out.println("obtener -> Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("obtener -> La persona NO se ha encontrado, id: " + id);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	/**
	 * Método que da de alta un libro en el servicio REST
	 * 
	 * @param l el libro que vamos a dar de alta
	 * @return el libro con el id actualizado que se ha dado de alta en el
	 * servicio REST. Null en caso de que no se haya podido dar de alta
	 */
	public Libro alta(Libro l){
		try {
			
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL , l, Libro.class);
			System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("alta -> La persona NO se ha dado de alta, id: " + l);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
		
		
	 }
	
	/**
	 * 
	 * Modifica un libro en el servicio REST
	 * 
	 * @param l el libro que queremos modificar, se hara a partir del 
	 * id por lo que tiene que estar relleno.
	 * @return true en caso de que se haya podido modificar el libro. 
	 * false en caso contrario.
	 */
	public boolean modificar(Libro l) {
	    try {
	    	System.out.println("URL para modificar: " + URL + "/" + l.getIdLibro());
	    	System.out.println("Datos del libro a modificar: " + l);
	    	restTemplate.put(URL + "/" + l.getIdLibro(), l, Libro.class);
	        return true;
	    } catch (HttpClientErrorException e) {
	        System.out.println("modificar -> El libro NO se ha modificado, id: " + l.getIdLibro());
	        System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
	        return false;
	    }
	}
	
	/**
	 * 
	 * Borra un libro en el servicio REST
	 * 
	 * @param id el id del libro que queremos borrar.
	 * @return true en caso de que se haya podido borrar el libro. 
	 * false en caso contrario.
	 */
	public boolean borrar(int id){
		try {
			
			restTemplate.delete(URL + "/" + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("borrar -> La persona NO se ha borrar, id: " + id);
		    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	
	/**
	 * Metodo que devuelve todos los libros o todos los libros filtradas
	 * por titulo del web service
	 * 
	 * @param titulo en caso de ser distinto de null, devolvera el listado
	 * filtrado por el titulo que le hayamos pasado en este parametro. En caso
	 * de que sea null, el listado de los libros sera completo
	 * @return el listado de los libros según el parametro de entrada o 
	 * null en caso de algun error con el servicio REST
	 */
	public List<Libro> listar(String titulo){
		String queryParams = "";		
		if(titulo != null) {
			queryParams += "?titulo=" + titulo;
		}
		
		try {
			
			ResponseEntity<Libro[]> response =
					  restTemplate.getForEntity(URL  + queryParams,Libro[].class);
			Libro[] arrayPersonas = response.getBody();
			return Arrays.asList(arrayPersonas);//convertimos el array en un ArrayList
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de libros");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}

}
