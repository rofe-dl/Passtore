package SceneSwitcher;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class SceneSwitcher extends Application{

    //Adder variables
    Label whatWillBeAdded, calcResult;
    Button btnAddA, btnSubA, btnAddB, btnSubB, calculate, switchToCounter;
    int a = 0, b = 0, result = a + b;

    //Counter variables
    Label counterLabel;
    Button btnUp, btnDown, switchToAdder;
    int c = 0;

    Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;//so the stage is accessible in private methods

        /////////////////////// CODE DEALING WITH ADDER SOFTWARE

        whatWillBeAdded = new Label("You will add " + a + " and " + b);//initializes labels
        calcResult = new Label("Calculation: " + result);

        btnAddA = new Button("+A"); btnSubA = new Button("-A");//initializes buttons
        btnAddB = new Button("+B"); btnSubB = new Button("-B");
        calculate = new Button("Calculate");
        switchToCounter = new Button("Switch !");

        HBox buttons = new HBox(15);//creates the hbox that will hold line of buttons
        Region spacer1 = new Region(), spacer2 = new Region(); HBox.setHgrow(spacer1, Priority.ALWAYS); HBox.setHgrow(spacer2, Priority.ALWAYS);//creates growing regions
        buttons.getChildren().addAll(spacer1, btnAddA, btnSubA, btnAddB, btnSubB, spacer2);//adds the regions and buttons to the hbox

        //creates borderpanes for other nodes
        VBox box = new VBox(15); box.getChildren().addAll(new BorderPane(whatWillBeAdded), buttons, new BorderPane(calculate), new BorderPane(calcResult),
                new BorderPane(switchToCounter));

        box.setPadding(new Insets(10));
        Scene scene = new Scene(box, 500, 190);

        stage.setScene(scene); stage.setResizable(false);
        stage.setTitle("Scene Switcher");
        stage.show();

        btnAddA.setOnAction(e -> increaseA()); btnSubA.setOnAction(e -> decreaseA());//sets the functions for the buttons
        btnAddB.setOnAction(e -> increaseB()); btnSubB.setOnAction(e -> decreaseB());
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result = a + b;
                calcResult.setText("Calculation: " + result);
            }
        });
        /////////////////////////////////////

        // CODE DEALING WITH COUNTER SOFTWARE
        counterLabel = new Label(String.valueOf(c));
        btnUp = new Button("Increase"); btnDown = new Button("Decrease"); switchToAdder = new Button("Switch !");

        btnUp.setOnAction(e -> increase()); btnDown.setOnAction(e -> decrease());
        HBox box2 = new HBox(10); box2.setPadding(new Insets(15));
        box2.getChildren().addAll(switchToAdder, btnUp, btnDown, counterLabel);

        Scene scene2 = new Scene(box2, 300, 50);


        switchToAdder.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.setScene(scene);
            }
        });

        switchToCounter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
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

    private void increase(){
        c++;
        counterLabel.setText(String.valueOf(c));
    }

    private void decrease(){
        c--;
        counterLabel.setText(String.valueOf(c));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
