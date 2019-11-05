package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class ShowDialogBox {
    public static void showDialog(AlertType type, String message, String title){
        if(type.equals(AlertType.ERROR)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }
}
