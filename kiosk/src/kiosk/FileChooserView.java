

package kiosk;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author berge
 */
public class FileChooserView {
    
    
    
    public static void fileChooserView(){
         Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Text Files", "*.txt"));
        fileChooser.showOpenDialog(window);
    }
}
