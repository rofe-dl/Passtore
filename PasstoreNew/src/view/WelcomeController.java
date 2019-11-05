package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mainapp.Passtore;

public class WelcomeController {
//    private Passtore programInstance;

    @FXML
    private void initialize(){

    }

    @FXML
    private Button listMasterAccountsButton, signInButton, signUpButton;

    @FXML
    private void handleSignInButton(){

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
