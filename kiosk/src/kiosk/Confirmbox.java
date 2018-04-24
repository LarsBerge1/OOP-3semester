package kiosk;

import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author berge
 */
class Confirmbox {
    
    static boolean answer;
    
    
    
    
    
    public static boolean display(String title, String message){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        Label label = new Label(message);
        
        Button yesBtn = new Button("Yes");
        yesBtn.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button noBtn = new Button("No");
        noBtn.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesBtn, noBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;   
    }
    
    /**
     * Confirm box to confirm the action spesified.
     *
     * @param title The title of the confirmbox.
     * @param message The message to the user.
     * @return True if user press OK, else false.
     */
    public static boolean confirmBox(String title, String message) {
        boolean answer = false;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            answer = true;
        } else if (result.get() == ButtonType.CANCEL) {
            answer = false;
        }
        return answer;
    }

}
