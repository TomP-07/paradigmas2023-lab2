package parser;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import java.io.*;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RSSParser extends GeneralParser {


    public void parse(String rawRSS) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xml = builder.parse(new InputSource(new StringReader(rawRSS)));
            xml.getDocumentElement().normalize();
            System.out.println("Root ELEM: " + xml.getDocumentElement().getNodeName());
            NodeList titles = xml.getElementsByTagName("title");
            for(int i = 0; i < titles.getLength(); i++) {
                Node title = titles.item(i);
                if(title.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                System.out.println(title.getTextContent());
            }
        } catch (ParserConfigurationException e) {

        } catch (Exception e) {

        }

    }

}
