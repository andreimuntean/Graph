/**
 * Represents a graph edge that is undirected.
 *
 * @author Andrei Muntean
 */
public class UndirectedEdge implements Edge
{
    // Vertex A.
    private Vertex vertexA;

    // Vertex B.
    private Vertex vertexB;

    /**
     * Constructs an edge from two vertices.
     *
     * @param vertexA Vertex A. One of the two vertices that form the edge.
     * @param vertexB Vertex B. One of the two vertices that form the edge.
     */
    public UndirectedEdge(Vertex vertexA, Vertex vertexB)
    {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
    }

    /**
     * Gets vertex A. This is one of the two vertices that form the edge.
     *
     * @return Vertex A.
     */
    public Vertex getVertexA()
    {
        return vertexA;
    }

    /**
     * Gets vertex B. This is one of the two vertices that form the edge.
     *
     * @return Vertex B.
     */
    public Vertex getVertexB()
    {
        return vertexB;
    }

    /**
     * Gets the vertices that constitute the edge.
     *
     * @return The vertices that constitute the edge.
     */
    public Vertex[] getVertices()
    {
        return new Vertex[] { vertexA, vertexB };
    }

    /**
     * Determines whether the edge is directed.
     */
    public boolean isDirected()
    {
        return false;
    }

    /**
     * Determines whether the specified vertex is contained by the edge. 
     *
     * @param vertex A vertex.
     *
     * @return True if the specified vertex is contained by the edge.
     */
    public boolean contains(Vertex vertex)
    {
        return vertex.equals(vertexA) || vertex.equals(vertexB);
    }

    /**
     * Determines whether this edge is equal to a specified edge.
     *
     * @param edge A specified edge.
     *
     * @return True if the edges are equal.
     */
    public boolean equals(Edge edge)
    {
        if (edge instanceof UndirectedEdge)
        {
            UndirectedEdge undirectedEdge = (UndirectedEdge)edge;

            return undirectedEdge.getVertexA().equals(vertexA) && undirectedEdge.getVertexB().equals(vertexB)
                || undirectedEdge.getVertexA().equals(vertexB) && undirectedEdge.getVertexB().equals(vertexA);
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "[" + vertexA + ", " + vertexB + "]";
    }
}