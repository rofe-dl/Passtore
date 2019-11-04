package passtorefiles;

import java.io.*;
import java.util.TreeSet;

public class FileReaderAndEditor{

    static TreeSet<MasterAccount> masterAccountsList;

    public static boolean masterAccountExists(String a){

        if (getUpdatedMasterAccountsList().isEmpty()) return false;

        for (MasterAccount i : getUpdatedMasterAccountsList()){
            if (a.equals(i.getName())) return true;
        }

        return false;
    }

    //Same as above method, except takes whole MasterAccount object and gets the username from it
    public static boolean masterAccountExists(MasterAccount a){
        return masterAccountExists(a.getName());
    }

    public static TreeSet<MasterAccount> getUpdatedMasterAccountsList(){

        try {
            FileInputStream in = new FileInputStream("savefile.sav");
            ObjectInputStream objectIn = new ObjectInputStream(in);
            masterAccountsList = (TreeSet<MasterAccount>) objectIn.readObject();
        } catch (Exception e) {
            masterAccountsList = new TreeSet<MasterAccount>();
            updateMasterAccountsList(masterAccountsList);
        }

        return masterAccountsList;
    }

    public static void updateMasterAccountsList(TreeSet<MasterAccount> a){
        try{
            FileOutputStream out = new FileOutputStream("savefile.sav");
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(a);
            objectOut.flush(); out.flush();

        }catch (IOException e){
            System.out.println("Failed to write to file for some reason. You sure you got permission to edit" +
                    " this directory?");
        }
    }
}