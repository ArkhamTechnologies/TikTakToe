public class Board {

    private final static int N_FIELDS = 3;

    private final int[] board;

    private int round;
    private final boolean verbose;

    public Board(boolean verbose) {
        this.board = new int[N_FIELDS * N_FIELDS];
        this.round = 0;
        this.verbose = verbose;
    }

    public Board() {
        this.board = new int[N_FIELDS * N_FIELDS];
        this.round = 0;
        this.verbose = false;
    }

    public int add(int i, int j) {

        if(this.board[i * N_FIELDS + j] > 0) {
            return -1;
        }


        int val = round % 2 + 1;
        round++;

        this.board[i * N_FIELDS + j] = val;

        if(verbose) {
            System.out.println(toString());
        }

        return hasTermintated();
    }

    public int hasTermintated() {

        if(round < 5) return 0;

        for(int i = 0; i < N_FIELDS; i++) {
            int val = board[N_FIELDS * i];
            int j = 1;
            while(val > 0 && j < N_FIELDS && val == board[N_FIELDS * i + j]) {
                j++;
            }

            if(j == N_FIELDS) return val;

            val = board[i];
            j = 1;
            while(val > 0 && j < N_FIELDS && val == board[N_FIELDS * j + i]) {
                j++;
            }

            if(j == N_FIELDS) return val;
        }

        int val = board[0];
        int j = 1;
        while(val > 0 && j < N_FIELDS && val == board[N_FIELDS * j + j]) {
            j++;
        }

        if(j == N_FIELDS) return val;

        val = board[N_FIELDS - 1];
        j = 1;
        while(val > 0 && j < N_FIELDS && val == board[N_FIELDS * j + N_FIELDS - 1 - j]) {
            j++;
        }

        if(j == N_FIELDS) return val;


        return 0;
    }

    public int getNFields() {
        return N_FIELDS;
    }

    public int getState() {

        int state = 0;
        int multiplier = 1;

        for(int i = 0; i < N_FIELDS; i++) {
            for(int j = 0; j < N_FIELDS; j++) {
                state += multiplier * board[i * N_FIELDS + j];
            }
            multiplier *= 3;

        }

        return state;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N_FIELDS; i++) {
            for(int j = 0; j < N_FIELDS; j++) {
                char symbol = 0;
                int val = board[i * N_FIELDS + j];
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