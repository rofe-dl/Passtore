package model;

import java.io.Serializable;
import java.lang.Comparable;
import javafx.beans.property.*;

public class Account implements Comparable<Account>, Serializable {

    private String site, email, password, username;

    public Account(String site, String email, String username, String password){
        this.site = site;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return this.site;
    }

    public StringProperty siteProperty() {
        return new SimpleStringProperty(this.site);
    }

    public String getEmail() {
        return this.email;
    }

    public StringProperty emailProperty() {
        return new SimpleStringProperty(this.email);
    }

    public String getPassword() {
        return this.password;
    }

    public StringProperty passwordProperty() {
        return new SimpleStringProperty(this.password);
    }

    public String getUsername() {
        return this.username;
    }

    public StringProperty usernameProperty() {
        return new SimpleStringProperty(this.username);
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