
package kiosk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the RegularlyPublishedLiterature class
 * @author amhat
 */
public class RegularlyPublishedLiteratureTest {
    RegularlyPublishedLiterature literature;
    public RegularlyPublishedLiteratureTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        literature = new RegularlyPublishedLiterature("title", "publisher", 0, "subject");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findProductInfo method, of class RegularlyPublishedLiterature.
     */
    @Test
    public void testGetTitle() {
        assertEquals("title", literature.getTitle());   
    }
    
    @Test
    public void testGetPublisher()
    {
        assertEquals("publisher", literature.getPublisher());
    }
    
    @Test
    public void testSetPublisher()
    {
        literature.setPublisher("per");
        assertEquals("per", literature.getPublisher());
    }
    
    @Test
    public void testSetTitle()
    {
        literature.setTitle("per");
        assertEquals("per", literature.getTitle());
    }
    

}