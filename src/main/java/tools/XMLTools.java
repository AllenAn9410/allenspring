package tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

public class XMLTools {
    private String path;

    public XMLTools() {
    }

    public XMLTools(String path) {
        this.path = path;
    }

    public Document covertFileToNode() throws Exception {
        String file = ReadFile.read(path);
        Document dom = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(file.getBytes()));
        return dom;
    }

    public static String xmlToString(Object object) throws Exception {
        if (object == null) {
            throw new Exception("node is null");
        }
        if (object instanceof Document) {
            return documentToString((Document) object);
        } else if (object instanceof Node) {
            return nodeToString((Element) object);
        }
        throw new Exception("paramters is invalid");
    }


    private static String nodeToString(Element element) throws Exception {
        StringWriter sw = new StringWriter();

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(element), new StreamResult(sw));

        return sw.toString();
    }

    private static String documentToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }


}
