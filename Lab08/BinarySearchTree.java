import java.util.ArrayList;
import java.util.ArrayDeque; 

public class BinarySearchTree<T extends Comparable> {

	//====================================================================================== Properties
	private BinaryNode root;

	//====================================================================================== Constructors
	public BinarySearchTree() {
		root = null;
	}

	// Constructor that takes an array of items and populates the tree
	public BinarySearchTree(T[] items) {	
		for (T item: items) {
			add(item);
		}
	}

	//====================================================================================== Methods
	public boolean add(T data) {	// Implement recursively and do NOT allow duplicates
		if (root == null) {
			root = new BinaryNode(data);
			return true;
		}
		return add(root, data);
	}
	
	private boolean add(BinaryNode node, T data) {
		if (node.data.compareTo(data) == 0) {
			return false;
		}
		if (node.data.compareTo(data) > 0) {
			if (node.left == null) {
				node.left = new BinaryNode(data);
				return true;
			} else {
				return add(node.left, data);
			}
		} else {
			if (node.right == null) {
				node.right = new BinaryNode(data);
				return true;
			} else {
				return add(node.right, data);
			}
		}
	}

	public String inOrder() {
		String result = "";

		result = inOrder(root);
		return result.trim();
	}

	private String inOrder(BinaryNode node) {
		String result = "";
		if (node != null) {
			result += inOrder(node.left);
			result += node.data.toString() + " ";
			result += inOrder(node.right);
		}
		return result;
	}

	public ArrayList<T> inOrderTraversal() {	
		ArrayList<T> result = new ArrayList<T>();
		return inOrderTraversal(root, result );
	}
	
	private ArrayList<T>  inOrderTraversal(BinaryNode node, ArrayList<T> result ) {
		if (node == null) return null;
		inOrderTraversal(node.left, result);
		result.add(node.data);
		inOrderTraversal(node.right, result);
		return result;
	}


	public ArrayList<T> breadthFirstTraversal() {
		ArrayList<T> result = new ArrayList<T>();
		ArrayDeque<BinaryNode> queue = new ArrayDeque<BinaryNode>();
		if (root == null) {
			return null;
		}
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode node = queue.remove();
			result.add(node.data);
			if (node.left != null) {
			queue.add(node.left);
			}
			if (node.right != null) {
			queue.add(node.right);
			}
		}
		return result;
	}


	// Returns whether the tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	
	// returns the largest value of all the leaves
	// If the tree is empty, throw an IllegalStateException()
	// Note, this is different than max as this is the max
	// of all leaf nodes
	public T maxLeaf() {
		if (root == null) {
			return null;
		}
		return maxLeaf(root);
	}
	
	private T maxLeaf(BinaryNode nodePtr) {
		while (nodePtr.right != null) {
			nodePtr = nodePtr.right;
		}
		if (nodePtr.isLeaf()) {
			return nodePtr.data;
		}
		return maxLeaf(nodePtr.left);
	}

	//====================================================================================== Inner Node Class
	private class BinaryNode {
		private T data;
		private BinaryNode left, right;

		private BinaryNode(T data) {
			this.data = data;
			left = right = null;
		}
		
		boolean isLeaf() {
			return (left == null) && (right == null);
		}
	}
}
