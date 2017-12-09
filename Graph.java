import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Graph {

   private Map<Integer, Vertex> vertices;
   private char[][] maze;

   public Graph(Map<Integer, Vertex> m, char[][] maze)
   {
       //vertices = m.clone();
       vertices = m.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> new Vertex(e.getValue().getPos(),e.getValue().getId())));
       maze = maze.clone();
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