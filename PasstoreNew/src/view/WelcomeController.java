package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import mainapp.Handler;
import mainapp.Passtore;

public class WelcomeController {
    private Passtore passtoreInstance;

    public void setPasstoreInstance(Passtore passtoreInstance){
        this.passtoreInstance = passtoreInstance;
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
    private void handleSignInButton(){
        if(!Handler.checkIfAccountExists( masterUsernameField.getText() )){

            DialogBox.showError("No such username exists, please sign up","Username Not Found");
            return;

        }else if(!Handler.checkIfPasswordMatches(masterUsernameField.getText(), masterPasswordField.getText())){

            DialogBox.showError("Your password is incorrect","Wrong Password");
            return;
        }

        this.passtoreInstance.showAccountUI(masterUsernameField.getText());
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
