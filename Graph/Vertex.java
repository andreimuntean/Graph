/**
 * Represents a graph vertex.
 *
 * @author Andrei Muntean
 */
public class Vertex
{
    // The id of the vertex.
    private int id;

    // The value of the vertex.
    private int value;

    /**
     * Constructs a vertex
     *
     * @param id The id of the vertex.
     */
    public Vertex(int id)
    {
        this.id = id;
    }

    /**
     * Constructs a vertex
     *
     * @param id The id of the vertex.
     * @param value The value of the vertex.
     */
    public Vertex(int id, int value)
    {
        this.id = id;
        this.value = value;
    }

    /**
     * Gets the id of the vertex.
     *
     * @return The id of the vertex.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Gets the value of the vertex.
     *
     * @return The value of the vertex.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Sets the value of the vertex to the specified value.
     *
     * @param value The new value.
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Checks whether the id of this vertex is equal to the id of a specified vertex.
     *
     * @param vertex A specified vertex.
     *
     * @return True if the vertices have the same id.
     */
    public boolean equals(Vertex vertex)
    {
        return id == vertex.id;
    }

    @Override
    public String toString()
    {
        return "" + id;
    }
}