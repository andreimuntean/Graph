/**
 * Represents the interface of a graph edge.
 *
 * @author Andrei Muntean
 */
interface Edge
{
    /**
     * Gets the vertices that constitute the edge.
     *
     * @return The vertices that constitute the edge.
     */
    Vertex[] getVertices();

    /**
     * Determines whether the edge is directed.
     */
    boolean isDirected();

    /**
     * Determines whether the specified vertex is contained by the edge. 
     *
     * @param vertex A vertex.
     *
     * @return True if the specified vertex is contained by the edge.
     */
    boolean contains(Vertex vertex);

    /**
     * Determines whether this edge is equal to another edge.
     *
     * @param edge A specified edge.
     *
     * @return True if the edges are equal.
     */
    boolean equals(Edge edge);
}