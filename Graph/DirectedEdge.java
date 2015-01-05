/**
 * Represents a graph edge that is directed.
 *
 * @author Andrei Muntean
 */
public class DirectedEdge implements Edge
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
    public DirectedEdge(Vertex origin, Vertex destination)
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
     * Gets the vertices that constitute the edge.
     *
     * @return The vertices that constitute the edge.
     */
    public Vertex[] getVertices()
    {
        return new Vertex[] { origin, destination };
    }

    /**
     * Determines whether the edge is directed.
     */
    public boolean isDirected()
    {
        return true;
    }

    /**
     * Determines whether the specified vertex is contained by the edge. 
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
     * Determines whether this edge has the same vertices as a specified edge.
     *
     * @param edge A specified edge.
     *
     * @return True if the edges have the same vertices.
     */
    public boolean equals(Edge edge)
    {
        if (edge instanceof DirectedEdge)
        {
            DirectedEdge directedEdge = (DirectedEdge)edge;

            return directedEdge.getOrigin().equals(origin) && directedEdge.getDestination().equals(destination);
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "(" + origin + ", " + destination + ")";
    }
}