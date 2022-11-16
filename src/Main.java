import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        FirstChoiceHillClimb hc = new FirstChoiceHillClimb();
        int n = sc.nextInt();
        long startTime = System.currentTimeMillis();
        hc.solve(new BoardOptimizedStart(n));
        long endTime = System.currentTimeMillis();
        System.out.println(n +" Queens Problem Solved in " + ((double)(endTime-startTime)/1000) + "seconds");
    }
}

