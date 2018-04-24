package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


/**
 * A register of books. 
 * Functions to search, add or delete books in the register.
 * 
 */
public class LiteratureRegister{
    List<Literature> literatures;
    
    /**
     * Constructor for the RegisterOfBooks class
     */
    public LiteratureRegister()
    {
       literatures = new ArrayList<>();  
    }
    
    /**
     * Registrate a literatur to the collection
     * @param l the literatur to add to the collection
     */
    public void registrateLiterature(Literature l)
    {
        literatures.add(l);
    }  
      
    /**
     * Finds and return a book with a particular title
     * 
     * @param title The title of the book
     * @param publisher the publisher of the book
     * @return A book with a particular title
     *
     */
    public Literature searchProductBy(String title, String publisher)
    {
        Literature product;
        try
        {
            product = literatures.stream()
                    .filter(literature -> (literature.getTitle().equals(title)) && (literature.getPublisher().equals(publisher)))
                    .findFirst()
                    .get();
        }
        catch(NoSuchElementException e)
        {
            product = null;
        }
        return product;
    }
    
    /**
     * Find and return the books by a particular publisher
     * 
     * @param publisher The publisher of the book
     * @return A list of books by a particular publisher 
     */
     public ArrayList<Literature> searchProductByPublisher(String publisher)
    {
       ArrayList<Literature> listToReturn = new ArrayList<>();
       listToReturn = literatures.stream()
                          .filter(literature -> literature.getPublisher().equals(publisher))
                          .collect(Collectors.toCollection(ArrayList::new));
       return listToReturn;
    }
    
     /**
      * Return the list of books
      * @return the list of books 
      */
    public Stream<Literature> getLiteraturesAsStream()
    {
        return literatures.stream();
    }
    public List<Literature> getLiteratures()
    {
        return literatures;
    }
    
    /**
     * Delete a particular book
     * 
     * @param product The product to delete
     * @return deleted True if book is deleted false if not
     */
    public boolean deleteProduct(Literature product)
    {
        boolean isDeleted = false;
        Iterator<Literature> it = literatures.iterator();
        while (it.hasNext())
        {
            Literature literature = it.next();
            if (literature == product)
            {
                it.remove();
                isDeleted = true;
            }
        }
        return isDeleted;
    }
    /**
     * Adds a book to a series
     * 
     * @param title The title of the book to change
     * @param publisher The publisher of the book to change
     * @param seriesTitle The title of the series
     * @param date The date of the publication
     * @return success true if book was successfully added false if not
     */
    public boolean addBookToSeries(String title, String publisher, String seriesTitle,
                                String date)
    {
        
        Literature bookToChange = searchProductBy(title, publisher);
        boolean success = bookToChange != null;
        if (success && bookToChange instanceof Book)
        {
            Book book = (Book) bookToChange;
            literatures.add(new BookInSeries(title, seriesTitle, date, publisher, book.getPublicationDate()));
            this.deleteProduct(bookToChange);
            
        }
        return success;
    }
    
    /**
     * Search for a book with a particular title
     * @param title The title of the book
     * @return bookToReturn
     */
    public Literature searchProductByTitle(String title)
    {
        Literature product;
        try{
        product = literatures.stream()
                    .filter(literature -> literature.getTitle().equals(title))
                    .findFirst()
                    .get();
        }
        catch(NoSuchElementException e)
        {
            product = null;
        }
        return product;
    }                                        
}
