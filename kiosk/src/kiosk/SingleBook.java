
package kiosk;

/**
 *A single book
 * @author amhat
 */
public class SingleBook extends Book{
    private int edition;            //The edition of the book.
    
    /**
     * Constructor for single books.
     * @param author The author of the book
     * @param title The title of the book
     * @param publisher The publisher of the book
     * @param publicationDate The publication day of the book
     * @param edition The edition of the book
     */
    public SingleBook(String title, String author, String publisher, String publicationDate, int edition)
    {
        super(author, publisher, title, publicationDate);
        this.edition = edition;
    }
    
    /**
     * Return information about a product
     * @return info the information about the product
     */
    @Override
    public String findProductInfo()
    {
        String info = super.findProductInfo();
        info += "\nEdition: " + edition;
        return info;
    }
    
     /**
     * Set the edition of the book.
     * @param edition of the book.
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }
    
    /**
     * Get the edition of the book.
     * @return the edition of the book.
     */
    public int getEdition() {
        return this.edition;
    }
            
}
