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
           if (maze[p.getX()][p.getY()] == PATH)
               accessible = true;
       } catch (IndexOutOfBoundsException outOfBonds) {};

       return accessible;
   }

    /* Checks if a given Position p is at an intersection of routes.
       An intersection is found if there exists at least 3 differents
       routes we can take (where we come from, and two others).
     */
   public boolean isAtIntersection(Position p)
   {
       int accessiblePaths = 0;
       int posX = p.getX();
       int posY = p.getY();

       // If the position is a Wall, we directly return false.
       if (!isAccessible(p)) return false;

       try {
           if (isAccessible(new Position(posX-1, posY))) accessiblePaths++;
           if (isAccessible(new Position(posX+1, posY))) accessiblePaths++;
           if (isAccessible(new Position(posX, posY-1))) accessiblePaths++;
           if (isAccessible(new Position(posX, posY+1))) accessiblePaths++;
       } catch (IndexOutOfBoundsException outOfBonds) {};

       return accessiblePaths == 3;
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
       int posX = p.getX();
       int posY = p.getY();

       // If the position is a Wall, we directly return false.
       if (!isAccessible(p)) return false;
       if (isAtBorder(p)) return true;
       // Check if there is a path on the left, right, above, and below
       else if (!isAtBorder(p)) {
           try {
               if (isAccessible(new Position(posX-1, posY))) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX+1, posY))) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX, posY-1))) accessiblePaths++;
               else surroundingWalls++;
               if (isAccessible(new Position(posX, posY+1))) accessiblePaths++;
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

       // check border left and right
       if (posX == 0 || posX == maze.length-1) {
           border = true;
       }
       // check border up and down
       if (posY == 0 || posY == maze[0].length-1) {
           border = true;
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

    public static void main(String[] args)
    {
        System.out.println("Test Maze:");
        char[][] testMaze = {
                {'#', '.', '#', '#'},
                {'#', '.', '#', '#'},
                {'.', '.', '#', '#'},
                {'#', '.', '#', '#'},
                {'#', '#', '#', '#'}
        };

        HashMap<Integer, Vertex> map = new HashMap<>();
        Graph graph = new Graph(map, testMaze);

        for (int i = 0 ; i<testMaze.length ; ++i) {
            for (int j = 0; j < testMaze[0].length; ++j) {
                System.out.print(testMaze[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nExpected vertices: ");
        int[] expectedVertices = {1, 8, 9, 13};
        char name = 'A';
        for (int v : expectedVertices) {
            System.out.println(name + "[" + v + "]");
            name++;
        }

        System.out.println("Found vertices:");
        name = 'A';
        for (int i = 0 ; i<testMaze.length ; ++i) {
            for (int j = 0; j < testMaze[0].length; ++j) {
                Position p = new Position(i, j);
                if (graph.isVertex(p)) {
                    System.out.println(name + "[" + graph.getIdFromPosition(p) + "]");
                    name++;
                }
            }
        }
    }
}