
package kiosk;

import javafx.scene.control.Label;
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
     * @param v
     * @vBox the Vbox where the product information is displayed
     */
    public void display(VBox v)
    {
        v.getChildren().add(new Label("Information: "));
        Label title = new Label("Title: " + literature.getTitle());
        Label publisher = new Label("Publisher: " + literature.getPublisher());
        v.getChildren().addAll(title, publisher);
    }
}
