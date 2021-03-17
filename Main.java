//Breadth First Search Algorithm
package com.LoafBurger;

import java.util.*;
import java.util.LinkedList;

public class Main {
    //Just a few global variables
    public static int pathCount;
    public static int x1;
    public static int y1;
    public static int x2;
    public static int y2;

    public static void main(String[] args) {

        introPhrase();  //A quick introduction(format following claned)

        Scanner scannerIntroduction = new Scanner(System.in);
        System.out.println("Do you want to play normally(normal) or activate cheats(cheats)?");
        String response = scannerIntroduction.nextLine();
        try{
            if(response.equalsIgnoreCase("normal")){
                MazeRunner myRunner = new MazeRunner();
                Scanner inputS = new Scanner(System.in); //creates the scanner
                myRunner.intro(); //calls the introduction method
                myRunner.game(inputS); //calls the game method
                System.out.println("Congratulations, you made it out alive!"); //prints a congratulatory message
            } else if(response.equalsIgnoreCase("cheats")){

            }
        } catch (Exception e){
            System.out.println("Please input the correct information :D");
        }


        //The basic outline of the grid that the algorithm is going to use
        //Grid is currently kind of scuffed because the x goes down and the y goes right
        //Old grid
        /*int[][] matrix1 = new int[][] {
                 //0,1
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

         */

        //New grid that fits the rubric
        char[][] matrix1 = new char[][]{
                   //0,1
                {'.', '.', '.', '.','.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.', '.',0, '.','.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.',0, '.','.','.','.',0,'.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.',0,'.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.',0,'.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.',0,'.',0,'.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', 0,'.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.',0,'.','.','.','.','.','.','.','.','.'},
                {'.','.', '.', '.', '.','.','.', '.', '.','.','.','.','.','.','.','.','.','.','.','.'},
        };


        intro();
        //These are just so it can update the grid once the coordinates have been chosen.
        matrix1[x1][y1] = 'x';
        matrix1[x2][y2] = 'x';
        printGrid(matrix1);

        //For this program, the 'x' is the row down and 'y' is the column right. Both x and y start at 0.
        int[] start = {x1,y1};
        int[] end = {x2,y2};
        System.out.println("This is a path you can take:");
        gridCreation(matrix1, start, end);
        System.out.println((pathCount-1) + " step(s)");
        System.out.println("Note: current coordinate system starts at 0,0 , where x is vertical and y is horizontal");
        System.exit(0);
    }

    //Variables that will help manage the coordinates
    private static class Cell implements Comparable<Cell> {
        int x;
        int y;
        int distance;
        Cell previous;

        Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.distance = dist;
            this.previous = prev;
        }

        //Given methods that I had to override
        @Override
        public int compareTo(Cell o) {
            return distance - o.distance;
        }

        @Override
        public String toString(){
            return "("+x+ ","+y+")";
        }
    }

        //Giving the option to use cheat codes with the default coordinates or customized coordinates that the user will choose
        public static void intro(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want the default(d) coordinates or do you want to choose the points yourself(c)?");
            String dOC = scanner.nextLine();
            if(dOC.equalsIgnoreCase("d")){
                x1 = 1;
                y1 = 0;
                x2 = 19;
                y2 = 19;
            } else{
                System.out.println("Starting Point X:");
                int inputx1 = scanner.nextInt();
                x1 = inputx1;
                System.out.println("Starting Point Y:");
                int inputy1 = scanner.nextInt();
                y1 = inputy1;
                System.out.println("Ending Point X:");
                int inputx2 = scanner.nextInt();
                x2 = inputx2;
                System.out.println("Ending Point Y:");
                int inputy2 = scanner.nextInt();
                y2 = inputy2;
            }

        }

    //Time O(n^2), Space O(n^2) //Breadth First Search Algorithm
    public static void gridCreation(char[][] grid, int[] start, int[] end) {
        if (grid[start[0]][start[1]] ==0 || grid[end[0]][end[1]] ==0)
            return;
        Cell[][] cells = new Cell[grid.length][grid[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (grid[i][j] != 0) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
                }
            }
        }


        LinkedList<Cell> queue = new LinkedList<>();
        Cell src = cells[start[0]][start[1]];
        src.distance = 0;
        queue.add(src);
        Cell dest = null;
        Cell pos;
        while ((pos = queue.poll()) != null) {
            if (pos.x==end[0] && pos.y == end[1]) {
                dest = pos;
            }
            check(cells, queue, pos.x - 1, pos.y, pos);
            check(cells, queue, pos.x + 1, pos.y, pos);
            check(cells, queue, pos.x, pos.y - 1, pos);
            check(cells, queue, pos.x, pos.y + 1, pos);
        }

        if (dest == null) {
            return;
        } else {
            LinkedList<Cell> path = new LinkedList<>();
            pos = dest;
            do {
                path.addFirst(pos);
                pathCount++;
            } while ((pos = pos.previous) != null);
            System.out.println(path);
        }
    }

    public static void check(Cell[][] cells, LinkedList<Cell> queue, int x, int y, Cell parent) {
        int dist = parent.distance + 1;
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        Cell pos = cells[x][y];
        if (dist < pos.distance) {
            pos.distance = dist;
            pos.previous = parent;
            queue.add(pos);

        }
    }

    //Just a simple print method for the user to visualize the grid
    public static void printGrid (char[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void introPhrase(){
        System.out.println("Hello! Welcome to Maze Runner! :D");
    }

}
