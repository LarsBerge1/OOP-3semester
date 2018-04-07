/*
* Represents all types of literature in the 
* Literature Kiosk
*/
package kiosk;

/**
 *
 * @author amhat
 */
public abstract class Literature {        
    private String publisher;     
    private String title;
    
    /**
     * Constructor for Literature
     * @param title The title of the literature
     * @param publisher The publisher of the literature
     */
    public Literature(String title, String publisher)
    {
        this.title = title;
        this.publisher = publisher;
    }
    
    /**
     * Return information about a product
     * @return info the information about the product
     */
    public String findProductInfo()
    {
        String info = "Title: " + this.title +"\nPublisher" + this.publisher;
        return info;
    }
    
    
    /**
     * Set the title of the book
     * @param title the title of the book.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * Get the title of the book
     * @return the title of the book
     */
    public String getTitle()
    {
        return this.title;
    }   
    
    /**
     * Set the publisher of the book.
     * @param publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }    

    /**
     * Get the publisher of the book
     * @return the publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }   
    
}
