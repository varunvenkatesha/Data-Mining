package FPTree;

import java.util.ArrayList;
import java.util.List;

public class ConditionalFPTree {
	
	public void dfs(TreeNode root, String targetNode, List<List<TreeNode>> conditionalTree, 
						List<TreeNode> path) {
		if (root.value.equals(targetNode) || root == null) {
			conditionalTree.add(new ArrayList<TreeNode>(path));
			return;
		}
		
		for (TreeNode child : root.children) {
			path.add(child);
			dfs(child, targetNode, conditionalTree, path);
			path.remove(path.size() - 1);
		}
	}
}
