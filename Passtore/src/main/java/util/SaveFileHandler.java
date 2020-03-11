package util;

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

    public static void addMasterAccount(MasterAccount ma){
        masterAccountsList.add(ma);
        SQLiteConnector.addMasterAccount(ma);
    }

    public static void addAccount(Account a, MasterAccount ma){
        ma.getAccountsList().add(a);
        SQLiteConnector.addAccount(a, ma);
    }
    
    public static void updateMasterAccount(MasterAccount ma, String username, String password){
        String oldUsername = ma.getUsername();

        ma.setPassword(password);
        ma.setUsername(username);
        SQLiteConnector.updateMasterAccount(oldUsername, ma);
        
    }

    public static void updateAccount(Account a, String site, String email, String username, String password){
        String oldSite = a.getSite();

        a.setEmail(email);
        a.setPassword(password);
        a.setSite(site);
        a.setUsername(username);
        SQLiteConnector.updateAccount(oldSite, a);
    }

    public static void deleteMasterAccount(MasterAccount ma){
        masterAccountsList.remove(ma);
        SQLiteConnector.deleteMasterAccount(ma);
    }

    public static void deleteAccount(Account a, MasterAccount ma){
        ma.getAccountsList().remove(a);
        SQLiteConnector.deleteAccount(a);
    }

}