/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author berge
 */
public class AlertBox {
    
    /**
     * Information box to inform the user
     * @param title The title of the alertbox
     * @param message The message to the user
     */
    public static void information(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
    
    public static String stringInputBox(String title, String message){
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label(message);
        
        Button okBtn = new Button("OK");
        okBtn.setOnAction( e -> window.close() );
        
        
        VBox layout = new VBox();
        TextField inputField = new TextField();
        String input = inputField.getText();
        
        layout.getChildren().addAll(label,inputField);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return input;
    }
    
    
}
