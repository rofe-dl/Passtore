package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import mainapp.*;
import model.*;

public class ChangeAccountDetailsController {

    Stage stage;
    MasterAccount currentMasterAccount;

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
        if (usernameField.getText().isEmpty()){
            DialogBox.showError("Username cannot be empty","Invalid Username");
            return;
        }else if(!passwordField.getText().equals(confirmPasswordField.getText())){
            DialogBox.showError("Your passwords don't match","Password Mismatch");
            return;
        }else if(Handler.checkIfAccountExists( this.usernameField.getText() ) && !this.usernameField.getText().equals(currentMasterAccount.getUsername())){
            DialogBox.showError("Username already exists! Please try another","Username Exists");
            return;
        }

        this.currentMasterAccount.setPassword(this.passwordField.getText());
        this.currentMasterAccount.setUsername(this.usernameField.getText());

        Handler.updateThisMasterAccount(this.currentMasterAccount);

        this.stage.close();

    }

    @FXML
    private void initialize(){
        changeButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                changeButton.fire();
            }
        });
    }

    public void setMasterAccount(MasterAccount currentAccount){
        this.currentMasterAccount = currentAccount;
        this.usernameField.setText(currentAccount.getUsername());
        this.passwordField.setText(currentAccount.getPassword());
        this.confirmPasswordField.setText(currentAccount.getPassword());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
