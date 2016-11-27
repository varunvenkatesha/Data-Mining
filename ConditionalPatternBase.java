package FPTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConditionalPatternBase {
	
	public List<List<Pair>> makeCopy(List<List<TreeNode>> conditionalFPTree) {
		List<List<Pair>> copyWithMinCount = new ArrayList<>();
		
		for (List<TreeNode> eachTree: conditionalFPTree) {
			int minCount = Integer.MAX_VALUE;
			List<Pair> copyTree = new ArrayList<>();
			
			 for (TreeNode node : eachTree) {
				 minCount = Math.min(node.count, minCount);
			 }
			 
			 for (TreeNode eachNode : eachTree) {
				 Pair newPair = new Pair(eachNode.value, minCount);
				 copyTree.add(newPair);
			 }
			 
			 copyWithMinCount.add(copyTree);
		}
		
		return copyWithMinCount;
	}
	
	public HashMap<String, List<Pair>> removeItem(HashMap<String, List<Pair>> copyOfConditionalTree) {
		for(Iterator<Map.Entry<String, List<Pair>>> it = copyOfConditionalTree.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, List<Pair>> entry = it.next();
			List<Pair> list = new ArrayList<>();
			for (Pair p : entry.getValue()) {
				if (!p.value.equals(entry.getKey())) {
						list.add(p);
				}
			}
			if (list.size() > 0) {
				copyOfConditionalTree.put(entry.getKey(), list);
			} else {
				it.remove();
			}
		}
		
		return copyOfConditionalTree;
	}
	
	public HashMap<String, List<Pair>> combineItems(HashMap<String, List<Pair>> conditionalTree) {
		boolean found = false;
		
		for(Map.Entry<String, List<Pair>> entry : conditionalTree.entrySet()) {
			List<Pair> list = new ArrayList<>();
			for (Pair p: entry.getValue()) {
				for (Pair pair : list) {
					if (pair.value.equals(p.value)) {
						found = true;
						int index = list.indexOf(pair);
						list.get(index).count += p.count;
					}
				}
				if (!found) {
					list.add(p);
				} else {
					found = false;
				}
			}
			conditionalTree.put(entry.getKey(), list);
		}
		
		return conditionalTree;
	}
	
	public HashMap<String, List<Pair>> removeIfLessThanMinSup(HashMap<String, List<Pair>> conditionalTree, int minSup) {
		for(Map.Entry<String, List<Pair>> entry : conditionalTree.entrySet()) {
			List<Pair> list = new ArrayList<>();
			for (Pair p: entry.getValue()) {
				if (p.count >= minSup) {
					list.add(p);
				}
			}
			conditionalTree.put(entry.getKey(), list);
		}
		
		return conditionalTree;
	}
}
