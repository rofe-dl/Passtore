package model;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.*;
import javafx.collections.*;

public class MasterAccount implements Comparable<MasterAccount>, Serializable {

    private ArrayList<Account> accountsList;
    private String username, password;

    public MasterAccount(String name, String password) {
        this.username = name;
        this.password = password;
        accountsList = new ArrayList<Account>();
    }

    public ObservableList<Account> getAccountsList() {
        return FXCollections.observableArrayList(accountsList);
    }

    public ArrayList<Account> getAccountsListAsArrayList(){
        return this.accountsList;
    }

    public String getUsername() {
        return this.username;
    }

    public StringProperty usernameProperty() {
        return new SimpleStringProperty(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public StringProperty passwordProperty() {
        return new SimpleStringProperty(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(MasterAccount a) {
        return this.getUsername().toLowerCase().compareTo(a.getUsername().toLowerCase());
    }

    @Override
    public boolean equals(Object e) {
        if (!(e instanceof MasterAccount)) return false;

        MasterAccount a = (MasterAccount) e;

        if (this.getUsername().equals(a.getUsername())) return true;

        return false;
    }

}