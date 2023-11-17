package es.SistemasRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import es.SistemasRest.entidad.Libro;
import es.SistemasRest.persistencia.DaoLibro;

@SpringBootApplication
public class A2RestApplication implements CommandLineRunner {

    @Autowired
    DaoLibro daoLibro;

    public static void main(String[] args) {
        SpringApplication.run(A2RestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
                    System.out.println("Ingrese el número a buscar");
                    int id = scanner.nextInt();
                    Libro libro = daoLibro.getLibroByID(id);

                    if (libro != null) {
                        System.out.println("El título del libro buscado es: " + libro.getTitulo());
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Escriba el libro a ingresar");
                    scanner.nextLine();
                    String titulo = scanner.nextLine();
                    System.out.println("Escriba el autor del libro");
                    String autor = scanner.nextLine();
                    System.out.println("Escriba la nota del libro");
                    String nota = scanner.nextLine();

                    Libro nuevoLibro = new Libro(daoLibro.list().size() + 1, titulo, autor, nota);
                    daoLibro.añadirLibro(nuevoLibro);
                    System.out.println("Libro añadido correctamente: " + nuevoLibro);
                    break;
                }
                case 3: {
                    System.out.println("Lista de libros: ");
                    daoLibro.list().forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.println("Ingrese el ID del libro a modificar");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 

                    Libro libroModificado = daoLibro.getLibroByID(id);
                    if (libroModificado != null) {
                        System.out.println("Ingrese el nuevo título del libro");
                        libroModificado.setTitulo(scanner.nextLine());
                        System.out.println("Ingrese el nuevo autor del libro");
                        libroModificado.setAutor(scanner.nextLine());
                        System.out.println("Ingrese la nueva nota del libro");
                        libroModificado.setNota(scanner.nextLine());

                        daoLibro.modificar(libroModificado);
                        System.out.println("Libro modificado correctamente: " + libroModificado);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Ingrese el ID del libro a borrar");
                    int id = scanner.nextInt();

                    if (daoLibro.eliminar(id)) {
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
