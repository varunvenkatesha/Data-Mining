package FPTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortOnFrequency {
	static HashMap<String, Integer> frequencyMap = new HashMap<>();
	HashMap<Integer, List<String>> database;
	int percentage;
	
	
	SortOnFrequency(HashMap<Integer, List<String>> database, int percentage) {
		this.database = database;
		this.percentage = percentage;
	}
	
	HashMap<Integer, List<String>> initializer() {
		calculateFrequency();
		removeLessFrequentElements(getMinSup());
		sortOnFrequency();
		return this.database;
	}
	
	void sortOnFrequency() {
		for (Map.Entry<Integer, List<String>> e : database.entrySet()) {
			Collections.sort(e.getValue(), new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return frequencyMap.get(s2) -  frequencyMap.get(s1);
				}
			});
		}
	}
	
	
	void calculateFrequency() {
		if (database == null || database.size() == 0) {
			return;
		}
		
		for (List<String> row : database.values()) {
			for (String s : row) {
				if (!frequencyMap.containsKey(s)) {
					frequencyMap.put(s, 1);
				} else {
					frequencyMap.put(s, frequencyMap.get(s) + 1);
				}
			}
		}
	}
	
	int getMinSup() {
		return (database.size() * percentage) / 100;
	}
	
	void removeLessFrequentElements(int minSup) {
		for (Map.Entry<Integer, List<String>> e : database.entrySet()) {
			List<String> row = new ArrayList<>();
			for (String item : e.getValue()) {
				if (frequencyMap.get(item) >= minSup) {
					row.add(item);
				}
			}
			database.put(e.getKey(), row);
		}
	}
}
