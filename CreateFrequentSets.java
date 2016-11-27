package FPTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CreateFrequentSets {
	public List<List<Pair>> generateCombinations(HashMap<String, List<Pair>> patternBase) {
		List<List<Pair>> frequentSets = new ArrayList<>();
		List<List<Pair>> frequentItemSets = new ArrayList<>();
		List<Pair> temp = new ArrayList<>();
		
		for (Entry<String, List<Pair>> e : patternBase.entrySet()) {
			generateAllCombinations(frequentItemSets, e.getValue(), 0, temp);
			for (List<Pair> p : frequentItemSets) {
				p.add(new Pair(e.getKey(), 0));
			}
			frequentSets.addAll(frequentItemSets);
			frequentItemSets = new ArrayList<>();
		}
		
		return frequentSets;
	}
	
	public void generateAllCombinations(List<List<Pair>> combinations, List<Pair> patternBase, int start,
											List<Pair> temp) {
		if (temp.size() > 0) {
			combinations.add(new ArrayList<>(temp));
		}
        
        for (int i = start; i < patternBase.size(); i++) {
            temp.add(patternBase.get(i));
            generateAllCombinations(combinations, patternBase, i+1, temp);
            temp.remove(temp.size()-1);
        }
		
	}
}
