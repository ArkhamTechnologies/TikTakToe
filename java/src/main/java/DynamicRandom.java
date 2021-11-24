public class DynamicRandom {

    private final Board board;

    public DynamicRandom(Board board) {
        this.board = board.deepcopy();
    }

    public double calculateExpectedCrossWinRate(int move) {

        GameStates result = board.add(move);

        if(result.equals(GameStates.CROSS_WIN)) {
            return 1.0;
        } else if(result.equals(GameStates.CIRCLE_WIN)) {
            return 0.0;
        } else if(result.equals(GameStates.DRAW)) {
            return 0.5;
        } else {
            double sum = 0.0;
            int count = 0;

            for(int i = 0; i < 9; i++) {
                if(!board.isInvalidMove(i)) {
                    DynamicRandom dynamicRandom = new DynamicRandom(board);
                    sum += dynamicRandom.calculateExpectedCrossWinRate(i);
                    count++;
                }
            }

            return count > 0 ? sum / count : 0;
        }
    }
}
