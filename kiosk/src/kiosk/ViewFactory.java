
package kiosk;

/**
 * Creates viewers
 * 
 */
public abstract class ViewFactory {
    public ViewFactory()
    {        
    }
    
    /**
     * Creates a viewer specific to which type of literature to present.
     * @param l the litterature to present to the user
     * @return a viewer specific to which type of literature to present.
     */
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
        else if (l instanceof Periodical)
        {
            Periodical rpl = (Periodical) l;
            return new PeriodicalView(rpl);
        }
        return null;
    }
}
