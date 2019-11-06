package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import mainapp.Handler;
import mainapp.Passtore;

public class WelcomeController {
    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

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

        new Passtore().showAccountUI(masterUsernameField.getText(), stage);
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
