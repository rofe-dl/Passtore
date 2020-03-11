package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Account;
import util.*;

public class EditAccountDetailsController extends Controller {
    private Account account;
    private Stage stage;

    @FXML
    private Button editButton;
    @FXML
    private TextField site;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private void initialize(){
        editButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                editButton.fire();
            }
        });
    }

    @FXML
    private void handleEditButton(){

        if (username.getText().trim().isEmpty() && site.getText().trim().isEmpty() && email.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            DialogBox.showError("At least one of the fields must have some value", "Empty Fields");
            return;
        }

        this.account.setSite(this.site.getText());
        this.account.setEmail(this.email.getText());
        this.account.setUsername(this.username.getText());
        this.account.setPassword(this.password.getText());


        this.stage.close();
    }

    public void setAccount(Account account){
        this.account = account;

        this.site.setText(this.account.getSite());
        this.email.setText(this.account.getEmail());
        this.username.setText(this.account.getUsername());
        this.password.setText(this.account.getPassword());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
