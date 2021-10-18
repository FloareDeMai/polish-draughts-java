public class Board {
    private int n;
    private Pawn[][] fields;

    public Pawn[][] initBoard(int n) {
        this.n = n;
        this.fields = new Pawn[n][n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(((x+y) % 2 == 0 && x==0) || ((x+y) %2 == 0 && x==1)) {
                    fields[x][y] = new Pawn(new Coordinates(x, y), true);
                }
                else if (((x+y)%2 == 0 && x==n-2) || ((x+y)%2 == 0 && x==n-1)) {
                    fields[x][y] = new Pawn(new Coordinates(x, y), false);
                }
                else
                    fields[x][y] = null;
            }
        }
        return fields;
    }

    public void printBoard(Pawn[][] board) {
        int rowNumber = 0;
        System.out.print("     ");
        for(int i = 0; i < board.length; ++i)
            System.out.print((char)(i + 'A') + "   ");
        System.out.println();
        for (Pawn[] pawns : board) {
            System.out.print(++rowNumber + ((rowNumber <= 9) ? ". | " : ".| "));
            for (int j = 0; j < board.length; j++) {
                if (pawns[j] == null){
                    System.out.print("·");
                }
                else
                    System.out.print(pawns[j].getPlayer() ? "\u25FC":"\u25FB");
                System.out.print(" | ");

            }
            System.out.println();

        }
    }




}
