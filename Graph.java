import java.util.HashMap;

public class Graph {

   private HashMap<Integer, Vertex> vertices;
   private char[][] maze;
   private final char WALL = '#';
   private final char PATH = '.';

   public Graph(HashMap<Integer, Vertex> m, char[][] maze)
   {
       this.vertices = (HashMap) m.clone();
       this.maze = maze.clone();
   }

   public HashMap<Integer, Vertex> getVertices() {
       return vertices;
   }

   /* In the maze problem, a Vertex is a station located
      - At the intersection between two routes
      - At the end of a route (entry or exit)
      Returns whether the Position p on the maze corresponds to
      a Vertex.
    */
   public boolean isVertex(Position p)
   {
       return isAtEnd(p) || isAtIntersection(p);
   }

   /* Checks if a given Position p is an accessible cell
   in the maze (i.e: is a '.')
    */
   public boolean isAccessible(Position p)
   {
       boolean accessible = false;

       try {
          accessible = maze[p.getX()][p.getY()] == PATH;
       } catch (IndexOutOfBoundsException outOfBonds) {};

       return accessible;
   }

    /* Checks if a given Position p is at an intersection of routes.
       An intersection is found if there exists both an horizontal and vertical
       path around the position.
     */
   public boolean isAtIntersection(Position p)
   {
       boolean verticalPath = false;
       boolean horizontalPath = false;
       int posX = p.getX();
       int posY = p.getY();

       try {
           if (isAccessible(new Position(posX-1, posY)) ||
               isAccessible(new Position(posX+1, posY)) verticalPath = true;
           if (isAccessible(new Position(posX, posY-1)) ||
               isAccessible(new Position(posX, posY+1)) horizonPath = true;
       } catch (IndexOutOfBoundsException outOfBonds) {};

       return verticalPath && horizontalPath;
   }

   /* Checks if a Position p is at the beginning/end of a route.
      A beginning/end is found either:
       - if the position is accessible and located at the border of the maze
       - if there are three walls and one accessible way (where we come from)
         surrounding the position.
    */
   public boolean isAtEnd(Position p)
   {
       int surroundingWalls = 0;
       int accessiblePaths = 0;

       if isAtBorder(p) return true;
       else {
           try {
               if (isAccessible(new Position(posX-1, posY)) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX+1, posY)) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX, posY-1)) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX, posY+1)) accessiblePaths++;
               else surroundingWalls++;
           } catch (IndexOutOfBoundsException outOfBonds) {};
       }

       return surroundingWalls == 3 && accessiblePaths == 1;
   }

   /* Checks if a Position is accessible and located at the border of the maze */
   public boolean isAtBorder(Position p)
   {
       boolean border = false;
       int posX = p.getX();
       int posY = p.getY();

       if (posX == 0 || posX == maze.length) {
           if (isAccessible(p)) border = true;
       }
       if (posY == 0 || posY == maze[0].length) {
           if (isAccessible(p)) border = true;
       }

       return border;
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