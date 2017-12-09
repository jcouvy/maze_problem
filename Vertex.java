import java.util.ArrayList;

public class Vertex {

    private Position pos;
    private int id;
    private ArrayList<Edge> neighbours;

    public Vertex(Position p, int i)
    {
        pos = p;
        id = i;
        neighbours = new ArrayList<Edge>();
    }

    public int getId() {
        return id;
    }

    public Position getPos()
    {
        return pos;
    }

    public ArrayList<Edge> getNeighbours()
    {
        return neighbours;
    }

    public String toString()
    {
        return "Vertex: "+id;
    }

    /* Adds an edge to the neighbours ArrayList. Returns True if a change occured
    False otherwise.
     */
    public boolean addEdge(Edge e)
    {
        return neighbours.add(e);
    }

}