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
        launch(args); //Calls the init(), then start() in extended Application
    }

    /**First method that automatically gets called in Application after main**/
    @Override
    public void init(){
        Handler.initializeFromSaveFile();
    }

    private Stage workingStage;

    /**Program starts**/
    @Override
    public void start(Stage primaryStage){

        this.workingStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
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
        FXMLLoader loader = new FXMLLoader();
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

    public void showChangeAccountDetailsUI(MasterAccount currentAccount){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ChangeAccountDetailsUI.fxml"));
        try{
            Scene scene = new Scene((VBox)loader.load());
            Stage stage = new Stage();

            stage.setResizable(false);
            stage.setTitle("Change Details");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            ChangeAccountDetailsController controller = loader.getController();
            controller.setStage(stage);
            controller.setMasterAccount(currentAccount);
            stage.getIcons().add(new Image("/view/ico.png"));
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAddAccountUI(MasterAccount masterAccount){
        FXMLLoader loader = new FXMLLoader();
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
        FXMLLoader loader = new FXMLLoader();
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AccountUI.fxml"));
        try{
            Scene scene = new Scene((AnchorPane)loader.load());
            AccountController controller = loader.getController();
            controller.setCurrentAccountAndStage(e, workingStage);
            controller.setPasstoreInstance(this);

            workingStage.setResizable(true);
            workingStage.setScene(scene);
            workingStage.setTitle("@" + e.getUsername());
            workingStage.show();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
