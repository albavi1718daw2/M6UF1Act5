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
		
		// Pedimos los datos del nuevo alumno
		System.out.println(" ");
		System.out.println("Autor: ");
		String author = teclado.nextLine();
		System.out.println("Título: ");
		String title = teclado.nextLine();
		System.out.println("Género: ");
		String genre = teclado.nextLine();
		System.out.println("Precio: ");
		String price = teclado.nextLine();
		System.out.println("Publicación: ");
		String publishDate = teclado.nextLine();
		System.out.println("Descripción: ");
		String description = teclado.nextLine();
		
		// Creamos los datos
		NodeList catalog = doc.getElementsByTagName("catalog");
		Element book = doc.createElement("book");
		int numeroLlibres = doc.getElementsByTagName("book").getLength() + 1;
		book.setAttribute("id", String.valueOf(numeroLlibres));
		Element autor = doc.createElement("author");
		Element titol = doc.createElement("title");
		Element genere = doc.createElement("genre");
		Element preu = doc.createElement("price");
		Element dataPublicacio = doc.createElement("publish_date");
		Element descripcio = doc.createElement("description");
		
		// Añadimos el texto
		autor.appendChild(doc.createTextNode(author));
		titol.appendChild(doc.createTextNode(title));
		genere.appendChild(doc.createTextNode(genre));
		preu.appendChild(doc.createTextNode(price));
		dataPublicacio.appendChild(doc.createTextNode(publishDate));
		descripcio.appendChild(doc.createTextNode(description));
		
		// Añadimos los hijos al alumno
		book.appendChild(autor);
		book.appendChild(titol);
		book.appendChild(genere);
		book.appendChild(preu);
		book.appendChild(dataPublicacio);
		book.appendChild(descripcio);
		
		catalog.item(0).appendChild(book);
		
		// Guardamos
		XMLSaver.save(file, doc);
	}
}
