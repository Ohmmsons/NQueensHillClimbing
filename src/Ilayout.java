/**
 * Ilayout
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */
public interface Ilayout {

    /**
     * Get a random sucessor of the receiver
     @return a random successor of the receiver.
     */
    Ilayout getSuccessor();

    /**
     * Get the of
     @return objective function.
     */
    int getObjectiveFunction();

}



