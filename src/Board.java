import java.util.*;

class Board implements Ilayout, Cloneable {

    int n;
    boolean[][] board; // Ocupado = true
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    Random r = new Random();

    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public Board(int m) throws IllegalStateException {
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
        Board clone = this.clone();
        //MOVE A RANDOM QUEEN TO A RANDOM SQUARE ON THE SAME ROW AND UPDATE COLS LDIAGS AND RDIAGS
        int r1 = r.nextInt(n);
        int r2 = r.nextInt(n);
        int index = clone.queenInRowIndex(r1);
        clone.board[r1][index] = false;
        clone.board[r1][r2] = true;
        //FALTA ATUALIZAR OS LDIAGS E RDIAGS
        clone.cols[index]--;
        clone.cols[r2]++;
        //clone.ldiags[???]--;
        //clone.ldiags[???]++;
        //clone.rdiags[???]--;
        //clone.rdiags[???]++;
        return clone;
    }

    private int queenInRowIndex(int row){
        for(int i = 0; i<n; i++){
            if(board[row][i])
                return i;
        }
        return -1;
    }

    public int getConflicts() {
        int nconflicts = 0;
        //Check Conflicts in columns
        for(int i = 0; i<n; i++){
            if(cols[i]>1) nconflicts+=cols[i]-1;
        }
        //Check Conflicts in diagonals
        for(int i = 0;i < rdiags.length; i++){
            if(rdiags[i]>1) nconflicts+= rdiags[i]-1;
            if(ldiags[i]>1) nconflicts+= ldiags[i]-1;
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

