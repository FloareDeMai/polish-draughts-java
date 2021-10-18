import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" +
                " +-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+\n" +
                " |P|o|l|i|s|h| |D|r|a|u|g|h|t|s|\n" +
                " +-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+\n");
        System.out.println("Menu");
        System.out.println("You can choose between");
        System.out.println("(1) Start game");
        System.out.println("(0) Quit game");
        String chose = scanner.nextLine();

        if (chose.equals("0")) {
            System.exit(0);
        }
        Game game = new Game();
        if ("1".equals(chose)) {
            game.startGame();
        }


    }
}
