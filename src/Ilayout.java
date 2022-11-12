public interface Ilayout {

    /**
     @return a random successor of the receiver.
     */
    Ilayout getSuccessor();

    /**
     @return number of conflicts.
     */
    int getObjectiveFunction();
}



