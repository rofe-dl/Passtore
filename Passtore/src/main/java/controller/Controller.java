package controller;

import mainapp.*;

/**
 * All other controllers inherit this controller class.
 */
public class Controller{
    /**
     * Holds the instance of Passtore class, so it's methods can
     * be called from all other controllers.
     */
    protected Passtore passtoreInstance;
    
    public void setProgramInstance(Passtore passtoreInstance){
        this.passtoreInstance = passtoreInstance;
    }
}