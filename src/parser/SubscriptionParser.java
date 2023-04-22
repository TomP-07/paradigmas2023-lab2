package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import subscription.SingleSubscription;
import subscription.Subscription;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser {

    Path filePath;

    public SubscriptionParser(Path filePath) {
        this.filePath = filePath;
    }

    public List<SingleSubscription> parse() {
        List<SingleSubscription> subscriptions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath.toString());
            JSONArray subscriptionsArray = new JSONArray(new JSONTokener(fileReader));
            for (int i = 0; i < subscriptionsArray.length(); i++) {
                try {
                    JSONObject subscriptionObject = subscriptionsArray.getJSONObject((i));

                    String url = subscriptionObject.getString("url");
                    JSONArray urlParamsArray = subscriptionObject.getJSONArray("urlParams");
                    List<String> urlParams = new ArrayList<>();
                    for (int j = 0; j < urlParamsArray.length(); j++)
                        urlParams.add(urlParamsArray.getString(j));


                    String urlType = subscriptionObject.getString("urlType");
                    SingleSubscription subscription = new SingleSubscription(url, urlParams, urlType);
                    subscriptions.add(subscription);
                } catch (JSONException e) {
                    System.out.printf("Error parseando subscripcion: %s%n", e.getCause().toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("El archivo de subscripciones no se encontro en el sistema. Path: %s%n", filePath.toAbsolutePath());
        } catch (JSONException e) {
            System.out.printf("Error parseando la lista de las subscripciones. %s%n", e.getCause().toString());
        }

        return subscriptions;
    }

}
