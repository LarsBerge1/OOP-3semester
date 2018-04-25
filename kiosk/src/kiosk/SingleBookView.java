
package kiosk;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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
        TextArea t = new TextArea("Edition " + sb.getEdition());
        vBox.getChildren().add(t);        
    }
}
