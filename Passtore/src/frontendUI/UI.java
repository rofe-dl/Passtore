package frontendUI;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import logic.Passtore;

public class UI extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    //UI Elements
    Stage stage;
    Scene openingScene;
    TextField masterUsernameField;
    PasswordField masterPasswordField;
    Button loginButton, createMasterAccountButton, listMasterAccountsButton;

    String nameEntered, passwordEntered;

    @Override
    public void start (Stage primaryStage){
        stage = primaryStage;

        createMasterAccountButton = new Button("Sign up"); //creates buttons
        listMasterAccountsButton = new Button("List accounts");
        loginButton = new Button("Sign In");


        Label welcome = new Label("Passtore");//creates big title saying "Password"
        welcome.setFont(new Font(50));

        Label signIn = new Label("Sign in");//creates sign in text
        signIn.setFont(new Font(20));

        Label username = new Label("Username"), password = new Label("Password");//creates label for username and password

        masterUsernameField = new TextField(); masterUsernameField.setPromptText("Name"); masterUsernameField.setPrefWidth(380); //sets username and password fields
        masterPasswordField = new PasswordField(); masterPasswordField.setPromptText("Password"); masterPasswordField.setPrefWidth(380);

        this.nameEntered = masterUsernameField.getText();
        this.passwordEntered = masterPasswordField.getText();

        HBox signInRow = new HBox(signIn);
        HBox usernameRow = new HBox(15);
        HBox passwordRow = new HBox(16);
        HBox loginButtonRow = new HBox(loginButton);

        signInRow.setPadding(new Insets(0,0,0,25));
        usernameRow.setPadding(new Insets(0,0,0,25));
        usernameRow.getChildren().addAll(username, masterUsernameField);
        passwordRow.setPadding(new Insets(0,0,0,25));
        passwordRow.getChildren().addAll(password, masterPasswordField);
        loginButtonRow.setPadding(new Insets(0,0,0,421));

        HBox bottomButtons = new HBox(10, createMasterAccountButton, listMasterAccountsButton);
        bottomButtons.setPadding(new Insets(25,25,25,323));

        VBox mainPane = new VBox(15);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(welcome, signInRow, usernameRow, passwordRow, loginButtonRow, bottomButtons);

        loginButton.setOnAction(e -> this.login());
        createMasterAccountButton.setOnAction((e -> this.addNewMasterAccount()));

        openingScene = new Scene(mainPane, 510, 330);
        stage.setScene(openingScene);
        stage.setResizable(false);
        stage.setTitle("Passtore (Beta)");
        stage.show();
    }

    public void login(){
        //Passtore.login(nameEntered, passwordEntered);
    }

    public void addNewMasterAccount(){
        MasterAccountCreationUI.start();
    }
}

//Enum of passwordwrong, passwordmismatch
//Make FileReaderAndEditor
//Opening stage has login, create master account, list master accounts
//FileReaderAndEditor checks if name exists
//if exists, check if password correct, logs you in
//Create master account checks if master account with similar name exists
//Adds new master account
