
package kiosk;

/**
 *Presents information about the book in series
 * 
 */
public class BookInSeriesView extends LiteratureView {
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
    public void display()
    {
        super.display();
        System.out.println("Author: " + bs.getAuthor());
        System.out.println("Publication date: " + bs.getPublicationDate());
        System.out.println("Series title: " + bs.getSeriesTitle());
        
    }
    
}
