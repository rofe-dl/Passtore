package util;

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import model.*;

public class SaveFileHandler {
    /** the main object that's serialized || contains list of all master accounts and their stored passwords
     * || saved as observable list as it allows viewing the list in a gui table**/
    private static ObservableList<MasterAccount> masterAccountsList;

    public static ObservableList<MasterAccount> getMasterAccountsList(){
        return masterAccountsList;
    }

    public static void populateMasterAccounts(){
        masterAccountsList = FXCollections.observableArrayList();
        try{
            ResultSet rs = SQLiteConnector.getMasterAccounts();
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");

                MasterAccount ma = new MasterAccount(username, password);
                populateAccounts(ma);
                masterAccountsList.add(ma);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void populateAccounts(MasterAccount ma){
        try{
            ResultSet rs = SQLiteConnector.getAccounts(ma.getUsername());
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String site = rs.getString("site");
                String email = rs.getString("email");

                ma.getAccountsList().add(new Account(site, email, username, password));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void updateThisMasterAccount(MasterAccount masterAccount){
        int indexOfAccount = Collections.binarySearch(masterAccountsList, masterAccount);

        
        masterAccountsList.remove(indexOfAccount);

        masterAccountsList.add(masterAccount);

        saveToFile();
    }

}