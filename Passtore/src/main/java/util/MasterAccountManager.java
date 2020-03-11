package util;

import javafx.collections.*;
import model.*;
import java.util.Collections;

public class MasterAccountManager{

    public static boolean checkPassword(String masterUsername, String masterPassword){
        ObservableList<MasterAccount> list = SaveFileHandler.getMasterAccountsList();
        for (MasterAccount i : list){
            if (i.getUsername().equals(masterUsername) && i.getPassword().equals(masterPassword)) return true;
        }

        return false;
    }

    public static boolean accountExists(String masterUsername){
        return login(masterUsername) >= 0; //if return is -1, no such account exists
    }

    /*****returns index number of the account from the list of master accounts*****/
    public static int login(String masterUsername){
        ObservableList<MasterAccount> list = SaveFileHandler.getMasterAccountsList();

        return Collections.binarySearch(list, new MasterAccount(masterUsername,""));
    }

    
}