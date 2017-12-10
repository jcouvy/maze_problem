import java.util.HashMap;


public class Graph {

    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    private HashMap<Integer, Vertex> vertices;
   private char[][] maze;

   public Graph(HashMap<Integer, Vertex> m, char[][] maze)
   {
       //vertices = m.clone();
       this.vertices = (HashMap)m.clone();
       this.maze = maze.clone();
   }

   public boolean isVertex(Position p)
   {
       //...
       return false;
   }

   public boolean existsEdgeBetween(Vertex v1, Vertex v2)
   {
       //...
       return false;
   }

   public int getIdFromPosition(Position p)
   {
       return p.getX() * maze[0].length + p.getY();
   }

   public int calcWeight(Vertex v1, Vertex v2)
   {
       Position p1 = v1.getPos();
       Position p2 = v2.getPos();
       return 0;
   }

   public void findNeighbours(Position initialPos)
   {
       if (!existsPathFrom(initialPos))
            return;
        else {
           if (isVertex(initialPos)) {
               int id = getIdFromPosition(initialPos);
               Vertex v = new Vertex(initialPos, id);
               /*if (!existsEdgeBetween()) {
                   int weight = calcWeight()
                   Edge e = new Edge(, , weight);
               }*/
           }
       }
   }

    /* Checks wether it is possible to move from a given Vertex v
    * This means there is a clear way either UP, DOWN, LEFT, or RIGHT.
    */
   private boolean existsPathFrom(Position p)
   {
       int posX = p.getX();
       int posY = p.getY();
       boolean result = false;
       /*
       try {
           result = maze[posX][posY] == '.' || maze[posX][posY] == '.' ||
                    maze[posX][posY] == '.' || maze[posX][posY] == '.');
       }catch(IndexOutOfBoundsException e());*/
       return result;
   }
}