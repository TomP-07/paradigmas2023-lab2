import HTTPRequest.HTTPRequester;
import feed.Article;
import feed.Feed;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import parser.*;
import parser.feed.FeedParser;
import parser.feed.RSSParser;
import parser.feed.RedditParser;
import subscription.SingleSubscription;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FeedReaderMain {

    static Heuristic DEFAULT_HEURISTIC = new QuickHeuristic();

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    private static List<Feed> getFeeds(SingleSubscription subscription) {
        System.out.printf("Obteniendo Feeds para una subscripcion%n");
        System.out.printf("Tipo de subscripcion: %s%n", subscription.getUrlType());
        for (String param : subscription.getUrlParams()) {
            try {
                System.out.printf("Parametro de este Feed: %s%n", param);
                String formattedUrl = String.format(subscription.getUrl(), param);
                System.out.printf("URL final: %s%n", formattedUrl);


                HTTPRequester requester = new HTTPRequester(formattedUrl);
                FeedParser parser;
                if (subscription.getUrlType().equals("rss")) {
                    parser = new RSSParser();
                } else if (subscription.getUrlType().equals("reddit")) {
                    parser = new RedditParser();
                } else {
                    System.out.println("Tipo de subscripcion invalida!");
                    // TODO! Podria estar bueno hacer nuestra propia clase de excepciones para handelearlas.
                    break;
                }
                System.out.println("Iniciando peticion y parseo del Feed.");
                parser.parseFeed(requester.getRequestData());
            } catch (MalformedURLException e) {
                System.out.printf("URL de la subscripcion malformada: %s%n", e.getCause().toString());
            } catch (IOException e) {
                System.out.printf("No se pudo conectar al servidor del feed, comprobar la conexion al internet: %s%n", e.getCause().toString());
            }

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");

        if (args.length <= 1) {
            /*
             * Leer el archivo de suscription por defecto;
             * Llamar al httpRequester para obtenr el feed del servidor
             * Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
             * Llamar al constructor de Feed
             */

            SubscriptionParser parser = new SubscriptionParser(Path.of("config/subscriptions.json"));
            List<SingleSubscription> subscriptions = parser.parse();

            ArrayList<Feed> feeds = new ArrayList<>();
            for (SingleSubscription subscription : subscriptions) {
                System.out.println(subscription);
                feeds.addAll(FeedReaderMain.getFeeds(subscription));
            }

            if (args.length == 0) {
                /*
                 * LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
                 */
                for (Feed feed : feeds)
                    feed.prettyPrint();
            } else {
                /*
                 * Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
                 * LLamar al prettyPrint de la tabla de entidades nombradas del feed.
                 */
                for (Feed feed : feeds) {
                    for(Article article : feed.getArticleList())
                        article.computeNamedEntities(FeedReaderMain.DEFAULT_HEURISTIC);
                    // TODO! Hay que modificar el pretty print de los articles para que si se calcularon las entidades nombradas entonces se impriman.
                    feed.prettyPrint();
                }
            }
        } else {
            printHelp();
        }
    }

}
