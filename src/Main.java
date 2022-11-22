import java.io.IOException;
import java.util.Scanner;
/**
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */
public class Main {
    /**
     * Main function, where we initialize the nqueens
     * @param args              String[]
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        HillClimb hc = new HillClimb();
        int n = sc.nextInt();
        long result = 0;
        for(int i = 0; i<1; i++){
            long startTime = System.currentTimeMillis();
            hc.solve(new NQueensBoard(n));
            long endTime = System.currentTimeMillis();
            result+=(endTime-startTime);
        }
        System.out.println(n +" Queens Problem Solved in on average " + ((double)(result)/1000) + "seconds");
    }
}

