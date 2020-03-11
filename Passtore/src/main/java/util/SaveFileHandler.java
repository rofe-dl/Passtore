package util;

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class SaveFileHandler {
    /** the main object that's serialized || contains list of all master accounts and their stored passwords
     * || saved as observable list as it allows viewing the list in a gui table**/
    private static ObservableList<MasterAccount> masterAccountsList;

    public static ObservableList<MasterAccount> getMasterAccountsList(){
        return masterAccountsList;
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