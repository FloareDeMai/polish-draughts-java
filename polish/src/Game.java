import java.util.Scanner;

public class Game {

    public static Scanner scanner = new Scanner(System.in);
    int howMany = lengthOfSquare();
    Board board = new Board();
    Pawn[][] ourBoard = board.initBoard(howMany);


    public void startGame() {
        boolean isWhiteTurn = false;
        while (true) {
            playRound(isWhiteTurn);
            isWhiteTurn = !isWhiteTurn;
            String myCoordinates = Pawn.getCoordinatesOfPawn(ourBoard, isWhiteTurn, scanner);
            String coordinatesForMove = Pawn.getCoordinatesForMove(ourBoard, myCoordinates, scanner);
            Pawn.movePawn(ourBoard, coordinatesForMove, myCoordinates, isWhiteTurn);
            clrScreen();
            if (checkWin(ourBoard).equals("Whites win")) {
                System.out.println("Whites win");
                board.printBoard(ourBoard);
                break;
            } else if (checkWin(ourBoard).equals("Blacks win")) {
                System.out.println("Blacks win");
                board.printBoard(ourBoard);
                break;
            }
        }
    }


    public void playRound(boolean turn) {
        board.printBoard(ourBoard);
        System.out.println();
        System.out.println("----------------------------");
        System.out.println(turn ? "black's turn, " + countBlacks(ourBoard) + " pawns left" : "white's turn, " + countWhites(ourBoard) + " pawns left");
        System.out.println("----------------------------");

    }


    public static int lengthOfSquare() {
        String userInput = "";
        while (true) {
            try {
                System.out.println("Enter a number between 10 an 20 to choose board size: ");
                userInput = scanner.nextLine();

                if (userInput.equals("0")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }

                if (Integer.parseInt(userInput) >= 10 && Integer.parseInt(userInput) <= 20)
                    return Integer.parseInt(userInput);
                System.out.println(userInput + " is not a valid number");
            } catch (Exception e) {
                System.out.println(userInput + " is not a valid number");
            }
        }
    }


    public static int countWhites(Pawn[][] board) {
        int count = 0;
        for (Pawn[] pawns : board) {

            for (int j = 0; j < board.length; j++) {
                if (pawns[j] == null)
                    continue;
                if (pawns[j].getPlayer()) {
                    count++;
                }
            }
        }
        return count;
    }


    public static int countBlacks(Pawn[][] board) {
        int count = 0;
        for (Pawn[] pawns : board) {

            for (int j = 0; j < board.length; j++) {
                if (pawns[j] == null)
                    continue;
                if (!pawns[j].getPlayer()) {
                    count++;
                }
            }
        }
        return count;
    }


    public static String checkWin(Pawn[][] board) {
        int whites = countWhites(board);
        int blacks = countBlacks(board);

        if (blacks == 0)
            return "Whites win";
        else if (whites == 0)
            return "Blacks win";
        else
            return "No win";
    }


    public static void clrScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}