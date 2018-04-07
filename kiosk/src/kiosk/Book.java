
package kiosk;


/**
 *  Description of class Book.
 *  The book Class is responsible for holding the information associated with a book.
 *
 * @author Lars Berge
 * @version 2018.02.12
 */
public abstract class Book extends Literature
{
    private String author;          // The author of the book    
    private String publicationDate; //The publication year of the book.
    

    public Book(String author, String publisher, String title, String publicationDate)
    {
        super(title, publisher);
        this.author = author;
        this.publicationDate = publicationDate;
    }
    
    /**
     * Return information about a product
     * @return info the information about the product
     */
    @Override
    public String findProductInfo()
    {
        String info = super.findProductInfo();
        info += "\nAuthor: " + this.author + "\nPublicationDate: " + this.publicationDate; 
        return info;
    }

    /**
     * Set the author of the book
     * @param author the author of the book.
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    /**
     * Get the author of the book
     * @return the author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Set the date of publication of the book.
     * @param publicationDate the publication date of the book.
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    /**
     * Returns the publicationDate
     * @return the publication date
     */
    public String getPublicationDate()
    {
        return publicationDate;
    }
}


