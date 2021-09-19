public class Board {

    private final int[] board;

    private int round;
    private final boolean verbose;

    public Board(boolean verbose) {
        this.board = new int[Constants.N_FIELDS * Constants.N_FIELDS];
        this.round = 0;
        this.verbose = verbose;
    }

    public Board() {
        this.board = new int[Constants.N_FIELDS * Constants.N_FIELDS];
        this.round = 0;
        this.verbose = false;
    }

    public int add(int ij) {
        return add(ij / Constants.N_FIELDS, ij % Constants.N_FIELDS);
    }

    public int add(int i, int j) {

        if(this.board[i * Constants.N_FIELDS + j] > 0) {
            return -1;
        }


        int val = round % 2 + 1;
        round++;

        this.board[i * Constants.N_FIELDS + j] = val;

        if(verbose) {
            System.out.println(toString());
        }

        return hasTermintated();
    }

    public int hasTermintated() {

        if(round < 5) return 0;

        for(int i = 0; i < Constants.N_FIELDS; i++) {
            int val = board[Constants.N_FIELDS * i];
            int j = 1;
            while(val > 0 && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * i + j]) {
                j++;
            }

            if(j == Constants.N_FIELDS) return val;

            val = board[i];
            j = 1;
            while(val > 0 && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + i]) {
                j++;
            }

            if(j == Constants.N_FIELDS) return val;
        }

        int val = board[0];
        int j = 1;
        while(val > 0 && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + j]) {
            j++;
        }

        if(j == Constants.N_FIELDS) return val;

        val = board[Constants.N_FIELDS - 1];
        j = 1;
        while(val > 0 && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + Constants.N_FIELDS - 1 - j]) {
            j++;
        }

        if(j == Constants.N_FIELDS) return val;


        return 0;
    }

    public int getNFields() {
        return Constants.N_FIELDS;
    }

    public int getState() {

        int state = 0;
        int multiplier = 1;

        for(int i = 0; i < Constants.N_FIELDS; i++) {
            for(int j = 0; j < Constants.N_FIELDS; j++) {
                state += multiplier * board[i * Constants.N_FIELDS + j];
            }
            multiplier *= 3;

        }

        return state;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < Constants.N_FIELDS; i++) {
            for(int j = 0; j < Constants.N_FIELDS; j++) {
                char symbol = 0;
                int val = board[i * Constants.N_FIELDS + j];
                if(val == 1) {
                    symbol = 'x';
                } else if(val == 2) {
                    symbol = 'o';
                }
                sb.append(symbol);
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


}
