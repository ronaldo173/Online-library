import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;

public class ToXML {

    StreamResult out;
    TransformerHandler th;

    public static void main(String args[]) {
        new ToXML().begin();
    }

    public void begin() {

        try (BufferedReader in = new BufferedReader(new FileReader("stops.txt"))) {

            out = new StreamResult("stops.xml");
            openXml();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            closeXml();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        th = tf.newTransformerHandler();

        // pretty XML output
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");

        th.setResult(out);
        th.startDocument();
        th.startElement(null, null, "UnitedKingdom", null);
    }

    public void process(String s) throws SAXException {
        th.startElement(null, null, "busStop", null);
        th.characters(s.toCharArray(), 0, s.length());
        th.endElement(null, null, "busStop");
    }

    public void closeXml() throws SAXException {
        th.endElement(null, null, "UnitedKingdom");
        th.endDocument();
    }
}