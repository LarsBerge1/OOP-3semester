
package kiosk;

/**
 * A book that is published as part of a series
 * 
 */
public class BookInSeries extends Book{
    private String seriesTitle;     //The title of the series.
    
   /**
     * Constructor for books in a series.
     * @param title the title of the book
     * @param seriesTitle the title of the series the book is a part of
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param publicationDate the date the book was published
     */
    public BookInSeries(String title, String seriesTitle, String author, String publisher, String publicationDate) {
        super(author, publisher, title, publicationDate);        
        this.seriesTitle = seriesTitle;
    }
    
    
    /**
     * Set the title of the book serie
     * @param seriesTitle the series title
     */
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }
    
    /**
     * Get the title of the book serie
     * @return the series title
     */
    public String getSeriesTitle() {
        return this.seriesTitle;
    }
    
}
