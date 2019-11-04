package passtorefiles;
import java.io.*;
import java.util.ArrayList;

public class FileReaderAndEditor{
    static FileInputStream in;
    static ObjectInputStream objectIn;
    static FileOutputStream out;
    static ObjectOutputStream objectOut;

    static ArrayList<MasterAccount> masterAccountsList;

    public static void initializeStreams(){
        try{
            out = new FileOutputStream("savefile.sav");
            objectOut = new ObjectOutputStream(out);
            in = new FileInputStream("savefile.sav");
            objectIn = new ObjectInputStream(in);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<MasterAccount> getUpdatedMasterAccountsList(){
        try {
//            in = new FileInputStream("savefile.sav");
//            objectIn = new ObjectInputStream(in);
            masterAccountsList = (ArrayList<MasterAccount>) objectIn.readObject();
//            in.close(); objectIn.close();
        } catch (Exception ex) {
            masterAccountsList = new ArrayList<MasterAccount>();
            try{
                out = new FileOutputStream("savefile.sav");
                objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(masterAccountsList);

            }catch (Exception e){
                System.out.println("Failed to write to file");
            }

        }

        return masterAccountsList;
    }

    public static void updateMasterAccountsList(ArrayList<MasterAccount> a){
        try{
            out = new FileOutputStream("savefile.sav");
            objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(a);

        }catch (Exception e){
            System.out.println("Failed to write to file");
        }
    }




    /*

    //Method returns the arraylist of master accounts extracted from the file
    public static ArrayList<MasterAccount> getUpdatedMasterAccounts(){
        ArrayList<MasterAccount> updatedMasterAccounts = null;
        try{
            FileInputStream fileIn = new FileInputStream("savefile.ser");
            ObjectInputStream reader = new ObjectInputStream(fileIn);
            updatedMasterAccounts = (ArrayList<MasterAccount>)reader.readObject();
            reader.close();
            fileIn.close();

            //If there is no file found, an empty arraylist of master accounts is made that is written into a file and also returned
        }catch (Exception e){

            updatedMasterAccounts = new ArrayList<MasterAccount>();
            System.out.println(System.lineSeparator() + "No save file exists, a new one has been created....");

            try{

                FileOutputStream fileOut = new FileOutputStream("savefile.ser");
                ObjectOutputStream writer = new ObjectOutputStream(fileOut);
                writer.writeObject(updatedMasterAccounts);
                writer.close();
                fileOut.close();

            }catch(Exception f){
                System.out.println("Failed to create file.");
            }
        }

        return updatedMasterAccounts;

    }

    //Method returns true or false if the local file contains a master accout with the username in the parameter
    public static boolean nameExists(String username) {
        if (getUpdatedMasterAccounts().isEmpty()){
            return false;
        }else{
            for (MasterAccount i : getUpdatedMasterAccounts()){
                if (i.username.equals(username)){
                    return true;
                }
            }
        }

        return false;
    }

    //Method checks whether the master username and password match with the ones in the file, if it does user logs in
    public static boolean masterPasswordCheck(String username, String password){

        for (MasterAccount i : getUpdatedMasterAccounts()){
            if (i.username.equals(username) && i.password.equals(password)){
                return true;
            }
        }
        return false;
    }

    //Method prints all the usernames of the master accounts from the file
    public static void listMasterAccounts(){
        if (getUpdatedMasterAccounts().isEmpty()){
            System.out.println(System.lineSeparator() + "No master accounts exist. Please create an account first." + System.lineSeparator());
            return;
        }
        int count = 1;
        for (MasterAccount i : getUpdatedMasterAccounts()){
            System.out.println(count + ". " + i.username);
            count++;
        }

    }

    //Takes an arraylist of master accounts as parameter and writes it into the save file
    public static void updateMasterAccounts(ArrayList<MasterAccount> updatedMasterAccounts){
        try {

            FileOutputStream fileOut = new FileOutputStream("savefile.ser");
            ObjectOutputStream writer = new ObjectOutputStream(fileOut);
            writer.writeObject(updatedMasterAccounts);
            writer.close();
            fileOut.close();

        }catch (Exception e){
            System.out.println(System.lineSeparator() + "Failed to save.");
        }
    }

     */
}