package util;

import java.sql.*;
import model.*;

/**
 * Class used to connect to the database.
 */
public class SQLiteConnector{
    /**
     * Object that maintains the connection with the db
     */
    private static Connection conn;
    
    /**
     * Directory of the database saved.
     */
    private static String url;

    /**
     * Connects to the database.
     * Location of the database depending on which OS it is.
     * if linux, stored in home/user/
     * if windows, store in C:/Users/username/
     */
    public static void connect(){
        if (System.getProperty("os.name").equals("Linux")) url = "jdbc:sqlite:" + System.getProperty("user.home") + "/passtoresavedata.db";
        else url = "jdbc:sqlite:C:\\Users\\" + System.getProperty("user.name") + "\\passtoresavedata.db";

        try{
            /* This loads the static initializer (static block) of the JDBC class 
            that registers itself to the DriverManager class of Java so the driver
            is registered. The JDBC class is in org.sqlite package of the JAR that
            maven has downloaded and added to the classpath*/

            Class.forName("org.sqlite.JDBC"); 

            conn = DriverManager.getConnection(url); //establishes connection

            createTables();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Creates the tables if they don't exist
     * Called from connect()
     * @throws SQLException
     */
    public static void createTables() throws SQLException{
        Statement s = conn.createStatement();

        String line = "CREATE TABLE IF NOT EXISTS masteraccounts(username TEXT PRIMARY KEY, password TEXT );";
        s.executeUpdate(line);

        line = "CREATE TABLE IF NOT EXISTS accounts( owner TEXT , site TEXT , password TEXT, email TEXT, username TEXT," +
                                                        " PRIMARY KEY (owner, site) );";
        s.executeUpdate(line);
    }

    /**
     * Used for running queries
     * @param query SELECT statement to run
     * @return the result set received from the query
     */
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

    /**
     * Used for insert, update and delete statements
     * @param line statement to run on the database
     */
    public static void update(String line){
        try{
            Statement s = conn.createStatement();
            s.executeUpdate(line);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateMasterAccount(String oldUsername, MasterAccount ma){
        update("UPDATE accounts SET owner = '" + ma.getUsername() + "' WHERE owner = '" + oldUsername + "'");
        update("UPDATE masteraccounts SET username = '" + ma.getUsername() + "'," +
                                            "password = '" + ma.getPassword() + "' WHERE username = '" + oldUsername + "'");
    }

    public static void updateAccount(String oldSite, Account a, MasterAccount ma){
        update("UPDATE accounts SET username = '" + a.getUsername() + "'," +
                                            "password = '" + a.getPassword() + "'," +
                                            "site = '" + a.getSite() + "'," + 
                                            "email = '" + a.getEmail() + "' WHERE site = '" + oldSite + "'"
                                            + " AND owner = '" + ma.getUsername() + "'");
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
        update("DELETE FROM accounts WHERE owner = '" + ma.getUsername() + "'");
    }

    public static void deleteAccount(Account a, MasterAccount ma){
        update("DELETE FROM accounts WHERE site = '" + a.getSite() + "' AND owner = '" + ma.getUsername() + "'");
    }

    public static ResultSet getMasterAccounts(){
        return getResultSet("SELECT * FROM masteraccounts");
    }

    public static ResultSet getAccounts(MasterAccount ma){
        return getResultSet("SELECT * FROM accounts WHERE owner = '" + ma.getUsername() + "'");
    }

}