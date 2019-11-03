package EventHandlers;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class MyApp extends Application {

    Button left, right;
    Label text;
    @Override
    public void start(Stage primaryStage){
        text = new Label("You have not done either!");
        left = new Button("Click to left!");
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText("You have lefted!");
            }
        });
        right = new Button("Click to right!");
        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText("You have righted!");
            }
        });

        HBox pane = new HBox(15);
        pane.getChildren().addAll(left, right, text);

        Scene scene = new Scene(pane);


        primaryStage.setTitle("My App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
