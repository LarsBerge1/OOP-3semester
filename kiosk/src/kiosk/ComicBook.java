
package kiosk;

/**
 * A comic book. Differs from other books since it has a specific "comic genre" field.
 * 
 */
public class ComicBook extends Book {
    private static String genre;
    public ComicBook(String author, String publisher, String title, String publicationDate, String genre)
    {
      super(author,publisher, title, publicationDate);  
      this.genre = genre;
    }
    
    /**
     * Returns the genre of the comic book
     * @return the genre of the comic book
     */
    public String getGenre()
    {
        return genre;
    }
    
}
