import java.util.*;

import static java.lang.Math.abs;

class Board implements Ilayout, Cloneable {

    int n;
    boolean[][] board; // Ocupado = true
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    Random r = new Random();

    private record Pair(int x, int y){}


    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public Board(int m) throws IllegalStateException {
        n = m;
        board = new boolean[n][n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
        Pair queenCoords[] = new Pair[n];
        //CREATE RANDOM BOARD WITH NO QUEENS ON THE SAME AND INITIALIZE COLS LDIAGS AND RDIAGS
        for(int i = 0; i<n; i++){
            int result = 0;
            int j = r.nextInt(n);
            board[i][j] = true;
            queenCoords[result++] = new Pair(i,j);
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++)
                if(board[i][j]) {
                    cols[j]++;
                    ldiags[i>j?(n-1)-Math.abs(i-j) : (n-1+Math.abs(i-j))]++;
                    rdiags[i+j]++;
                }
        }
    }

    public Board(int m, boolean b){
        n = m;
        board = new boolean[n][n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Conflicts = " + getObjectiveFunction()+"\n") ;
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++)
                str.append(board[i][j] ? "👑" + " " : "🔲" + " ");
            str.append("\n");
        }
        return str.toString();
    }


    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getSuccessor() {
        Board clone = this.clone();
        //MOVE A RANDOM QUEEN TO A RANDOM SQUARE ON THE SAME ROW AND UPDATE COLS LDIAGS AND RDIAGS
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n); //nova coluna que vai ser posta
        int index = clone.queenInRowIndex(r1); // coluna que a rainha esta
        clone.board[r1][index] = false;
        clone.board[r1][r2] = true;
        clone.cols[index]--;
        clone.cols[r2]++;
        clone.ldiags[r1>index ? (n-1)-Math.abs(r1-index) : (n-1)+Math.abs(r1-index)]--;
        clone.rdiags[r1+index]--;
        clone.ldiags[r1>r2 ? (n-1)-Math.abs(r1-r2) : (n-1)+Math.abs(r1-r2)]++;
        clone.rdiags[r1+r2]++;
        return clone;
    }

    private int queenInRowIndex(int row){
        for(int i = 0; i<n; i++){
            if(board[row][i])
                return i;
        }
        return -1;
    }

    public int getObjectiveFunction() {
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
        Board clone = new Board(n,true);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n;j++)
                clone.board[i][j] = this.board[i][j];
            clone.cols[i] = this.cols[i];
        }
        for(int i = 0; i<ldiags.length; i++){
            clone.ldiags[i] = this.ldiags[i];
            clone.rdiags[i] = this.rdiags[i];
        }
        return clone;
    }
}

