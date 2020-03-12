package model;
import javafx.beans.property.*;
import javafx.collections.*;

/**
 * Structure of a master account
 */
public class MasterAccount implements Comparable<MasterAccount> {

    private ObservableList<Account> accounts;
    private StringProperty username, password;

    public MasterAccount(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.accounts = FXCollections.observableArrayList();
    }

    public ObservableList<Account> getAccountsList() {
        return this.accounts;
    }

    public String getUsername() {
        return this.username.get();
    }

    public StringProperty usernameProperty() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return this.password.get();
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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