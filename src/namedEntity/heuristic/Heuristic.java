package namedEntity.heuristic;

public abstract class Heuristic {

    public String getHeuristicName() {
        return "";
    }

    public abstract boolean isEntity(String word);

}
