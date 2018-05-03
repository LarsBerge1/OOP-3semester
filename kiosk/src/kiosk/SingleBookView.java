
package kiosk;

import javafx.scene.control.Label;
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
     * @param infoArea the area where the information is shown
     */    
    @Override
    public void display(TextArea infoArea)
    {
        super.display(infoArea);
        infoArea.appendText("\nEdition: " + sb.getEdition());        
    }
    
}
