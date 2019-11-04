package frontendUI;

import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.*;

public class ConfirmationBox {

    static boolean yesOrNo = false;
    static Stage stage;

    public static boolean yesOrNo(String message){


        stage = new Stage();
        stage.setTitle("Confirmation Box");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        Button yes = new Button("Yes");
        Button no = new Button ("No");
        yes.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                yesOrNo = true;
                stage.close();
            }
        });
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yesOrNo = false;
                stage.close();
            }
        });

        Label text = new Label(message);

        HBox box = new HBox(20, yes, no);
        box.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(15, text, box);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 40, 20 ,40));

        stage.setScene(new Scene(vbox));
        stage.showAndWait();
        return yesOrNo;
    }
}
