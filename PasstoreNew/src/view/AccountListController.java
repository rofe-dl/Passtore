package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mainapp.Passtore;
import model.MasterAccount;

public class AccountListController {
    @FXML
    private TableView<MasterAccount> masterAccountTableView;
    @FXML
    private TableColumn<MasterAccount, String> usernameColumn;


    @FXML
    private void initialize(){
        masterAccountTableView.setItems(Passtore.getListOfMasterAccounts());
        usernameColumn.setCellValueFactory(e -> e.getValue().usernameProperty());

    }
}
