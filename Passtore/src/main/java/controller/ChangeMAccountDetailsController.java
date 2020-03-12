package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;
import util.*;

/**
 * Controller for editing master account details.
 */
public class ChangeMAccountDetailsController extends Controller{

    Stage stage;
    MasterAccount ma;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField  confirmPasswordField;

    @FXML
    private Button changeButton;

    @FXML
    private void handleChangeButton(){
        if (usernameField.getText().trim().isEmpty()){
            
            DialogBox.showError("Username cannot be empty","Invalid Username");

        }else if(!passwordField.getText().equals(confirmPasswordField.getText())){

            DialogBox.showError("Your passwords don't match","Password Mismatch");

        }else if(MasterAccountChecker.checkAccountExists( usernameField.getText().trim() ) && !usernameField.getText().trim().equals(ma.getUsername())){

            DialogBox.showError("Username already exists! Please try another","Username Exists");

        }else{

            String newUsername = usernameField.getText().trim();
            String newPassword = passwordField.getText();
            
            Updater.updateMasterAccount(this.ma, newUsername, newPassword);
            this.stage.close();
        }

    }

    /**
     * First method that runs when the corresponding fxml file is loaded.
     */
    @FXML
    private void initialize(){
        changeButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                changeButton.fire();
            }
        });
    }

    public void setMasterAccount(MasterAccount ma){
        this.ma = ma;

        usernameField.setText(this.ma.getUsername());
        passwordField.setText(this.ma.getPassword());
        confirmPasswordField.setText(this.ma.getPassword());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
