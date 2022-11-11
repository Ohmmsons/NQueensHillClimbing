import java.util.*;

class Board implements Ilayout, Cloneable {

    int n;
    boolean[][] board;
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    public Board(int m) throws IllegalStateException { //Simulates a two dimensional board using a String by filling in the empty spaces with " "
        n = m;
        board = new boolean[n][n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
        //CREATE RANDOM BOARD WITH NO QUEENS ON THE SAME AND INITIALIZE COLS LDIAGS AND RDIAGS
    }

    public String toString() {
        return "Conflicts = " + getConflicts();
    }

    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getRandomSuccessor() {
        //MOVE A RANDOM QUEEN TO A RANDOM UNOCCUPIED SQUARE
        return null;
    }

    public int getConflicts() {
        int result = 0;
        for(int i = 0;i < n; i++){
            if(cols[i]>1) result++;
            if(rdiags[i]>1) result++;
            if(ldiags[i]>1) result++;
        }
        return result;
    }

    public Board clone() {
        try {
            return (Board) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

