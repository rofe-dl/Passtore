package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MasterAccount;
import util.SaveFileHandler;

public class AccountListController  extends Controller{
    @FXML
    private TableView<MasterAccount> masterAccountTableView;
    @FXML
    private TableColumn<MasterAccount, String> usernameColumn;


    @FXML
    private void initialize(){
        masterAccountTableView.setItems(SaveFileHandler.getMasterAccountsList());
        usernameColumn.setCellValueFactory(e -> e.getValue().usernameProperty());
    }
}
