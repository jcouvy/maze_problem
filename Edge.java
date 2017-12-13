public class Edge {

    private Vertex start;
    private Vertex end;
    private final int weight;

    public Edge(Vertex s, Vertex e, int w)
    {
        start = s;
        end = e;
        weight = w;
    }

    public Vertex getStart()
    {
        return start;
    }

    public Vertex getEnd()
    {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public String toString()
    {
        return "(" + end.getId() + "," + weight + ")";
    }

}