import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        HillClimb hc = new HillClimb();
        Scanner sc = new Scanner(System.in);
//        BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\Ohm\\IdeaProjects\\NQueens\\outOn.csv"));
//        int n = 50;
//        while(n<15000) {
//            long startTime = System.currentTimeMillis();
//            Board solution = (Board) hc.solve(new Board(n));
//            long endTime = System.currentTimeMillis();
//            fw.write(n + "," + ((double)endTime-(double)startTime)/1000 + "\n");
//            n*=1.5;
//        }
//        fw.flush();
//        fw.close();
//        System.out.println("DONE");
        int n = sc.nextInt();
        long startTime = System.currentTimeMillis();
        Board solution = (Board) hc.solve(new Board(n));
        long endTime = System.currentTimeMillis();
        if (solution == null) System.out.println("no solution found");
        else System.out.println("SOLUTION OF " + n + " QUEENS FOUND IN " + ((double)endTime-(double)startTime)/1000 +"SECONDS" + "\n" + solution);
        sc.close();
    }
}

