import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private static final boolean verbose = true;
    @Test
    public void basicTest() {

        Board b = new Board();

        assertEquals("zero state", 0, b.getState());


    }

    @Test
    public void verticalTest() {

        Board b = new Board(verbose);

        int result = b.add(0, 0);
        assertEquals(0, result);
        result = b.add(0, 1);
        assertEquals(0, result);
        result = b.add(1, 0);
        assertEquals(0, result);
        result =b.add(0, 2);
        assertEquals(0, result);
        result = b.add(2, 0);
        assertEquals(1, result);
    }

    @Test
    public void horizontalTest() {

        Board b = new Board(verbose);

        int result = b.add(2, 2);
        assertEquals(0, result);
        result = b.add(1, 0);
        assertEquals(0, result);
        result = b.add(0, 1);
        assertEquals(0, result);
        result =b.add(2, 0);
        assertEquals(0, result);
        result = b.add(0, 2);
        assertEquals(0, result);
        result = b.add(0, 0);
        assertEquals(2, result);
    }

}
