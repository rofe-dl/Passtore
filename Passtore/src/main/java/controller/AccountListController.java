package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MasterAccount;
import util.Updater;

public class AccountListController  extends Controller{
    @FXML
    private TableView<MasterAccount> masterAccountTableView;
    @FXML
    private TableColumn<MasterAccount, String> usernameColumn;


    @FXML
    private void initialize(){
        masterAccountTableView.setItems(Updater.getMasterAccountsList());
        usernameColumn.setCellValueFactory(e -> e.getValue().usernameProperty());
    }
}
