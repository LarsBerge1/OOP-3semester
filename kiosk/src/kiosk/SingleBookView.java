
package kiosk;

/**
 * Presents information about the single book
 * 
 */
public class SingleBookView  extends LiteratureView {
    private SingleBook sb;
    public SingleBookView(SingleBook sb)
    {
     super(sb);   
     this.sb = sb;
    }
    
    /**
     * Displays the information associated with a SingleBook object
     */
    @Override
    public void display()
    {
        super.display();
        System.out.println("Author: " + sb.getAuthor());
        System.out.println("Publication date: " + sb.getPublicationDate());
        System.out.println("Edition: " + sb.getEdition());
    }
}
