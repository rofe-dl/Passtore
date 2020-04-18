package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import util.*;

/**
 * Controller for the main welcome screen
 */
public class WelcomeController extends Controller{

    @FXML
    private TextField masterUsernameField;

    @FXML
    private PasswordField masterPasswordField;

    @FXML
    private Button listMasterAccountsButton,  signUpButton;

    @FXML
    private Button signInButton;

    /**
     * First method that runs when the corresponding fxml file is loaded.
     */
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

    /** methods starting with handle in these controllers shows the function that gets called when the button is pressed **/
    @FXML
    private void handleSignInButton(){
        if(!MasterAccountChecker.checkAccountExists( masterUsernameField.getText().trim() )){

            DialogBox.showError("No such username exists, please sign up","Username Not Found");
            return;

        }else if(!MasterAccountChecker.checkPassword(masterUsernameField.getText().trim(), masterPasswordField.getText())){

            DialogBox.showError("Your password is incorrect","Wrong Password");
            return;
            
        }

        super.passtoreInstance.masterAccountScreen(masterUsernameField.getText().trim());
    }

    @FXML
    private void handleSignUpButton(){
        super.passtoreInstance.addMasterAccount();
    }

    @FXML
    private void handleListOfMasterAccountsButton(){
        super.passtoreInstance.masterAccountsList();
    }

}
