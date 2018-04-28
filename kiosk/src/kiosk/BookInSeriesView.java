
package kiosk;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *Presents information about the book in series
 * 
 */
public class BookInSeriesView extends BookView {
    private BookInSeries bs;
    public BookInSeriesView(BookInSeries bs)
    {
        super(bs);
        this.bs = bs;
        
    }
    
    /**
     * Displays the information associated with a BookInSeries object
     */
    @Override
    public void display(VBox vBox)
    {
        super.display(vBox);
        Label seriesTitle = new Label("Series title: " + bs.getSeriesTitle());
        vBox.getChildren().add(seriesTitle);
    }    
}
