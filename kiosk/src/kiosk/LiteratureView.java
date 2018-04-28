
package kiosk;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Presents information about the literature
 * 
 */
public abstract class LiteratureView {
    private final Literature literature;
    public LiteratureView(Literature literature)
    {
        this.literature = literature;
    }
    
    /**
     * Displays the information associated with an literature object
     * @vBox the Vbox where the product information is displayed
     */
    public void display(VBox v)
    {
        
        TextField title = new TextField("Title: " + literature.getTitle());
        TextField publisher = new TextField("Publisher: " + literature.getPublisher());
        v.getChildren().addAll(title, publisher);
    }
}
