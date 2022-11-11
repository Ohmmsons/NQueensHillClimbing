import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HillClimb hc = new HillClimb();
        Board solution = (Board) hc.solve(new Board(sc.nextInt()));
        if (solution == null) System.out.println("no solution found");
        else System.out.println(solution);
        sc.close();
    }
}

