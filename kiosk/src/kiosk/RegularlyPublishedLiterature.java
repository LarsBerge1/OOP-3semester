
package kiosk;
    
/**
 *Represents literature that is published reglurarly. For instance: newspapers,
 * periodicals/journals and magazines
 * @author amhat
 */
public class RegularlyPublishedLiterature extends Literature {
    private int numberOfYearlyReleases;
    private String subject;
    
    
    /**
     *@param title The title of the literature
     *@param publisher The publisher of the literature
     *@param numberOfYearlyReleases the number of edition published in a year
     * @param subject the subject of the literature
     */
    public RegularlyPublishedLiterature(String title, String publisher, int numberOfYearlyReleases, String subject)
    {
        super(title, publisher);
        this.numberOfYearlyReleases = numberOfYearlyReleases;
        this.subject = subject;
    }
    
    /**
     * Return information about a product
     * @return info the information about the product
     */
    @Override
    public String findProductInfo()
    {
        String info = super.findProductInfo();
        info += "\nNumber of yearly releases: " + numberOfYearlyReleases +
                "\nSubject: " + subject;
        return info;
    }
    
}
