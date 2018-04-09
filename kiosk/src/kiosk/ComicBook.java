
package kiosk;

/**
 *
 * @author amhat
 */
public class ComicBook extends Book {
    private static String genre;
    public ComicBook(String author, String publisher, String title, String publicationDate, String genre)
    {
      super(author,publisher, title, publicationDate);  
      this.genre = genre;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
}
