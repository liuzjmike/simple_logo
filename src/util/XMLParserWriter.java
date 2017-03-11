package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserWriter {
	
	public static void saveState(File file, String root, Map<String,String> parameters) throws TransformerException, IOException {
		try {
			Document myDoc = getDocument();
			Element workspaceElement = myDoc.createElement(root);
			myDoc.appendChild(workspaceElement);
		    
			for (String parameter : parameters.keySet()) {
				writeParameter(parameter,parameters.get(parameter),myDoc,workspaceElement);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(myDoc);
//			File currentDirectory = new File(".").getCanonicalFile();
			StreamResult result = new StreamResult(file);
//			StreamResult result = new StreamResult(new File(currentDirectory+File.separator+directory+File.separator+file+".xml"));
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static void writeParameter(String key, String value, Document myDoc, Element parentEl) {
		Element el = myDoc.createElement(key);
		el.appendChild(myDoc.createTextNode(value));
		parentEl.appendChild(el);
	}
	
	public static Map<String,String> extractContent(File file, boolean orderMatters) {
		Map<String,String> parameters;
		if (orderMatters) {
			parameters = new LinkedHashMap<String,String>();
		} else {
			parameters = new HashMap<String,String>();
		}
		try {
			Document myDoc = getDocument(file);
			NodeList nodeList = getNodeList(myDoc);
			for (int i = 1; i<nodeList.getLength(); i++) {
				readElement(nodeList.item(i),parameters,myDoc);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return parameters;
	}
	
	private static Document getDocument() throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document myDoc = docBuilder.newDocument();
		return myDoc;
	}
	
	private static Document getDocument(File file) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		dBuilder = dbFactory.newDocumentBuilder();

		Document myDoc = dBuilder.parse(file);
		myDoc.getDocumentElement().normalize();
		
		return myDoc;
	}
	
	private static NodeList getNodeList(Document doc) {
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("*");
		return nodeList;
	}
	
	private static void readElement(Node node,Map<String, String> parameters,Document myDoc) {
		Element element = (Element) node;
		String attr = element.getNodeName();
		String value = myDoc.getElementsByTagName(attr).item(0).getTextContent();
		parameters.put(attr, value);
	}
}
