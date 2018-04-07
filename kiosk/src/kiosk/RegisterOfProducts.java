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
public class RegisterOfProducts{
    ArrayList<Literature> products;
    
    /**
     * Constructor for the RegisterOfBooks class
     */
    public RegisterOfProducts()
    {
       products = new ArrayList<>();  
    }
    
    /**
     * Adds a book that isn't a part of a series to the list of products
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
      products.add(new SingleBook(title, author, publisher, publicationDate, edition)); 
    }
    /**
     * Adds a book that is a part of a series to the list of products
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
       products.add(new BookInSeries(title, seriesTitle, author, publisher, publicationDate));    
    }
    
    /**
     * Adds a regularly published litterature to the list
     * of products
     * @param numberOfYearlyReleases number of editions published each year
     * @param publisher the publisher of the literature
     * @param subject the subject of the literature
     * @param title the title of the literature
     */
    
    public void registrateRegularlyPublishedLiterature(String title, String publisher, int numberOfYearlyReleases, String subject)
    {
        products.add(new RegularlyPublishedLiterature(title, publisher, numberOfYearlyReleases, subject));
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
        Literature product = null;
        if ( products.stream().anyMatch(book -> (book.getTitle().equals(title)) && (book.getPublisher().equals(publisher))))
        {
            product = products.stream()
                    .filter(book -> (book.getTitle().equals(title)) && (book.getPublisher().equals(publisher)))
                    .findFirst()
                    .get();
        }
        return product;
    }
    

    
    /**
     * Find and return the books by a particular publisher
     * 
     * @param publisher The publisher of the book
     * @return A list of books by a particular publisher 
     */
     public ArrayList<Literature> searchBookByPublisher(String publisher)
    {
       ArrayList<Literature> listToReturn = new ArrayList<>();
       if (products.stream().anyMatch(book-> book.getPublisher().equals(publisher)))
       {
       listToReturn = products.stream()
                          .filter(book -> book.getPublisher().equals(publisher))
                          .collect(Collectors.toCollection(ArrayList::new));
       }
       return listToReturn;
    }
    
     /**
      * Return the list of books
      * @return the list of books 
      */
    public Stream<Literature> getListOfProducts()
    {
        return products.stream();
    }
    
    /**
     * Delete a particular book
     * 
     * @param product The product to delete
     * @return deleted True if book is deleted false if not
     */
    public boolean deleteProduct(Literature product)
    {
        boolean deleted = false;
        Iterator<Literature> it = products.iterator();
        while (it.hasNext())
        {
            Literature literature = it.next();
            if (literature == product)
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
        
        Literature bookToChange = searchProductBy(title, publisher);
        boolean success = bookToChange != null;
        if (success && bookToChange instanceof Book)
        {
            Book book = (Book) bookToChange;
            products.add(new BookInSeries(title, seriesTitle, date, publisher, book.getPublicationDate()));
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
        Literature product  = null;
        if ( products.stream().anyMatch(book -> book.getTitle().equals(title))) 
        {
            product = products.stream()
                    .filter(book -> book.getTitle().equals(title))
                    .findFirst()
                    .get();
        }
        return product;
    }
    
    /**
     * Fills the phonebook with some standard data
     * to make it faster for testing.
     */
    public void fillBookRegister()
    {
        //Add to single.
        products.add(new SingleBook("Fysikk", "Frank", "Pearson", "07.03.2018", 1)); 
        products.add(new SingleBook("Norsk", "Jarl", "Norge", "24.01.2017", 2));
        products.add(new SingleBook("Matte", "Lars", "Pearson", "07.03.2018", 3));
        products.add(new SingleBook("Naturfag", "Andreas", "Hei", "07.03.2018", 4));
        // Add to series.
        products.add(new BookInSeries("Simple","Calclus", "Lars", "Person", "07.05.2011"));
        products.add(new BookInSeries("Medium","Calclus", "Jarl", "Person", "23.11.2013"));
        products.add(new BookInSeries("Hard","Calclus", "Andreas", "Person", "13.03.2015"));
        products.add(new BookInSeries("Advanced","Calclus", "Arne", "Person", "02.07.2018"));
    }
                                        
}

