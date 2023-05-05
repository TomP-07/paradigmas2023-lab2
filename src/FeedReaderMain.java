import HTTPRequest.HTTPRequester;
import feed.Article;
import feed.Feed;
import namedEntity.heuristic.DigimonHeuristic;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.RandomHeuristic;
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
import java.util.Random;


public class FeedReaderMain {

    private static Heuristic getRandomHeuristic(){
        Heuristic[] options = {QUICK_HEURISTIC, RANDOM_HEURISTIC, DIGIMON_HEURISTIC};
        Random random = new Random();
        int index = random.nextInt(options.length);
        return options[index];
    }
    static Heuristic QUICK_HEURISTIC = new QuickHeuristic();
    static Heuristic RANDOM_HEURISTIC = new RandomHeuristic();
    static Heuristic DIGIMON_HEURISTIC = new DigimonHeuristic();

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    // extrae un feed de una subscripcion especifica
    private static List<Feed> getFeeds(SingleSubscription subscription) {
        System.out.printf("Obteniendo Feeds para una subscripcion%n");
        System.out.printf("Tipo de subscripcion: %s%n", subscription.getUrlType());
        List<Feed> feeds = new ArrayList<Feed>();
        // para cada seccion de la suscripcion
        for (String param : subscription.getUrlParams()) {
            try {
                System.out.printf("Parametro de este Feed: %s%n", param);
                // formateamos la url con la seccion
                String formattedUrl = String.format(subscription.getUrl(), param);
                System.out.printf("URL final: %s%n", formattedUrl);

                // hacemos la reequest http de la url formateada
                HTTPRequester requester = new HTTPRequester(formattedUrl);

                // creamos un parser de acuerdo al tipo dado e el json
                FeedParser parser;
                String typeRequest;
                if (subscription.getUrlType().equals("rss")) {
                    parser = new RSSParser();
                    typeRequest = "RSS";
                } else if (subscription.getUrlType().equals("reddit")) {
                    parser = new RedditParser();
                    typeRequest = "REDDIT";
                } else {
                    System.out.println("Tipo de subscripcion invalida!");
                    // TODO! Podria estar bueno hacer nuestra propia clase de excepciones para handelearlas.
                    break;
                }
                
                // parseamos la data de la request
                System.out.println("Iniciando peticion y parseo del Feed.");
                feeds.add(parser.parseFeed(requester.getRequestData(typeRequest)));
            } catch (MalformedURLException e) {
                System.out.printf("URL de la subscripcion malformada: %s%n", e.getCause().toString());
            } catch (IOException e) {
                System.out.printf("No se pudo conectar al servidor del feed, comprobar la conexion al internet: %s%n", e.getCause().toString());
            }

        }
        return feeds;
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");

        if (args.length <= 1) {
            /*
             * 
             * Llamar al httpRequester para obtenr el feed del servidor
             * Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
             * Llamar al constructor de Feed
             */

            // Leer el archivo de suscription por defecto;
            SubscriptionParser parser = new SubscriptionParser(Path.of("config/subscriptions.json"));
            List<SingleSubscription> subscriptions = parser.parse();

            // generamos una lista de feeds, donde vamos agregando la feed de cada subscripcion 
            ArrayList<Feed> feeds = new ArrayList<>();
            for (SingleSubscription subscription : subscriptions) {
                System.out.println(subscription);
                try{
                    feeds.addAll(FeedReaderMain.getFeeds(subscription));
                }catch(Exception e){
                    System.out.println(e);
                }

            }

            if (args.length == 0) {
                // LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
                for (Feed feed : feeds)
                    feed.prettyPrint();
                    
            } else {
                /*
                 * Llamar a la heuristica para que compute las entidades nombradas de CADA ARTICULO del feed
                 * LLamar al prettyPrint de la tabla de entidades nombradas del feed.
                 */
                for (Feed feed : feeds) {
                    for(Article article : feed.getArticleList()){
                        Heuristic h = getRandomHeuristic();
                        article.computeNamedEntities(h);
                    }
                    // Al haber ya calculado las entidades nombradas de los articulos del feed esto va a causar de que se impriman tambien
                    // las entidades nombradas que acabamos de calcular.
                    feed.prettyPrint();
                }
            }
        } else {
            printHelp();
        }
    }

}
