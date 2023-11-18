
package es.SistemasRest;

import es.SistemasRest.servicio.ServicioProxyCliente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class A2RestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(A2RestApplication.class, args);
    }

    
    //El método run nos sirve para iniciar la aplicación. Creamos una instancia ServicioProxyCliente y le pasamos los objetos restTemplate y scanner por el constructor.
    //Llamamos al método iniciarAplicacion.
    @Override
    public void run(String... args) throws Exception {
        ServicioProxyCliente clienteLibros = new ServicioProxyCliente(restTemplate(), scanner());
        clienteLibros.iniciarAplicacion();
    }

    
    //Utilizamos la anotación @Bean para crear instancia ded tipo Scanner y Restemp
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
