package kiosk;

import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author berge
 */
public class FileChooserView {
    
    
    /**
     * Returns value specifies the file chosen by the 
     * user or null if no selection has been made.
     * @return the selected file.
     */
    public static File textFileChooserView(){
         Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(window);
        return selectedFile ;
        
    }
}
