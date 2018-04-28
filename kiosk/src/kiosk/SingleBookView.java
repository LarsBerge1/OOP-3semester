
package kiosk;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

/**
 * Presents information about the single book
 * 
 */
public class SingleBookView  extends BookView {
    private SingleBook sb;
    public SingleBookView(SingleBook sb)
    {
     super(sb);   
     this.sb = sb;
    }
    
    /**
     * Displays the information associated with a SingleBook object
     */
    @Override
    public void display(VBox vBox)
    {
        super.display(vBox);
        Label l = new Label("Edition: " + sb.getEdition());
        vBox.getChildren().add(l);        
    }
}
