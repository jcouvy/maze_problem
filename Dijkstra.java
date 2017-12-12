import java.util.HashMap;

public class Dijkstra{

    public static void main(String[] args) {
        System.out.println("Test Maze:");
        char[][] testMaze = {
                {'#', '.', '#', '#'},
                {'#', '.', '#', '#'},
                {'.', '.', '.', '#'},
                {'#', '.', '#', '#'},
                {'#', '.', '.', '#'},
                {'#', '#', '#', '#'}
        };

        Position postest;
        Vertex vertex;
        HashMap<Integer, Vertex> map = new HashMap<>();
        Graph graph = new Graph(map, testMaze);

        // Display maze
        for (int i = 0 ; i<testMaze.length ; ++i) {
            for (int j = 0; j < testMaze[0].length; ++j) {
                System.out.print(testMaze[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------- POS + ID ----------------");
        // Show all vertices in the maze
        for (int i = 0 ; i<testMaze.length ; ++i) {
            for (int j = 0; j < testMaze[0].length; ++j) {
                postest = new Position(i,j);
                if(graph.isVertex(postest)){
                    System.out.println(postest.toString() + " with id : "+ graph.getIdFromPosition(postest));
                    vertex = new Vertex(postest,graph.getIdFromPosition(postest));
                    map.put(graph.getIdFromPosition(postest),vertex);
                }
            }
        }
        System.out.println();
        System.out.println("--------- Display Hashmap ----------------");
        System.out.println(map);

        System.out.println();
        System.out.println("--------- Path ? ----------------");
        vertex = map.get(10);
        System.out.println("We begin by this vertex : "+ vertex);
        graph.findNeighbours(vertex);
        System.out.println(vertex.getNeighbours());






    }
}