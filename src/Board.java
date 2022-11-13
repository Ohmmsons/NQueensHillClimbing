import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Board implements Ilayout, Cloneable {

    int n;
    int[] board; // Ocupado = true
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    Random r = new Random();

    private record Pair(int x, int y){}

    int conflicts;

    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public Board(int m) throws IllegalStateException {
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
        //CREATE RANDOM BOARD WITH NO QUEENS ON THE SAME AND INITIALIZE COLS LDIAGS AND RDIAGS
        for(int i = 0; i<n; i++){
            board[i] = r.nextInt(n);
        }
        for(int i = 0; i<n; i++){
            int j = board[i];
            cols[j]++;
            ldiags[i>j?(n-1)-Math.abs(i-j) : (n-1+Math.abs(i-j))]++;
            rdiags[i+j]++;
        }
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

        conflicts=nconflicts;
    }

    public Board(int m, boolean b){
        n = m;
        board = new int[n];
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Conflicts = " + getObjectiveFunction()+"\n") ;

        return str.toString();
    }


    public int hashCode() {
        return Arrays.hashCode(cols) + Arrays.hashCode(ldiags) + Arrays.hashCode(rdiags);
    }

    public Ilayout getSuccessor() {
        Board clone;
        int of = this.getObjectiveFunction();
        ArrayList<Integer> ec = emptyColumns();
        do{
            clone = this.clone();
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n);
        if(!ec.isEmpty()) r2 = ec.get(r.nextInt(ec.size()));
        int index = clone.board[r1]; // coluna que a rainha esta
        clone.board[r1] = r2;
        clone.cols[index]--;//coluna antiga
        clone.cols[r2]++;//coluna nova
        int ldiold = r1>index ? (n-1)-Math.abs(r1-index) : (n-1)+Math.abs(r1-index);//ldiagonal antiga
        int ldinew = r1>r2 ? (n-1)-Math.abs(r1-r2) : (n-1)+Math.abs(r1-r2);//ldiagonal nova
        int rdiold = r1+index;//rdiagonal antiga
        int rdinew = r1+r2;//rdiagonal nova
        clone.ldiags[ldiold]--;
        clone.rdiags[rdiold]--;
        clone.ldiags[ldinew]++;
        clone.rdiags[rdinew]++;
        int removedConflictsCols = cols[index]>1 ? 1 : 0;
        int removedConflictsLDiags = ldiags[ldiold]>1 ? 1 : 0;
        int removedConflictsRDiags = rdiags[rdiold]>1 ? 1 : 0;
        int newConflictsCols = clone.cols[r2]>1 ? 1 :0;
        int newConflictsLDiags = clone.ldiags[ldinew]>1 ? 1 : 0;
        int newConflictsRDiags = clone.rdiags[rdinew]>1 ? 1 : 0;
        clone.conflicts -= (removedConflictsCols + removedConflictsLDiags + removedConflictsRDiags);
        clone.conflicts += newConflictsCols + newConflictsLDiags + newConflictsRDiags;
        }while(clone.getObjectiveFunction()>of);
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

    public Board clone() {
        Board clone = new Board(n,true);
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