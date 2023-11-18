package es.SistemasRest.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import es.SistemasRest.entidad.Libro;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class ServicioProxyCliente {

    private static final String URL = "http://localhost:8080/libros";
    private static final Logger logger = LoggerFactory.getLogger(ServicioProxyCliente.class);

    private final RestTemplate restTemplate;
    private final Scanner scanner;

    @Autowired
    public ServicioProxyCliente(RestTemplate restTemplate, Scanner scanner) {
        this.restTemplate = restTemplate;
        this.scanner = scanner;
    }

    public void iniciarAplicacion() {
        int opcion;
        do {
            System.out.println("1. Agregar Libro");
            System.out.println("2. Eliminar Libro por ID");
            System.out.println("3. Modificar Libro por ID");
            System.out.println("4. Obtener Libro por ID");
            System.out.println("5. Listar todos los Libros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    eliminarLibro();
                    break;
                case 3:
                    modificarLibro();
                    break;
                case 4:
                    obtenerLibro();
                    break;
                case 5:
                    listarLibros();
                    break;
            }
        } while (opcion != 6);
    }

    private void agregarLibro() {
        System.out.println("Ingrese los datos del libro:");

        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("Nota: ");
            String nota = scanner.nextLine();

            Libro libro = new Libro(id, titulo, autor, nota);

            restTemplate.postForEntity(URL, libro, Libro.class);
            System.out.println("Libro agregado.");
        } catch (HttpClientErrorException e) {
            System.err.println("Error al agregar el libro. Detalles: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            System.err.println("Error interno del servidor al agregar el libro. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al agregar el libro. Detalles: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int id = scanner.nextInt();

        try {
            restTemplate.delete(URL + "/" + id);
            System.out.println("Libro eliminado.");
        } catch (HttpClientErrorException e) {
            System.err.println("Error al eliminar el libro. Detalles: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            System.err.println("Error interno del servidor al eliminar el libro. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al eliminar el libro. Detalles: " + e.getMessage());
        }
    }

    private void modificarLibro() {
        System.out.print("Ingrese el ID del libro a modificar: ");
        int id = scanner.nextInt();

        try {
            System.out.println("Ingrese los nuevos datos del libro:");
            scanner.nextLine();
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("Nota: ");
            String nota = scanner.nextLine();

            Libro libro = new Libro(id, titulo, autor, nota);

            restTemplate.put(URL + "/" + id, libro);
            System.out.println("Libro modificado.");
        } catch (HttpClientErrorException e) {
            System.err.println("Error al modificar el libro. Detalles: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            System.err.println("Error interno del servidor al modificar el libro. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al modificar el libro. Detalles: " + e.getMessage());
        }
    }

    private void obtenerLibro() {
        System.out.print("Ingrese el ID del libro a obtener: ");
        int id = scanner.nextInt();

        try {
            Libro libro = restTemplate.getForObject(URL + "/" + id, Libro.class);
            if (libro != null) {
                System.out.println("Libro obtenido: " + libro);
            } else {
                System.out.println("Libro no encontrado.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener el libro. Detalles: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            System.err.println("Error interno del servidor al obtener el libro. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener el libro. Detalles: " + e.getMessage());
        }
    }

    private void listarLibros() {
        try {
            Libro[] libros = restTemplate.getForObject(URL, Libro[].class);
            if (libros != null && libros.length > 0) {
                List<Libro> libroList = Arrays.asList(libros);
                System.out.println("Lista de libros:");
                libroList.forEach(System.out::println);
            } else {
                System.out.println("No se pudieron obtener los libros.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error al listar los libros. Detalles: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            System.err.println("Error interno del servidor al listar los libros. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al listar los libros. Detalles: " + e.getMessage());
        }
    }
}
