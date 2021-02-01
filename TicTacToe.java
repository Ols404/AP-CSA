
import java.util.*;

public class TicTacToe {


    //The following arrays are the things that are going to keep track of the positions of both the player and the computer
    private static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    private static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        //This is the thing that is going to create the board
        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '}};


        printGameBoard(gameBoard);

        //This keeps them in a loop so the players can keep making moves.
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your placement (1 - 9)");
            int playerPos = scanner.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)){
                System.out.println("This position is taken, please enter a correct position");
                playerPos = scanner.nextInt();
            }

            //This takes in the user input and places the symbol and the position that was stated.
            placeDownPiece(gameBoard, playerPos, "player");

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }


            //This takes in the user input and places the symbol and the position that was stated.
            placeDownPiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

           String result =  checkTheWinner();
            System.out.println(result);
        }

    }

    //Nested For-each loop that prints the whole gameBoard
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeDownPiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';

        if(user.equalsIgnoreCase("player")){
            symbol = 'X';
            playerPositions.add((pos));
        } else if(user.equalsIgnoreCase("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkTheWinner(){

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol= Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1= Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningConditions = new ArrayList<List>();


        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for(List l: winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congrats! You win!";
            } else if(cpuPositions.containsAll(l)){
                return "CPU wins! GG Noob";
            } else if(playerPositions.size() + cpuPositions.size() == 9){
                return "This is a tie!";
            }
        }



        return " ";
    }

}
