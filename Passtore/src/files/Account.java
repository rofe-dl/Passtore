package files;
import java.io.Serializable;
import java.lang.Comparable;
//Structure of a normal account
public class Account implements Comparable<Account>, Serializable{

    public String email, site, password, username;
    public int index;
    //The index number of an account object is not fixed and changes everytime all the accounts of a master
    //account is sorted and listed in the sortAndListAccounts method of MasterAccount

    public Account(String site, String email, String username, String password){
        this.site = site;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(Account account){
        return this.site.toUpperCase().compareTo(account.site.toUpperCase());
    }

    @Override
    public String toString(){
        return this.index + ". " + site;
    }
}