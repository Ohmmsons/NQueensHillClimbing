import java.util.*;

class SuperFastBoard implements Ilayout, Cloneable {

    int n;
    int[] board;
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    int[] rdiagsConflicts;
    int[] ldiagsConflicts;

    int[] rowsConflicts;
    Random r = new Random();

    private record Pair(int x, int y){}

    int conflicts;

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
    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public SuperFastBoard(int m) throws IllegalStateException {
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n * 2 - 1];
        rdiags = new int[n * 2 - 1];
        ldiagsConflicts = new int[n * 2 - 1];
        rdiagsConflicts = new int[n * 2 - 1];

        for(int i = 0; i<n; i++)
            board[i]=i;
        shuffle(board);
        for (int i = 0; i < n; i++) {
            int j = board[i];
            ldiags[i-n-1 + j]++;
            rdiags[i + j]++;
        }
        int nconflicts = 0;
        int sum1 = 0;
        int sum2 = 0;
        //Check Conflicts in diagonals
        for (int i = 0; i < rdiags.length; i++) {
            rdiagsConflicts[i] = (rdiags[i]*(rdiags[i]-1))/2;
            ldiagsConflicts[i] = (ldiags[i]*(ldiags[i]-1))/2;
            sum1+=rdiagsConflicts[i];
            sum2+=ldiagsConflicts[i];
        }
        for(int i = 0; i<n; i++)
            rowsConflicts[i] = rdiagsConflicts[rDiagIndex(i,board[i])] +  ldiagsConflicts[lDiagIndex(i,board[i])];
        conflicts = sum1+sum2;
        System.out.println(conflicts);
    }

    public SuperFastBoard(int m, boolean b){
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
    }

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
    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getSuccessor(){
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n);
        int index1 = this.board[r1]; // coluna que a rainha esta
        int index2 = this.board[r2];
        swap(this.board,r1,r2);
        return this;
    }

    private void updateRowConflicts(){
        int sum1=0;
        int sum2=0;
        for (int i = 0; i < rdiags.length; i++) {
            rdiagsConflicts[i] = (rdiags[i]*(rdiags[i]-1))/2;
            ldiagsConflicts[i] = (ldiags[i]*(ldiags[i]-1))/2;
            sum1+=rdiagsConflicts[i];
            sum2+=ldiagsConflicts[i];
        }
        for(int i = 0; i<n; i++)
            rowsConflicts[i] = rdiagsConflicts[rDiagIndex(i,board[i])] +  ldiagsConflicts[lDiagIndex(i,board[i])];
    }


    private int lDiagIndex(int row, int column){
        return row - n - 1 + column;
    }

    private int rDiagIndex(int row, int column){
        return row + column;
    }

    public void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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

    public SuperFastBoard clone() {
        SuperFastBoard clone = new SuperFastBoard(n,true);
        if (n >= 0) System.arraycopy(this.board, 0, clone.board, 0, n);
        if (n >= 0) System.arraycopy(this.ldiags, 0, clone.ldiags, 0, ldiags.length);
        if (n >= 0) System.arraycopy(this.rdiags, 0, clone.rdiags, 0, rdiags.length);
        clone.conflicts = this.conflicts;
        return clone;
    }
}