
package kiosk;

/**
 * A book that is published as part of a series
 * @author amhat
 */
public class BookInSeries extends Book{
    private String seriesTitle;     //The title of the series.
    
   /**
     * Constructor for books in a series.
     * @param title
     * @param seriesTitle
     * @param author
     * @param publisher
     * @param publicationDate
     */
    public BookInSeries(String title, String seriesTitle, String author, String publisher, String publicationDate) {
        super(author, publisher, title, publicationDate);        
        this.seriesTitle = seriesTitle;
    }
    
    /**
     * Return information about a product
     * @return info the information about the product
     */
    @Override
    public String findProductInfo()
    {
        String info = super.findProductInfo();
        info += "\nSeries title: " + seriesTitle;
        return info;
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
