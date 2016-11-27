package FPTree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	int count;
	String value;
	List<TreeNode> children;
	
	TreeNode(String value) {
		this.count = -1;
		this.value = value;
		this.children = new ArrayList<>();
	}
	
	TreeNode(String value, int count) {
		this.count = count;
		this.value = value;
		this.children = new ArrayList<>();
	}
}
