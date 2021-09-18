public class BoardConfiguration {

    private final static int N_FIELDS = 3;

    private final int[] board;

    private boolean lastAddedX;

    public BoardConfiguration() {
        this.board = new int[N_FIELDS * N_FIELDS];
    }

    public int add(int i, int j) {

        int val = 1;
        if(lastAddedX) {
            val = 2;
        }

        this.board[i * N_FIELDS + j] = val;

        lastAddedX ^= true;

        return hasTermintated();
    }

    public int hasTermintated() {

        // check horizontals
        for(int i = 0; i < N_FIELDS; i++) {
            boolean xWon = true;
            boolean oWon = true;

            for(int j = 0; j < N_FIELDS; j++) {
                xWon &= board[N_FIELDS * i + j] == 1;
                oWon &= board[N_FIELDS * i + j] == 2;
            }

            if(xWon) return 1;
            else if(oWon) return 2;

        }

        // check verticals
        for(int i = 0; i < N_FIELDS; i++) {
            boolean xWon = true;
            boolean oWon = true;

            for(int j = 0; j < N_FIELDS; j++) {
                xWon &= board[N_FIELDS * j + i] == 1;
                oWon &= board[N_FIELDS * j + i] == 2;
            }

            if(xWon) return 1;
            else if(oWon) return 2;

        }

        boolean xWon = true;
        boolean oWon = true;

        for(int i = 0; i < N_FIELDS; i++) {
            xWon &= board[N_FIELDS * i + i] == 1;
            oWon &= board[N_FIELDS * i + i] == 2;
        }

        if(xWon) return 1;
        else if(oWon) return 2;

        xWon = true;
        oWon = true;

        for(int i = 0; i < N_FIELDS; i++) {
            xWon &= board[N_FIELDS * i + i] == 1;
            oWon &= board[N_FIELDS * i + i] == 2;
        }

        if(xWon) return 1;
        else if(oWon) return 2;

        xWon = true;
        oWon = true;

        for(int i = 0; i < N_FIELDS; i++) {
            xWon &= board[N_FIELDS * i + N_FIELDS - 1 - i] == 1;
            oWon &= board[N_FIELDS * i + N_FIELDS - 1 - i] == 2;
        }

        if(xWon) return 1;
        else if(oWon) return 2;

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
