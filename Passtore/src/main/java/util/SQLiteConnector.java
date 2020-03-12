package util;

import java.sql.*;
import model.*;

public class SQLiteConnector{
    private static Connection conn;
    private static String url;

    public static void connect(){
        if (System.getProperty("os.name").equals("Linux")) url = "jdbc:sqlite:" + System.getProperty("user.home") + "/passtoresavedata.db";
        else url = "jdbc:sqlite:C:/passtoresavedata.db";

        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            createTables();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createTables() throws SQLException{
        Statement s = conn.createStatement();

        String line = "CREATE TABLE IF NOT EXISTS masteraccounts(username TEXT PRIMARY KEY, password TEXT );";
        s.executeUpdate(line);

        line = "CREATE TABLE IF NOT EXISTS accounts( owner TEXT, site TEXT PRIMARY KEY, password TEXT, email TEXT, username TEXT );";
        s.executeUpdate(line);
    }

    private static ResultSet getResultSet(String query){
        ResultSet rs = null;
        try{
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return rs;
    }

    public static void update(String line){
        try{
            Statement s = conn.createStatement();
            s.executeUpdate(line);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateMasterAccount(String oldUsername, MasterAccount updated){
        update("UPDATE masteraccounts SET username = '" + updated.getUsername() + "'," +
                                            "password = '" + updated.getPassword() + "' WHERE username = '" + oldUsername + "'");
    }

    public static void updateAccount(String oldSite, Account updated){
        update("UPDATE accounts SET username = '" + updated.getUsername() + "'," +
                                            "password = '" + updated.getPassword() + "'," +
                                            "site = '" + updated.getSite() + "'," + 
                                            "email = '" + updated.getEmail() + "' WHERE site = '" + oldSite + "'");
    }

    public static void addMasterAccount(MasterAccount ma){
        update("INSERT INTO masteraccounts VALUES('" + ma.getUsername()+ "','" + ma.getPassword() + "')");
    }

    public static void addAccount(Account a, MasterAccount ma){
        update("INSERT INTO accounts VALUES('" + ma.getUsername()+ "','" + a.getSite() + 
                                                "','" + a.getPassword() + "','" + a.getEmail() +
                                                "','" + a.getUsername() + "')");
    }

    public static void deleteMasterAccount(MasterAccount ma){
        update("DELETE FROM masteraccounts WHERE username = '" + ma.getUsername() + "'");
    }

    public static void deleteAccount(Account a){
        update("DELETE FROM accounts WHERE site = '" + a.getSite() + "'");
    }

    public static ResultSet getMasterAccounts(){
        return getResultSet("SELECT * FROM masteraccounts");
    }

    public static ResultSet getAccounts(String masterUsername){
        return getResultSet("SELECT * FROM accounts WHERE owner = '" + masterUsername + "'");
    }

}