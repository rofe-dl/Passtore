package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MasterAccount;
import model.MasterAccountData;
/**
 * Controller for showing the list of master accounts from welcome screen.
 */
public class AccountListController  extends Controller{
    @FXML
    private TableView<MasterAccount> masterAccountTableView;
    @FXML
    private TableColumn<MasterAccount, String> usernameColumn;

    /**
     * First method that runs when the corresponding fxml file is loaded.
     */
    @FXML
    private void initialize(){
        masterAccountTableView.setItems(MasterAccountData.getMasterAccountsList());
        usernameColumn.setCellValueFactory(e -> e.getValue().usernameProperty());
    }
}
