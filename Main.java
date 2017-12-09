import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("maze_input.txt");
        Scanner inputFile = new Scanner(file);

        int row = inputFile.nextInt();
        int col = inputFile.nextInt();
        char[][] maze = new char[row][col];
        inputFile.nextLine();
        //fill the maze
        for (int i =0; i<row;i++) {
            String words = inputFile.nextLine();
            int size = Math.min(words.length(),col);
            for (int j=0; j<size; j++)
                maze[i][j]=words.charAt(j);
        }
        int stationpos = inputFile.nextInt();
        int exitpos = inputFile.nextInt();





    }
}