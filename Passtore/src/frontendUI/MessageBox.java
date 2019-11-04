package frontendUI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.*;

public class MessageBox {

    static Stage stage;

    public static void giveMessage(String message, String title){


        stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        Button okButton = new Button("OK");

        okButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
            }
        });

        Label text = new Label(message);

        HBox okRow = new HBox(20, okButton);
        okRow.setAlignment(Pos.CENTER);

        VBox mainPane = new VBox(25, text, okRow);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(20, 60, 20 ,60));

        stage.setScene(new Scene(mainPane));
        stage.showAndWait();
    }

}
