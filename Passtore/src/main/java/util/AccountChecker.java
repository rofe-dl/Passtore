package util;
import javafx.collections.ObservableList;
import model.*;

/**
 * Tool used on normal accounts.
 */
public class AccountChecker{

    /**
     * Checks if a site exists in a master account, as accounts by 
     * the same site name are not allowed
     * @param site site to check
     * @param ma master account to check in
     * @return true if exists
     */
    public static boolean checkSiteExists(String site, MasterAccount ma){
        ObservableList<Account> list = ma.getAccountsList();
        for(Account i : list){
            if (i.getSite().equals(site)) return true;
        }

        return false;
    }
}