public class Position {

    private final int x;
    private final int y;

    public Position(int i, int j)
    {
        x = i;
        y = j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object v)
    {
        boolean retVal = false;
        if (v instanceof Position) {
            Position p = (Position) v;
            retVal = (getX() == p.getX() && getY() == p.getY());
        }
        return retVal;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}