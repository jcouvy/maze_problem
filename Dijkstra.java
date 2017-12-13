import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra{

    public void calculate(Vertex v){
        int cpt=0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();

        queue.add(v);
        while(!queue.isEmpty()){
            Vertex u = queue.poll();

            for(Edge voisin : u.getNeighbours()){
                int newDist = cpt+ voisin.getWeight();

                // I think we need to put on vertex a value weight also on the vertex
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Test Maze:");
        char[][] testMaze = {
                {'#', '.', '#', '#'},
                {'#', '.', '#', '#'},
                {'#', '.', '.', '#'},
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
        System.out.println("Add Edges (a la mano)");
        //
        map.get(1).addEdge(new Edge(map.get(1),map.get(9),2));
        map.get(9).addEdge(new Edge(map.get(9),map.get(1),2));
        //
        map.get(9).addEdge(new Edge(map.get(9),map.get(10),1));
        map.get(10).addEdge(new Edge(map.get(10),map.get(9),1));
        //
        map.get(9).addEdge(new Edge(map.get(9),map.get(18),3));
        map.get(18).addEdge(new Edge(map.get(18),map.get(9),3));

        System.out.println("1 : "+map.get(1).getNeighbours());
        System.out.println("9 : "+map.get(9).getNeighbours());
        System.out.println("10 : "+map.get(10).getNeighbours());
        System.out.println("18 : "+map.get(18).getNeighbours());

    }
}