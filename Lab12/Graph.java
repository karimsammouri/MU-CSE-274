import java.util.List;

public interface Graph {
	/**
	 * Adds a vertex to this graph, associating object with vertex.
	 * returns true if the vertex was added, and false otherwise
	 */
	public boolean addVertex(String vertexName);

	/**
	 * Removes a single vertex with the given value from this graph.
	 * returns true if the vertex was removed, and false otherwise
	 */
	public boolean removeVertex(String vertexName);

	/**
	 * Inserts an edge between two pre-existing different vertices of this graph.
 	 * returns true if the edge was added, and false otherwise
	 */
	public boolean addEdge(String vertexFromName, String vertexToName);

	/**
	 * Removes an edge between two pre-existing vertices of this graph.
	 * returns true if the edge was removed, and false otherwise.
	 */
	public boolean removeEdge(String vertexFromName, String vertexToName);

	/**
	 * 
	 * @param vertexName
	 * @return the Vertex that has the name vertexName; 
	 * returns null if there is no Vertex with that name
	 */
	public Vertex getVertex( String vertexName);
	
	/**
	 * Returns true if the specified vertex exists, and false otherwise
	 */
	public boolean hasVertex(String vertexName);

	/**
	 * Returns true if an edge exists between the specified vertices, and false
	 * otherwise
	 */
	public boolean hasEdge(String vertexFromName, String vertexToName);

	/** Returns true if this graph is empty (no vertices), false otherwise. */
	public boolean isEmpty();

	/**
	 * Returns all the vertices connected by an edge to the vertex specified.
	 * 
	 * @throws IllegalArgumentException if the vertex does not exist
	 */
	public List<String> neighbors(String vertexName);

	/**
	 * Makes the graph complete by connecting each vertex to all other vertices in the graph.
	 */
	public void makeComplete();

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @returns the number of vertices
	 */
	public int vertexCount();
	

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @returns the number of vertices
	 */
	public int edgeCount();

	/** Returns a string representation of the graph. */
	public String toString();
}