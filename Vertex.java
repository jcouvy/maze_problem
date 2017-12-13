import java.util.ArrayList;

public class Vertex  implements Comparable<Vertex> {

    private Position pos;
    private int id;
    private ArrayList<Edge> neighbours;
    private boolean visited;
    private Vertex predecessor;
    private double distance = Double.MAX_VALUE;

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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }




    public String toString()
    {
        return "Vertex ["+id+"]" + " " + pos.toString();
    }

    /* Adds an edge to the neighbours ArrayList. Returns True if a change occured
    False otherwise.
     */
    public boolean addEdge(Edge e)
    {
        return neighbours.add(e);
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(this.distance, o.getDistance());
    }
}