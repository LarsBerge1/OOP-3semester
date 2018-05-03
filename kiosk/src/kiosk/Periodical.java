
package kiosk;
    
/**
 *Represents literature that is published reglurarly. For instance: newspapers,
 * periodicals/journals and magazines
 * @author amhat
 */
public class Periodical extends Literature {
    private final int numberOfYearlyReleases;
    private final String subject;
    
    
    /**
     * Constructor for the RegularlyPublishedLiterature 
     *@param title The title of the literature
     *@param publisher The publisher of the literature
     *@param numberOfYearlyReleases the number of edition published in a year
     *@param subject the subject of the literature
     */
    public Periodical(String title, String publisher, int numberOfYearlyReleases, String subject)
    {
        super(title, publisher);
        this.numberOfYearlyReleases = numberOfYearlyReleases;
        this.subject = subject;
    }
    
    /**
     * Returns the number of yearly releases
     * @return the number of yearly releases
     */
    
    public int getNumberOfYearlyReleases()
    {
        return numberOfYearlyReleases;
    }
    
    /**
     * Returns the subject
     * @return subject
     */
    public String getSubject()
    {
        return subject;
    }    
}
