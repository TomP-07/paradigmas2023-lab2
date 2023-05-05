package namedEntity.heuristic;

import java.util.Map;

public abstract class Heuristic {

	private static Map<String, String> categoryMap = Map.of(
			"Microsft", "Company", 
			"Apple", "Company", 
			"Google", "Company",
			"Musk", "Person",
			"Biden", "Person",
			"Trump", "Person",
			"Messi", "Person",
			"Federer", "Person",
			"USA", "Country",
			"Russia", "Country"
			);
	
	
	public String getCategory(String entity){
		String cat = categoryMap.get(entity);
		return  cat == null ? "N/C" : cat;
	}
	
	public String getHeuristicName(){
		return "";
	}
	
	public abstract boolean isEntity(String word);
		
}
