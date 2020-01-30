import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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

public class XMLRemover {

	public static void remover() throws TransformerFactoryConfigurationError, TransformerException {

		Scanner teclado = new Scanner(System.in);
		
		// Cargamos en memoria el archivo XML
		File file = new File("books.xml");
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
		
		// Pedimos los datos del alumno a eliminar
		System.out.println(" ");
		System.out.println("Elije el ID del alumno que quieres eliminar: ");
		String id = teclado.nextLine();
		
		// Eliminamos el alumno
		NodeList items = doc.getElementsByTagName("book");
		
		for (int i = 0; i < items.getLength(); i++) {
			
		    Element element = (Element) items.item(i);
		    
		    if (element.getAttribute("id").equalsIgnoreCase(id)) {
		        
		        element.getParentNode().removeChild(element);
		    }
		}
		
		// Guardamos
		XMLSaver.save(file, doc);
	}
}
