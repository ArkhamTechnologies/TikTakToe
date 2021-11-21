import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    private static final boolean verbose = true;
    @Test
    public void basicTest() {

        Board b = new Board();
    }

    @Test
    public void verticalTest() {

        Board b = new Board(verbose);

        GameStates result = b.add(0, 0);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(0, 1);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(1, 0);
        assertEquals(GameStates.ONGOING, result);
        result =b.add(0, 2);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(2, 0);
        assertEquals(GameStates.CROSS_WIN, result);
    }

    @Test
    public void horizontalTest() {

        Board b = new Board(verbose);

        GameStates result = b.add(2, 2);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(1, 0);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(0, 1);
        assertEquals(GameStates.ONGOING, result);
        result =b.add(2, 0);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(0, 2);
        assertEquals(GameStates.ONGOING, result);
        result = b.add(0, 0);
        assertEquals(GameStates.ONGOING, result);
    }

    @Test
    public void deepcopyTest() {

        Board b = new Board(verbose);

        GameStates result = b.add(2, 2);

        Board newBoard = b.deepcopy();

        assertEquals(false, b == newBoard);

        assertArrayEquals(b.getBoard(), newBoard.getBoard());

        assertEquals(b.getRound(), newBoard.getRound());
    }

}
