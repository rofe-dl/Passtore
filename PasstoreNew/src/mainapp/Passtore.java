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

    @Override
    public void start(Stage primaryStage){
        Stage stage = primaryStage;
        primaryStage.setTitle("Passtore");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/WelcomeUI.fxml"));
        VBox mainPane;

        listOfMasterAccounts.add(new MasterAccount("Hoo", "lol"));
        listOfMasterAccounts.add(new MasterAccount("Rofedl", "lol"));

        try{
            mainPane = (VBox) loader.load();
            primaryStage.setScene(new Scene(mainPane));
            primaryStage.setResizable(false);
            primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}
