package namedEntity.heuristic;

import java.util.HashMap;
import java.util.Map;

public abstract class Heuristic {

    private static Map<String, Tuple<String, String>> categoryMap = new HashMap<>();

    static {
        categoryMap.put("Microsft", new Tuple<>("Company", "Electronic"));
        categoryMap.put("Apple", "Company");
        categoryMap.put("Google", "Company");
        categoryMap.put("Musk", "Person");
        categoryMap.put("Biden", "Person");
        categoryMap.put("Trump", "Person");
        categoryMap.put("Messi", "Person");
        categoryMap.put("Federer", "Person");
        categoryMap.put("USA", "Country");
        categoryMap.put("Russia", "Country");
        categoryMap.put("Microsoft", "Company");
        categoryMap.put("Apple", "Company");
        categoryMap.put("Google", "Company");
        categoryMap.put("Amazon", "Company");
        categoryMap.put("Tesla", "Company");
        categoryMap.put("Ford", "Company");
        categoryMap.put("Coca-Cola", "Company");
        categoryMap.put("McDonald's", "Company");
        categoryMap.put("Nike", "Company");
        categoryMap.put("Sony", "Company");
        categoryMap.put("IBM", "Company");
        categoryMap.put("Samsung", "Company");
        categoryMap.put("Oracle", "Company");
        categoryMap.put("HP", "Company");
        categoryMap.put("Facebook", "Company");
        categoryMap.put("Twitter", "Company");
        categoryMap.put("Uber", "Company");
        categoryMap.put("Airbnb", "Company");
        categoryMap.put("Alphabet", "Company");
        categoryMap.put("SpaceX", "Company");
        categoryMap.put("Virgin Galactic", "Company");
        categoryMap.put("Elon Musk", "Person");
        categoryMap.put("Jeff Bezos", "Person");
        categoryMap.put("Bill Gates", "Person");
        categoryMap.put("Mark Zuckerberg", "Person");
        categoryMap.put("Warren Buffett", "Person");
        categoryMap.put("Oprah Winfrey", "Person");
        categoryMap.put("Queen Elizabeth II", "Person");
        categoryMap.put("Barack Obama", "Person");
        categoryMap.put("Vladimir Putin", "Person");
        categoryMap.put("Angela Merkel", "Person");
        categoryMap.put("Emmanuel Macron", "Person");
        categoryMap.put("Xi Jinping", "Person");
        categoryMap.put("Joe Biden", "Person");
        categoryMap.put("Donald Trump", "Person");
        categoryMap.put("Lionel Messi", "Person");
        categoryMap.put("Cristiano Ronaldo", "Person");
        categoryMap.put("Serena Williams", "Person");
        categoryMap.put("Roger Federer", "Person");
        categoryMap.put("Simone Biles", "Person");
        categoryMap.put("Usain Bolt", "Person");
        categoryMap.put("USA", "Country");
        categoryMap.put("Russia", "Country");
        categoryMap.put("China", "Country");
        categoryMap.put("India", "Country");
        categoryMap.put("United Kingdom", "Country");
        categoryMap.put("France", "Country");
        categoryMap.put("Germany", "Country");
        categoryMap.put("Italy", "Country");
        categoryMap.put("Spain", "Country");
        categoryMap.put("Japan", "Country");
        categoryMap.put("Canada", "Country");
        categoryMap.put("Mexico", "Country");
        categoryMap.put("Brazil", "Country");
        categoryMap.put("Australia", "Country");
        categoryMap.put("New Zealand", "Country");
        categoryMap.put("iPhone", "Product");
        categoryMap.put("iPad", "Product");
        categoryMap.put("MacBook", "Product");
        categoryMap.put("Windows", "Product");
        categoryMap.put("Xbox", "Product");

    }


    public String getCategory(String entity) {
        String cat = categoryMap.get(entity);
        return cat == null ? "N/C" : cat;
    }

    public String getHeuristicName() {
        return "";
    }

    public abstract boolean isEntity(String word);

}
