package mainapp;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import model.*;
import view.*;

/**MAIN PROGRAM STARTS HERE, FULL OF 'SHOW' METHODS THAT STARTS MOST OF THE UI ELEMENTS"**/

public class Passtore extends Application{

    public static void main(String[] args) {
        launch(args); //Calls the init(), then start() from parent class
    }

    

    /**First method that automatically gets called in Application after main**/
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
    private static FXMLLoader loader;

    /**Program starts**/
    @Override
    public void start(Stage primaryStage){

        this.workingStage = primaryStage;
        loader = new FXMLLoader();

        try{

            /** two common lines in all methods here, loads the respective UI from the fxml file and calls it's respective controller to control UI elements**/
            loader.setLocation(getClass().getResource("/view/WelcomeUI.fxml"));
            VBox mainPane = (VBox) loader.load();

            WelcomeController controller = loader.getController();

            /**sends an instance of main program to ui controller so other methods here can be called **/
            controller.setPasstoreInstance(this);

            workingStage.setTitle("Passtore");
            workingStage.setScene(new Scene(mainPane));
            workingStage.setResizable(false);
            workingStage.getIcons().add(new Image("/view/ico.png"));
            workingStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** shows UI of the list of master accounts **/
    public void showAccountListUI(){

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/AccountListUI.fxml"));
            Scene scene = new Scene((VBox)loader.load());

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Accounts");
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** shows UI of the box that can edit master account details**/
    public void showChangeMasterAccountDetailsUI(MasterAccount currentAccount){

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/ChangeMasterAccountDetailsUI.fxml"));
            Scene scene = new Scene((VBox)loader.load());

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Change Details");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            ChangeMasterAccountDetailsController controller = loader.getController();
            controller.setStage(stage); //makes a controller object and passes it the stage so it's stage.close() method can be called there
            controller.setMasterAccount(currentAccount); //passes the master account whose details will be edited

            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** shows UI to add an account to a master account **/
    public void showAddAccountUI(MasterAccount masterAccount){

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/AddAccountUI.fxml"));
            Scene scene = new Scene((VBox)loader.load());

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Add Account");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            AddAccountController controller = loader.getController();
            controller.setStage(stage); //makes a controller object and passes it the stage so it's stage.close() method can be called there
            controller.setMasterAccount(masterAccount); //passes master account to which a new account will be added

            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** shows UI that creates new master account **/
    public void showMasterAccountCreationUI(){

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/MasterAccountCreationUI.fxml"));
            Scene scene = new Scene((VBox)loader.load());

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Sign Up");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            MasterAccountCreationController controller = loader.getController();
            controller.setStage(stage); //makes a controller object and passes it the stage so it's stage.close() method can be called there

            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** shows main UI after logging into a master account **/
    public void showAccountUI(String masterUsername){

        int indexOfThatAccount = Handler.login(masterUsername);
        MasterAccount e = Handler.getMasterAccountsList().get(indexOfThatAccount); //finds the account with the given username

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/AccountUI.fxml"));
            Scene scene = new Scene((AnchorPane)loader.load());

            AccountController controller = loader.getController();
            controller.setCurrentAccountAndStage(e, workingStage); //passes the account found, working stage is passed as the welcome stage is now replaced
            controller.setPasstoreInstance(this);

            workingStage.setMinHeight(300);
            workingStage.setMinWidth(600);
            workingStage.setResizable(true);
            workingStage.setScene(scene);
            workingStage.setTitle("@" + e.getUsername());
            workingStage.show();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void showEditAccountDetailsUI(Account account){

        loader = new FXMLLoader();

        try{
            loader.setLocation(getClass().getResource("/view/EditAccountDetailsUI.fxml"));
            Scene scene = new Scene((VBox)loader.load());

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Edit Account");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            EditAccountDetailsController controller = loader.getController();
            controller.setStage(stage); //makes a controller object and passes it the stage so it's stage.close() method can be called there
            controller.setAccount(account);

            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
