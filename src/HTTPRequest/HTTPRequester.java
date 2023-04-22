package HTTPRequest;


/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequester {

    public HTTPRequester() {

    }

    public String getFeedRss(String urlFeed) throws MalformedURLException, IOException {
        URL url = new URL(urlFeed);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        System.out.printf("Response code: %s%n", responseCode);

        InputStream responseStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) response.append(inputLine);
        reader.close();
        responseStream.close();

        String feedRssXml = response.toString();
        return feedRssXml;
    }

    public String getFeedReedit(String urlFeed) {
        String feedReeditJson = null;
        return feedReeditJson;
    }

}
