import java.util.*;

/* Driver program for 2-person game containing black and white player. Run time arguments to the program are 5 in
number and they are:
size of the board, -h or -c for first player (-h indicates human player and –c indicates computer program as the player),
name of first player, -h or -c for second player, name of second player.
*/
public class Driver {
    /* the following line represents 5 seconds per move. This time variable is not used in the code, except for extra
   credit. It can be used by players to determine the depth of the tree they wish to explore. */
    public static void main(String[] args) {

        final int MAX_TIME_PER_MOVE = 5;
        //Scanner sc = new Scanner(System.in);
        Game gameBoard = new Game();
        BlackPlayer blackPlayer = null;
        WhitePlayer whitePlayer = null;

        if (args[0].equals("-c")) {
            blackPlayer = new BlackPlayer(args[1]);
        }
        if (args[2].equals("-c")) {
            whitePlayer = new WhitePlayer(args[3]);
        }

        Move currentMove;
        boolean done = false;
        String turn = "white";
        gameBoard.printBoard();
        while (!done) {
            if (turn.equals("black")) {
                if (blackPlayer != null) {
                    currentMove = blackPlayer.getMove();
                } else {
                    currentMove = getHumanMove(args[2]);
                }
            } else if (whitePlayer != null) {
                currentMove = whitePlayer.getMove();
            } else {
                currentMove = getHumanMove(args[4]);
            }
            //currentMove.printMove();

            if (gameBoard.isIllegalMove(currentMove) || (gameBoard.isGameOver())) {
                done = true;
                // FILL CODE HERE TO PRINT WHO MADE ILLEGAL MOVE AND WHY OR IF THE GAME IS
                // OVER WHO THE WINNER IS OR IF TIME IS EXCEEDED EXPLAIN WHO THE WINNER IS
            } else {
                gameBoard.update(currentMove, turn);
                gameBoard.printBoard();
                //sc.nextLine();
                if (turn.equals("black")) {
                    if (whitePlayer != null) { whitePlayer.update(currentMove); }
                    turn = "white";
                } else {
                    if (blackPlayer != null) { blackPlayer.update(currentMove); }
                    turn = "black";
                }
            }
        } // end while
    } // end main

    public static Move getHumanMove(String name) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello " + name + "\nProvide x1 y1 x2 y2 for choosing (x1,y1) and (x2, y2)");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        return new Move(x1, y1, x2, y2);
    }
} // end class
