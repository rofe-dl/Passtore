package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainapp.Handler;
import mainapp.Passtore;
import model.*;

public class AccountController {

    private MasterAccount masterAccount;
    private Passtore passtoreInstance;
    private Stage stage;

    @FXML
    private Button addAccountButton;

    @FXML
    private Button editAccountButton;

    @FXML
    private Button removeAccountButton;

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
    private MenuItem changeAccountDetailsItem;
    @FXML
    private MenuItem deleteMasterAccountItem;
    @FXML
    private MenuItem logoutItem;

    @FXML
    private void initialize(){
        siteColumn.setCellValueFactory(cellData -> cellData.getValue().siteProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
    }

    @FXML
    private void handleChangeAccountDetailsItem(){
        this.passtoreInstance.showChangeAccountDetailsUI(this.masterAccount);
        this.stage.setTitle("@" + this.masterAccount.getUsername());
    }

    @FXML
    private void handleDeleteMasterAccountItem(){
        boolean delete = DialogBox.showConfirmation("Are you sure you want to delete this master account? Changes will be IRREVERSIBLE", "Deletion Confirmation");
        if (delete){
            Handler.getMasterAccountsList().remove(this.masterAccount);
            DialogBox.showDialog("You will now be logged out", "Logging Out");
            Handler.saveToFile();
            this.passtoreInstance.start(this.stage);
        }
    }

    @FXML
    private void handleRemoveAccountButton(){
        if (this.accountTableView.getSelectionModel().getSelectedIndex() == -1) {
            DialogBox.showDialog("You haven't selected any account", "No Account Selected");
            return;
        }
        boolean delete = DialogBox.showConfirmation("Are you sure you want to remove this account?", "Removal Confirmation");
        if (delete){
            int index = this.accountTableView.getSelectionModel().getSelectedIndex();
            this.masterAccount.getAccountsListAsArrayList().remove(index);
            Handler.updateThisMasterAccount(this.masterAccount);
            assignListToTable(this.masterAccount);
        }
    }

    @FXML
    private void handleEditAccountButton(){

    }

    @FXML
    private void handleAddAccountButton(){
        this.passtoreInstance.showAddAccountUI(this.masterAccount);
        assignListToTable(this.masterAccount);
    }

    @FXML
    private void handleLogoutItem(){
        this.passtoreInstance.start(this.stage);
    }

    public void setCurrentAccountAndStage(MasterAccount e, Stage stage){
        this.masterAccount = e;
        this.stage = stage;
        assignListToTable(e);
    }

    private void assignListToTable(MasterAccount masterAccount){
        accountTableView.setItems(masterAccount.getAccountsList());
    }

    public void setPasstoreInstance(Passtore passtoreInstance){
        this.passtoreInstance = passtoreInstance;
    }
}
