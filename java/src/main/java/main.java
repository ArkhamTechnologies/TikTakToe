import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Board b = new Board(true);
        RandomPolicy r = new RandomPolicy();

        Scanner scan = new Scanner(System.in);

        int result = 0;

        int round = 0;

        System.out.println("TicTacToe\n");

        System.out.println(
                "Inputs:\n" +
                        "exit \t- Exit program\n" +
                        "0-8 \t- Location of cross you want to put\n"
        );

        while(result < 1 && round < 9) {

            if(round % 2 == 0) {
                System.out.print("Put next cross at: ");
                String s = scan.next().toLowerCase();

                if(s.equals("exit")) {
                    break;
                }

                int playerAction = Integer.parseInt(s);
                result = b.add(playerAction);

                if(result < 0) {
                    System.out.println("Invalid action!");
                    break;
                }

                r.addAction(playerAction);
                round++;
            } else {
                int newAction = r.getAction();
                result = b.add(newAction);
                round++;
            }
        }

        if(result == 1) {
            System.out.println("You won!");
        } else if(result == 2) {
            System.out.println("Computer won!");
        } else if(result == 0){
            System.out.println("Draw!");
        }

        System.out.println("Game ended!");


    }
}
