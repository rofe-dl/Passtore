package controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;
import util.*;
import javafx.scene.control.*;

public class AddAccountController extends Controller {

    private MasterAccount currentMasterAccount;
    private Stage stage;

    @FXML
    private Button addButton;
    @FXML
    private TextField username;
    @FXML
    private TextField site;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private void initialize(){
        addButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                addButton.fire();
            }
        });
    }

    @FXML
    private void handleAddButton(){
        if (username.getText().trim().isEmpty() && site.getText().trim().isEmpty() && email.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            DialogBox.showError("At least one of the fields must have some value", "Empty Fields");
            return;
        }


        this.currentMasterAccount.getAccountsList().add( new Account( site.getText(),  email.getText(), username.getText(), password.getText() ) );
        //^ adds to observable list
        this.currentMasterAccount.getAccountsArrayList().add( new Account ( site.getText(),  email.getText(), username.getText(), password.getText() ) );
        // ^adds to actual arraylist to get serialized later

        Handler.updateThisMasterAccount(this.currentMasterAccount);

        this.stage.close();
    }



    @FXML
    public void setMasterAccount(MasterAccount e){
        this.currentMasterAccount = e;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
