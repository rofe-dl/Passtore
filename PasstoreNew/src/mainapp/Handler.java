package mainapp;

import javafx.collections.ObservableList;
import model.MasterAccount;

public class Handler {
    public static boolean checkIfPasswordMatches(String masterUsername,
                                                 String masterPassword,
                                                 ObservableList<MasterAccount> e){
        for (MasterAccount i : e){
            if (i.equals(masterUsername, masterPassword)) return true;
        }

        return false;
    }

    public static boolean checkIfAccountExists(String masterUsername,
                                               ObservableList<MasterAccount> e){
        for (MasterAccount i : e){
            if (i.getUsername().equals(masterUsername)) return true;
        }

        return false;
    }
}
