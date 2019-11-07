package model;

import java.io.Serializable;
import java.lang.Comparable;
import javafx.beans.property.*;

public class Account implements Comparable<Account>, Serializable {

    private String site, email, password, username;
    private transient StringProperty pSite, pEmail, pPassword, pUsername;

    public Account(String site, String email, String username, String password){
        this.site = site;
        this.email = email;
        this.password = password;
        this.username = username;

        this.pSite = new SimpleStringProperty(site);
        this.pEmail = new SimpleStringProperty(email);
        this.pPassword = new SimpleStringProperty(password);
        this.pUsername = new SimpleStringProperty(username);
    }

    public void deserializeIntoProperty(){
        this.pSite = new SimpleStringProperty(this.site);
        this.pEmail = new SimpleStringProperty(this.email);
        this.pPassword = new SimpleStringProperty(this.password);
        this.pUsername = new SimpleStringProperty(this.username);
    }

    public void setSite(String site) {
        this.site = site;
        this.pSite.set(site);
    }

    public void setEmail(String email) {
        this.email = email;
        this.pEmail.set(email);
    }

    public void setPassword(String password) {
        this.password = password;
        this.pPassword.set(password);
    }

    public void setUsername(String username) {
        this.username = username;
        this.pUsername.set(username);
    }

    public String getSite() {
        return this.pSite.get();
    }

    public StringProperty siteProperty() {
        return this.pSite;
    }

    public String getEmail() {
        return this.pEmail.get();
    }

    public StringProperty emailProperty() {
        return this.pEmail;
    }

    public String getPassword() {
        return this.pPassword.get();
    }

    public StringProperty passwordProperty() {
        return this.pPassword;
    }

    public String getUsername() {
        return this.pUsername.get();
    }

    public StringProperty usernameProperty() {
        return this.pUsername;
    }

    @Override
    public int compareTo(Account account){
        return this.getSite().toLowerCase().compareTo(account.getSite().toLowerCase());
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Account)) return false;

        if (this.getSite().equals( ( (Account) o).getSite()) ) return true;

        return false;
    }

}