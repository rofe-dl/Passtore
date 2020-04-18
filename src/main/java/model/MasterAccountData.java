package model;

import javafx.collections.ObservableList;

/**
 * Class that contains the observable list of master accounts
 * that is loaded and populated from the database in the util.Updater class
 */

public class MasterAccountData {
    /**
     * The list that holds the master accounts found from the database
     */
    private static ObservableList<MasterAccount> masterAccountsList;

    public static ObservableList<MasterAccount> getMasterAccountsList(){
        return masterAccountsList;
    }

    public static void setMasterAccountList(ObservableList<MasterAccount> list){
        masterAccountsList = list;
    }
}