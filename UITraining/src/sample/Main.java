package sample;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Main extends Application {

    Button bt; Label lb; int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("My Test Program");
        bt = new Button("Click me !");
        bt.setOnAction(e -> clickButton());
        lb = new Label("You have not clicked the button yet.");

        BorderPane pane = new BorderPane();
        pane.setCenter(bt);
        pane.setBottom(lb);

        Scene scene = new Scene(pane, 300, 400);


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void clickButton(){
       count++;
       lb.setText("You have clicked the button " + count + " time(s)");
    }
}
