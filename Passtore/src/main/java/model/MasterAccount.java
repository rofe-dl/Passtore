package model;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.*;
import javafx.collections.*;

/** structure of a master account **/
public class MasterAccount implements Comparable<MasterAccount>, Serializable {

    /** Each variable has it's own property variable
     * Property variables are the ones displayed on gui as they can show live changes
     *  Property variables aren't serializable, so marked transient
     *  String variables are the ones that get serialized**/

    private ArrayList<Account> accountsList;
    private String username, password;

    private transient ObservableList<Account> pAccountsList;
    private transient StringProperty pUsername, pPassword;

    public MasterAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountsList = new ArrayList<Account>();

        this.pUsername = new SimpleStringProperty(username);
        this.pPassword = new SimpleStringProperty(password);
        this.pAccountsList = FXCollections.observableArrayList();
    }

    /**  Creates the property objects from it's respective string at the start of the program **/
    public void deserializeIntoProperty(){
        this.pUsername = new SimpleStringProperty(this.username);
        this.pPassword = new SimpleStringProperty(this.password);
        this.pAccountsList = FXCollections.observableArrayList(this.accountsList);
    }

    public ObservableList<Account> getAccountsList() {
        return this.pAccountsList;
    }

    //same as above method, but returns the arraylist
    public ArrayList<Account> getAccountsArrayList(){
        return this.accountsList;
    }

    public String getUsername() {
        return this.pUsername.get();
    }

    public StringProperty usernameProperty() {
        return this.pUsername;
    }

    public void setUsername(String username) {
        this.username = username;
        this.pUsername.set(username);
    }

    public String getPassword() {
        return this.pPassword.get();
    }

    public StringProperty passwordProperty() {
        return this.pPassword;
    } //never really used but eh let's keep it

    public void setPassword(String password) {
        this.password = password;
        this.pPassword.set(password);
    }

    @Override
    public int compareTo(MasterAccount a) {
        return this.getUsername().toLowerCase().compareTo(a.getUsername().toLowerCase()); //sorts by username
    }

    @Override
    public boolean equals(Object e) {
        if (!(e instanceof MasterAccount)) return false;

        MasterAccount a = (MasterAccount) e;

        if (this.getUsername().equals(a.getUsername())) return true;

        return false;
    }

}