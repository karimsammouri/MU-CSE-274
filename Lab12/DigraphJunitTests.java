import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DigraphJunitTests {
	
	@Test
	public void testConstructor() {
		DiGraph graph = new DiGraph();
		
		assertTrue( graph.isEmpty());
		assertEquals( 0, graph.vertexCount());
		assertEquals( 0, graph.edgeCount());
	}
	
	@Test
	public void testAddVertex() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		
		assertFalse( graph.isEmpty());
		assertEquals( 25, graph.vertexCount());
		assertEquals( 0, graph.edgeCount());
		
		// check that duplicates are not allowed
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		assertFalse( graph.isEmpty());
		assertEquals( 25, graph.vertexCount());
		assertEquals( 0, graph.edgeCount());
	}
		
	@Test
	public void testVertexCount() {
		DiGraph graph = new DiGraph();
		assertEquals( 0, graph.vertexCount());
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
			assertEquals( i+1, graph.vertexCount());
		}
	}
	
	@Test
	public void testHasVertex() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		
		for ( int i = 0; i < 25; i++) {
			assertTrue( graph.hasVertex(Integer.toString(i) ));
		}
		
		assertFalse( graph.hasVertex("25"));
		

	
	}
		
	@Test
	public void testGetVertex() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		Vertex vertex;
		for ( int i = 0; i < 25; i++) {
			vertex = graph.getVertex(Integer.toString(i));
			assertEquals(Integer.toString(i), vertex.getName());
		}		
		vertex = graph.getVertex("25");  // There is no node with name "25";
		assertEquals( null,vertex );
		
		vertex = graph.getVertex("");  // There is no node with name ""
		assertEquals( null,vertex );
	}
	
	@Test
	public void testRemoveVertex() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		assertEquals( 25, graph.vertexCount());
		assertFalse(graph.removeVertex("25" ));
		for ( int i = 0; i < 25; i++) {
			assertTrue(graph.removeVertex( Integer.toString(i)));
			assertEquals( null, graph.getVertex(Integer.toString(i)) );
			assertEquals( 25 - (i+1), graph.vertexCount());
		}
		assertEquals( 0, graph.vertexCount());
		
		assertFalse(graph.removeVertex("25" ));
		
		graph.addVertex("One");
		graph.addVertex("Two");
		graph.addVertex("Three");
		graph.addVertex("Four");
		graph.addVertex("Five");
		
		assertEquals( 5, graph.vertexCount());
		assertTrue(graph.removeVertex( "Three"));
		assertEquals( null, graph.getVertex("Three") );
		assertEquals( 4, graph.vertexCount());
		
		assertTrue(graph.removeVertex( "One"));
		assertEquals( null, graph.getVertex("One") );
		assertEquals( 3, graph.vertexCount());
		
		assertTrue(graph.removeVertex( "Five"));
		assertEquals( null, graph.getVertex("Five") );
		assertEquals( 2, graph.vertexCount());
		
		assertTrue(graph.removeVertex( "Two"));
		assertEquals( null, graph.getVertex("Two") );
		assertEquals( 1, graph.vertexCount());
		
		assertTrue(graph.removeVertex( "Four"));
		assertEquals( null, graph.getVertex("Four") );
		assertEquals( 0, graph.vertexCount());
		
		assertTrue( graph.isEmpty());
	}
	
	@Test
	public void testAddEdge() {
		DiGraph graph = new DiGraph();
		graph.addVertex("One");
		graph.addVertex("Two");
		graph.addVertex("Three");
		graph.addVertex("Four");
		graph.addVertex("Five");
		
		assertEquals( 5, graph.vertexCount());
		assertEquals( 0, graph.edgeCount());
		graph.addEdge("One", "Two");
		assertEquals( 1, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Two] Three: [] Two: []", graph.toString());
		graph.addEdge("One", "Three");
		graph.addEdge("One", "Four");
		graph.addEdge("One", "Five");
		
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		
		graph.addEdge("One", "One");  // no self edges allowed
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		graph.addEdge("One", "Two");  // no duplicate edges allowed
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		graph.addEdge("Two", "Three");
		graph.addEdge("Two", "Four");
		graph.addEdge("Three", "One");
		graph.addEdge("Four", "One");
		graph.addEdge("Four", "Three");
		graph.addEdge("Five", "One");
		graph.addEdge("Five", "Three");
		graph.addEdge("Three", "Two");
		graph.addEdge("Three", "Four");	
		
		assertEquals( 13, graph.edgeCount());
		assertEquals( "Five: [One Three] Four: [One Three] One: [Five Four Three Two] Three: [Four One Two] Two: [Four Three]", 
				graph.toString());
		
		graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		
		for ( int i = 0; i < 25; i++) {
			for ( int j=0; j < 25; j++) {
				if ( i != j) graph.addEdge(Integer.toString(i), Integer.toString(j));
			}
		}
		assertEquals( 25*24, graph.edgeCount());
		
	}
	
	@Test
	public void testRemoveEdge() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		
		for ( int i = 0; i < 25; i++) {
			for ( int j=0; j < 25; j++) {
				if ( i != j) graph.addEdge(Integer.toString(i), Integer.toString(j));
			}
		}
		assertEquals( 25*24, graph.edgeCount());
		
		int countRemoved = 0;
		for ( int i = 0; i < 25; i++) {
			for ( int j=0; j < 25; j++) {
				if ( i != j) {
					countRemoved++;
					graph.removeEdge(Integer.toString(i), Integer.toString(j));
					assertEquals( 25*24 - countRemoved, graph.edgeCount());
				}
			}
		}
		assertEquals( 0, graph.edgeCount());
	}
	
	@Test
	public void testHasEdge() {
		DiGraph graph = new DiGraph();
		for ( int i = 0; i < 25; i++) {
			graph.addVertex( Integer.toString(i));
		}
		
		for ( int i = 0; i < 25; i++) {
			for ( int j=0; j < 25; j++) {
				if ( i != j) { 
					graph.addEdge(Integer.toString(i), Integer.toString(j));
					assertTrue( graph.hasEdge(Integer.toString(i), Integer.toString(j)));
				}
			}
		}
		
		for ( int i = 0; i < 25; i++) {
			for ( int j=0; j < 25; j++) {
				if ( i != j) {
					assertTrue( graph.hasEdge(Integer.toString(i), Integer.toString(j)));
					graph.removeEdge(Integer.toString(i), Integer.toString(j));
					assertFalse( graph.hasEdge(Integer.toString(i), Integer.toString(j)));
				}
				
			}
		}
		assertEquals( 0, graph.edgeCount());
	}
	
	@Test
	public void testNeighbors() {
		DiGraph graph = new DiGraph();
		graph.addVertex("One");
		graph.addVertex("Two");
		graph.addVertex("Three");
		graph.addVertex("Four");
		graph.addVertex("Five");
		
		assertEquals( 5, graph.vertexCount());
		assertEquals( 0, graph.edgeCount());
		graph.addEdge("One", "Two");
		assertEquals( 1, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Two] Three: [] Two: []", graph.toString());
		graph.addEdge("One", "Three");
		graph.addEdge("One", "Four");
		graph.addEdge("One", "Five");
		
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		
		graph.addEdge("One", "One");  // no self edges allowed
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		graph.addEdge("One", "Two");  // no duplicate edges allowed
		assertEquals( 4, graph.edgeCount());
		assertEquals( "Five: [] Four: [] One: [Five Four Three Two] Three: [] Two: []", 
				graph.toString());
		
		graph.addEdge("Two", "Three");
		graph.addEdge("Two", "Four");
		graph.addEdge("Three", "One");
		graph.addEdge("Four", "One");
		graph.addEdge("Four", "Three");
		graph.addEdge("Five", "One");
		graph.addEdge("Five", "Three");
		graph.addEdge("Three", "Two");
		graph.addEdge("Three", "Four");	
		
		assertEquals("[Five, Four, Three, Two]", graph.neighbors("One").toString());
		assertEquals("[Four, Three]", graph.neighbors("Two").toString());
		assertEquals("[Four, One, Two]", graph.neighbors("Three").toString());
		assertEquals("[One, Three]", graph.neighbors("Four").toString());
		assertEquals("[One, Three]", graph.neighbors("Five").toString());
	}
	
	@Test
	public void testComplete() {
		DiGraph graph = new DiGraph();
		graph.addVertex("One");
		graph.addVertex("Two");
		graph.addVertex("Three");
		graph.addVertex("Four");
		graph.addVertex("Five");
		graph.makeComplete();
		
		assertEquals( 20, graph.edgeCount());
		assertEquals("[Five, Four, Three, Two]", graph.neighbors("One").toString());
		assertEquals("[Five, Four, One, Three]", graph.neighbors("Two").toString());
		assertEquals("[Five, Four, One, Two]", graph.neighbors("Three").toString());
		assertEquals("[Five, One, Three, Two]", graph.neighbors("Four").toString());
		assertEquals("[Four, One, Three, Two]", graph.neighbors("Five").toString());
	}

}
