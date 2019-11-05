package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import mainapp.Handler;
import mainapp.Passtore;

public class WelcomeController {
//    private Passtore programInstance;

    @FXML
    private void initialize(){
        signUpButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                signUpButton.fire();
            }
        });
        signInButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                signInButton.fire();
            }
        });
        listMasterAccountsButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                listMasterAccountsButton.fire();
            }
        });
    }

    @FXML
    private TextField masterUsernameField;

    @FXML
    private PasswordField masterPasswordField;

    @FXML
    private Button listMasterAccountsButton,  signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private void handleSignInButton(){
        if(!Handler.checkIfAccountExists(masterUsernameField.getText(), Passtore.getListOfMasterAccounts())){

            ShowDialogBox.showDialog(Alert.AlertType.ERROR, "No such username exists, please sign up","Username Not Found");
            return;
        }else if(!Handler.checkIfPasswordMatches(masterUsernameField.getText(),
                masterPasswordField.getText(),
                Passtore.getListOfMasterAccounts())){

            ShowDialogBox.showDialog(Alert.AlertType.ERROR, "Your password is incorrect","Wrong Password");
            return;
        }
    }

    @FXML
    private void handleSignUpButton(){
        new Passtore().showMasterAccountCreationUI();
    }

    @FXML
    private void handleListOfMasterAccountsButton(){
        new Passtore().showAccountListUI();
    }



}
