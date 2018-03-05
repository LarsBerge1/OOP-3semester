
package Tests;

import kiosk.RegisterOfBooks;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amhat
 */
public class TestRegisterOfBooks {
    
    public  TestRegisterOfBooks(){
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestSearchBookByTitle()
    {
        RegisterOfBooks bookReg = new RegisterOfBooks();
        bookReg.registrateSingleBook("Title", "Author", "Publisher",
                                      "publicationDate", 1);
        assertEquals("Title", bookReg.searchBookByTitle("Title").getTitle());
        assertEquals(null, bookReg.searchBookByTitle(" "));
    }
    
    @Test
    public void TestDeleteBook()
    {
        RegisterOfBooks bookReg = new RegisterOfBooks();
        bookReg.registrateSingleBook("Title", "Author", "Publisher",
                                      "publicationDate", 1);
        
        assertEquals(true, bookReg.deleteBook(bookReg.searchBookByTitle("Title")));
        assertEquals(false, bookReg.deleteBook(bookReg.searchBookByTitle(" ")));    
    }
}
