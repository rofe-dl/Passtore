package mainapp;

import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.*;

import model.*;
import view.*;

public class Passtore extends Application{
    private static ObservableList<MasterAccount> listOfMasterAccounts = FXCollections.observableArrayList();

    public static ObservableList<MasterAccount> getListOfMasterAccounts(){
        return listOfMasterAccounts;
    }

    private Stage workingStage;
    @Override
    public void start(Stage primaryStage){

        this.workingStage = primaryStage;
        workingStage.setTitle("Passtore");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/WelcomeUI.fxml"));
        WelcomeController controller = loader.getController();


        MasterAccount rofedl = new MasterAccount("u", "p");
        rofedl.getAccountsList().add(new Account("si", "em", "us", "p"));
        listOfMasterAccounts.add(new MasterAccount("Hoo", "lol"));
        listOfMasterAccounts.add(rofedl);


        try{
            VBox mainPane = (VBox) loader.load();
            controller.setStage(this.workingStage);
            workingStage.setScene(new Scene(mainPane));
            workingStage.setResizable(false);
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
            stage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAccountUI(String masterUsername, Stage workingStage){
        MasterAccount e = getListOfMasterAccounts().get(Handler.login(masterUsername));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AccountUI.fxml"));
        try{
            Scene scene = new Scene((AnchorPane)loader.load());
            this.workingStage = workingStage;
            this.workingStage.setScene(scene);

            this.workingStage.setTitle("@" + e.getUsername());

            AccountController controller = loader.getController();
            controller.setCurrentAccountAndStage(e,this.workingStage);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
