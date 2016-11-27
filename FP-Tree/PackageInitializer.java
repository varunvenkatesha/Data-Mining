package FPTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageInitializer {
	static TreeNode root;
	static HashMap<String, Integer> frequencyTable;
	static int minSup;
	static int percentage;
	
	
	public static HashMap<Integer, List<String>> createDatabase(String filePath) {
		HashMap<Integer, List<String>> database = new HashMap<>();
		@SuppressWarnings("unused")
		FileParser fileParser = new FileParser(database, filePath);
		
		return FileParser.database;
	}
	
	public static HashMap<Integer, List<String>> sortOnFrequncy(HashMap<Integer, List<String>> databse) {
		SortOnFrequency sortOnFrequency = new SortOnFrequency(databse, percentage);
		frequencyTable = SortOnFrequency.frequencyMap;
		minSup = sortOnFrequency.getMinSup();
		printMinSup();
		return sortOnFrequency.initializer();
	}
	
	public static void createFPTree(HashMap<Integer, List<String>> databse) {
		BaseTree tree = new BaseTree();
		tree.addChildren(databse);
		root = tree.root;
		//printFPTree(tree);
	}
	
	public static HashMap<String, List<List<TreeNode>>> createConditionalFPtree() {
		ConditionalFPTree conditionalFPTree = new ConditionalFPTree();
		HashMap<String, List<List<TreeNode>>> conditionalFPTreeForEachNode = new HashMap<>();
		
		for (Map.Entry<String, Integer> entry : frequencyTable.entrySet()) {
			List<List<TreeNode>> conditionalTree = new ArrayList<>();
			List<TreeNode> path = new ArrayList<>();
			
			conditionalFPTree.dfs(root, entry.getKey(), conditionalTree, path);
			conditionalFPTreeForEachNode.put(entry.getKey(), conditionalTree);
		}
		
		//printConditionalFPTree(conditionalFPTreeForEachNode);
		return conditionalFPTreeForEachNode;
	}
	
	public static HashMap<String, List<Pair>> generateConditionalPatternBase(HashMap<String, List<List<TreeNode>>> conditionalFPTree) {
		ConditionalPatternBase patternBase = new ConditionalPatternBase();
		HashMap<String, List<Pair>> conditionalPatternBase = new HashMap<>();
		
		for (Map.Entry<String, List<List<TreeNode>>> entry : conditionalFPTree.entrySet()) {
			for (List<Pair> l : patternBase.makeCopy(entry.getValue())) {
				if (!conditionalPatternBase.containsKey(entry.getKey())) {
					conditionalPatternBase.put(entry.getKey(), l);
				} else {
					List<Pair> list = conditionalPatternBase.get(entry.getKey());
					for (Pair p : l) {
						list.add(p);
					}
					conditionalPatternBase.put(entry.getKey(), list);
				}
			}
		}
		
		conditionalPatternBase = patternBase.removeItem(conditionalPatternBase);
		conditionalPatternBase = patternBase.combineItems(conditionalPatternBase);
		conditionalPatternBase = patternBase.removeIfLessThanMinSup(conditionalPatternBase, minSup);
		
		//printConditionalPatternBase(conditionalPatternBase);
		return conditionalPatternBase;
	}
	
	public static void generateAllFrequentItemSets(HashMap<String, List<Pair>> conditionalPatternBase) {
		CreateFrequentSets frequntSets = new CreateFrequentSets();
		
		List<List<Pair>> frequentItemSets = frequntSets.generateCombinations(conditionalPatternBase);
		
		System.out.println("Frequent Itemsets are : ");
		System.out.println();
		
		for (int i = 0; i < frequentItemSets.size(); i++) {
			System.out.print("[");
			for (int j = 0; j < frequentItemSets.get(i).size(); j++) {
				System.out.print(frequentItemSets.get(i).get(j).value);
				if (j < frequentItemSets.get(i).size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
	}
	
	public static void printMinSup() {
		System.out.println();
		System.out.print("Minsup for the given percentage is :  " + PackageInitializer.minSup);
		System.out.println();
		System.out.println();
	}
	
	public static void printFPTree(BaseTree tree) {
		System.out.println("Printing FPTree of given input file : ");
		System.out.println();
		System.out.println("Parent : Child");
		tree.printTree(root);
		System.out.println();
		System.out.println();
	}
	
	public static void printConditionalFPTree(HashMap<String, List<List<TreeNode>>> conditionalFPTreeForEachNode) {
		System.out.println("Printing Condotional FP Tree for each item : ");
		System.out.println();
		
		for (Map.Entry<String, List<List<TreeNode>>> entry : conditionalFPTreeForEachNode.entrySet()) {
			for (List<TreeNode> tn : entry.getValue()) {
				System.out.print(entry.getKey() + "---->");
				for (TreeNode node : tn) {
					System.out.print(node.value + ": " + node.count + "		");
				}
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println();
	}
	
	public static void printConditionalPatternBase(HashMap<String, List<Pair>> conditionalPatternBase) {
		System.out.println("Printing Condotional Pattern Base for each item : ");
		System.out.println();

		for (Map.Entry<String, List<Pair>> entry : conditionalPatternBase.entrySet()) {
			System.out.print(entry.getKey() + "----->");
			for (Pair eachPair : entry.getValue()) {
				System.out.print(eachPair.value + ": " + eachPair.count + "   ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
}
