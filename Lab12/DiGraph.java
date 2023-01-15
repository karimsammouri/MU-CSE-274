import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DiGraph implements Graph {

	private Set<Vertex> vertices;
	private int edgeCount;

	// Constructs an empty graph
	public DiGraph() {
		edgeCount = 0;
		vertices = new TreeSet<Vertex>();
	}

	@Override
	public boolean addVertex(String vertexName) {
		if (vertexName == null) {
			return false;
		}
		return vertices.add(new Vertex(vertexName));
	}

	@Override
	public boolean removeVertex(String vertexName) {
		if (vertexName == null) {
			return false;
		}
		return vertices.remove(new Vertex(vertexName));
	}

	@Override
	public boolean addEdge(String vertexFromName, String vertexToName) {
		Vertex fromVertex = getVertex(vertexFromName);
		Vertex toVertex = getVertex(vertexToName);
		if (fromVertex == null || toVertex == null
				|| fromVertex.equals(toVertex)) {
			return false;
		}
		if (fromVertex.getAdjacentVertices().contains(toVertex)) {
			return false;
		}
		edgeCount++;
		return fromVertex.getAdjacentVertices().add(toVertex);
	}

	@Override
	public boolean removeEdge(String vertexFromName, String vertexToName) {
		Vertex fromVertex = getVertex(vertexFromName);
		Vertex toVertex = getVertex(vertexToName);
		if (fromVertex == null || toVertex == null) {
			return false;
		}
		if (!fromVertex.getAdjacentVertices().contains(toVertex)) {
			return false;
		}
		edgeCount--;
		return fromVertex.getAdjacentVertices().remove(toVertex);
	}

	@Override
	public Vertex getVertex(String vertexName) {
		if (vertexName == null) {
			return null;
		}
		for (Vertex vertex : vertices) {
			if (vertex.getName().equals(vertexName)) {
				return vertex;
			}
		}
		return null;
	}

	@Override
	public boolean hasVertex(String vertexName) {
		if (vertexName == null) {
			return false;
		}
		return vertices.contains(new Vertex(vertexName));
	}

	@Override
	public boolean hasEdge(String vertexFromName, String vertexToName) {
		Vertex fromVertex = getVertex(vertexFromName);
		Vertex toVertex = getVertex(vertexToName);
		if (fromVertex == null || toVertex == null
				|| fromVertex.equals(toVertex)) {
			return false;
		}
		return fromVertex.getAdjacentVertices().contains(toVertex);
	}

	@Override
	public boolean isEmpty() {
		return vertices.size() == 0;
	}

	@Override
	public List<String> neighbors(String vertexName) {
		List<String> result = new ArrayList<String>();
		Vertex vertex = getVertex(vertexName);
		if (vertex == null) {
			throw new IllegalArgumentException();
		}
		for (Vertex v : vertex.getAdjacentVertices()) {
			result.add(v.getName());
		}
		return result;
	}

	@Override
	public void makeComplete() {
		for (Vertex vertex : vertices) {
			for (Vertex vertexNeighbor : vertices) {
				addEdge(vertex.getName(), vertexNeighbor.getName());
			}
		}
	}

	@Override
	public int vertexCount() {
		return vertices.size();
	}

	@Override
	public int edgeCount() {
		return edgeCount;
	}

	public String toString() {
		String result = "";

		for (Vertex vertex : vertices) {
			result += vertex.toString() + " ";
		}

		return result.trim();
	}
}
