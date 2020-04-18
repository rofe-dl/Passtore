package mainapp;
/**
 * Start class required as JAR file won't start if the starting class inherits a third party
 * So program launches here
 */
public class Start {
    /**
     * Has no other job but to start the program by 
     * calling the real main method.
     */
    public static void main(String[] args) {
        Passtore.main(args);
    }
}
