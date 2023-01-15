/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Nov 14, 2020
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class GraphAlgorithms {

	PriorityQueue<PathState> pq; // Used for Dijkstra's shortest path algorithm
	Queue<Vertex> queue; // use for BFS
	Stack<Vertex> stack; // used for DFS and for Topological Sort
	Set<String> visited; // names of the visited vertices

	public GraphAlgorithms() {
		pq = new PriorityQueue<PathState>();
	}

	public String findShortestPath(Graph graph, String startVertexName,
			String endVertexName) {
		Vertex startVertex = graph.getVertex(startVertexName);
		Vertex endVertex = graph.getVertex(endVertexName);
		visited = new HashSet<String>();
		if (startVertex == null || endVertex == null) {
			return "path not found";
		}
		pq.add(new PathState(startVertex, 0, startVertex.getName() + " "));
		while (!pq.isEmpty()) {
			PathState nextEntry = pq.remove();
			if (nextEntry.vertex.equals(endVertex)) {
				return "Shortest Path " + nextEntry.pathToThisVertex
						+ "\nTotal weight: " + nextEntry.totalPathWt;
			}
			visited.add(nextEntry.vertex.getName());
			Map<String, Integer> neighbors = nextEntry.vertex
					.getAdjacentVerticesWeighted();
			for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
				if (!visited.contains(entry.getKey())) {
					pq.add(new PathState(graph.getVertex(entry.getKey()),
							nextEntry.totalPathWt + entry.getValue(),
							nextEntry.pathToThisVertex + entry.getKey() + " "));
				}
			}
		}
		return "path not found";
	}

	public String breadthFirstTraversal(Graph graph, String startVertexName) {
		String result = "";
		queue = new ArrayDeque<Vertex>();
		visited = new TreeSet<String>();
		Vertex startVertex = graph.getVertex(startVertexName);
		if (startVertex == null) {
			return "path not found";
		}
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			Vertex currentVertex = queue.remove();
			if (!visited.contains(currentVertex.getName())) {
				result += currentVertex.getName() + " ";
				visited.add(currentVertex.getName());
				for (String vertexName : currentVertex.getAdjacentVertices()) {
					if (!visited.contains(vertexName)) {
						queue.add(graph.getVertex(vertexName));
					}
				}
			}
		}
		return result.trim();
	}

	public String depthFirstTraversal(Graph graph, String startVertexName) {
		String result = "";
		stack = new Stack<Vertex>();
		visited = new TreeSet<String>();
		Vertex startVertex = graph.getVertex(startVertexName);
		if (startVertex == null) {
			return "path not found";
		}
		stack.push(startVertex);
		result = startVertexName + " ";
		visited.add(startVertex.getName());
		while (!stack.isEmpty()) {
			Vertex currentVertex = stack.peek();
			boolean flag = false;
			for (String neighbor : currentVertex.getAdjacentVertices()) {
				if (!visited.contains(neighbor)) {
					stack.push(graph.getVertex(neighbor));
					result += neighbor + " ";
					visited.add(neighbor);
					flag = true;
					break;
				}
			}
			if (!flag) {
				stack.pop();
			}
		}
		return result.trim();
	}

	public String topologicalSort(Graph graph) {
		stack = new Stack<Vertex>();
		visited = new TreeSet<String>();
		int numVertices = graph.vertexCount();
		for (int i = 0; i < numVertices; i++) {
			for (Vertex ver : graph.getVertices()) {
				if (!hasUnvisitedDescendants(graph, ver)
						&& !visited.contains(ver.getName())) {
					stack.push(ver);
					visited.add(ver.getName());
					break;
				}
			}
		}
		String result = "";
		while (!stack.isEmpty()) {
			result += stack.pop().getName() + " ";
		}
		return result.trim();
	}

	private boolean hasUnvisitedDescendants(Graph graph, Vertex vertex) {
		for (String ver : vertex.getAdjacentVertices()) {
			if (!visited.contains(ver)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		Graph graph = new Graph("graphData3.csv");
		GraphAlgorithms graphAlgorithms = new GraphAlgorithms();

		System.out.println(graphAlgorithms.findShortestPath(graph, "CVG", "LAX"));
		System.out.println(graphAlgorithms.findShortestPath(graph, "CVG", "DEN"));
		System.out.println(graphAlgorithms.findShortestPath(graph, "ATL", "DEN"));
		System.out.println(graphAlgorithms.breadthFirstTraversal(graph, "CVG"));
		System.out.println(graphAlgorithms.depthFirstTraversal(graph, "CVG"));
		System.out.println(graphAlgorithms.topologicalSort(graph));

	}

	public class PathState implements Comparable {
		public Vertex vertex;
		public int totalPathWt;
		public String pathToThisVertex;

		public PathState(Vertex v, int wt, String path) {
			vertex = v;
			totalPathWt = wt;
			pathToThisVertex = path;
		}

		@Override
		public int compareTo(Object other) {
			if (this.totalPathWt < ((PathState) other).totalPathWt)
				return -1;
			else if (this.totalPathWt > ((PathState) other).totalPathWt)
				return 1;
			else
				return 0;

		}
	}
}
