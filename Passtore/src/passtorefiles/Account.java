package passtorefiles;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;

public class Account implements Serializable, Comparable<Account> {

    private String site, email, password, username;

    public Account(String site, String email, String username, String password){
        this.site = site;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(Account account){
        return this.site.toLowerCase().compareTo(account.site.toLowerCase());
    }

}