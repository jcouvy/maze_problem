import java.util.*;

public class Dijkstra{

    public  void calculate(Vertex v){
        v.setDistance(0);
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(v);
        v.setVisited(true);

        while(!queue.isEmpty()){
            Vertex atual = queue.poll();

            for(Edge voisin : atual.getNeighbours()){

                Vertex target = voisin.getEnd();
                if(!target.isVisited()){
                    double newDist = atual.getDistance()+voisin.getWeight();
                    if( newDist < target.getDistance() ){
                        queue.remove(target);
                        target.setDistance(newDist);
                        target.setPredecessor(atual);
                        queue.add(target);
                    }
                }

            }
            atual.setVisited(true);
        }

    }

    public  List<Vertex> getShortestPathTo(Vertex targetVertex){
        List<Vertex> path = new ArrayList<>();

        for(Vertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
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

        System.out.println("1 :  "+map.get(1).getNeighbours());
        System.out.println("9 :  "+map.get(9).getNeighbours());
        System.out.println("10 : "+map.get(10).getNeighbours());
        System.out.println("18 : "+map.get(18).getNeighbours());

        System.out.println();
        System.out.println("--------- Test Dijk ----------------");
        // Depend with which one with start
        Dijkstra shortestPath = new Dijkstra();
        shortestPath.calculate(map.get(18));
        System.out.println("Shortest Path 18 to 1: " + shortestPath.getShortestPathTo(map.get(1)));
        System.out.println("length :" +map.get(1).getDistance());

    }
}
