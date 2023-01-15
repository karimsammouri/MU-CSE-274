import java.util.Set;
import java.util.TreeSet;


public class Vertex implements Comparable<Vertex>{

	private String name;
	private Set<Vertex> adjacentVertices;
	private boolean visited;

	public Vertex(String name) {
		this.name = name;
		this.adjacentVertices = new TreeSet<Vertex>();
		this.visited = false;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Vertex> getAdjacentVertices() {
		return adjacentVertices;
	}
	
	public boolean getVisited() {
		return visited;
	}

	// Two vertices will be considered equal if their name is the same
	// (ignoring whether the neighbors are the same).
	// Helpful so that contains works the way we want.
	public boolean equals(Object other) {
		if (!(other instanceof Vertex))
			return false;

		return ((Vertex) other).name.equals(this.name);
	}

	// This allows vertices to be compared based solely on the name
	// (ignoring the neighbors)
	@Override
	public int compareTo(Vertex otherVertex) {
		return this.name.compareTo(otherVertex.name);
	}

	public String toString() {
		String result = this.name + ": [";

		for (Vertex v : adjacentVertices) {
			result += v.name + " ";
		}

		result = result.trim();
		result += "]";
		return result;
	}

}