/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import java.util.ArrayList;

/**
 *
 * @author amhat
 */
public class BookSeries{
    private ArrayList<Book> books;
    private String name;
    
    
    public BookSeries(String seriesName)
    {
        name = seriesName;
        books = new ArrayList<>();
    }
    
    public void addBookToSeries(Book book)
    {
        books.add(book);
    }
    
    public ArrayList<Book> getBookSeries()
    {
        return books;
    }
}
