package view;

import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.control.*;
import mainapp.Passtore;
import model.MasterAccount;

public class MasterAccountCreationController {
    Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField  confirmPasswordField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private void handleSignUpButton(){
        if (usernameField.getText().isEmpty()){
            ShowDialogBox.showDialog(Alert.AlertType.ERROR, "Username cannot be empty",
                    "Invalid Username");
            return;
        }else if(!passwordField.getText().equals(confirmPasswordField.getText())){
            ShowDialogBox.showDialog(Alert.AlertType.ERROR, "Your passwords don't match",
                    "Password Mismatch");
            return;
        }

        Passtore.getListOfMasterAccounts().add(new MasterAccount(usernameField.getText(), passwordField.getText()));
        this.stage.close();

    }

    @FXML
    private void initialize(){

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
