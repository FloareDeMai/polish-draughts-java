import java.util.Scanner;

public class Pawn {
    private Coordinates position;
    private int x;
    private int y;
    private boolean isWhiteTurn;

    public Pawn(Coordinates position, boolean isWhiteTurn) {
        this.position = new Coordinates(x, y);
        this.isWhiteTurn = isWhiteTurn;
    }


    public Coordinates getPosition() {
        return position;
    }


    public boolean getIsWhite() {
        return false;
    }


    public boolean getPlayer() {
        return isWhiteTurn;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static String getCoordinatesOfPawn(Pawn[][] board, boolean isWhiteTurn, Scanner scanner) {
        String coordinates = "";
        while (true) {
            System.out.println("Enter the coordinates for the pawn you want to move");
            coordinates = scanner.nextLine();

            if (coordinates.equals("0")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            int currentX = 0;
            int currentY = 0;
            if (coordinates.substring(1).length() >= 1 && coordinates.substring(1).length() <= 2 && coordinates.substring(1).matches("\\d+")) {
                currentY = parseYMoveAndCurrentY(coordinates);
                currentX = parseXMoveAndCurrentX(coordinates);
            } else {
                System.out.println(coordinates + " is not valid");
                continue;
            }

            if (((currentX < board.length && currentX >= 0) && (currentY < board.length && currentY >= 0))
                    && ((board[currentY][currentX] != null && board[currentY][currentX].getPlayer() == isWhiteTurn))) {
                return coordinates;
            } else {
                System.out.println(coordinates + " is not a valid move");
            }

        }
    }


    public static String getCoordinatesForMove(Pawn[][] board, String coordPawn, Scanner scanner) {
        String coordinates = "";

        while (true) {
            System.out.println("Where do you want to move the pawn?");
            coordinates = scanner.nextLine();

            if (coordinates.equals("0")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            if (coordinates.substring(1).length() >= 1 && coordinates.substring(1).length() <= 2 && coordinates.substring(1).matches("\\d+")) {
                int nextY = parseYMoveAndCurrentY(coordinates);
                int nextX = parseXMoveAndCurrentX(coordinates);

                int currentX = parseXMoveAndCurrentX(coordPawn);
                int currentY = parseYMoveAndCurrentY(coordPawn);

                if ((nextX < board.length && nextX >= 0) && (nextY < board.length && nextY >= 0) && board[nextY][nextX] == null) {

                    if ((currentX + 2 == nextX && currentY + 2 == nextY)
                            || (currentX - 2 == nextX && currentY + 2 == nextY) || (currentY - 2 == nextY && currentX - 2 == nextX)
                            || (currentY - 2 == nextY && currentX + 2 == nextX)) {

                        return coordinates;
                    } else {
                        System.out.println(coordinates + " is not a valid coordinate");
                    }

                } else {
                    System.out.println(coordinates + " is not a valid coordinate");
                }
            } else {
                System.out.println(coordinates + " is not a valid coordinate");
            }

        }
    }


    public static void movePawn(Pawn[][] board, String coordMove, String coordPawn, boolean isWhiteTurn) {
        //for move
        int xMove = parseXMoveAndCurrentX(coordMove);
        int yMove = parseYMoveAndCurrentY(coordMove);


        //for current position
        int currentX = parseXMoveAndCurrentX(coordPawn);
        int currentY = parseYMoveAndCurrentY(coordPawn);


        Pawn selectedPawn = new Pawn(new Coordinates(currentX, currentY), isWhiteTurn);


        board[currentY][currentX] = null;
        board[yMove][xMove] = selectedPawn;


        //check la dreapta sus
        if (currentY - 2 == yMove && currentX + 2 == xMove) {
            if (board[currentY - 1][currentX + 1] == null) {
                board[currentY - 1][currentX + 1] = null;
            } else if (board[currentY - 1][currentX + 1].getPlayer() != isWhiteTurn && board[currentY - 1][currentX + 1] != null) {
                board[currentY - 1][currentX + 1] = null;
            }
        }

        //check la dreapta
        if (currentX + 2 == xMove && currentY + 2 == yMove) {
            //check daca e pion intre
            if (board[currentY + 1][currentX + 1] == null) {
                board[currentY + 1][currentX + 1] = null;
            } else if (board[currentY + 1][currentX + 1].getPlayer() != isWhiteTurn && board[currentY + 1][currentX + 1] != null) {
                board[currentY + 1][currentX + 1] = null;
            }
        }
        //check la stanga
        if (currentX - 2 == xMove && currentY + 2 == yMove) {
            //check daca e pion intre
            if (board[currentY + 1][currentX - 1] == null) {
                board[currentY + 1][currentX - 1] = null;
            } else if (board[currentY + 1][currentX - 1].getPlayer() != isWhiteTurn && board[currentY + 1][currentX - 1] != null) {
                board[currentY + 1][currentX - 1] = null;
            }
        }
        //check la stanga sus
        if (currentY - 2 == yMove && currentX - 2 == xMove) {
            //check daca e pion intre
            if (board[currentY - 1][currentX - 1] == null) {
                board[currentY - 1][currentX - 1] = null;
            } else if (board[currentY - 1][currentX - 1].getPlayer() != isWhiteTurn && board[currentY - 1][currentX - 1] != null) {
                board[currentY - 1][currentX - 1] = null;
            }
        }
    }


    public static int parseXMoveAndCurrentX(String coordinates) {
        return Integer.parseInt(String.valueOf(Character.toUpperCase(coordinates.charAt(0)) - 65));
    }


    public static int parseYMoveAndCurrentY(String coordinates) {
        return Integer.parseInt(coordinates.substring(1)) - 1;
    }


}