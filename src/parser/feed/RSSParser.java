package parser.feed;

import feed.Feed;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import parser.feed.FeedParser;

import javax.xml.parsers.*;
import java.io.*;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RSSParser extends FeedParser {

    @Override
    public Feed parseFeed(String rawRSS) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xml = builder.parse(new InputSource(new StringReader(rawRSS)));
            xml.getDocumentElement().normalize();
            System.out.println("Root ELEM: " + xml.getDocumentElement().getNodeName());
            NodeList titles = xml.getElementsByTagName("title");
            for (int i = 0; i < titles.getLength(); i++) {
                Node title = titles.item(i);
                if (title.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                System.out.println(title.getTextContent());
            }
        } catch (ParserConfigurationException e) {

        } catch (Exception e) {

        }
        return null;
    }
}
