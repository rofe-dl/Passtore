package controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.*;
import javafx.scene.control.*;
import model.MasterAccount;
import util.*;

public class MasterAccountCreationController extends Controller {
    Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField  confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private void handleSignUpButton(){
        if (usernameField.getText().trim().isEmpty()){
            DialogBox.showError("Username cannot be empty","Invalid Username");
            return;
        }else if(!passwordField.getText().equals(confirmPasswordField.getText())){
            DialogBox.showError("Your passwords don't match","Password Mismatch");
            return;
        }else if(Handler.checkIfAccountExists( this.usernameField.getText().trim() )){
            DialogBox.showError("Username already exists! Please try another","Username Exists");
            return;
        }

        Handler.getMasterAccountsList().add(new MasterAccount(usernameField.getText().trim(), passwordField.getText()));
        Handler.saveToFile();

        this.stage.close();

    }

    @FXML
    private void initialize(){
        signUpButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                signUpButton.fire();
            }
        });
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
