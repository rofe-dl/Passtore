package controller;

import javafx.collections.*;
import model.MasterAccount;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/** class that handles file loading and reading **/
public class Handler {

    /** the main object that's serialized || contains list of all master accounts and their stored passwords
     * || saved as observable list as it allows viewing the list in a gui table**/
    private static ObservableList<MasterAccount> masterAccountsList;

    public static ObservableList<MasterAccount> getMasterAccountsList(){
        return masterAccountsList;
    }

    public static boolean checkIfPasswordMatches(String masterUsername, String masterPassword){
        for (MasterAccount i : masterAccountsList){
            if (i.getUsername().equals(masterUsername) && i.getPassword().equals(masterPassword)) return true;
        }

        return false;
    }

    public static boolean checkIfAccountExists(String masterUsername){
        int x = Collections.binarySearch(masterAccountsList, new MasterAccount(masterUsername,""));

        return (x >= 0)? true : false;
    }

    /*****returns index number of the account from the list of master accounts*****/
    public static int login(String masterUsername){
        int x = Collections.binarySearch(masterAccountsList, new MasterAccount(masterUsername,""));

        return x;
    }

    public static void updateThisMasterAccount(MasterAccount masterAccount){
        int x = Collections.binarySearch(masterAccountsList, masterAccount);
        masterAccountsList.remove(x);
        masterAccountsList.add(masterAccount);
        saveToFile();
    }

    /** method that gets called whenever a change is brought to the static list, writes the change into the file **/
    public static void saveToFile(){
        Collections.sort(masterAccountsList);
        try{

            FileOutputStream out = new FileOutputStream("passtoresavefile");
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject( new ArrayList<MasterAccount>(masterAccountsList) ); //observable list converted to arraylist, as the former isn't serializable
            objectOut.flush(); out.flush();
            objectOut.close(); out.close();

        }catch (IOException e){
            System.out.println("Failed to write to file for some reason. You sure you got permission to edit" +
                    " this directory?");
        }
    }

    /** Loads save file and deserializes the list into the static variable **/
    public static void initializeFromSaveFile(){
        try {
            FileInputStream in = new FileInputStream("passtoresavefile");
            ObjectInputStream objectIn = new ObjectInputStream(in);

            ArrayList<MasterAccount> arrayListExtracted = (ArrayList<MasterAccount>) objectIn.readObject();
            masterAccountsList = FXCollections.observableArrayList(arrayListExtracted); //converts extracted arraylist into an observablelist
            in.close(); objectIn.close();
            
        } catch (Exception e) {

            masterAccountsList = FXCollections.observableArrayList(); //if no save file, a new one is created
            saveToFile();
        }
    }
}
