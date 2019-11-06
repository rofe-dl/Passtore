package mainapp;

import javafx.collections.ObservableList;
import model.MasterAccount;
import java.util.Collections;

public class Handler {
    public static boolean checkIfPasswordMatches(String masterUsername,
                                                 String masterPassword,
                                                 ObservableList<MasterAccount> e){
        for (MasterAccount i : e){
            if (i.checkIfPasswordMatches(masterUsername, masterPassword)) return true;
        }

        return false;
    }

    public static boolean checkIfAccountExists(String masterUsername,
                                               ObservableList<MasterAccount> e){
        Collections.sort(Passtore.getListOfMasterAccounts());
        int x = Collections.binarySearch(Passtore.getListOfMasterAccounts(), new MasterAccount(masterUsername,""));

        return (x >= 0)? true : false;
    }

    public static int login(String username){
        Collections.sort(Passtore.getListOfMasterAccounts());

        int x = Collections.binarySearch(Passtore.getListOfMasterAccounts(), new MasterAccount(username,""));

        return x;
    }
}
