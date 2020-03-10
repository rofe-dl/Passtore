package controller;

import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import model.*;

/**MAIN PROGRAM STARTS HERE, FULL OF 'SHOW' METHODS THAT STARTS MOST OF THE UI ELEMENTS"**/

public class Passtore extends Application{

    public static void main(String[] args) {
        launch(args); //Calls the init(), then start() from parent class
    }

    @Override
    public void init(){
        Handler.initializeFromSaveFile(); //loads save file

        for(MasterAccount i : Handler.getMasterAccountsList()){ //check method location for explanation
            i.deserializeIntoProperty();
            for (Account j : i.getAccountsList()){
                j.deserializeIntoProperty();
            }
        }
    }

    private Stage workingStage;
    private Scene workingScene;
    private FXMLLoader loader;

    /**
     * Loads the UI from the .fxml file into the global variable loader
     * @param fxmlDir  the directory of the .fxml file to load from
     * @return         the layout pane obtain from .load() as a Parent
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

    private void showPrimaryWindow(String title, boolean resize){
        this.workingStage.setTitle(title);
        this.workingStage.setResizable(resize);
        this.workingStage.setScene(this.workingScene);
        this.workingStage.getIcons().add(new Image("/view/ico.png"));

        this.workingStage.show();
    }

    private void showSecondaryWindow(String title, Stage stage, Scene scene){
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/view/ico.png"));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**Program starts**/
    @Override
    public void start(Stage primaryStage){
        this.workingStage = primaryStage;

        VBox mainPane = (VBox) loadFXML("/view/Welcome.fxml");
        this.workingScene = new Scene(mainPane);

        WelcomeController controller = loader.getController();
        controller.setProgramInstance(this); //sends an instance of the main program to call methods of this class

        showPrimaryWindow(controller, "Passtore", false);
    }

    public void showAccountListUI(){
        VBox mainPane = (VBox) loadFXML("/view/AccountList.fxml");
        showSecondaryWindow("Accounts", new Stage(), new Scene(mainPane));
    }

    
    public void changeMasterAccountDetails(MasterAccount masterAccount){
        VBox mainPane = (VBox) loadFXML("/view/ChangeMasterAccountDetails.fxml");
        Stage stage = new Stage();

        ChangeMAccountDetails controller = loader.getController();
        controller.setStage(stage); //makes a controller object and passes it the stage so it's stage.close() method can be called there
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

    public void showAccount(String masterUsername){

        int indexOfThatAccount = Handler.login(masterUsername);
        MasterAccount e = Handler.getMasterAccountsList().get(indexOfThatAccount); //finds the account with the given username

        AnchorPane mainPane = (AnchorPane) loadFXML("/view/Account.fxml");
        this.workingScene.setRoot(mainPane);

        AccountController controller = loader.getController();
        controller.setCurrentAccount(e); //passes the account found
        controller.setStage(this.workingStage);
        controller.setProgramInstance(this);

        workingStage.setMinHeight(300);
        workingStage.setMinWidth(600);
        workingStage.setResizable(true);
        workingStage.setScene(scene);
        workingStage.setTitle("@" + e.getUsername());
        workingStage.show();

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
