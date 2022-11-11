public interface Ilayout {

    /**
     @return a random successor of the receiver.
     */
    Ilayout getRandomSuccessor();

    /**
     @return number of conflicts.
     */
    int getConflicts();
}



