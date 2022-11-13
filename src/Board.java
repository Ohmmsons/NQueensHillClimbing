import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Board implements Ilayout, Cloneable {

    int n;
    String board; // Ocupado = true
    int[] cols;
    int[] ldiags;
    int[] rdiags;

    Random r = new Random();

    private record Pair(int x, int y){}

    private String randomLine(){
        return shuffle(String.format("%" + (n - 1) + "s", " ") + "X");
    }

    public String shuffle(String input){
        List<Character> characters = new ArrayList<>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }


    //THE BOARD IS MADE UP OF N QUEENS, ALL ON DIFFERENT ROWS TO SIMPLIFY THE PROBLEM
    public Board(int m) throws IllegalStateException {
        n = m;
        board = "";
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
        //CREATE RANDOM BOARD WITH NO QUEENS ON THE SAME AND INITIALIZE COLS LDIAGS AND RDIAGS
        for(int i = 0; i<n; i++){
            board+=randomLine();
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++)
                if(board.charAt(i*n+j) == 'X'){
                    cols[j]++;
                    ldiags[i>j?(n-1)-Math.abs(i-j) : (n-1+Math.abs(i-j))]++;
                    rdiags[i+j]++;
                }
        }
    }

    public Board(int m, boolean b){
        n = m;
        board = "";
        cols = new int[n];
        ldiags = new int[n*2-1];
        rdiags = new int[n*2-1];
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
//        str.append("Conflicts = " + getObjectiveFunction()+"\n") ;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++)
                str.append(board.charAt(i*n+j) == 'X' ? "👑" + " " : "🔲" + " ");
            str.append("\n");
        }
        return str.toString();
    }

    public int hashCode() {
        return board.hashCode();
    }

    public Ilayout getSuccessor() {
        Board clone;
        clone = this.clone();
        int r1 = r.nextInt(n); //linha que vamos selecionar a rainha
        int r2 = r.nextInt(n); //nova coluna que vai ser posta
        int index = clone.queenInRowIndex(r1); // coluna que a rainha esta
        clone.board = switchPlaces(clone.board,r1*n+index,r1*n+r2);
        clone.cols[index]--;
        clone.cols[r2]++;
        clone.ldiags[r1 > index ? (n - 1) - Math.abs(r1 - index) : (n - 1) + Math.abs(r1 - index)]--;
        clone.rdiags[r1 + index]--;
        clone.ldiags[r1 > r2 ? (n - 1) - Math.abs(r1 - r2) : (n - 1) + Math.abs(r1 - r2)]++;
        clone.rdiags[r1 + r2]++;
        return clone;
    }

    public String switchPlaces(String s, int i, int j) {
        char[] b = s.toCharArray();
        char old = b[i];
        b[i] = b[j];
        b[j] = old;
        return new String(b);
    }

    private int queenInRowIndex(int row){
        for(int i = 0; i<n; i++){
            if(board.charAt(row*n+i) == 'X')
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
        clone.cols = Arrays.copyOf(cols,cols.length);
        clone.ldiags = Arrays.copyOf(ldiags,ldiags.length);
        clone.rdiags = Arrays.copyOf(rdiags,rdiags.length);
        clone.board = board;
        return clone;
    }
}

