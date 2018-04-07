
package kiosk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test class for the SingleBook class
 * @author amhat
 */
public class SingleBookTest {
    SingleBook book;
    public SingleBookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        book = new SingleBook("title", "author", "publisher", "publicationDate", 0);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of setEdition method, of class SingleBook.
     */
    @Test
    public void testSetEdition() {
        book.setEdition(5);
        assertEquals(5, book.getEdition());
    }

    /**
     * Test of getEdition method, of class SingleBook.
     */
    @Test
    public void testGetEdition() {
        assertEquals(0, book.getEdition());
    }
    
    
    /**
     * Test of findProductInfo method, of class RegularlyPublishedLiterature.
     */
    @Test
    public void testGetTitle()
    {
        assertEquals("title", book.getTitle());   
    }
    
    @Test
    public void testGetPublisher()
    {
        assertEquals("publisher", book.getPublisher());
    }
    
    @Test
    public void testSetPublisher()
    {
        book.setPublisher("per");
        assertEquals("per", book.getPublisher());
    }
    
    @Test
    public void testSetTitle()
    {
        book.setTitle("per");
        assertEquals("per", book.getTitle());
    }
    @Test
    public void testGetPublicationDate()
    {
        assertEquals("publicationDate", book.getPublicationDate());
    }
    
}
