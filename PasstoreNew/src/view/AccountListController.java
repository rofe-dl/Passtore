package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import mainapp.Handler;
import model.MasterAccount;

public class AccountListController {
    @FXML
    private TableView<MasterAccount> masterAccountTableView;
    @FXML
    private TableColumn<MasterAccount, String> usernameColumn;


    @FXML
    private void initialize(){
        masterAccountTableView.setItems(Handler.getMasterAccountsList());
        usernameColumn.setCellValueFactory(e -> e.getValue().usernameProperty());
    }
}
