import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@Test
	void testInsert() {
		BinarySearchTree<Integer> tree = new BinarySearchTree();
		tree.insert(100);
		tree.insert(50);
		tree.insert(150);
		tree.insert(25);
		tree.insert(75);
		tree.insert(125);
		tree.insert(100);
		tree.insert(175);
		tree.insert(12);
		tree.insert(37);
		tree.insert(137);
		tree.insert(112);
		System.out.println( tree.inOrder());
	}

}
