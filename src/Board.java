import java.util.*;

class Board implements Ilayout, Cloneable {

    int n;
    boolean[][] board; // Ocupado = true
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    Random r = new Random();

    public Board(int m) throws IllegalStateException { //Simulates a two dimensional board using a String by filling in the empty spaces with " "
        n = m;
        board = new boolean[n][n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
        //CREATE RANDOM BOARD WITH NO QUEENS ON THE SAME AND INITIALIZE COLS LDIAGS AND RDIAGS
        for(int i = 0; i<n; i++){
            board[i][r.nextInt(n)] = true;
        }
        for(int i = 0; i<cols.length; i++){
            for(int j = 0; j<n; j++)
                if(board[i][j]) cols[j]++;
        }
        for(int i = 0; i<ldiags.length; i++){
            //Calcular rainhas em cada diagonal esquerda
        }
        for(int i = 0; i< rdiags.length; i++){
            //Calcular rainhas em cada diagonal direita
        }
    }

    public String toString() {
        return "Conflicts = " + getConflicts();
    }

    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getRandomSuccessor() {
        //MOVE A RANDOM QUEEN TO A RANDOM UNOCCUPIED SQUARE AND UPDATE NCONFLICTS
        return null;
    }

    public int getConflicts() {
        int nconflicts = 0;
        for(int i = 0;i < n; i++){
            if(cols[i]>1) nconflicts++;
            if(rdiags[i]>1) nconflicts++;
            if(ldiags[i]>1) nconflicts++;
        }
        return nconflicts;
    }

    public Board clone() {
        try {
            return (Board) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

