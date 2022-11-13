public class HillClimb{

    static class State {
        private Ilayout layout;
        private double of;

        public State(Ilayout l) {
            layout = l;
            of = l.getObjectiveFunction();
        }

        public String toString() {
            return layout.toString();
        }

        public double getOF() {
            return of;
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
        while (current.getOF()>0) {
            current = new State(current.layout.getSuccessor());
        }
        return current.layout;
    }
}