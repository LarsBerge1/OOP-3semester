
package kiosk;


import javafx.scene.control.TextArea;

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
     * @param infoArea the area to add the information
     */ 
    @Override
    public void display(TextArea infoArea)
    {
        super.display(infoArea);
        infoArea.appendText("\nSeries title: " + bs.getSeriesTitle());
    } 
}
