
package kiosk;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Presents information about the literature
 * 
 */
public abstract class LiteratureView {
    private Literature literature;
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
        TextArea t = new TextArea("Title: " + literature.getTitle() + "\nPublisher: " + literature.getPublisher());
        v.getChildren().add(t);
    }
}
