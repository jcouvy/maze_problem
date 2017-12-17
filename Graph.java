import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

   private HashMap<Integer, Vertex> vertices;
   private char[][] maze;
   private final char WALL = '#';
   private final char PATH = '.';

   public Graph(char[][] maze)
   {
       this.vertices = new HashMap<Integer, Vertex>();
       this.maze = maze.clone();

       for (int i=0 ; i<maze.length ; ++i) {
           for(int j=0 ; j<maze[0].length ; ++j) {
               Position p = new Position(i, j);
               if (isVertex(p)) {
                   int id = getIdFromPosition(p);
                   Vertex v = new Vertex(p, id);
                   vertices.put(id, v);
               }
           }
       }

       for (Vertex v1 : vertices.values()) {
           findNeighbours(v1);
       }
   }

   public HashMap<Integer, Vertex> getVertices() {
       return vertices;
   }

    /* Returns the ID of a Vertex from a given Position
    according to the following formulae:
    VertexID(V) = position(i, j) = i * col + j;
     */
    public int getIdFromPosition(Position p)
    {
        return p.getX() * maze[0].length + p.getY();
    }

    /* Hashmap lookup to search for a Vertex in the graph from a given Position.
    (Each vertex is identified by its ID in the table).
    Returns the vertex if found, null otherwise.
     */
    public Vertex getVertexFromPosition(Position p)
    {
        int vertexId = getIdFromPosition(p);
        return vertices.get(vertexId);
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

       return accessiblePaths >= 3;
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

   /* In the maze problem, we assume that there are no movement
   in diagonal. Two vertices can be either on the same row, or
   the same column. If an edge exists between the two, then the
   weight is equal to the distance (amount of dots) between each
   vertex.

    NB: cannot work if a turn is not considered as an intersection
    */
//   public int calcWeight(Vertex v1, Vertex v2)
//   {
//       Position p1 = v1.getPos();
//       Position p2 = v2.getPos();
//
//       if (p1.getX() == p2.getX())
//           return Math.abs(p1.getY() - p2.getY());
//       else if (p1.getY() == p2.getY())
//           return Math.abs(p1.getX() - p2.getX());
//       else {
//           int translation = Math.abs(p1.getX() - p2.getX());
//           return Math.abs(translation - p2.getY());
//       }
//   }

   /*
   Returns true if an edge is between the two given Vertices v1 and v2
    */
   public boolean existsEdgeBetween(Vertex v1, Vertex v2)
   {
       return v1.getNeighbours().contains(v2);
   }

   /* Searches for all surrounding vertices around a given Vertex.
   One iteration is constituted of 3 operations:
   1. We inspect the 4 surrounding positions Up, Down, Left, and Right.
   If the position is accessible and hasn't been visited already, it is enqueued into a next-moves linked list.

   2. The next move is popped from the list and we inspect the position.
   The total cost of the current path (i.e: the edge) is incremented everytime we try a new position.

   3. If it is a Vertex then it is a neighbour: we create two edges as the graph is
   bi-directional. The cost of the path is reset to 0 and we start another iteration.

   The hasFoundNeighbour flag prevents to break out of the while loop if only one path is accessible
   and no vertex can be found directly

   Loop back and repeat until the next moves list is empty.
    */
   public void findNeighbours(Vertex v1) {
       LinkedList<Position> nextMoves = new LinkedList<Position>();
       LinkedList<Position> visited = new LinkedList<Position>();

       Position currentPos = v1.getPos();
       Position initialPos = v1.getPos();
       Position[] possibleMoves = new Position[4];

       int cost = 0;
       int posX, posY;
       boolean hasFoundNeighbour;

       nextMoves.add(currentPos);
       do {

           visited.add(currentPos);
           hasFoundNeighbour = false;

           if (isVertex(currentPos) && currentPos != initialPos) {
               hasFoundNeighbour = true;
               Vertex v2 = getVertexFromPosition(currentPos);
               if (!existsEdgeBetween(v1, v2))
                   v1.addEdge(new Edge(v1, v2, cost));
               try {
                   currentPos = nextMoves.removeLast();
                   cost = 1;
               } catch (Exception e) { break; }
               continue;
           }

           posX = currentPos.getX();
           posY = currentPos.getY();

           possibleMoves[0] = new Position(posX-1, posY); // Up
           possibleMoves[1] = new Position(posX+1, posY); // Down
           possibleMoves[2] = new Position(posX, posY-1); // Left
           possibleMoves[3] = new Position(posX, posY+1); // Right

           for (Position p : possibleMoves) {
               if (isAccessible(p) && !visited.contains(p))
                   nextMoves.add(p);
           }

           try {
               currentPos = nextMoves.removeLast();
               cost++;
           } catch (Exception e) {
               break;
           }

         // Would loop forever if no neighbours are found, however the requirements of the
         // maze are such that no isolated vertex can exist
       } while (!nextMoves.isEmpty() || !hasFoundNeighbour);

   }

   public static void main(String[] args)
   {
       System.out.println("Test Maze:");
       char[][] testMaze = {
               {'#', '.', '#', '#'},
               {'#', '.', '#', '#'},
               {'.', '.', '.', '#'},
               {'#', '.', '#', '#'},
               {'#', '.', '#', '#'}
       };

       Graph graph = new Graph(testMaze);

       for (int i = 0 ; i<testMaze.length ; ++i) {
           for (int j = 0; j < testMaze[0].length; ++j) {
               System.out.print(testMaze[i][j]);
           }
           System.out.println();
       }

       System.out.println("\nExpected vertices: ");
       int[] expectedVertices = {1, 8, 9, 10, 17};
       char name = 'A';
       for (int v : expectedVertices) {
           System.out.println(name + "[" + v + "]");
           name++;
       }

       System.out.println("Found vertices:");
       for (Vertex v : graph.vertices.values()) {
           System.out.println(v);
       }

       System.out.println("\nGet Vertex from Position (0,1): ");
       System.out.println(graph.getVertexFromPosition(new Position(0, 1)));

       System.out.println("\nFinding neighbours of all vertices :");
       for (Vertex v : graph.vertices.values()) {
           System.out.println("Neighbours of " + v);
           for (Edge e : v.getNeighbours()) {
               System.out.println(e.getEnd() + " Cost: " + e.getWeight());
           }
           System.out.println();
       }

   }
}
