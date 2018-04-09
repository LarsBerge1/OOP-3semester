
package kiosk;

/**
 *
 * @author amhat
 */
public abstract class ViewFactory {
    public ViewFactory()
    {        
    }
    
    public static LiteratureView getView(Literature l)
    {
        if (l instanceof BookInSeries)
        {
            BookInSeries bs = (BookInSeries) l;
            return new BookInSeriesView(bs);
        }
        else if (l instanceof SingleBook)
        {
            SingleBook sb = (SingleBook) l;
            return new SingleBookView(sb);
        }
        else if (l instanceof ComicBook)
        {
            ComicBook cb = (ComicBook) l;
            return new ComicBookView(cb);
        }
        else if (l instanceof RegularlyPublishedLiterature)
        {
            RegularlyPublishedLiterature rpl = (RegularlyPublishedLiterature) l;
            return new RegularlyPublishedLiteratureView(rpl);
        }
        return null;
    }
}
