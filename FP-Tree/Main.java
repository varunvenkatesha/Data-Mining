package FPTree;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the complete path of the file to be read : ");
		String path = scanner.nextLine();
		System.out.println();
		
		System.out.println("Enter the percentage of input to be considered : ");
		PackageInitializer.percentage = scanner.nextInt();
		scanner.close();

		HashMap<Integer, List<String>> databse = PackageInitializer.createDatabase(path);
		databse = PackageInitializer.sortOnFrequncy(databse);
		
		PackageInitializer.createFPTree(databse);
		HashMap<String, List<List<TreeNode>>> conditionalFPTree = PackageInitializer.createConditionalFPtree();
		HashMap<String, List<Pair>> conditionalPatternBase = PackageInitializer.generateConditionalPatternBase(conditionalFPTree);
		PackageInitializer.generateAllFrequentItemSets(conditionalPatternBase);
	}
}
