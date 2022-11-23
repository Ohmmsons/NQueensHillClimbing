/**
 * NQueens Unit Tests
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class NQueensUnitTests {
    @Test
    public void testCountConflicts() {
        int a[] = {0,1,2};
        NQueensBoard board = new NQueensBoard(3, a);
        assertEquals(board.countConflicts(), 2);

        int b[] = {0,2,1};
        board = new NQueensBoard(3, b);
        assertEquals(board.countConflicts(), 1);
        
        int c[] = {1,3,0,2};
        int d[] = {2,0,3,1};
        board= new NQueensBoard(4, c);
        assertEquals(board.countConflicts(), 0);
        board = new NQueensBoard(4, d);
        assertEquals(board.countConflicts(), 0);

        int e[] = {4,1,3,6,2,7,5,0};
        int f[] = {2,5,7,0,3,6,4,1};
        board= new NQueensBoard(8, e);
        assertEquals(board.countConflicts(), 0);
        board = new NQueensBoard(8, f);
        assertEquals(board.countConflicts(), 0);
    }

    @Test
    public void testEmptyColumns() {
        int a[] = {1,4,7,1,4,7,1,7};
        int b[] = {0,2,3,5,6};
        ArrayList<Integer> list = new ArrayList<>() {{ for (int i : b) add(i); }};
        NQueensBoard board = new NQueensBoard(8, a);
        assertEquals(board.emptyColumns(), list);

        int c[] = {0,1,2,3,4,5,6,0};
        int d[] = {7};
        list = new ArrayList<>() {{ for (int i : d) add(i); }};
        board = new NQueensBoard(8, c);
        assertEquals(board.emptyColumns(), list);
    }

    @Test
    public void testUpdateConflicts() {
        int a[] = {1,3,0,0};
        int n = 4;
        NQueensBoard board = new NQueensBoard(n, a);
        assertEquals(board.getObjectiveFunction(), 1); // 1 conflict
        int row = 3; int colold = 0; int colnew = 3;
        NQueensBoard clone = board.clone();
        clone.father = board;
        clone.updateConflicts(colold, colnew, n-row-1+colold, row+colold, n-row-1+colnew, row+colnew);
        assertEquals(clone.getObjectiveFunction(), 1); // 1 conflicts

        int b[] = {1,2,0,0};
        board = new NQueensBoard(n, b);
        assertEquals(board.getObjectiveFunction(), 3); // 3 conflict
        row = 3;
        colnew = 3;
        clone = board.clone();
        clone.father = board;
        clone.updateConflicts(colold, colnew, n-row-1+colold, row+colold, n-row-1+colnew, row+colnew);
        assertEquals(clone.getObjectiveFunction(),1); // 1 conflict
    }
}
