import java.util.*;

public class HillClimb{

    static class State {
        private Ilayout layout;
        private Ilayout goal;
        private double nc;

        public State(Ilayout l) {
            layout = l;
            nc = l.getConflicts();
        }

        public String toString() {
            return layout.toString();
        }

        public double getNC() {
            return nc;
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }

    final public Ilayout solve(Ilayout s) {
        State current = new State(s);
        while (current.layout.getConflicts()>0){
            State next = new State(current.layout.getRandomSuccessor());
            if(next.getNC()<current.getNC())
                current = next;
        }
        return current.layout;
    }

}