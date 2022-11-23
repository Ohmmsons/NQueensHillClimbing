import java.util.*;

/**
 * NQueensBoard
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
    int[] cols;
    NQueensBoard father;

    Random r = new Random();

    int conflicts;

    /**
     * Shuffle array elements
     */
    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            Random rand = new Random();
            int j = rand.nextInt(i + 1);
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Creates the initial state of the board
     *
     * @param m int
     * @pre m >0
     */
    public NQueensBoard(int m) throws IllegalStateException {
        n = m;
        board = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
        cols = new int[n];

        for (int i = 0; i < n; i++)
            board[i] = i;
        shuffle(board);
        for (int i = 0; i < n; i++) {
            int j = board[i];
            cols[j]++;
            ldiags[n - i - 1 + j]++;
            rdiags[i + j]++;
        }
        conflicts = countConflicts();
    }
    /**
     * Creates the initial state of the board with array a
     *
     * @param m
     * @param a int[]
     * @pre m >0
     */
    public NQueensBoard(int m,int[] a) throws IllegalStateException {
        n = m;
        board = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
        cols = new int[n];
        System.arraycopy(a,0,board,0,n);
        for (int i = 0; i < n; i++) {
            int j = board[i];
            cols[j]++;
            ldiags[n - i - 1 + j]++;
            rdiags[i + j]++;
        }
        conflicts = countConflicts();
    }

    /**
     * @return ArrayList<Integer> which is the list of empty columns in the board
     */
    private ArrayList<Integer> emptyColumns() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (cols[i] == 0) list.add(i);
        return list;
    }

    /**
     * @return number of conflicts in board by counting number of queens in each diagonal and subtracting 1 if there is more than 1 queen in it
     */
    public int countConflicts() {
        int nconflicts = 0;
        //Check Conflicts in diagonals
        for (int i = 0; i < n; i++) {
            if (cols[i] > 1) nconflicts += cols[i] - 1;
        }
        for (int i = 0; i < rdiags.length; i++) {
            if (rdiags[i] > 1) nconflicts += rdiags[i] - 1;
            if (ldiags[i] > 1) nconflicts += ldiags[i] - 1;
        }
        return nconflicts;
    }

    /**
     * Makes the first play in the game
     *
     * @param m int
     * @param b boolean
     * @pre m >0 &amp;&amp; b != null
     */
    public NQueensBoard(int m, boolean b) {
        n = m;
        board = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
        cols = new int[n];
    }



    /**
     * Returns a string representation of the object
     *
     * @return string, which represents the board
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Conflicts = ").append(getObjectiveFunction()).append("\n");
        for (int j : board) {
            str.append("⬜".repeat(Math.max(0, j)));
            str.append("👑");
            str.append("⬜".repeat(Math.max(0, n - 1 - j)));
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
     *
     * @return Ilayout
     */
    public Ilayout getSuccessor() {
        NQueensBoard clone;
        clone = this.clone();
        clone.father = this;
        List<Integer> ec = emptyColumns();
        int row = r.nextInt(n); //linha que vamos selecionar a rainha
        int colnew;
        if (!ec.isEmpty()) colnew = ec.get(r.nextInt(ec.size()));
        else colnew = r.nextInt(n);
        int colold = clone.board[row];
        clone.board[row] = colnew;
        int ldiold = n - row - 1 + colold;//ldiagonal antiga
        int ldinew = n - row - 1 + colnew;//ldiagonal nova
        int rdiold = row + colold;//rdiagonal antiga
        int rdinew = row + colnew;//rdiagonal nova
        clone.cols[colold]--;
        clone.cols[colnew]++;
        clone.ldiags[ldiold]--;
        clone.rdiags[rdiold]--;
        clone.ldiags[ldinew]++;
        clone.rdiags[rdinew]++;
        clone.updateConflicts(colold, colnew, ldiold, rdiold, ldinew, rdinew);
        return clone;
    }

    /**
     * updates conflicts of board after change
     */
    public void updateConflicts(int colold, int colnew, int ldiold, int rdiold, int ldinew, int rdinew) {
        int removedConflictsCols = father.cols[colold] > 1 ? 1 : 0;
        int newConflictsCols = this.cols[colnew] > 1 ? 1 : 0;
        int removedConflictsLDiags = father.ldiags[ldiold] > 1 ? 1 : 0;
        int removedConflictsRDiags = father.rdiags[rdiold] > 1 ? 1 : 0;
        int newConflictsLDiags = this.ldiags[ldinew] > 1 ? 1 : 0;
        int newConflictsRDiags = this.rdiags[rdinew] > 1 ? 1 : 0;
        conflicts -= (removedConflictsCols + removedConflictsLDiags + removedConflictsRDiags);
        conflicts += newConflictsCols + newConflictsLDiags + newConflictsRDiags;
    }


    /**
     * Returns the number of conflicts of the current Ilayout
     *
     * @return int which is the number of conflicts
     */
    public int getObjectiveFunction() {
        return conflicts;
    }


    /**
     * Creates and returns a copy of this object
     *
     * @return copy of NqueensBoard
     */
    public NQueensBoard clone() {
        NQueensBoard clone = new NQueensBoard(n, true);
        if (n >= 0) System.arraycopy(this.board, 0, clone.board, 0, n);
        clone.conflicts = this.conflicts;
        System.arraycopy(this.cols, 0, clone.cols, 0, cols.length);
        System.arraycopy(this.ldiags, 0, clone.ldiags, 0, ldiags.length);
        System.arraycopy(this.rdiags, 0, clone.rdiags, 0, rdiags.length);
        return clone;
    }
}