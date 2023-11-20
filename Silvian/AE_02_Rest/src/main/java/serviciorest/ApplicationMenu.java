package serviciorest;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Application.java -> ES EL PUNTO (CLASS) DE ENTRADA EN LA NUSTRA APPLICACION.
 *                     EN EL MOMENTO QUE LO VAMOS A ARRANCAR , NOS VA A ARRANCAR EL SERVIDOR TOMCAT.
 * EL SERVIDOR TOMCAT -> ES EL  SERVIDOR QUE NO AYUDA  PARA DESPLEGAR. 
 */
/*
 * PARA ESTE CLASS USAMOS LA ANOTACIÑON - @SpringBootApplicacion QUE NOS OFRECE LAS SIGUIENTES VENTAJAS :
 * 1. VA A REALIZAR  QUE LA APLICACION SE AUTOCOFIGURE POR DEFECTO.
 * 2. VA A REALIZAR BUSQUEDAS DE METODOS CON ANOTACIÓN - @BEAN - DENTRO DE LA APLICACION PARA DAR DE ALTA 
 *    OBJETOS EN EL CONTEXTO DE SPRING.
 * 3. VA A REALIZAR BUSQUEDAS DE ANOTACIONES SPRING PARA DAR DE ALTA OBJETOS E INEYCTAR  DEPENDENCIAS
 */
@SpringBootApplication
public class ApplicationMenu {

	public static void main(String[] args) {
		
		System.out.println(" SERVICIO REST -> CARGANDO EL CONTEXTO DE SPRING ...");
 /*
  * CON LA AYUDA DEL METODO - run - ARRANCAMOS EL CONTEXTO DE SPRING Y DAREMOS DE ALTA TODOS LOS OBJETOS 
  *  QUE HAYAMOS CONFIGURADO EN NUESTRA APLICACION ASI COMO SUS DEPENDENCIAS CON OTROS OBJETOS
  */
		SpringApplication.run(ApplicationMenu.class, args);
		System.out.println("SERVICIO REST -> CONTEXTO DE SPRING CARGADO !");
		
		int opcion =0;

		do {
		opcion = pintarMenu();
		switch (opcion) {
		case 1:
			System.out.println(" DAR DE ALTA UN LIBRO  ");
			
			
			break;
		case 2:
		    System.out.println(" DAR DE BAJA UN LIBRO POR ID ");
			
			break;
		case 3:
			System.out.println(" MODIFICAR UN LIBRO POR ID ");
			
			break;
		case 4:
			System.out.println(" OBTENER UN LIBRO POR ID   ");
			break;
			
		case 5 :
			System.out.println("  LISTAR TODOS LOS LIBROS    ");
			break;
			
		}
		
		}while(opcion != 6);
		
		System.out.println(" === FIN DE PROGRAMA === ");
		}
	

	public static int pintarMenu() {
		Scanner leer = new Scanner(System.in);
		int opcion=0;
		System.out.println("               ★★★★★ MENU ★★★★★           ");
		System.out.println("    ---------------------------------------  ");
		System.out.println("  1.          DAR DE ALTA UN LIBRO           ");
		System.out.println("  2.       DAR DE BAJA UN LIBRO POR ID       ");
		System.out.println("  3.        MODIFICAR UN LIBRO POR ID        ");
		System.out.println("  4.         OBTENER UN LIBRO POR ID         ");
		System.out.println("  5          LISTAR TODOS LOS LIBROS         ");
		System.out.println("  6                   SALIR                           ");
		System.out.println(" PULSA UNA OPCION DE 1 A 5 PARA REALIZAR LA OPERACION O PULSA 6 PARA CERRAR EL PROCESO.");
		opcion=leer.nextInt();
     	while (opcion <1 || opcion >6 ) {
			System.out.println("PULAS UNA OPCION  DE 1 A 4");
			opcion=leer.nextInt();
			}
		return opcion;

		
	}

}