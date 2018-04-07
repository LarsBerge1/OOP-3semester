
package kiosk;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.Stream;

/**
 *
 * @author berge
 */
public class RegisterOfProductsTest {
    
    public RegisterOfProductsTest() {
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

    /**
     * Test of registrateSingleBook method, of class RegisterOfBooks.
     */
    @Test
    public void testRegistrateSingleBook() {
        System.out.println("registrateSingleBook");
        String title = "";
        String author = "";
        String publisher = "";
        String publicationDate = "";
        int edition = 0;
        RegisterOfProducts instance = new RegisterOfProducts();
        instance.registrateSingleBook(title, author, publisher, publicationDate, edition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrateBookInSeries method, of class RegisterOfBooks.
     */
    @Test
    public void testRegistrateBookInSeries() {
        System.out.println("registrateBookInSeries");
        String title = "";
        String seriesTitle = "";
        String author = "";
        String publisher = "";
        String publicationDate = "";
        RegisterOfProducts instance = new RegisterOfProducts();
        instance.registrateBookInSeries(title, seriesTitle, author, publisher, publicationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchBookBy method, of class RegisterOfBooks.
     */
    @Test
    public void testSearchBookBy() {
        System.out.println("searchBookBy");
        String title = "";
        String publisher = "";
        RegisterOfProducts instance = new RegisterOfProducts();
        Book expResult = null;
        Book result = instance.searchBookBy(title, publisher);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchBookByPublisher method, of class RegisterOfBooks.
     */
    @Test
    public void testSearchBookByPublisher() {
        System.out.println("searchBookByPublisher");
        String publisher = "";
        RegisterOfProducts instance = new RegisterOfProducts();
        ArrayList<Book> expResult = null;
        ArrayList<Book> result = instance.searchBookByPublisher(publisher);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListOfProducts method, of class RegisterOfBooks.
     */
    @Test
    public void testGetListOfBooks() {
        System.out.println("getListOfBooks");
        RegisterOfProducts instance = new RegisterOfProducts();
        Stream<Book> expResult = Stream.empty();
        Stream<Book> result = instance.getListOfProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBook method, of class RegisterOfBooks.
     */
    @Test
    public void testDeleteBook() {
        System.out.println("deleteBook");
        Book book = null;
        RegisterOfProducts instance = new RegisterOfProducts();
        boolean expResult = false;
        boolean result = instance.deleteBook(book);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBookToSeries method, of class RegisterOfBooks.
     */
    @Test
    public void testAddBookToSeries() {
        System.out.println("addBookToSeries");
        String title = "";
        String publisher = "";
        String seriesTitle = "";
        String date = "";
        RegisterOfProducts instance = new RegisterOfProducts();
        boolean expResult = false;
        boolean result = instance.addBookToSeries(title, publisher, seriesTitle, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchBookByTitle method, of class RegisterOfBooks.
     */
    @Test
    public void testSearchBookByTitle() {
        System.out.println("searchBookByTitle");
        String title = "";
        RegisterOfProducts instance = new RegisterOfProducts();
        Book expResult = null;
        Book result = instance.searchBookByTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void TestSearchBookByTitle()
    {
        RegisterOfProducts bookReg = new RegisterOfProducts();
        bookReg.registrateSingleBook("Title", "Author", "Publisher",
                                      "publicationDate", 1);
        assertEquals("Title", bookReg.searchBookByTitle("Title").getTitle());
        assertEquals(null, bookReg.searchBookByTitle(" "));
    }
    
    @Test
    public void TestDeleteBook()
    {
        RegisterOfProducts bookReg = new RegisterOfProducts();
        bookReg.registrateSingleBook("Title", "Author", "Publisher",
                                      "publicationDate", 1);
        
        assertEquals(true, bookReg.deleteBook(bookReg.searchBookByTitle("Title")));
        assertEquals(false, bookReg.deleteBook(bookReg.searchBookByTitle(" ")));    
    }
    
}
