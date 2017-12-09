public class Position {

    private final int x;
    private final int y;

    public Position(int i, int j)
    {
        x = i;
        y = j;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}