import java.util.*; //import statement

public class MazeRunner {

    //initializes variables
    Maze myMaze; //initializes the maze
    int moves; //initializes the number of moves

    //constructor that sets variables
    MazeRunner() {
        myMaze = new Maze();
        moves = 0; //sets the number of moves to zero
    }

    //main method
    public static void main(String[] args) {
        MazeRunner myRunner = new MazeRunner();
        Scanner inputS = new Scanner(System.in); //creates the scanner
        myRunner.intro(); //calls the introduction method
        myRunner.game(inputS); //calls the game method
        System.out.println("Congratulations, you made it out alive!"); //prints a congratulatory message
    }

    //introduction
    public void intro() {
        System.out.println("Welcome to maze runner!"); //prints a welcome message
        myMaze.printMap(); //prints the current state of the map
    }

    //game
    public void game(Scanner inputS) {
        while(!myMaze.didIWin()) { //while the user has not yet won
            userMove(inputS); //calls the user move method
            //move message function should be called here later
        }
    }

    //the method is private because it does not need to be called outside of the file
    //asks the user to input a move choice, and ensures that the input is either right, left, up or down
    private String getUserMove(Scanner inputS) {
        while (true) {
            //asks the user where they would like to move, and stores the answer in a variable
            String userMoveChoice = getValidStringInput(inputS, "Where would you like to move? (R, L, U, D)");
            // ensures that the user inputs a valid move choice (right, left, up or down)
            //otherwise, the while loop repeats and asks the user to input their move choice again
            if (userMoveChoice.equals("R") ||userMoveChoice.equals("L") || userMoveChoice.equals("U")
                    || userMoveChoice.equals ("D")) {
                return userMoveChoice; //returns the user's move choice
            }
        }
    }

    //ensures that the user's move choice is valid
    private void userMove(Scanner inputS) {
        System.out.println("Move: " + ++moves); //prints out the user's current move count
        String userMoveChoice = getUserMove(inputS); //gets the user's move choice
        System.out.println("You chose to move: " + userMoveChoice); //prints out the user's move choice
        //navigate pit method should be called here later
        if (userMoveChoice.equals("R") && myMaze.canIMoveRight()) { //ensures that the user is able to move right
            myMaze.moveRight(); //moves right
        } else if (userMoveChoice.equals("L") && myMaze.canIMoveLeft()) {
            myMaze.moveLeft();
        } else if (userMoveChoice.equals("U") && myMaze.canIMoveUp()) {
            myMaze.moveUp();
        } else if (userMoveChoice.equals("D") && myMaze.canIMoveDown()) {
            myMaze.moveDown();
        } else {
            System.out.println("You cannot move in that direction because there is a wall, " +
                    "please pick a new direction."); //prints out a message if there is a wall
        }
        myMaze.printMap(); //prints out the maze
    }

    //asks a question (that requires a String input) and then returns the String input
    public static String getValidStringInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        return inputS.nextLine(); //returns the String input
    }
}