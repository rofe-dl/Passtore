package SceneSwitcher;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class SceneSwitcher extends Application{

    Label whatWillBeAdded, calcResult;
    Button btnAddA, btnSubA, btnAddB, btnSubB, switchToCounter;
    int a = 0, b = 0, result = a + b;

    Label counterLabel;
    Button btnUp, switchToAdder;

    Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        whatWillBeAdded = new Label("You will add " + a + " and " + b);
        calcResult = new Label("Calculation: " + result);
        btnAddA = new Button("+A"); btnSubA = new Button("-A");
        btnAddB = new Button("+B"); btnSubB = new Button("-B");
        switchToCounter = new Button("Switch !");

        HBox buttons = new HBox(10); HBox.setMargin(buttons, new Insets(10));
        buttons.getChildren().addAll(btnAddA, btnSubA, btnAddB, btnSubB); buttons.setSpacing(15);
        BorderPane adderLabelPos = new BorderPane(whatWillBeAdded), switcherPos = new BorderPane(switchToCounter)
                , calcResultPos = new BorderPane(calcResult);

        VBox box = new VBox(); box.getChildren().addAll(adderLabelPos, buttons, switcherPos, calcResultPos);
        box.setSpacing(15);
        Scene scene = new Scene(box);

        stage.setScene(scene);
        stage.setTitle("Scene Switcher");
        stage.show();

        btnAddA.setOnAction(e -> increaseA()); btnSubA.setOnAction(e -> decreaseA());
        btnAddB.setOnAction(e -> increaseB()); btnSubB.setOnAction(e -> decreaseB());
        switchToCounter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //stage.setScene();
            }
        });

    }

    private void increaseA(){
        a++;
        whatWillBeAdded.setText("You will add " + a + " and " + b);
    }

    private void decreaseA(){
        --a;
        whatWillBeAdded.setText("You will add " + a + " and " + b);
    }

    private void increaseB(){
        b++;
        whatWillBeAdded.setText("You will add " + a + " and " + b);
    }

    private void decreaseB(){
        b--;
        whatWillBeAdded.setText("You will add " + a + " and " + b);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
