import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLEditor {

	public static void editar() throws TransformerFactoryConfigurationError, TransformerException {

		Scanner teclado = new Scanner(System.in);

		// Cargamos en memoria el archivo XML
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("Vaya, parece que algo ha salido mal.");
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Vaya, parece que algo ha salido mal.");
		}

		// Obtenemos el nodo raíz
		Node rootNode = doc.getDocumentElement();

		// Pedimos los datos del alumno a editar
		System.out.println(" ");
		System.out.println("Elije el ID del alumno que quieres editar: ");
		String id = teclado.nextLine();

		
		// Editamos el alumno
		NodeList items = doc.getElementsByTagName("alumne");
		
		NodeList informacion = null;

		// Primero cojo toda la información que tiene el alumno
		for (int i = 0; i < items.getLength(); i++) {

			Element element = (Element) items.item(i);

			if (element.getAttribute("id").equalsIgnoreCase(id)) {

				informacion = element.getChildNodes();
			}
		}
		
		// Una vez que la tengo, pregunto si quiere editar ese campo y si quiere, lo edito
		for (int i = 0; i < informacion.getLength(); i++) {
			
			if (!informacion.item(i).getNodeName().equals("#text")) {
				
				System.out.println("¿Quieres editar " + informacion.item(i).getNodeName() + 
						"(s/n)? El valor actual es: " + informacion.item(i).getTextContent());
				String modificar = teclado.nextLine();
				
				if (modificar.equalsIgnoreCase("s")) {
					
					System.out.println(" ");
					System.out.println("¿Que quieres poner?");
					String valor = teclado.nextLine();
					informacion.item(i).setTextContent(valor);
				}
			}
		}
		
		// Guardamos
		XMLSaver.save(file, doc);
	}
}
