
package kiosk;


import javafx.scene.control.TextArea;

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
     * @param infoArea the area to present informations     * 
     */
    public void display(TextArea infoArea)
    {
        infoArea.appendText("Information: ");
        infoArea.appendText("\nTitle: " + literature.getTitle());
        infoArea.appendText("\nPublisher: " + literature.getPublisher());
    }
}
