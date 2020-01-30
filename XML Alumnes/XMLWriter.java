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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLWriter {

	public static void writer() throws TransformerFactoryConfigurationError, TransformerException {
		
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
		
		// Pedimos los datos del nuevo alumno
		System.out.println(" ");
		System.out.println("Nombre: ");
		String name = teclado.nextLine();
		System.out.println("Primer apellido: ");
		String surname1 = teclado.nextLine();
		System.out.println("Segundo apellido: ");
		String surname2 = teclado.nextLine();
		System.out.println("Nota final: ");
		String mark = teclado.nextLine();
		
		// Creamos los datos
		NodeList alumnes = doc.getElementsByTagName("alumnes");
		Element alumne = doc.createElement("alumne");
		int numeroAlumnes = doc.getElementsByTagName("alumne").getLength() + 1;
		alumne.setAttribute("id", String.valueOf(numeroAlumnes));
		Element nom = doc.createElement("nom");
		Element cognom1 = doc.createElement("cognom1");
		Element cognom2 = doc.createElement("cognom2");
		Element notaFinal = doc.createElement("notaFinal");
		
		// Añadimos el texto
		nom.appendChild(doc.createTextNode(name));
		cognom1.appendChild(doc.createTextNode(surname1));
		cognom2.appendChild(doc.createTextNode(surname2));
		notaFinal.appendChild(doc.createTextNode(mark));
		
		// Añadimos los hijos al alumno
		alumne.appendChild(nom);
		alumne.appendChild(cognom1);
		alumne.appendChild(cognom2);
		alumne.appendChild(notaFinal);
		
		alumnes.item(0).appendChild(alumne);
		
		// Guardamos
		XMLSaver.save(file, doc);
	}
}
