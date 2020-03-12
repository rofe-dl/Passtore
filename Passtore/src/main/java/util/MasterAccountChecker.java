package util;

import javafx.collections.*;
import model.*;

/**
 * Tool used on master accounts.
 */

public class MasterAccountChecker{

    /**
     * Checks if the username and password combination is correct for a master account
     * @param masterUsername the username
     * @param masterPassword the password
     * @return true if correct
     */
    public static boolean checkPassword(String masterUsername, String masterPassword){
        ObservableList<MasterAccount> list = MasterAccountData.getMasterAccountsList();
        for (MasterAccount i : list){
            if (i.getUsername().equals(masterUsername) && i.getPassword().equals(masterPassword)) return true;
        }

        return false;
    }

    /**
     * Checks if a master account by a certain name exists or not
     * @param masterUsername name to check against
     * @return false if account doesn't exist, true if it does
     */
    public static boolean checkAccountExists(String masterUsername){
        return login(masterUsername) >= 0; //because login() method returns -1 if account doesn't exist
    }

    /**
     * Logs the user in by returning the index of the master account
     * @param masterUsername username of the master account
     * @return the index of the master account from the list, -1 if there's no such account
     */
    public static int login(String masterUsername){
        ObservableList<MasterAccount> list = MasterAccountData.getMasterAccountsList();
        int index = 0;

        for(MasterAccount i : list){
            if(i.getUsername().equals(masterUsername) ) return index;

            index++;
        }

        return -1;
    }

    
}