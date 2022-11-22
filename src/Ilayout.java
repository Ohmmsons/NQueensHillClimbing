/**
 * @author Jude Adam
 * @author Francisco Antonio
 * @author Andre Granja
 * @version 1.0
 */
public interface Ilayout {

    /**
     @return a random successor of the receiver.
     */
    Ilayout getSuccessor();

    /**
     @return objective function.
     */
    int getObjectiveFunction();

}



