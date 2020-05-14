package util;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import java.util.Optional;

/** class that can show a dialog box with any message **/
public class DialogBox {

    public static Alert setupBox(String message, String title, AlertType boxType){
        Alert alert = new Alert(boxType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/view/ico.png"));
        return alert;
    }

    public static void showError(String message, String title){
        setupBox(message, title, AlertType.ERROR).showAndWait();
    }

    public static boolean showConfirmation(String message, String title){
        Optional<ButtonType> result = setupBox(message, title, AlertType.CONFIRMATION).showAndWait();;
        return result.get() == ButtonType.OK;
    }

    public static void showDialog(String message, String title){
        setupBox(message, title, AlertType.INFORMATION).showAndWait();
    }

}
