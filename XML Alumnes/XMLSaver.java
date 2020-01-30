import java.io.File;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLSaver {

	public static void save(File file, Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println("¿Quieres guardar los cambios (s/n)?");
		String guardar = teclado.nextLine();
		
		if (guardar.equalsIgnoreCase("s")) {
			// Guardamos
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			StreamResult result = new StreamResult(file);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			System.out.println("Documento guardado de forma correcta.");
		}
	}
}
