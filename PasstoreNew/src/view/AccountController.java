package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

public class AccountController {

    private MasterAccount currentAccount;
    private Stage stage;

    @FXML
    private Button addAccountButton;

    @FXML
    private Button editAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private TableView<Account> accountTableView;

    @FXML
    private TableColumn<Account,String> siteColumn;

    @FXML
    private TableColumn<Account,String> emailColumn;

    @FXML
    private TableColumn<Account,String> usernameColumn;

    @FXML
    private TableColumn<Account,String> passwordColumn;

    @FXML
    private Menu accountMenu;

    @FXML
    private MenuItem changeMasterUsernameItem;
    @FXML
    private MenuItem changeMasterPasswordItem;
    @FXML
    private MenuItem deleteMasterAccountItem;
    @FXML
    private MenuItem logoutItem;

    @FXML
    private TextField searchField;

    @FXML
    private void initialize(){
        siteColumn.setCellValueFactory(cellData -> cellData.getValue().siteProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
    }

    public void setCurrentAccountAndStage(MasterAccount e, Stage stage){
        this.currentAccount = e;
        this.stage = stage;
        accountTableView.setItems(e.getAccountsList());
    }
}
