import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HillClimb hc = new HillClimb();
        int n = sc.nextInt();
        long startTime = System.currentTimeMillis();
        Board solution = (Board) hc.solve(new Board(n));
        long endTime = System.currentTimeMillis();
        if (solution == null) System.out.println("no solution found");
        else System.out.println("SOLUTION OF " + n + " QUEENS FOUND IN " + ((double)endTime-(double)startTime)/1000 +"SECONDS" + "\n" + solution);
        sc.close();
    }
}

