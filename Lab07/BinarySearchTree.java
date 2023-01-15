import org.w3c.dom.Node;

public class BinarySearchTree<T extends Comparable> {

	BinaryNode<T> root;

	public BinarySearchTree() {
		root = null;
	}

	public boolean insert(T data) {

		if (isEmpty()) {
			root = new BinaryNode<T>(data);
			return true;
		}
		return recursiveInsert(root, data);
	}

	private boolean recursiveInsert(BinaryNode<T> node, T newData) {
		if (node.data.compareTo( newData) == 0) {
			return false;
		} else if (node.data.compareTo(newData) > 0) {
			if (node.left == null) {
				node.left = new BinaryNode(newData);
				return true;
			} else
				return recursiveInsert(node.left, newData);
		} else {
			if (node.right == null) {
				node.right = new BinaryNode(newData);
				return true;
			} else
				return recursiveInsert(node.right, newData);
		}
	}

	// Returns the in-order traversal of the tree rooted at this node
	// Space separate; all on one line
	public String inOrder() {
		String result = "";
		result = inOrderRecursive(root);
		return result.trim();
		
	}

	private String inOrderRecursive(BinaryNode node) {
		String result = "";
		if (node == null)
			return "";
		result += inOrderRecursive(node.left);
		result += node.data.toString() + " ";
		result += inOrderRecursive(node.right);
		return result;
	}
	
	// Prints the pre-order traversal of the tree rooted at this node
	// Space separated. all on one line
	String preOrderTraversal() {
		String result = "";
		result = preOrderRecursive(root);
		return result.trim();
	}
	
	private String preOrderRecursive(BinaryNode node) {
		String result = "";
		if (node == null)
			return "";
		result += node.data.toString() + " ";
		result += preOrderRecursive(node.left);
		result += preOrderRecursive(node.right);
		return result;
	}

	// returns the height of the tree. If the tree has only
	// one node, its height is 0
	int getHeight() {
		int result = getHeightRecursive(root);
		if (result == 0) {
			return result;
		} else {
			return result - 1;
		}
	}
	
	private int getHeightRecursive(BinaryNode node) {
		if (node == null)
			return 0;
		if (getHeightRecursive(node.left) > getHeightRecursive(node.right)) {
			return 1 + getHeightRecursive(node.left);
		} else {
			return 1 + getHeightRecursive(node.right);
		}
	}

	// Returns the number of leaves in the tree rooted at this node
	int numberOfLeaves() {
		return numberOfLeavesRecursive(root);
	}
	
	private int numberOfLeavesRecursive(BinaryNode node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		return numberOfLeavesRecursive(node.left) + numberOfLeavesRecursive(node.right);
	}

	public boolean isEmpty() {
		return root == null;
	}

	// Print all the nodes in the tree rooted at this node,
	// using post-order traversal: left subtree, right subtree, then root data
	public String postOrderTraversal() {
		String result = "";
		if (isEmpty()) {
			return result;
		}
		if (root.isLeaf()) {
			return root.data.toString();
		}
		result = postOrderRecursive(root);
		return result.trim();
	}
	
	private String postOrderRecursive(BinaryNode node) {
		String result = "";
		if (node == null)
			return "";
		result += postOrderRecursive(node.left);
		result += postOrderRecursive(node.right);
		result += node.data.toString() + " ";
		return result;
	}

	// Returns the number of nodes in the binary tree
	// that is rooted at this node. Write recursively.
	int nodeCount() { // counting the root of the tree
		return nodeCountRecursive(root);
	}
	
	private int nodeCountRecursive(BinaryNode node) {
		if (node == null)
			return 0;
		return 1 + nodeCountRecursive(node.left) + nodeCountRecursive(node.right);
	}

	// Return true if a value is in the tree, and false otherwise
	boolean find(T key) {
		return findRecursive(key, root);
	}
	
	private boolean findRecursive(T key, BinaryNode node) {
		if (node == null) {
			return false;
		}
		if (node.data.compareTo(key) == 0) {
			return true;
		} else if (node.data.compareTo(key) > 0) {
			return findRecursive(key, node.left);
		} else {
			return findRecursive(key, node.right);
		}
	}

	private class BinaryNode<T extends Comparable> {
		private T data;
		private BinaryNode left, right;

		public BinaryNode(T newData) {
			this.data = newData;
			this.left = null;
			this.right = null;
		}

		// Returns true if this node is a leaf, and false otherwise
		boolean isLeaf() {
			return (left == null && right == null);
		}
	}
}
