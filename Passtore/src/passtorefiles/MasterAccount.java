package passtorefiles;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.Iterator;

public class MasterAccount implements Serializable{




    /*
    public String username, password;
    public ArrayList<Account> accounts = new ArrayList<Account>();
    //Scanner is passed to every method as it is not serilizable so it's obtained from the main class everytime the program runs and is passed around

    public MasterAccount(String username, String password){
        this.username = username;
        this.password = password;
    }


    public void menu(Scanner reader){

        System.out.println(System.lineSeparator() + "What would you like to do?" + System.lineSeparator());
        System.out.println("1. Retrieve a password");
        System.out.println("2. Add an account");
        System.out.println("3. Remove an account");
        System.out.println("4. Change master password");
        System.out.println("5. Logout");
        System.out.println("6. Delete this master account");
        System.out.print(System.lineSeparator() + "Command: ");
        String command = reader.nextLine();

        if (command.equals("1")){
            retrievePassword(reader);;
        }else if (command.equals("2")){
            addAccount(reader);
        }else if (command.equals("3")){
            removeAccount(reader);
        }else if (command.equals("4")){
            changeMasterPassword(reader);
        }else if (command.equals("5")){
            return;
        }else if (command.equals("6")){
            System.out.print(System.lineSeparator() + "Are you sure you want to delete this master account? Press y to continue: ");
            String reply = reader.nextLine();
            if (reply.matches("[yY]")){
                System.out.print(System.lineSeparator() + "You will now be logged out ....");
                reader.nextLine();
                removeMasterAccount();
                return;
            }else{
                System.out.println(System.lineSeparator() + "Master account not deleted");
            }
        }else{
            System.out.println(System.lineSeparator() + "Please enter a valid command");
        }

        menu(reader);
    }

    //sorts the accounts by site names and displays them with an index number

    //Method to add a new account to this master account
    public void addAccount(Scanner reader){
        System.out.print(System.lineSeparator() + "(Press 0 to return to the menu)" + System.lineSeparator() + "Press any key to continue: ");
        String command = reader.nextLine();
        if (command.equals("0")){
            System.out.println(System.lineSeparator() + "Account adding process cancelled.");
            return;
        }
        System.out.print(System.lineSeparator() + "Enter site name: ");
        String site = reader.nextLine();
        if (getSiteNames().contains(site)){//the if block checks if there's another account with same site name
            System.out.println(System.lineSeparator() + "Site name already exists, please enter another" + System.lineSeparator());
            return;
        }
        System.out.print("Enter email: ");
        String email = reader.nextLine();
        System.out.print("Enter username: ");
        String username = reader.nextLine();
        System.out.print("Enter password: ");
        String password = reader.nextLine();
        this.accounts.add(new Account(site, email, username, password));
        System.out.println(System.lineSeparator() + "New account has been added!");
        save();
    }


    //Method to get the password of any account stored in this master account
    public void retrievePassword(Scanner reader){

        if (this.accounts.isEmpty()){
            System.out.println(System.lineSeparator() + "No accounts are there to show.....");
            return;
        }

        sortAndListAccounts(reader);

        int numberAsked = -1;
        while (true){
            try{//A try-catch block to ensure user can only enter a numberr
                System.out.println("(Press 0 to return to the menu)");
                System.out.print("Enter number of the site you want the password of: ");
                numberAsked = Integer.parseInt(reader.nextLine());
                break;
            }catch(Exception e){
                System.out.println(System.lineSeparator() + "Please enter a valid number.");
                sortAndListAccounts(reader);
            }
        }

        if (numberAsked == 0){
            System.out.println(System.lineSeparator() + "Password retrieval cancelled.");
            return;
        }
        boolean found = false;//A boolean that denotes if the number the user gave was found in the list
        for (Account i : this.accounts){
            if (i.index == numberAsked){
                found = true;
                System.out.println(System.lineSeparator() + "Email: " + i.email);
                System.out.println("Username: " + i.username);
                System.out.println("Password: " + i.password);
                System.out.print(System.lineSeparator() + "Press any key to continue....");
                reader.nextLine();
                break;
            }
        }
        if(!found){
            System.out.println(System.lineSeparator() + "Please enter a valid number.");
            return;
        }
    }

    public void removeAccount(Scanner reader){

        if (this.accounts.isEmpty()){
            System.out.println(System.lineSeparator() + "No accounts are there to show.....");
            return;
        }

        sortAndListAccounts(reader);

        int numberAsked = -1;
        while (true){
            try{
                System.out.println("(Press 0 to return to the menu)");
                System.out.print("Enter number of the site you want to remove: ");
                numberAsked = Integer.parseInt(reader.nextLine());
                break;
            }catch(Exception e){
                System.out.println(System.lineSeparator() + "Please enter a valid number.");
                sortAndListAccounts(reader);
            }
        }

        if (numberAsked == 0){
            System.out.println(System.lineSeparator() + "Account removal cancelled.");
            return;
        }

        boolean found = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            if (iterator.next().index == numberAsked){
                found = true;
                System.out.print(System.lineSeparator() + "You sure you want to remove that account? Press y to continue: ");
                String reply = reader.nextLine();
                if (reply.matches("[yY]")){
                    iterator.remove();
                    System.out.println(System.lineSeparator() + "Account has been removed!");
                    break;
                }else{
                    System.out.println(System.lineSeparator() + "Account removal cancelled.");
                    return;
                }
            }
        }

        if(!found){
            System.out.println(System.lineSeparator() + "Please enter a valid number.");
            return;
        }

        save();
    }

    //Method to change the master username and master password
    public void changeMasterPassword(Scanner reader){
        System.out.print(System.lineSeparator() + "(Press 0 to return to the menu)" + System.lineSeparator() + "Press any other key to continue: ");
        String reply = reader.nextLine();
        if (reply.equals("0")){
            System.out.println(System.lineSeparator() + "Master password not changed.");
            return;
        }

        System.out.print("Enter new master password: ");
        this.password = reader.nextLine();

        System.out.println(System.lineSeparator() + "New master password set!");
        save();
    }

    //Returns an arraylist containing all the site names of every account stored in this master account
    public ArrayList<String> getSiteNames(){
        ArrayList<String> sites = new ArrayList<String>();
        for (Account i : accounts){
            sites.add(i.site);
        }
        return sites;
    }

    //Basically removes the previous version of this master account and adds it again to the file
    public void save(){
        removeMasterAccount();
        ArrayList<MasterAccount> updatedMasterAccounts = FileReaderAndEditor.getUpdatedMasterAccounts();
        updatedMasterAccounts.add(this);
        FileReaderAndEditor.updateMasterAccounts(updatedMasterAccounts);
    }

    public void removeMasterAccount(){
        ArrayList<MasterAccount> updatedMasterAccounts = FileReaderAndEditor.getUpdatedMasterAccounts();

        Iterator<MasterAccount> iterator = updatedMasterAccounts.iterator();
        while (iterator.hasNext()){
            if(iterator.next().username.equals(this.username)){
                iterator.remove();
            }
        }
        FileReaderAndEditor.updateMasterAccounts(updatedMasterAccounts);
    }*/

}