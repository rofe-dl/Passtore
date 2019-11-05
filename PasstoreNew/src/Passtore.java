import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.*;

import model.MasterAccount;

public class Passtore extends Application{
    private ObservableSet<MasterAccount> listOfMasterAccounts = FXCollections.observableSet();

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Passtore");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/UI.fxml"));
        VBox mainPane;

        try{
            mainPane = (VBox) loader.load();
            primaryStage.setScene(new Scene(mainPane));
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
