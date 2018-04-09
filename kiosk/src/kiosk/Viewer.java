
package kiosk;

/**
 *
 * @author amhat
 */
public abstract class Viewer {
    public Viewer()
    {        
    }
    
    public static void display(Literature product)
    {
        System.out.println("Title: " + product.getTitle());
        System.out.println("Publisher: " + product.getPublisher());
        if (product instanceof BookInSeries)
        {
            printBookInSeriesDetails((BookInSeries) product);
        }
        else if (product instanceof SingleBook)
        {
            SingleBook sb = (SingleBook) product;
            printSingleBookDetails(sb);
        }
        else if (product instanceof ComicBook)
        {
            ComicBook cb = (ComicBook) product;
            printComicBookDetails(cb);
        }
        else if (product instanceof RegularlyPublishedLiterature)
        {
            RegularlyPublishedLiterature rpl = (RegularlyPublishedLiterature) product;
            printRPLInfo(rpl);
        }
        System.out.println();
    }
    
    private static void printBookInSeriesDetails(BookInSeries book)
    {
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication date: " + book.getPublicationDate());
        System.out.println("Series title: " + book.getSeriesTitle());
    }
    
    private static void printSingleBookDetails(SingleBook book)
    {
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication date: " + book.getPublicationDate());
        System.out.println("Edition: " + book.getEdition());
    }
    private static void printComicBookDetails(ComicBook book)
    {
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication date: " + book.getPublicationDate());
        System.out.println("Genre: " + book.getGenre());
      
    }
   
    private static void printRPLInfo(RegularlyPublishedLiterature periodical)
    {
        System.out.println("Yearly releases: " + periodical.getNumberOfYearlyReleases());
        System.out.println("Subject: " + periodical.getSubject());
    }
    
}
