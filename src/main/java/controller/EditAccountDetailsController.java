package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;
import util.*;

/**
 * Controller for editing account details.
 */
public class EditAccountDetailsController extends Controller {
    /**
     * The master account this account belongs to.
     */
    private MasterAccount ma;
    /**
     * The account that is being edited
     */
    private Account a;
    
    private Stage stage;

    @FXML
    private Button editButton;
    @FXML
    private TextField site;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    /**
     * First method that runs when the corresponding fxml file is loaded.
     */
    @FXML
    private void initialize(){
        editButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                editButton.fire();
            }
        });
    }

    @FXML
    private void handleEditButton(){

        if (username.getText().trim().isEmpty() && site.getText().trim().isEmpty() && email.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            DialogBox.showError("At least one of the fields must have some value", "Empty Fields");
            return;
        }

        String newSite = site.getText().trim();
        String newEmail = email.getText().trim();
        String newUsername = username.getText().trim();
        String newPassword = password.getText().trim();

        if (AccountChecker.checkSiteExists(newSite, ma) && !this.a.getSite().equals(newSite)){
            DialogBox.showError("Account of this site already exists, please change site name", "Account Exists");
        }else{
            Updater.updateAccount(this.ma, this.a, newSite, newEmail, newUsername, newPassword);
            this.stage.close();
        }

    }

    public void setAccount(Account account){
        this.a = account;

        site.setText(this.a.getSite());
        email.setText(this.a.getEmail());
        username.setText(this.a.getUsername());
        password.setText(this.a.getPassword());
    }

    public void setMasterAccount(MasterAccount ma){
        this.ma = ma;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
