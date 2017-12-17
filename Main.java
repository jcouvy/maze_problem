import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("maze_input.txt");
        Scanner inputFile = new Scanner(file);

        int row = inputFile.nextInt();
        int col = inputFile.nextInt();
        char[][] maze = new char[row][col];
        inputFile.nextLine();

        // Fill the Maze
        for (int i =0; i<row;i++) {
            String words = inputFile.nextLine();
            int size = Math.min(words.length(),col);
            for (int j=0; j<size; j++)
                maze[i][j]=words.charAt(j);
        }
        int stationPos = inputFile.nextInt();
        int exitPos = inputFile.nextInt();

        // Vertex Station
        Position posStation = new Position(stationPos/col,stationPos%col);
        Vertex verStation = new Vertex(posStation,stationPos);

        // Vertex Exit
        Position posExit = new Position(exitPos/col,exitPos%col);
        Vertex verExit = new Vertex(posExit,exitPos);


        // Graph Creation
        Graph g = new Graph(maze);
        //System.out.println(g.getVertices());

        // File Diagram
        String tmp;
        ArrayList<String> val = new ArrayList<>();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output/diagram.txt"), "utf-8"))) {

            for (Vertex v : g.getVertices().values()) {
                for (Edge e : v.getNeighbours()) {
                    val.add("("+e.getEnd().getId()+","+e.getWeight()+")");
                }
                tmp = val.toString();
                val.clear();
                writer.write(v.getId()+tmp+"\n");
            }
        } catch (IOException e) {}

        // File Shortest Path
        Dijkstra d = new Dijkstra();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output/shortest_path.txt"), "utf-8"))) {
            d.calculate(g.getVertices().get(stationPos));
            writer.write("Shortest Path: " + d.getShortestPathTo(g.getVertices().get(exitPos))+"\n");
            writer.write("length :" +g.getVertices().get(exitPos).getDistance());
        } catch (IOException e) {}


    }
}