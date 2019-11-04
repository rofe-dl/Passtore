package frontendUI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.*;

public class MessageBox {


    public static void giveMessage(String message, String title){
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        Button okButton = new Button("   OK   ");

        okButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
            }
        });

        Label text = new Label(message);

        HBox okRow = new HBox(20, okButton);
        okRow.setAlignment(Pos.BOTTOM_RIGHT);

        VBox mainPane = new VBox(25, text, okRow);
        mainPane.setAlignment(Pos.TOP_RIGHT);
        mainPane.setPadding(new Insets(20, 60, 20 ,60));

        stage.setScene(new Scene(mainPane));
        stage.showAndWait();
    }

}
