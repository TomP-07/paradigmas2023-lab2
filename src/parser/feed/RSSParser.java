package parser.feed;

import feed.Article;
import feed.Feed;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import parser.feed.FeedParser;

import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
            // consigo la lista de items de la pagina
            NodeList itemNodes = xml.getElementsByTagName("item");

            // genero la lista de titulos para poder crear el feed con su determinado titulo
            NodeList titles = xml.getElementsByTagName("title");

            // genero el feed que retornaremos
            Feed feed = new Feed(titles.item(0).toString());

            // por cada item consigo su titulo, link, descripcion y fecha
            for (int i = 0; i < itemNodes.getLength(); i++) {

                Node itemNode = itemNodes.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                    String description = itemElement.getElementsByTagName("description").item(0).getTextContent();
                    String pubDateS = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    
                    // formateo la fecha
                    String inputFormat = "EEE, dd MMM yyyy HH:mm:ss Z";
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
                    Date pubDate = (Date) inputDateFormat.parse(pubDateS);
                    
                    // añado el articulo al feed
                    Article newArticle = new Article(title,description,pubDate,link);
                    feed.addArticle(newArticle);
                }                
            }

        } catch (ParserConfigurationException e) {

        } catch (Exception e) {

        }
        return null;
    }
}
