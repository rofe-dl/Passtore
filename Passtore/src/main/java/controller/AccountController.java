package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;
import util.*;

public class AccountController extends Controller{

    private MasterAccount masterAccount;
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
    private MenuItem changeMasterAccountDetailsItem;
    @FXML
    private MenuItem deleteMasterAccountItem;
    @FXML
    private MenuItem logoutItem;

    @FXML
    private void initialize(){
        /** sets the property each column will represent **/
        siteColumn.setCellValueFactory(cellData -> cellData.getValue().siteProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        addAccountButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                addAccountButton.fire();
            }
        });
        removeAccountButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                removeAccountButton.fire();
            }
        });
        editAccountButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                editAccountButton.fire();
            }
        });
    }

    @FXML
    private void handleChangeMasterAccountDetailsItem(){
        super.passtoreInstance.changeMasterAccountDetails(this.masterAccount);
        this.stage.setTitle("@" + this.masterAccount.getUsername());
    }

    @FXML
    private void handleDeleteMasterAccountItem(){
        boolean delete = DialogBox.showConfirmation("Are you sure you want to delete this master account? Changes will be IRREVERSIBLE", "Deletion Confirmation");
        if (delete){
            Handler.getMasterAccountsList().remove(this.masterAccount);
            DialogBox.showDialog("You will now be logged out", "Logging Out");
            Handler.saveToFile();
            handleLogoutItem();
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
            Account account = this.masterAccount.getAccountsList().get(index);

            this.masterAccount.getAccountsList().remove(account); //removes from observable
            this.masterAccount.getAccountsArrayList().remove(account); //removes from actual arraylist to get serialized

            Handler.updateThisMasterAccount(this.masterAccount);
        }
    }

    @FXML
    private void handleEditAccountButton(){
        if (this.accountTableView.getSelectionModel().getSelectedIndex() == -1) {
            DialogBox.showDialog("You haven't selected any account", "No Account Selected");
            return;
        }

        int index = this.accountTableView.getSelectionModel().getSelectedIndex();
        Account account = this.masterAccount.getAccountsList().get(index);

        this.passtoreInstance.editAccountDetails(account);

        Handler.updateThisMasterAccount(this.masterAccount);
    }

    @FXML
    private void handleAddAccountButton(){
        this.passtoreInstance.addAccount(this.masterAccount);
    }

    @FXML
    private void handleLogoutItem(){
        /** A new stage is passed instead of it's current one as current one may have been resized manually causing welcome screen to not retain intended size**/
        Stage newStage = new Stage();
        this.stage.close();
        super.passtoreInstance.start(newStage);
    }

    public void setCurrentAccount(MasterAccount e){
        this.masterAccount = e;
        this.accountTableView.setItems(masterAccount.getAccountsList());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
