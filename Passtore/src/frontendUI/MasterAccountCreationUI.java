package frontendUI;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.Passtore;
import passtorefiles.FileReaderAndEditor;
import passtorefiles.MasterAccount;

import java.io.File;

public class MasterAccountCreationUI {

    static Stage stage;
    static TextField usernameField;
    static PasswordField passwordField, confirmPasswordField;

    public static void start(){
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sign Up");

        Label newUsernameLabel = new Label("Username              ");
        Label newPasswordLabel = new Label("Password               ");
        Label confirmPasswordLabel = new Label("Confirm Password ");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewMasterAccount());

        usernameField = new TextField();
        usernameField.setPrefColumnCount(25);
        passwordField = new PasswordField();
        passwordField.setPrefColumnCount(25);
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefColumnCount(25);

        HBox.setHgrow(usernameField, Priority.ALWAYS);
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        HBox.setHgrow(confirmPasswordField, Priority.ALWAYS);

        HBox usernameRow = new HBox(10,newUsernameLabel, usernameField);
        HBox passwordRow = new HBox(10, newPasswordLabel, passwordField);
        HBox confirmPasswordRow = new HBox(10, confirmPasswordLabel, confirmPasswordField);


        VBox mainPane = new VBox(10, usernameRow, passwordRow, confirmPasswordRow, addButton);
        mainPane.setPadding(new Insets(20));
        mainPane.setAlignment(Pos.BASELINE_RIGHT);

        stage.setScene(new Scene(mainPane));
        stage.showAndWait();
    }

    private static void addNewMasterAccount(){
        if(usernameField.getText().isEmpty()){
            MessageBox.giveMessage("Username field is empty! Please enter a name.", "Username Error");
            usernameField.requestFocus();
            return;
        }else if (!passwordField.getText().equals(confirmPasswordField.getText())){
            MessageBox.giveMessage("The passwords don't match!", "Password Error");
            return;
        }

        MasterAccount newMasterAccount = new MasterAccount(usernameField.getText(), passwordField.getText());

        if(FileReaderAndEditor.masterAccountExists(newMasterAccount)) {
            MessageBox.giveMessage("Username is already taken, please enter another", "Username Exists");
            usernameField.requestFocus();
            return;
        }

        Passtore.createNewMasterAccount(newMasterAccount);
        stage.close();
    }
}
