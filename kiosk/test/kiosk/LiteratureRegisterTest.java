
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
public class LiteratureRegisterTest {
    LiteratureRegister registerOfProducts;
    
    public LiteratureRegisterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {       
        
        registerOfProducts = new LiteratureRegister();
        
        registerOfProducts.registrateLiterature(new SingleBook("Fysikk", "Frank", "Pearson", "07.03.2018", 1));
        registerOfProducts.registrateLiterature(new SingleBook("Norsk", "Jarl", "Norge", "24.01.2017", 2));
        registerOfProducts.registrateLiterature(new SingleBook("Matte", "Lars", "Pearson", "07.03.2018", 3));
        registerOfProducts.registrateLiterature(new SingleBook("Naturfag", "Andreas", "Hei", "07.03.2018", 4));
        
        registerOfProducts.registrateLiterature(new BookInSeries("Simple","Calclus", "Lars", "Gyldendal", "07.05.2011"));
        registerOfProducts.registrateLiterature(new BookInSeries("Medium","Calclus", "Jarl", "Pearson", "23.11.2013"));
        registerOfProducts.registrateLiterature(new BookInSeries("Hard","Calclus", "Andreas", "Gyldendal", "13.03.2015"));
        registerOfProducts.registrateLiterature(new BookInSeries("Advanced","Calclus", "Arne", "Kagge", "02.07.2018"));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registrateSingleBook method.
     */
    @Test
    public void testRegistrateLiterature() {
        LiteratureRegister register = new LiteratureRegister();
        register.registrateLiterature(new SingleBook("Title", "Author", "Publisher", "publicationDate", 1));
        assertTrue(register.getLiteraturesAsStream().anyMatch(book -> book.getTitle().equals("Title")));        
    }


    /**
     * Test of searchBookBy method.
     */
    @Test
    public void testSearchProductBy() {
        LiteratureRegister register = new LiteratureRegister();
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
        Stream<Literature> s = registerOfProducts.getLiteraturesAsStream();
        assertTrue(s.anyMatch(p -> p.getPublisher().equals("Pearson")));
        s = registerOfProducts.getLiteraturesAsStream();
        assertTrue(s.anyMatch(p -> p.getPublisher().equals("Gyldendal")));
    }

    /**
     * Test of deleteBook method.
     */
    @Test
    public void testDeleteBook() {
        LiteratureRegister register = new LiteratureRegister();
        register.registrateLiterature(new BookInSeries("title", "seriesTitle", "author", "publisher", "publicationDate"));
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
        LiteratureRegister register = new LiteratureRegister();
        register.registrateLiterature(new SingleBook("title", "author", "publisher", "publicationDate", 0));
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
