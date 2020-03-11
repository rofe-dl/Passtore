package util;

import java.sql.*;
import model.*;

public class SQLiteConnector{
    private static Connection conn;
    private static String url = "jdbc:sqlite:passtoresavedata.db";

    public static void connect(){
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

    public static void addMasterAccount(MasterAccount ma){
        update("INSERT INTO masteraccounts" + ma.getUsername());
    }

    public static void deleteAccount(Account a){
        update("DELETE FROM accounts WHERE site =" + a.getSite());
    }

    public static void deleteMasterAccount(MasterAccount ma){
        update("DELETE FROM masteraccounts WHERE username =" + ma.getUsername());
    }

    public static void deleteAccount(Account a){
        update("DELETE FROM accounts WHERE site =" + a.getSite());
    }

    public static ResultSet getMasterAccounts(){
        return getResultSet("SELECT * FROM masteraccounts");
    }

    public static ResultSet getAccounts(String masterUsername){
        return getResultSet("SELECT * FROM accounts WHERE owner=" + masterUsername);
    }

}