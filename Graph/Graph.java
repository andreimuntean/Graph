import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a graph.
 *
 * @author Andrei Muntean
 */
public class Graph
{
	// A set of vertices.
	private ArrayList<Vertex> vertices;

	// A set of edges.
	private ArrayList<Edge> edges;

	/**
	 * Constructs a graph.
	 */
	public Graph()
	{
		// Initializes the list of vertices and the list of edges.
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	/**
	 * Constructs a graph from an array of vertices.
	 *
	 * @param vertices An array of vertices.
	 */
	public Graph(Vertex[] vertices)
	{
		// Initializes the list of edges.
		edges = new ArrayList<Edge>();

		// Initializes the list of vertices.
		this.vertices = new ArrayList<Vertex>();

		// Stores the vertices.
		for (Vertex vertex : vertices)
		{
			add(vertex);
		}
	}

	/**
	 * Constructs a graph from an array of edges.
	 *
	 * @param edges An array of edges.
	 */
	public Graph(Edge[] edges)
	{
		// Initializes the list of vertices.
		vertices = new ArrayList<Vertex>();

		// Initializes the list of edges.
		this.edges = new ArrayList<Edge>();

		// Stores the edges.
		for (Edge edge : edges)
		{
			add(edge);
		}
	}

	/**
	 * Constructs a graph from a list of edges read from a specified path.
	 *
	 * @param path A file path.
	 */
	public Graph(String path) throws FileNotFoundException
	{
		readFromFile(path);
	}

	/**
	 * Gets the number of vertices in the graph.
	 *
	 * @return The number of vertices in the graph.
	 */
	public int countVertices()
	{
		return vertices.size();
	}

	/**
	 * Gets the number of edges in the graph.
	 *
	 * @return The number of edges in the graph.
	 */
	public int countEdges()
	{
		return edges.size();
	}

	/**
	 * Gets the vertex with the specified index.
	 *
	 * @return The vertex with the specified index.
	 * @exception ArrayIndexOutOfBoundsException Specified index is out of bounds.
	 */
	public Vertex getVertex(int index) throws ArrayIndexOutOfBoundsException
	{
		return vertices.get(index);
	}

	/**
	 * Gets the edge with the specified index.
	 *
	 * @return The edge with the specified index.
	 * @exception ArrayIndexOutOfBoundsException Specified index is out of bounds.
	 */
	public Edge getEdge(int index) throws ArrayIndexOutOfBoundsException
	{
		return edges.get(index);
	}

	/**
	 * Gets the vertex with the specified id.
	 *
	 * @return The vertex with the specified id.
	 */
	public Vertex getVertexWithId(int id)
	{
		for (Vertex vertex : vertices)
		{
			if (vertex.getId() == id)
			{
				return vertex;
			}
		}

		return null;
	}

	/**
	 * Adds the specified edge. Does not store duplicates.
	 *
	 * @param edge An edge.
	 */
	public void add(Edge edge)
	{
		for (Edge storedEdge : edges)
		{
			if (edge.equals(storedEdge))
			{
				// This edge is already stored. Abort.
				return;
			}
		}

		// Adds the edge.
		edges.add(edge);

		// Updates the vertices.
		// Vertices that are already stored will not be stored again.
		add(edge.getOrigin());
		add(edge.getDestination());
	}

	/**
	 * Adds the specified vertex. Does not store duplicates.
	 *
	 * @param vertex A vertex.
	 */
	public void add(Vertex vertex)
	{
		for (Vertex storedVertex : vertices)
		{
			if (vertex.equals(storedVertex))
			{
				// This vertex is already stored. Abort.
				return;
			}
		}

		// Adds the vertex.
		vertices.add(vertex);
	}

	/**
	 * Removes the edge with the specified index.
	 *
	 * @param index The index of the edge.
	 */
	public void removeEdge(int index)
	{
		edges.remove(index);
	}

	/**
	 * Removes the vertex with the specified index.
	 *
	 * @param index The index of the vertex.
	 */
	public void removeVertex(int index)
	{
		// Gets the vertex with the specified index.
		Vertex vertex = vertices.get(index);

		// Removes all edges associated with this vertex.
		for (int edgeIndex = 0; edgeIndex < edges.size(); ++edgeIndex)
		{
			// Gets the edge with the specified index.
			Edge edge = edges.get(edgeIndex);

			if (edge.contains(vertex))
			{
				edges.remove(edgeIndex);

				// Stops the index from incrementing.
				--edgeIndex;
			}
		}

		// Removes the vertex.
		vertices.remove(index);
	}

	/**
	 * Constructs the graph from a list of edges read from a specified path.
	 *
	 * @param path A file path.
	 */
	public void readFromFile(String path) throws FileNotFoundException
	{
		// Initializes the file scanner.
		Scanner scanner = new Scanner(new File(path));

		// Initializes the list of vertices and the list of edges.
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		// Reads each line sequentially
		while (scanner.hasNext())
		{
			// The id of the origin vertex.
			int originId = scanner.nextInt();

			// The id of the destination vertex.
			int destinationId = scanner.nextInt();

			// Creates the origin and destination vertices and forms the edge.
			Edge edge = new Edge(new Vertex(originId), new Vertex(destinationId));

			// Adds the edge to the list of edges.
			add(edge);
		}
	}

	@Override
	public String toString()
	{
		String graphString = "Vertices:\n";

		for (Vertex vertex : vertices)
		{
			graphString += vertex + " ";
		}

		graphString += "\n\nEdges:\n";

		for (Edge edge : edges)
		{
			graphString += edge + "\n";
		}

		// Replaces the new lines with platform-independent line separators
		// and returns the result.
		return graphString.replace("\n", System.getProperty("line.separator"));
	}
}