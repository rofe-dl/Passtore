package model;

import java.lang.Comparable;
import javafx.beans.property.*;

public class Account implements Comparable<Account> {

    private StringProperty site, email, password, username;

    public Account(String site, String email, String username, String password){
        this.site = new SimpleStringProperty(site);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.username = new SimpleStringProperty(username);
    }

    public void setSite(String site) {
        this.site.set(site);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
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