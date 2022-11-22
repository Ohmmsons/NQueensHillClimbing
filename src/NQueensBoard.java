import java.util.*;
/**
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */
class NQueensBoard implements Ilayout, Cloneable {

    int n;
    int[] board;

    int[] ldiags;
    int[] rdiags;


    Random r = new Random();

    int conflicts;

    /**
     * Shuffle array elements
     */
    public static void shuffle(int[] arr)
    {
        for(int i=arr.length-1;i > 0;i--)
        {
            Random rand = new Random();
            int j = rand.nextInt(i+1);
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Makes the first play in the game
     * @param m                 int
     * @pre m >0
     */
    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public NQueensBoard(int m) throws IllegalStateException {
            n = m;
            board = new int[n];;
            ldiags = new int[n * 2 - 1];
            rdiags = new int[n * 2 - 1];
            for(int i = 0 ; i<n;i ++)
                board[i]=i;
            shuffle(board);
            for (int i = 0; i < n; i++) {
                int j = board[i];
                ldiags[n-i-1 + j]++;
                rdiags[i + j]++;
            }

            //Check Conflicts in diagonals
            for (int i = 0; i < rdiags.length; i++) {
                if (rdiags[i] > 1) conflicts += rdiags[i] - 1;
                if (ldiags[i] > 1) conflicts += ldiags[i] - 1;
            }
    }

    /**
     * Makes the first play in the game
     * @param m                 int
     * @param b                 boolean
     * @pre m >0 &amp;&amp; b != null
     */
    public NQueensBoard(int m, boolean b){
        n = m;
        board = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
    }

    /**
     * Returns a string representation of the object
     * @return string, which represents the board
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Conflicts = " + getObjectiveFunction()+"\n") ;
        for(int i = 0; i < board.length; i++){
            str.append("⬜".repeat(Math.max(0, board[i])));
            str.append("👑");
            str.append("⬜".repeat(Math.max(0, n - 1 - board[i])));
            str.append("\n");
        }
        return str.toString();
    }
    /**
     * Returns a hash code value for the object
     */
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    /**
     * Returns a random neighbour of the current Ilayout
     * @return Ilayout
     */
    public Ilayout getSuccessor() {
        NQueensBoard clone;
        clone = this.clone();
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n);
        int index = clone.board[r1]; // coluna que a rainha esta
        clone.board[r1] = r2;
        int ldiold = n-r1-1 + index;//ldiagonal antiga
        int ldinew = n-r1-1 + r2;//ldiagonal nova
        int rdiold = r1 + index;//rdiagonal antiga
        int rdinew = r1 + r2;//rdiagonal nova
        clone.ldiags[ldiold]--;
        clone.rdiags[rdiold]--;
        clone.ldiags[ldinew]++;
        clone.rdiags[rdinew]++;
        int removedConflictsLDiags = ldiags[ldiold] > 1 ? 1 : 0;
        int removedConflictsRDiags = rdiags[rdiold] > 1 ? 1 : 0;
        int newConflictsLDiags = clone.ldiags[ldinew] > 1 ? 1 : 0;
        int newConflictsRDiags = clone.rdiags[rdinew] > 1 ? 1 : 0;
        clone.conflicts -= (removedConflictsLDiags + removedConflictsRDiags);
        clone.conflicts += newConflictsLDiags + newConflictsRDiags;
        return clone;
    }


    /**
     * Returns the number of conflicts of the current Ilayout
     * @return int which is the number of conflicts
     */
    public int getObjectiveFunction() {
        return conflicts;
    }


    /**
     * Creates and returns a copy of this object
     * @return copy of NqueensBoard
     */
    public NQueensBoard clone() {
        NQueensBoard clone = new NQueensBoard(n,true);
        if (n >= 0) System.arraycopy(this.board, 0, clone.board, 0, n);
        clone.conflicts = this.conflicts;
        System.arraycopy(this.ldiags, 0, clone.ldiags, 0, ldiags.length);
        System.arraycopy(this.rdiags, 0, clone.rdiags, 0, rdiags.length);
        return clone;
    }
}