package passtorefiles;

import java.io.Serializable;
import java.lang.Comparable;

public class Account implements  Serializable{

    public String email, password, username;

    public Account(String site, String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }


}