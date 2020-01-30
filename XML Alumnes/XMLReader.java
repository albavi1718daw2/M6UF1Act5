import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public static void reader() throws ParserConfigurationException, SAXException, IOException {

		// Cargamos en memoria el archivo XML
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		// Obtenemos el nodo raíz
		Node rootNode = doc.getDocumentElement();

		// Mostramos los resultados
		nodesPrinter(rootNode);		
	}

	public static void nodesPrinter(Node rootNode) {
		
		// Variables que usaremos para almacenar
		NodeList child;
		NamedNodeMap attributes;

		// Guardamos los nodos hijo
		child = rootNode.getChildNodes();

		// Recorremos cada nodo en busca de información
		for (int i = 0; i < child.getLength(); i++) {
			
			// Si el nombre es alumne, accedemos
			if (!child.item(i).getNodeName().equals("#text")) {
				
				// Comprobamos primero si hay atributos
				if (child.item(i).hasAttributes()) {

					// Guardamos los atributos
					attributes = child.item(i).getAttributes();
					
					// Mostramos la información
					for (int j = 0; j < attributes.getLength(); j++) {

						System.out.println(" ");
						System.out.println("ID del alumno: " + attributes.item(j).getNodeValue());
					}
					
				} else {
					
					// Imprimimos el valor de los nodos
					System.out.println(child.item(i).getNodeName() + ": " + child.item(i).getTextContent());

				}
				
				// En caso de que el nodo hijo contenga otro nodo hijo
				// repetirá el mismo proceso de búsqueda
				if (child.item(i).hasChildNodes()) {
					
					nodesPrinter(child.item(i));
				}
			}
		}
	}
}