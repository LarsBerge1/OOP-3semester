package kiosk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
public class LiteratureRegister implements Serializable, Iterable<Literature>
{
    List<Literature> literatures;
    HashMap<String, BookSeries> bookSeries;
    /**
     * Constructor for the RegisterOfBooks class
     */
    public LiteratureRegister()
    {
       literatures = new ArrayList<>();
       bookSeries = new HashMap<>();
    }
    
    /**
     * Registrate a literatur to the collection
     * @param l the literatur to add to the collection
     */
    public void registrateLiterature(Literature l)
    {
        if (l instanceof BookInSeries)
        {
            BookInSeries bs = (BookInSeries) l;
            String seriesTitle = bs.getSeriesTitle();
            addToBookSeries(seriesTitle, bs);            
        }
        literatures.add(l);
    }
    
    public void changeToBookInSeries(String seriesTitle, SingleBook book){
        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String publicationDate = book.getPublicationDate();
        BookInSeries bs = new BookInSeries(title, seriesTitle, author, publisher, publicationDate);
        addToBookSeries(seriesTitle, book);
        literatures.remove(book);
        literatures.add(bs);
    }
    
    /**
     * Adds a book to a series
     * 
     * @param seriesTitle The title of the series
     * @param book the book to add to the series
     */
    private void addToBookSeries(String seriesTitle, Book book)
    {        
        if (bookSeries.containsKey(seriesTitle))
        {
            bookSeries.get(seriesTitle).addBookToSeries(book);
        }
        else
        {
            BookSeries series = new BookSeries(seriesTitle);
            series.addBookToSeries(book);
            bookSeries.put(seriesTitle, series);
        }
    }
    
    /**
     * Finds and return a book with a particular title
     * 
     * @param title The title of the book
     * @param publisher the publisher of the book
     * @return A book with a particular title
     * @throws NoSuchElementException if product is not found
     */
    public Literature searchProductBy(String title, String publisher)
    {
        Literature product = literatures.stream()
                    .filter(literature -> (literature.getTitle().equals(title)) && (literature.getPublisher().equals(publisher)))
                    .findFirst()
                    .get();
        
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
     * Search for a book with a particular title
     * @param title The title of the book
     * @return bookToReturn
     * @throws NoSuchElementException if product is not found
     */
    public Literature searchProductByTitle(String title)
    {
        Literature product = literatures.stream()
                    .filter(literature -> literature.getTitle().equals(title))
                    .findFirst()
                    .get();
        
        return product;
    }                                        

    @Override
    public Iterator<Literature> iterator() {
        return literatures.iterator();
    }
}
