package kiosk;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A register of books. 
 * Functions to search, add or delete books in the register.
 * @amhat
 * @berge
 * @author 
 */
public class RegisterOfBooks {
    ArrayList<Book> listOfBooks;
    
    /**
     * Constructor for the RegisterOfBooks class
     */
    public RegisterOfBooks()
    {
       listOfBooks = new ArrayList<>();  
    }
    
    /**
     * Adds a book that isn't a part of a series to the list of books
     * 
     * @param title The name of the book
     * @param author The name of the author
     * @param publisher The name of the publisher
     * @param publicationDate The day the book was published
     * @param edition The edition of the book
     */
    public void registrateSingleBook(String title, String author, String publisher,
                                     String publicationDate, int edition)
    {
      listOfBooks.add(new Book(title, author, publisher, publicationDate, edition)); 
    }
    /**
     * Adds a book that is a part of a series to the list of books
     * 
     * @param title The title of the book
     * @param seriesTitle The title of the series
     * @param author The name of the author
     * @param publisher The name of the publisher
     * @param publicationDate The day the book was published 
     */    
    public void registrateBookInSeries(String title, String seriesTitle, String author,
                                String publisher,  String publicationDate)
    {
       listOfBooks.add(new Book(title, seriesTitle, author, publisher, publicationDate));    
    }
    
    /**
     * Finds and return a book with a particular title
     * 
     * @param title The title of the book
     * @param publisher the publisher of the book
     * @return A book with a particular title
     *
     */
    public Book searchBookBy(String title, String publisher)
    {
        Book bookToReturn = null;
        if ( listOfBooks.stream().anyMatch(book -> (book.getTitle().equals(title)) && (book.getPublisher().equals(publisher))))
        {
            bookToReturn = listOfBooks.stream()
                    .filter(book -> (book.getTitle().equals(title)) && (book.getPublisher().equals(publisher)))
                    .findFirst()
                    .get();
        }
        return bookToReturn;
    }
    

    
    /**
     * Find and return the books by a particular publisher
     * 
     * @param publisher The publisher of the book
     * @return A list of books by a particular publisher 
     */
     public ArrayList<Book> searchBookByPublisher(String publisher)
    {
       ArrayList<Book> listToReturn = new ArrayList<>();
       if (listOfBooks.stream().anyMatch(book-> book.getPublisher().equals(publisher)))
       {
       listToReturn = listOfBooks.stream()
                          .filter(book -> book.getPublisher().equals(publisher))
                          .collect(Collectors.toCollection(ArrayList::new));
       }
       return listToReturn;
    }
    
     /**
      * Return the list of books
      * @return the list of books 
      */
    public Stream<Book> getListOfBooks()
    {
        return listOfBooks.stream();
    }
    
    /**
     * Delete a particular book
     * 
     * @param book The book to delete
     * @return deleted True if book is deleted false if not
     */
    public boolean deleteBook(Book book)
    {
        boolean deleted = false;
        Iterator<Book> it = listOfBooks.iterator();
        while (it.hasNext())
        {
            Book b = it.next();
            if (b == book)
            {
                it.remove();
                deleted = true;
            }
        }
        return deleted;
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
        
        Book bookToChange = searchBookBy(title, publisher);
        boolean success = bookToChange != null ? true : false;
        if (success)
        {
        bookToChange.setSeriesTitle(seriesTitle);
        bookToChange.setPublicationDate(date);
        }
        return success;
    }
    
    /**
     * Search for a book with a particular title
     * @param title The title of the book
     * @return bookToReturn
     */
    public Book searchBookByTitle(String title)
    {
        Book bookToReturn = null;
        if ( listOfBooks.stream().anyMatch(book -> book.getTitle().equals(title))) 
        {
            bookToReturn = listOfBooks.stream()
                    .filter(book -> book.getTitle().equals(title))
                    .findFirst()
                    .get();
        }
        return bookToReturn;
    }
    
    /**
     * Fills the phonebook with some standard data
     * to make it faster for testing.
     */
    public void fillBookRegister()
    {
        //Add to single.
        listOfBooks.add(new Book("Fysikk", "Frank", "Pearson", "07.03.2018", 1)); 
        listOfBooks.add(new Book("Norsk", "Jarl", "Norge", "24.01.2017", 2));
        listOfBooks.add(new Book("Matte", "Lars", "Pearson", "07.03.2018", 3));
        listOfBooks.add(new Book("Naturfag", "Andreas", "Hei", "07.03.2018", 4));
        // Add to series.
        listOfBooks.add(new Book("Simple","Calclus", "Lars", "Person", "07.05.2011"));
        listOfBooks.add(new Book("Medium","Calclus", "Jarl", "Person", "23.11.2013"));
        listOfBooks.add(new Book("Hard","Calclus", "Andreas", "Person", "13.03.2015"));
        listOfBooks.add(new Book("Advanced","Calclus", "Arne", "Person", "02.07.2018"));
    }
                                        
}

