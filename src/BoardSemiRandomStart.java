import java.util.*;

class BoardSemiRandomStart implements Ilayout, Cloneable {

    int n;
    int[] board;
    int[] cols;
    int[] ldiags;
    int[] rdiags;


    Random r = new Random();

    private record Pair(int x, int y) {
    }

    public static void shuffle(int arr[])
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
    int conflicts;

    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public BoardSemiRandomStart(int m) throws IllegalStateException {
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
        for (int i = 0; i < n; i++)
            board[i] = i;
        shuffle(board);
        for (int i = 0; i < n; i++) {
            int j = board[i];
            cols[j]++;
            ldiags[n-i-1 + j]++;
            rdiags[i + j]++;
        }
        int nconflicts = 0;
        //Check Conflicts in columns
        for (int i = 0; i < n; i++) {
            if (cols[i] > 1) nconflicts += cols[i] - 1;
        }
        //Check Conflicts in diagonals
        for (int i = 0; i < rdiags.length; i++) {
            if (rdiags[i] > 1) nconflicts += rdiags[i] - 1;
            if (ldiags[i] > 1) nconflicts += ldiags[i] - 1;
        }
        conflicts = nconflicts;
    }

    public BoardSemiRandomStart(int m, boolean b) {
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Conflicts = " + getObjectiveFunction() + "\n");
        for (int i = 0; i < board.length; i++) {
            str.append("⬜".repeat(Math.max(0, board[i])));
            str.append("👑");
            str.append("⬜".repeat(Math.max(0, n - 1 - board[i])));
            str.append("\n");
        }
        return str.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getSuccessor() {
        BoardSemiRandomStart clone;
        int of = this.getObjectiveFunction();
        ArrayList<Integer> ec = emptyColumns();
        clone = this.clone();
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n);
        if (!ec.isEmpty()) r2 = ec.get(r.nextInt(ec.size()));
        int index = clone.board[r1]; // coluna que a rainha esta
        clone.board[r1] = r2;
        clone.cols[index]--;//coluna antiga
        clone.cols[r2]++;//coluna nova
        int ldiold = n-r1-1 + index;//ldiagonal antiga
        int ldinew = n-r1-1 + r2;//ldiagonal nova
        int rdiold = r1 + index;//rdiagonal antiga
        int rdinew = r1 + r2;//rdiagonal nova
        clone.ldiags[ldiold]--;
        clone.rdiags[rdiold]--;
        clone.ldiags[ldinew]++;
        clone.rdiags[rdinew]++;
        int removedConflictsCols = cols[index] > 1 ? 1 : 0;
        int removedConflictsLDiags = ldiags[ldiold] > 1 ? 1 : 0;
        int removedConflictsRDiags = rdiags[rdiold] > 1 ? 1 : 0;
        int newConflictsCols = clone.cols[r2] > 1 ? 1 : 0;
        int newConflictsLDiags = clone.ldiags[ldinew] > 1 ? 1 : 0;
        int newConflictsRDiags = clone.rdiags[rdinew] > 1 ? 1 : 0;
        clone.conflicts -= (removedConflictsCols + removedConflictsLDiags + removedConflictsRDiags);
        clone.conflicts += newConflictsCols + newConflictsLDiags + newConflictsRDiags;
        return clone;
    }


    private ArrayList<Integer> emptyColumns(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++)
            if(cols[i]==0) list.add(i);
        return list;
    }

    public int getObjectiveFunction() {
        return conflicts;
    }

    public BoardSemiRandomStart clone() {
        BoardSemiRandomStart clone = new BoardSemiRandomStart(n,true);
        for(int i = 0; i<n; i++){
            clone.board[i] = this.board[i];
            clone.cols[i] = this.cols[i];
        }
        clone.conflicts = this.conflicts;
        for(int i = 0; i<ldiags.length; i++){
            clone.ldiags[i] = this.ldiags[i];
            clone.rdiags[i] = this.rdiags[i];
        }
        return clone;
    }
}