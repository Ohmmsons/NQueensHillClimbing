import java.io.*;

/**
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */

public class HillClimb{

    static class State {
        private Ilayout layout;
        private double of;

        public State(Ilayout l) {
            layout = l;
            of = l.getObjectiveFunction();
        }

        /**
         * Returns a string representation of the object
         * @return string, which represents the board
         */
        public String toString() {
            return layout.toString();
        }
        /**
         * Returns the number of conflicts of the current Ilayout
         * @return double which is the number of conflicts
         */
        public double getOF() {
            return of;
        }

        /**
         * Returns a hash code value for the object
         * @return int which is hash code value for the object
         */
        public int hashCode() {
            return toString().hashCode();
        }

        /**
         * Returns a random neighbour of the current Ilayout
         * @return Ilayout
         */
        public State getSuccessor(){return new State(layout.getSuccessor());}

        /**
         * Indicates whether some other object is "equal to" this one
         * @param o Object
         * @return boolean which is true if this object is the same as the obj argument, false otherwise
         */
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }

    /**
     * While loop to find a state whose conflicts=0, whenever a state has fewer conflicts than the current, current = state
     * @params  layout
     * @return  Ilayout, which represents a Ilayout whose conflicts=0
     * @throws IOException
     */
    final public Ilayout solve(Ilayout s) throws IOException {
        State current = new State(s);
        while (current.getOF()>0) {
            State suc = current.getSuccessor();
            if(suc.getOF()<=current.getOF()){
                current = suc;
            }

        }
        return current.layout;
    }
}