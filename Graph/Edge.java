/**
 * Represents a graph edge.
 *
 * @author Andrei Muntean
 */
public class Edge
{
	// The origin vertex.
	private Vertex origin;

	// The destination vertex.
	private Vertex destination;

	/**
	 * Constructs an edge from two vertices.
	 *
	 * @param origin The origin vertex.
	 * @param destination The destination vertex.
	 */
	public Edge(Vertex origin, Vertex destination)
	{
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * Gets the origin vertex.
	 *
	 * @return The origin vertex.
	 */
	public Vertex getOrigin()
	{
		return origin;
	}

	/**
	 * Gets the destination vertex.
	 *
	 * @return The destination vertex.
	 */
	public Vertex getDestination()
	{
		return destination;
	}

	/**
	 * Checks whether the specified vertex is contained by the edge. 
	 *
	 * @param vertex A vertex.
	 *
	 * @return True if the specified vertex is the origin or the destination.
	 */
	public boolean contains(Vertex vertex)
	{
		return vertex.equals(origin) || vertex.equals(destination);
	}

	/**
	 * Checks whether this edge has the same vertices as a specified edge.
	 *
	 * @param edge A specified edge.
	 *
	 * @return True if the edges have the same vertices.
	 */
	public boolean equals(Edge edge)
	{
		return edge.getOrigin().equals(origin) && edge.getDestination().equals(destination);
	}

	@Override
	public String toString()
	{
		return "{" + origin + ", " + destination + "}";
	}
}