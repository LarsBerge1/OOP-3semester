
package kiosk;

import java.util.ArrayList;
import java.util.List;

/**
 * A book series. Holds every book that is a part of the series.
 */
public class BookSeries{
    private final ArrayList<Book> books;
    private final String name;
    
    
    /**
     * Creates a new book series
     * @param seriesName the name of the series
     */
    public BookSeries(String seriesName)
    {
        name = seriesName;
        books = new ArrayList<>();
    }
    
    /**
     * Adds a book to the book series
     * @param book the book to add
     */
    public void addBookToSeries(Book book)
    {
        books.add(book);
    }
    
    /**
     * Returns the books that are a part of the series
     * @return A list of the books in the series
     */
    public List<Book> getBooksInSeries()
    {
        return books;
    }
}
