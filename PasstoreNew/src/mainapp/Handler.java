package mainapp;

import javafx.collections.*;
import model.MasterAccount;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Handler {

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

    /*****returns index number of the username from the list of master accounts*****/
    public static int login(String masterUsername){
        int x = Collections.binarySearch(masterAccountsList, new MasterAccount(masterUsername,""));

        return x;
    }

    public static void initializeFromSaveFile(){
        try {
            FileInputStream in = new FileInputStream("savefile.sav");
            ObjectInputStream objectIn = new ObjectInputStream(in);

            ArrayList<MasterAccount> arrayListExtracted = (ArrayList<MasterAccount>) objectIn.readObject();
            masterAccountsList = FXCollections.observableArrayList(arrayListExtracted);
        } catch (Exception e) {

            masterAccountsList = FXCollections.observableArrayList();
            saveToFile();
        }
    }

    public static void updateThisMasterAccount(MasterAccount masterAccount){
        int x = Collections.binarySearch(masterAccountsList, masterAccount);
        masterAccountsList.remove(x);
        masterAccountsList.add(masterAccount);
        saveToFile();
    }


    public static void saveToFile(){
        Collections.sort(masterAccountsList);
        try{

            FileOutputStream out = new FileOutputStream("savefile.sav");
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject( new ArrayList<MasterAccount>(masterAccountsList) );
            objectOut.flush(); out.flush();

        }catch (IOException e){
            System.out.println("Failed to write to file for some reason. You sure you got permission to edit" +
                    " this directory?");
        }
    }
}
