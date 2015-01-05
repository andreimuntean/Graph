import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;
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
	public Graph(String path) throws Exception, FileNotFoundException
	{
		readFromFile(path);
	}

	/**
	 * Determines if the graph is undirected, mixed or directed.
	 *
	 * @return The type of the graph.
	 */
	public GraphType getType()
	{
		GraphType graphType = GraphType.UNKNOWN;

		for (Edge edge : edges)
		{
			if (edge.isDirected())
			{
				if (graphType == GraphType.UNDIRECTED)
				{
					return GraphType.MIXED;
				}
				else
				{
					graphType = GraphType.DIRECTED;
				}
			}
			else
			{
				if (graphType == GraphType.DIRECTED)
				{
					return GraphType.MIXED;
				}
				else
				{
					graphType = GraphType.UNDIRECTED;
				}
			}
		}

		return graphType;
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
		add(edge.getVertices());
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
	 * Adds the specified edges. Does not store duplicates.
	 *
	 * @param edges The edges.
	 */
	public void add(Edge[] edges)
	{
		for (Edge edge : edges)
		{
			add(edge);
		}
	}

	/**
	 * Adds the specified vertices. Does not store duplicates.
	 *
	 * @param vertices The vertices.
	 */
	public void add(Vertex[] vertices)
	{
		for (Vertex vertex : vertices)
		{
			add(vertex);
		}
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

				// Since the next index became the current index,
				// this stops the index from incrementing.
				--edgeIndex;
			}
		}

		// Removes the vertex.
		vertices.remove(index);
	}

	/**
	 * Constructs the graph from a specified file.
	 *
	 * @param path A file path.
	 */
	public void readFromFile(String path) throws Exception, FileNotFoundException
	{
		// Initializes the file scanner.
		Scanner scanner = new Scanner(new File(path));

		// Initializes the list of vertices and the list of edges.
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		try
		{
			// Reads the vertices and the edges.
			String vertexString = scanner.nextLine();
			String edgeString = scanner.nextLine();

			// Gets the elements of the vertex set.
			vertexString = vertexString.substring(5, vertexString.length() - 1);

			// Goes through every element.
			for (String element : vertexString.split(", "))
			{
				int id = Integer.parseInt(element);

				// Creates a vertex.
				add(new Vertex(id));
			}

			// Gets the elements of the edge set.
			edgeString = edgeString.substring(5, edgeString.length() - 1);

			// Converts the pairs between square brackets into undirected edges.
			for (String pair : StringManipulation.getSubstringsBetween(edgeString, "[", "]"))
			{
				String[] ids = pair.split(", ");

				// The id of the first vertex.
				int firstId = Integer.parseInt(ids[0]);

				// The id of the second vertex.
				int secondId = Integer.parseInt(ids[1]);

				// Creates the two vertices, forms the edge and adds it to the list of edges.
				add(new UndirectedEdge(new Vertex(firstId), new Vertex(secondId)));
			}

			// Converts the pairs between round brackets into directed edges.
			for (String pair : StringManipulation.getSubstringsBetween(edgeString, "(", ")"))
			{
				String[] ids = pair.split(", ");

				// The id of the origin vertex.
				int originId = Integer.parseInt(ids[0]);

				// The id of the target vertex.
				int targetId = Integer.parseInt(ids[1]);

				// Creates the two vertices, forms the edge and adds it to the list of edges.
				add(new DirectedEdge(new Vertex(originId), new Vertex(targetId)));
			}
		}
		catch (Exception exception)
		{
			throw new Exception("\"" + path + "\" is not a graph file.");
		}
	}

	/**
	 * Writes the graph to a specified file.
	 *
	 * @param path A file path.
	 */
	public void writeToFile(String path) throws FileNotFoundException
	{
		try
		{
			PrintWriter writer = new PrintWriter(path, "UTF-8");

			writer.print(toString());
			writer.close();
		}
		catch (UnsupportedEncodingException exception)
		{
			// This should never happen.
			System.out.println(exception);
		}
	}

	@Override
	public String toString()
	{
		// Transforms the vertices into a string.
		String graphString = "V = {";

		for (int index = 0; index < vertices.size(); ++index)
		{
			graphString += vertices.get(index);

			// Adds a comma if this vertex is not the last one in the list.
			if (index < vertices.size() - 1)
			{
				graphString += ", ";
			}
		}

		// Transforms the edges into a string.
		graphString += "}\nE = {";

		for (int index = 0; index < edges.size(); ++index)
		{
			graphString += edges.get(index);

			// Adds a comma if this edge is not the last one in the list.
			if (index < edges.size() - 1)
			{
				graphString += ", ";
			}
		}

		// Replaces the new lines with platform-independent line separators
		// and returns the result.
		return graphString.replace("\n", System.getProperty("line.separator")) + "}";
	}
}