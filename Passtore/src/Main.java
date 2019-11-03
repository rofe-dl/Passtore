
import java.util.Scanner;
import java.util.ArrayList;
import files.*;

//A program that uses a master account to unlock all account passwords stored in that master account.
//All Account objects stored in an arraylist in a MasterAccount object
//All MasterAccount objects stored in an ArrayList object that's serialized into a local file
//FileReaderAndEditor is a tool made up of static methods used for different purposes
public class Main{
    public static void main(String[] args){

        Scanner reader = new Scanner(System.in);

        System.out.println();
        System.out.println("===================================");
        System.out.println("    WELCOME TO PASSWORD MANAGER    ");
        System.out.println("===================================");

        while(true){
            System.out.println(System.lineSeparator() + "Press any number from below" + System.lineSeparator());
            System.out.println("1. Login");
            System.out.println("2. Create master account");
            System.out.println("3. List master accounts");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Command: ");

            String command = reader.nextLine();
            if (!(command.matches("[1234]"))){
                System.out.println();
                System.out.println("Please enter a valid number.");
                continue;
            }else if (command.matches("1")){
                login(reader);
            }else if (command.matches("2")){
                createMasterAccount(reader, FileReaderAndEditor.getUpdatedMasterAccounts());
            }else if (command.matches("3")){
                System.out.println();
                FileReaderAndEditor.listMasterAccounts();
            }else if (command.matches("4")){
                return;
            }
        }
    }

    public static void login(Scanner reader){

        System.out.print(System.lineSeparator() + "Username: ");
        String username = reader.nextLine();
        //Checks if any account with that username exists in the first place
        if (!FileReaderAndEditor.nameExists(username)){
            System.out.println(System.lineSeparator() + "No such account exists, please create an account or try another username.");
            return;
        }

        while (true){
            System.out.print("Password: ");
            String password = reader.nextLine();
            if (FileReaderAndEditor.masterPasswordCheck(username, password)){
                System.out.println(System.lineSeparator() + "Login Successful!");
                for (MasterAccount i : FileReaderAndEditor.getUpdatedMasterAccounts()){
                    if (i.username.equals(username)){
                        i.introduction(reader);
                    }
                }
                break;
            }else if (password.equals("0")){
                return;
            }else{
                System.out.println(System.lineSeparator() + "Wrong password! (Press 0 to return to the main menu)" + System.lineSeparator());
                continue;
            }
        }
    }
    //Method to create a new master account
    public static void createMasterAccount(Scanner reader, ArrayList<MasterAccount> updatedMasterAccounts){

        String username = "";

        while (true){
            System.out.print(System.lineSeparator() + "(Press 0 to return to the main menu)" + System.lineSeparator() + "Please enter new username: ");
            username = reader.nextLine();

            if(username.equals("0")){
                System.out.println(System.lineSeparator() + "Master account creation cancelled.");
                return;
            }else if(FileReaderAndEditor.nameExists(username)){//Checks if the master username already exists
                System.out.println(System.lineSeparator() + "Name already exists, please enter another");
                continue;
            }else{
                break;
            }
        }

        System.out.print("Please enter new password: ");
        String password = reader.nextLine();

        //Adds the new master account to the arraylist of master accounts gotten from parameter
        //and adds the new arraylist to the file
        updatedMasterAccounts.add(new MasterAccount(username, password));
        FileReaderAndEditor.updateMasterAccounts(updatedMasterAccounts);
        System.out.println(System.lineSeparator() + "New master account created!");

    }
}