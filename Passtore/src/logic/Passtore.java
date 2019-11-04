package logic;

import passtorefiles.*;
import java.util.TreeSet;

/*
                    ===================================================================
    A program that uses a master account to unlock all account passwords stored in that master account.
    All Account objects stored in an TreeMap in a MasterAccount object
    All MasterAccount objects stored in an ArrayList object that's serialized into a local file
    FileReaderAndEditor is a tool made up of static methods used for different purposes
    Program starts from UI.java
                    ===================================================================
*/

public class Passtore {

    static MasterAccount currentAccount;

    public static void createNewMasterAccount(MasterAccount newMasterAccount){
        TreeSet<MasterAccount> a = FileReaderAndEditor.getUpdatedMasterAccountsList();
        a.add(newMasterAccount);
        FileReaderAndEditor.updateMasterAccountsList(a);
    }

    public static void login(){
        //currentAccount
    }

    public static boolean loginIsSuccess(String usernameEntered, String passwordEntered){
        for (MasterAccount i : FileReaderAndEditor.getUpdatedMasterAccountsList()){
            if (i.getName().equals(usernameEntered) && i.getPassword().equals(passwordEntered)){
                currentAccount = i;
                return true;
            }
        }

        return false;
    }



}