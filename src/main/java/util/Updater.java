package util;

import javafx.collections.FXCollections;
import java.sql.*;
import model.*;
/**
 * Updates data within the program. Updates both the observable list in model.MasterACcountData
 * and the database in each method, thus changing data both on the frontend  for the viewer
 * and backend for the db.
 */
public class Updater {

    /**
     * Fills the observable master accounts list with data from the database
     */
    public static void populateMasterAccounts(){
        MasterAccountData.setMasterAccountList(FXCollections.observableArrayList());
        try{
            ResultSet rs = SQLiteConnector.getMasterAccounts();
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");

                MasterAccount ma = new MasterAccount(username, password);
                populateAccounts(ma);
                MasterAccountData.getMasterAccountsList().add(ma);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Fills the observable accounts list in the master account object
     * with accounts found from the database
     * @param ma master account to fill up
     */
    public static void populateAccounts(MasterAccount ma){
        try{
            ResultSet rs = SQLiteConnector.getAccounts(ma);
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

    /* Methods below perform create, update and delete operations on the data*/

    public static void addMasterAccount(MasterAccount ma){
        MasterAccountData.getMasterAccountsList().add(ma);
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

    public static void updateAccount(MasterAccount ma, Account a, String site, String email, String username, String password){
        String oldSite = a.getSite();

        a.setEmail(email);
        a.setPassword(password);
        a.setSite(site);
        a.setUsername(username);
        SQLiteConnector.updateAccount(oldSite, a, ma);
    }

    public static void deleteMasterAccount(MasterAccount ma){
        MasterAccountData.getMasterAccountsList().remove(ma);
        SQLiteConnector.deleteMasterAccount(ma);
    }

    public static void deleteAccount(Account a, MasterAccount ma){
        ma.getAccountsList().remove(a);
        SQLiteConnector.deleteAccount(a, ma);
    }

}