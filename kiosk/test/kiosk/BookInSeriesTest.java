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
 * @author amhat
 */
public class BookInSeriesTest {
    BookInSeries book;
    public BookInSeriesTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
        book = new BookInSeries("title", "seriesTitle", "author", "publisher", "publicationDate");
    }
    
    @After
    public void tearDown() 
    {
    }

    /**
     * Test of setSeriesTitle method, of class BookInSeries.
     */
    @Test
    public void testSetSeriesTitle()
    {
        book.setSeriesTitle("test");
        assertEquals("test", book.getSeriesTitle());
    }

    /**
     * Test of getSeriesTitle method, of class BookInSeries.
     */
    @Test
    public void testGetSeriesTitle()
    {
        assertEquals("seriesTitle", book.getSeriesTitle());
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
