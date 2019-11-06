package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogBox {
    public static void showError(String message, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/view/ico.png"));

        alert.showAndWait();
    }

    public static boolean showConfirmation(String message, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/view/ico.png"));

        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK)? true : false;
    }

    public static void showDialog(String message, String title){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);


        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/view/ico.png"));

        alert.showAndWait();
    }
}
