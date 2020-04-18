package controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;
import util.*;
import javafx.scene.control.*;

/**
 * Controller for adding an account to a master account
 */
public class AddAccountController extends Controller {
    /**
     * The master account the account will be added to
     */
    private MasterAccount ma;
    private Stage stage;

    @FXML
    private Button addButton;
    @FXML
    private TextField username;
    @FXML
    private TextField site;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    /**
     * First method that runs when the corresponding fxml file is loaded.
     */
    @FXML
    private void initialize(){
        addButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                addButton.fire();
            }
        });
    }

    @FXML
    private void handleAddButton(){

        if (username.getText().trim().isEmpty() && site.getText().trim().isEmpty() && email.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            DialogBox.showError("At least one of the fields must have some value", "Empty Fields");
            return;
        }
        
        Account newAccount = new Account( site.getText().trim(),  email.getText().trim(), username.getText().trim(), password.getText() );

        if(AccountChecker.checkSiteExists(site.getText().trim(), this.ma)){
            DialogBox.showError("Account of this site already exists, please change site name", "Account Exists");
        }else{
            Updater.addAccount(newAccount, this.ma);
            this.stage.close();
        }

    }

    @FXML
    public void setMasterAccount(MasterAccount ma){
        this.ma = ma;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
