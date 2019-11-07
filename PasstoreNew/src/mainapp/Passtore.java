package mainapp;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.*;
import model.*;
import view.*;

/**MAIN PROGRAM STARTS HERE, FULL OF METHODS THAT START THE UI, IMPORTANT LOGIC HANDLED IN HANDLER.JAVA"**/

public class Passtore extends Application{

    public static void main(String[] args) {
        launch(args); //Calls the init(), then start() from parent class
    }

    /**First method that automatically gets called in Application after main**/
    @Override
    public void init(){
        Handler.initializeFromSaveFile();

        for(MasterAccount i : Handler.getMasterAccountsList()){
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
        loader.setLocation(getClass().getResource("/view/WelcomeUI.fxml"));

        try{
            VBox mainPane = (VBox) loader.load();
            WelcomeController controller = loader.getController();
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

    public void showAccountListUI(){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AccountListUI.fxml"));
        try{
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

    public void showChangeMasterAccountDetailsUI(MasterAccount currentAccount){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ChangeMasterAccountDetailsUI.fxml"));
        try{
            Scene scene = new Scene((VBox)loader.load());
            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Change Details");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            ChangeMasterAccountDetailsController controller = loader.getController();
            controller.setStage(stage);
            controller.setMasterAccount(currentAccount);
            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAddAccountUI(MasterAccount masterAccount){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddAccountUI.fxml"));
        try{
            Scene scene = new Scene((VBox)loader.load());
            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Add Account");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            AddAccountController controller = loader.getController();
            controller.setStage(stage);
            controller.setMasterAccount(masterAccount);
            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showMasterAccountCreationUI(){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MasterAccountCreationUI.fxml"));
        try{
            Scene scene = new Scene((VBox)loader.load());
            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Sign Up");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            MasterAccountCreationController controller = loader.getController();
            controller.setStage(stage);
            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAccountUI(String masterUsername){
        int indexOfThatAccount = Handler.login(masterUsername);
        MasterAccount e = Handler.getMasterAccountsList().get(indexOfThatAccount);

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AccountUI.fxml"));
        try{
            Scene scene = new Scene((AnchorPane)loader.load());
            AccountController controller = loader.getController();
            controller.setCurrentAccountAndStage(e, workingStage);
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
        loader.setLocation(getClass().getResource("/view/EditAccountDetailsUI.fxml"));
        try{
            Scene scene = new Scene((VBox)loader.load());
            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Edit Account");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            EditAccountDetailsController controller = loader.getController();
            controller.setStage(stage);
            controller.setAccount(account);

            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
