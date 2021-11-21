import java.util.Arrays;

public class Board {

    private final BoardConstants[] board;

    private int round;
    private final boolean verbose;

    public Board(boolean verbose) {
        this.board = new BoardConstants[Constants.N_FIELDS * Constants.N_FIELDS];
        this.round = 0;
        this.verbose = verbose;

        Arrays.fill(board, BoardConstants.EMPTY);
    }

    public Board() {
        this.board = new BoardConstants[Constants.N_FIELDS * Constants.N_FIELDS];
        this.round = 0;
        this.verbose = false;

        Arrays.fill(board, BoardConstants.EMPTY);

    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public boolean isValidMove(int ij) {
        return !this.board[ij].equals(BoardConstants.EMPTY);
    }


    public GameStates add(int ij) {
        return add(ij / Constants.N_FIELDS, ij % Constants.N_FIELDS);
    }

    public GameStates add(int i, int j) {

        if(isValidMove(i * Constants.N_FIELDS + j)) {
            return GameStates.INVALID;
        }

        round++;

        if(round % 2 == 0) {
            this.board[i * Constants.N_FIELDS + j] = BoardConstants.CIRCLE;
        } else {
            this.board[i * Constants.N_FIELDS + j] = BoardConstants.CROSS;
        }


        if(verbose) {
            System.out.println(toString());
        }

        return hasTermintated();
    }

    public GameStates hasTermintated() {

        if(round < 5) return GameStates.ONGOING;

        if(round > 9) return GameStates.DRAW;

        for(int i = 0; i < Constants.N_FIELDS; i++) {
            BoardConstants val = board[Constants.N_FIELDS * i];
            int j = 1;
            while(!val.equals(BoardConstants.EMPTY) && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * i + j]) {
                j++;
            }

            if(j == Constants.N_FIELDS) return hasWon(val);

            val = board[i];
            j = 1;
            while(!val.equals(BoardConstants.EMPTY) && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + i]) {
                j++;
            }

            if(j == Constants.N_FIELDS) return hasWon(val);
        }

        BoardConstants val = board[0];
        int j = 1;
        while(!val.equals(BoardConstants.EMPTY) && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + j]) {
            j++;
        }

        if(j == Constants.N_FIELDS) return hasWon(val);

        val = board[Constants.N_FIELDS - 1];
        j = 1;
        while(!val.equals(BoardConstants.EMPTY) && j < Constants.N_FIELDS && val == board[Constants.N_FIELDS * j + Constants.N_FIELDS - 1 - j]) {
            j++;
        }

        if(j == Constants.N_FIELDS) return hasWon(val);


        return GameStates.ONGOING;
    }

    public int getNFields() {
        return Constants.N_FIELDS;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < Constants.N_FIELDS; i++) {
            for(int j = 0; j < Constants.N_FIELDS; j++) {
                char symbol = 0;
                BoardConstants val = board[i * Constants.N_FIELDS + j];
                if(val.equals(BoardConstants.CROSS)) {
                    symbol = 'x';
                } else if(val.equals(BoardConstants.CIRCLE)) {
                    symbol = 'o';
                }
                sb.append(symbol);
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public BoardConstants[] getBoard() {
        return this.board;
    }

    public Board deepcopy() {
        Board newBoard = new Board(this.verbose);

        System.arraycopy(this.board, 0, newBoard.getBoard(), 0, this.board.length);

        newBoard.setRound(this.round);

        return newBoard;
    }

    private GameStates hasWon(BoardConstants boardConstants) {
        if(boardConstants.equals(BoardConstants.CIRCLE)) {
            return GameStates.CIRCLE_WIN;
        } else {
            return GameStates.CROSS_WIN;
        }
    }



}
