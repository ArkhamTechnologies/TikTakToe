import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Board b = new Board(true);
        RandomPolicy r = new RandomPolicy();

        Scanner scan = new Scanner(System.in);

        GameStates result = GameStates.ONGOING;

        System.out.println("TicTacToe\n");

        System.out.println(
                "Inputs:\n" +
                        "exit \t- Exit program\n" +
                        "0-8 \t- Location of cross you want to put\n"
        );

        while(result.equals(GameStates.ONGOING)) {

            if(b.getRound() % 2 == 0) {
                System.out.print("Put next cross at: ");
                String s = scan.next().toLowerCase();

                if(s.equals("exit")) {
                    break;
                }

                int playerAction = Integer.parseInt(s);
                result = b.add(playerAction);

                while(result.equals(GameStates.INVALID)) {
                    System.out.println("Invalid action! - Try again");

                    System.out.print("Put next cross at: ");
                    s = scan.next().toLowerCase();

                    if(s.equals("exit")) {
                        break;
                    }

                    playerAction = Integer.parseInt(s);
                    result = b.add(playerAction);
                }

                double prob = new DynamicRandom(b).calculateExpectedCrossWinRate(playerAction);

                System.out.println(prob);

                r.addAction(playerAction);
            } else {
                int newAction = r.getAction();
                result = b.add(newAction);
            }
        }

        if(result.equals(GameStates.CROSS_WIN)) {
            System.out.println("You won!");
        } else if(result.equals(GameStates.CIRCLE_WIN)) {
            System.out.println("Computer won!");
        } else if(result.equals(GameStates.DRAW)){
            System.out.println("Draw!");
        }

        System.out.println("Game ended!");


    }
}
