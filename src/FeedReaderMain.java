import HTTPRequest.HTTPRequester;
import parser.RSSParser;
import parser.SubscriptionParser;
import subscription.SingleSubscription;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length == 0) {
            /*
            Leer el archivo de suscription por defecto;
            Llamar al httpRequester para obtenr el feed del servidor
            Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
            Llamar al constructor de Feed
            LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
            */

            SubscriptionParser parser = new SubscriptionParser(Path.of("config/subscriptions.json"));
            List<SingleSubscription> subscriptions = parser.parse();

            for (SingleSubscription sub : subscriptions) {
                System.out.println(sub);
                HTTPRequester requester = new HTTPRequester();
                if (sub.getUrlType().equals("rss")) {
                    System.out.println("Iniciando RSS Request para cada uno de los parametros dados:");
                    for (String param : sub.getUrlParams()) {
                        try {
                            String formattedUrl = String.format(sub.getUrl(), param);
                            System.out.println(formattedUrl);
                            String feedRSS = requester.getFeedRss(formattedUrl);
                            new RSSParser().parse(feedRSS);
                        } catch (MalformedURLException e) {
                            System.out.printf("URL de la subscripcion malformada: %s%n", e.getCause().toString());
                        } catch (IOException e) {
                            System.out.printf("No se pudo conectar al servidor RSS, comprobar la conexion al internet: %s%n", e.getCause().toString());
                        }
                    }
                }
            }


        } else if (args.length == 1) {

            /*
            Leer el archivo de suscription por defecto;
            Llamar al httpRequester para obtenr el feed del servidor
            Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
            Llamar al constructor de Feed
            Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
            LLamar al prettyPrint de la tabla de entidades nombradas del feed.
             */

        } else {
            printHelp();
        }
    }

}
