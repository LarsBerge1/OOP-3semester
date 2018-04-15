
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
    RegisterOfProducts registerOfProducts;
    
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
        
        registerOfProducts = new RegisterOfProducts();
        
        registerOfProducts.registrateSingleBook("Fysikk", "Frank", "Pearson", "07.03.2018", 1);
        registerOfProducts.registrateSingleBook("Norsk", "Jarl", "Norge", "24.01.2017", 2);
        registerOfProducts.registrateSingleBook("Matte", "Lars", "Pearson", "07.03.2018", 3);
        registerOfProducts.registrateSingleBook("Naturfag", "Andreas", "Hei", "07.03.2018", 4);
        
        registerOfProducts.registrateBookInSeries("Simple","Calclus", "Lars", "Gyldendal", "07.05.2011");
        registerOfProducts.registrateBookInSeries("Medium","Calclus", "Jarl", "Pearson", "23.11.2013");
        registerOfProducts.registrateBookInSeries("Hard","Calclus", "Andreas", "Gyldendal", "13.03.2015");
        registerOfProducts.registrateBookInSeries("Advanced","Calclus", "Arne", "Kagge", "02.07.2018");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registrateSingleBook method.
     */
    @Test
    public void testRegistrateSingleBook() {
        RegisterOfProducts register = new RegisterOfProducts();
        register.registrateSingleBook("Title", "Author", "Publisher", "publicationDate", 1);
        assertTrue(register.getProducts().anyMatch(book -> book.getTitle().equals("Title")));        
    }

    /**
     * Test of registrateBookInSeries method.
     */
    @Test
    public void testRegistrateBookInSeries() {
        RegisterOfProducts register = new RegisterOfProducts();
        register.registrateBookInSeries("Title", "seriesTitle", "author", "publisher", "publicationDate");
        assertTrue(register.getProducts().anyMatch(book -> book.getTitle().equals("Title")));
    }

    /**
     * Test of searchBookBy method.
     */
    @Test
    public void testSearchProductBy() {
        RegisterOfProducts register = new RegisterOfProducts();
        Literature result1 = register.searchProductBy("title", "publisher");
        assertEquals(null, result1);
        Literature result2 = registerOfProducts.searchProductBy("Fysikk", "Pearson");
        assertTrue(result2.getTitle().equals("Fysikk"));
        assertTrue(result2.getPublisher().equals("Pearson"));
    }   

    /**
     * Test of searchProductByPublisher method.
     */
    @Test
    public void testSearchProductByPublisher() {
        ArrayList<Literature> result = registerOfProducts.searchProductByPublisher("Gyldendal");
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(l -> l.getPublisher().equals("Gyldendal")));
        
        ArrayList<Literature> result2 = registerOfProducts.searchProductByPublisher("non-existing publisher");
        assertTrue(result2.isEmpty());
        assertTrue(result2.stream().noneMatch(l -> l.getPublisher().equals("non-existing publisher")));
    }

    /**
     * Test of getProducts method.
     */
    @Test
    public void testGetBooks() {
        Stream<Literature> s = registerOfProducts.getProducts();
        assertTrue(s.anyMatch(p -> p.getPublisher().equals("Pearson")));
        s = registerOfProducts.getProducts();
        assertTrue(s.anyMatch(p -> p.getPublisher().equals("Gyldendal")));
    }

    /**
     * Test of deleteBook method.
     */
    @Test
    public void testDeleteBook() {
        RegisterOfProducts register = new RegisterOfProducts();
        register.registrateBookInSeries("title", "seriesTitle", "author", "publisher", "publicationDate");
        Literature literature = register.searchProductByTitle("title");
        boolean result = register.deleteProduct(null);
        assertFalse(result);
        assertEquals(literature.getPublisher(),"publisher");
        
    }

    /**
     * Test of addBookToSeries method, of class RegisterOfBooks.
     */
    @Test
    public void testAddBookToSeries() {
        RegisterOfProducts register = new RegisterOfProducts();
        register.registrateSingleBook("title", "author", "publisher", "publicationDate", 0);
        assertTrue(register.addBookToSeries("title", "publisher", "seriesTitle", "date"));        
    }
    /**
     * Test of searchBookByTitle method, of class RegisterOfBooks.
     */
    
    @Test
    public void TestSearchProductByTitle()
    {
        assertEquals("Fysikk", registerOfProducts.searchProductByTitle("Fysikk").getTitle());
        assertEquals(null, registerOfProducts.searchProductByTitle(" "));
    }
}
