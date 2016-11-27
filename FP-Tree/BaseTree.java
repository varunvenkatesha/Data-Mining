package FPTree;

import java.util.HashMap;
import java.util.List;

public class BaseTree {
	TreeNode root;
	
	BaseTree() {
		root = new TreeNode("root");
	}
	
	void addChildren(HashMap<Integer, List<String>> database) {
		for (List<String> path : database.values()) {
			addChild(this.root, path, 0);
		}
	}
	
	TreeNode addChild(TreeNode root, List<String> path, int index) {
		if (root == null || index >= path.size()) {
			return null;
		}
		
		List<TreeNode> childern = root.children;
		
		for (TreeNode node : childern) {
			if ((node.value).equals(path.get(index))) {
				node.count++;
				return addChild(node, path, index+1);
			}
		}
		
		TreeNode currentChild = new TreeNode(path.get(index), 1);
		root.children.add(currentChild);
		
		return addChild(currentChild, path, index+1);
	}
	
	void printTree(TreeNode root) {
		if (root == null) {
			System.out.println("----------------------");
			return;
		}
		for (TreeNode t : root.children) {
			System.out.println(root.value + "	" + t.value + " : " + t.count);
			printTree(t);
		}
	}
}
