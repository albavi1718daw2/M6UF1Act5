import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public class Menu {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main (String [] args) throws TransformerFactoryConfigurationError, TransformerException {
		
		// Llamamos al menú
		menu();
	}
	
	public static void menu() throws TransformerFactoryConfigurationError, TransformerException {
		
		int opcion;
		
		// Pedimos al usuario que queremos hacer
		System.out.println("Elije lo que quieres hacer: ");
		System.out.println(" ");
		System.out.println("1) Crear nuevo alumno");
		System.out.println("2) Modificar alumno");
		System.out.println("3) Eliminar alumno");
		System.out.println("4) Ver el fichero");
		System.out.println("5) Salir");
		opcion = teclado.nextInt();
		
		opciones(opcion);
	}
	
	public static void opciones(int opcion) throws TransformerFactoryConfigurationError, TransformerException {
		
		switch (opcion) {

		case 1:
			
			System.out.println(" ");
			XMLWriter.writer();
			System.out.println(" ");
			menu();
			break;
			
		case 2:
			
			System.out.println(" ");
			XMLEditor.editar();
			System.out.println(" ");
			menu();
			break;
			
		case 3:
			
			System.out.println(" ");
			XMLRemover.remover();
			System.out.println(" ");
			menu();
			break;
			
		case 4:
			
			try {
				XMLReader.reader();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				
				System.out.println("Vaya, parece que ha habido un error.");
			}
			System.out.println(" ");
			menu();
			break;
			
		case 5:
			
			Scanner teclado = new Scanner(System.in);
			System.out.println(" ");
			System.out.println("Has salido de forma correcta.");
			System.out.println(" ");
			String guardar = teclado.nextLine();
			break;

		default:
			
			System.out.println("Has elegido una opción incorrecta.");
			menu();
		}
	}
}
