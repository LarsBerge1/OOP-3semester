/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berge
 */
public class BookTest {
    
    public BookTest() {
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
     * Test of setTitle method, of class Book.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "Test";
        Book instance = new Book("Title","Author","Pearson","07.03.2018",1);
        instance.setTitle(title);
        String result = instance.getTitle();
        assertEquals(title, result);
    }

    /**
     * Test of getTitle method, of class Book.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");  
        String expResult = "Title";
        Book instance = new Book(expResult,"Author","Pearson","07.03.2018",1);
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSeriesTitle method, of class Book.
     */
    @Test
    public void testSetSeriesTitle() {
        System.out.println("setSeriesTitle");
        String seriesTitle = "SeriesTitle";
        Book instance = new Book("Title"," ","Author","Pearson","07.03.2018");
        instance.setSeriesTitle(seriesTitle);
        String result = instance.getSeriesTitle();
        assertEquals(seriesTitle, result);
    }

    /**
     * Test of getSeriesTitle method, of class Book.
     */
    @Test
    public void testGetSeriesTitle() {
        System.out.println("getSeriesTitle");
        String expResult = "SeriesTitle";
        Book instance = new Book("Title",expResult,"Author","Pearson","07.03.2018");
        String result = instance.getSeriesTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthor method, of class Book.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String expResult = "Author";
        Book instance = new Book("Title"," ","Pearson","07.03.2018",1);
        instance.setAuthor(expResult);
        String result = instance.getAuthor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthor method, of class Book.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        String expResult = "Author";
        Book instance = new Book("Title",expResult,"Pearson","07.03.2018",1);
        String result = instance.getAuthor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEdition method, of class Book.
     */
    @Test
    public void testSetEdition() {
        System.out.println("setEdition");
        int edition = 3;
        Book instance = new Book("Title","Author","Pearson","07.03.2018",1);
        instance.setEdition(edition);
        int result = instance.getEdition();
        assertEquals(edition, result);
    }

    /**
     * Test of getEdition method, of class Book.
     */
    @Test
    public void testGetEdition() {
        System.out.println("getEdition");
        int expResult = 0;
        Book instance = new Book("Title","Author","Pearson","07.03.2018",expResult);
        int result = instance.getEdition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPublicationDate method, of class Book.
     */
    @Test
    public void testSetPublicationDate() {
        System.out.println("setPublicationDate");
        String expResult = "2018.03.07";
        Book instance = new Book("Title","Author","Pearson","07.03.2018",1);
        instance.setPublicationDate(expResult);
        String result = instance.getPublicationYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPublicationYear method, of class Book.
     */
    @Test
    public void testGetPublicationYear() {
        System.out.println("getPublicationYear");
        String expResult = "07.03.2018";
        Book instance = new Book("Title","Author","Pearson",expResult,1);
        String result = instance.getPublicationYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPublisher method, of class Book.
     */
    @Test
    public void testSetPublisher() {
        System.out.println("setPublisher");
        String expResult = "nosraeP";
        Book instance = new Book("Title","Author","Pearson","07.03.2018",1);
        instance.setPublisher(expResult);
        String result = instance.getPublisher();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPublisher method, of class Book.
     */
    @Test
    public void testGetPublisher() {
        System.out.println("getPublisher");
        String expResult = "Pearson";
        Book instance = new Book("Title","Author",expResult,"07.03.2018",1);
        String result = instance.getPublisher();
        assertEquals(expResult, result);
    }    
}
