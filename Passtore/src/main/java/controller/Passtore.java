package controller;

import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import model.*;
import util.*;

/**
 * Passtore is a program that can store all your different account passwords under
 * a single master account, so only remembering one password gives you access to
 * all your passwords. Don't recommend using it for storing very sensitive
 * data as passwords are still stored as simple strings in this version in a SQLite
 * database.
 * 
 * @author Rafidul Islam
 * @version 2.0
 * @since 12-03-2020
 */
public class Passtore extends Application{

    /**
     * Current stage of the main window
     */
    private Stage workingStage;
    /**
     * Current scene of the main window
     */
    private Scene workingScene;
    /**
     * Loader used to load the UI from FXML files
     */
    private FXMLLoader loader;


    public static void main(String[] args) {
        launch(args); //Calls the init() first, then start() from parent class
    }

    /**
     * Connects the program to the database and fills up data if it exists
     */
    @Override
    public void init(){
        SQLiteConnector.connect();
        Updater.populateMasterAccounts();
    }

    /**
     * Loads the UI from the .fxml file into the global variable loader
     * @param fxmlDir  the directory of the .fxml file to load from
     * @return         the layout pane obtain from .load() as a Parent, to be downcasted from caller
     */
    private Parent loadFXML(String fxmlDir){
        Parent parent = null;
        try{
            loader = new FXMLLoader(getClass().getResource(fxmlDir));
            parent = loader.load();
        }catch (IOException e){
            e.printStackTrace(); //should never happen
        }
    
        return parent;
    }

    /**
     * Used to show or change the primary working window.
     * Only used twice.
     * @param title new title of the stage
     * @param resize whether the stage will be resizable or not
     */
    private void showPrimaryWindow(String title, boolean resize){
        this.workingStage.setTitle(title);
        this.workingStage.setResizable(resize);
        this.workingStage.setScene(this.workingScene);
        this.workingStage.getIcons().add(new Image("/view/ico.png"));

        this.workingStage.show();
    }

    /**
     * Used to show secondary small windows like account creations or editors or small lists
     * 
     * @param title title of the stage
     * @param stage a new stage object, so primary working stage isn't replaced
     * @param scene scene to be used in this window
     */
    private void showSecondaryWindow(String title, Stage stage, Scene scene){
        stage.setTitle(title);
        stage.setResizable(false); //secondary windows never resizable
        stage.setScene(scene);
        stage.getIcons().add(new Image("/view/ico.png"));

        stage.initModality(Modality.APPLICATION_MODAL); //prevents other windows from receiving input
        stage.showAndWait();
    }

    /**
     * Start method called from the javafx Application class
     * @param primaryStage a new stage passed by default from Application class
     */
    @Override
    public void start(Stage primaryStage){
        this.workingStage = primaryStage;

        VBox mainPane = (VBox) loadFXML("/view/Welcome.fxml");
        this.workingScene = new Scene(mainPane);

        WelcomeController controller = loader.getController();
        controller.setProgramInstance(this); //sends an instance of the main program to call methods of this class

        showPrimaryWindow("Passtore", false);
    }

    /**
     * Logs the user into the program and shows their account screen
     * @param masterUsername the main username of the account owner
     */
    public void showAccount(String masterUsername){

        int indexOfThatAccount = MasterAccountChecker.login(masterUsername);
        MasterAccount e = Updater.getMasterAccountsList().get(indexOfThatAccount); //finds the account with the given username

        AnchorPane mainPane = (AnchorPane) loadFXML("/view/Account.fxml");
        this.workingScene.setRoot(mainPane);

        AccountController controller = loader.getController();
        controller.setCurrentAccount(e); //passes the account found
        controller.setStage(this.workingStage); //sends the current stage so it can be closed from the controller
        controller.setProgramInstance(this); //sends the instance of the running program so methods in this class can be called

        workingStage.setMinHeight(300);
        workingStage.setMinWidth(600);
        showPrimaryWindow("@" + e.getUsername(), true);

    }

    /**
     * Shows the list of master accounts in the database
     */
    public void showAccountListUI(){
        VBox mainPane = (VBox) loadFXML("/view/AccountList.fxml");
        showSecondaryWindow("Accounts", new Stage(), new Scene(mainPane));
    }

    /**
     * Change details of a master account, like username or password
     * @param masterAccount the masteraccount whose details to be changed
     */
    public void changeMasterAccountDetails(MasterAccount masterAccount){
        VBox mainPane = (VBox) loadFXML("/view/ChangeMasterAccountDetails.fxml");
        Stage stage = new Stage();

        ChangeMAccountDetailsController controller = loader.getController();
        controller.setStage(stage); //sends the stage so it can be closed from the controller
        controller.setMasterAccount(masterAccount); 

        showSecondaryWindow("Change Details", stage, new Scene(mainPane));
    }

    public void addAccount(MasterAccount masterAccount){
        VBox mainPane = (VBox) loadFXML("/view/AddAccount.fxml");
        Stage stage = new Stage();

        AddAccountController controller = loader.getController();
        controller.setStage(stage); 
        controller.setMasterAccount(masterAccount); 

        showSecondaryWindow("Add Account", stage, new Scene(mainPane));
    }

    public void masterAccountCreation(){
        VBox mainPane = (VBox) loadFXML("/view/MasterAccountCreation.fxml");
        Stage stage = new Stage();

        MasterAccountCreationController controller = loader.getController();
        controller.setStage(stage); 
        
        showSecondaryWindow("Sign Up", stage, new Scene(mainPane));
    }


    public void editAccountDetails(Account account){
        VBox mainPane = (VBox) loadFXML("/view/EditAccountDetails.fxml");
        Stage stage = new Stage();

        EditAccountDetailsController controller = loader.getController();
        controller.setStage(stage); 
        controller.setAccount(account);

        showSecondaryWindow("Edit Account", stage, new Scene(mainPane));
    }


}
