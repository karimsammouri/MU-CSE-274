import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeJuniTests {

	private static BinarySearchTree<Integer> bst;
	private static BinarySearchTree<String> bst1;
	private static BinarySearchTree<Double> bst2;
	private static BinarySearchTree<Integer> bst3;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bst = new BinarySearchTree<Integer>();
		for (int i = 1; i < 20; i++) {
			bst.add(i);
		}
		
		bst1 = new BinarySearchTree<String>();
		bst1.add("One"); bst1.add("Two"); bst1.add("Two"); bst1.add("Three"); 
		bst1.add("Four"); bst1.add("Five"); bst1.add("Six"); bst1.add("Seven"); 
		
		bst2 = new BinarySearchTree<Double>();
		for ( int i = 0; i < 10; i++) {
			bst2.add(Math.random()*100);
		}
		
		bst3 = new BinarySearchTree<Integer>();
		bst3.add(100); bst3.add(50); bst3.add(150);bst3.add(25); bst3.add(75); bst3.add(125); bst3.add(175);
		bst3.add(12); bst3.add(37); bst3.add(63); bst3.add(80); bst3.add(112); bst3.add(137); bst3.add(163); bst3.add(180);
	}


	@Test
	public void testAdd() {
		
		ArrayList<Integer> result = bst.inOrderTraversal();
		
		for (int i=1; i<20; i++) {
			assertEquals(i, result.get(i-1) );
		}
		
		ArrayList<String> result1 = bst1.inOrderTraversal();
		assertEquals( "Five", result1.get(0));
		assertEquals( "Four", result1.get(1));
		assertEquals( "One", result1.get(2));
		assertEquals( "Seven", result1.get(3));
		assertEquals( "Six", result1.get(4));
		assertEquals( "Three", result1.get(5));
		assertEquals( "Two", result1.get(6));
		
		assertFalse( bst2.isEmpty());
		ArrayList<Double> result2 = bst2.inOrderTraversal();
		for ( int i = 0; i< 10; i++) {
			double temp = result2.get(i);
			assertTrue( temp >= 0.0 && temp < 100.0 );
		}
		assertEquals( "12 25 37 50 63 75 80 100 112 125 137 150 163 175 180", bst3.inOrder());
		
	}

	@Test
	public void testArrayConstructor() {
		Integer[] items = { 10, 45, 7, -3, 12, 33, -9, 13, 17, 54, 77, 2, -14 };
		BinarySearchTree<Integer>  copy = new BinarySearchTree<Integer>( items );
		assertEquals( "-14 -9 -3 2 7 10 12 13 17 33 45 54 77", copy.inOrder());
	}
	
	@Test
	public void testBreadthFirstTraversal() {
		ArrayList<Integer> result = bst.breadthFirstTraversal();
		for (int i=1; i<20; i++) {
			assertEquals(i, result.get(i-1) );
		}
		
		ArrayList<String> result1 = bst1.breadthFirstTraversal();
		assertEquals( "One", result1.get(0));
		assertEquals( "Four", result1.get(1));
		assertEquals( "Two", result1.get(2));
		assertEquals( "Five", result1.get(3));
		assertEquals( "Three", result1.get(4));
		assertEquals( "Six", result1.get(5));
		assertEquals( "Seven", result1.get(6));
		
		result = bst3.breadthFirstTraversal();
		Object[] expected = {100, 50, 150, 25, 75, 125, 175, 12, 37, 63, 80, 112, 137, 163, 180};
		assertArrayEquals( expected, result.toArray() );	
	}
	
	@Test
	public void testMaxLeaf() {
		
		assertEquals( 19, bst.maxLeaf() );
		assertEquals( "Seven", bst1.maxLeaf() );
		
	}

}
