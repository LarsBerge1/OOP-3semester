
package kiosk;

/**
 * Presents information about the literature
 * 
 */
public abstract class LiteratureView {
    private Literature literature;
    public LiteratureView(Literature literature)
    {
        this.literature = literature;
    }
    
    /**
     * Displays the information associated with an literature object
     */
    public void display()
    {
        System.out.println();
        System.out.println("Title: " + literature.getTitle());
        System.out.println("Publisher: " + literature.getPublisher());
    }
}
