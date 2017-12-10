import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        HashMap<Integer, Vertex> vertexMap =new HashMap<>();

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

        // Fill the Map
        vertexMap.put(stationPos,verStation);
        vertexMap.put(exitPos,verExit);

        // Graph Creation
        Graph g = new Graph(vertexMap,maze);
        System.out.println(g.getVertices());
        System.out.println(vertexMap);

        // Check is vertex

        Position postest = new Position(0,5);
        System.out.println(g.isAtBorder(postest));
        /*for(int i = 0; i<row;i++){
            for(int j=0;j<col;j++){
                postest = new Position(i,j);
                if(g.isVertex(postest)){
                    System.out.println(postest.toString());
                }
            }
        }*/


    }
}