package es.actividad.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import es.actividad.cliente.entidad.Libro;
import es.actividad.cliente.servicio.ServicioProxyLibro;



@SpringBootApplication
public class Actividad2PspClienteApplication implements CommandLineRunner{
	
	@Autowired
	@Lazy
	private ServicioProxyLibro spl;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Actividad2PspClienteApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("****** Arrancando el cliente REST ******");
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("Elige una opción");
            System.out.println("1-Buscar Libro por ID");
            System.out.println("2-Añadir libro");
            System.out.println("3-Lista de libros");
            System.out.println("4-Modificar libro");
            System.out.println("5-Borrar libro");
            System.out.println("6-Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese el id del libro a buscar");
                    int id = scanner.nextInt();
                    Libro libro = spl.obtener(id);

                    if (libro != null) {
                        System.out.println("El título del libro buscado es: " + libro.getTitulo());
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                }
                 case 2: {
                	System.out.println("Escriba el id del libro que quiere dar de alta");
                	int idLibro = scanner.nextInt();
                	scanner.nextLine();
                    System.out.println("Escriba el libro a ingresar");
                    String titulo = scanner.nextLine();
                    System.out.println("Escriba la editorial del libro");
                    String editorial = scanner.nextLine();
                    System.out.println("Escriba la nota del libro");
                    String nota = scanner.nextLine();

                    Libro nuevoLibro = new Libro(idLibro, titulo, nota, editorial);
                    spl.alta(nuevoLibro);
                    System.out.println("Libro añadido correctamente: " + nuevoLibro);
                    break;
                }
                case 3: {
                	 System.out.println("Lista de libros: ");
                	    List<Libro> listaLibrosAux = spl.listar(null);

                	    if (listaLibrosAux != null) {
                	        listaLibrosAux.forEach((v) -> System.out.println(v));
                	    } else {
                	        System.out.println("Error al obtener la lista de libros");
                	    }
                	    break;
                }
                case 4: {
                    System.out.println("Ingrese el ID del libro a modificar");
                    int id = scanner.nextInt();
                    

                    Libro libroModificado = spl.obtener(id);
                    if (libroModificado != null) {
                        System.out.println("Ingrese el nuevo título del libro");
                        libroModificado.setTitulo(scanner.nextLine());
                        System.out.println("Ingrese la editorial del libro");
                        libroModificado.setEditorial(scanner.nextLine());
                        System.out.println("Ingrese la nueva nota del libro");
                        libroModificado.setNota(scanner.nextLine());

                        spl.modificar(libroModificado);
                        System.out.println("Libro modificado correctamente: " + libroModificado);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Ingrese el ID del libro a borrar");
                    int id = scanner.nextInt();

                    if (spl.borrar(id)) {
                        System.out.println("Libro eliminado correctamente");
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Opción no válida");
            }
        }
		
		
	}

}
