/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

/**
 *
 * @author amhat
 */
public class BookInSeriesView extends LiteratureView {
    private BookInSeries bs;
    public BookInSeriesView(BookInSeries bs)
    {
        super(bs);
        this.bs = bs;
        
    }
    public void display()
    {
        super.display();
        System.out.println("Author: " + bs.getAuthor());
        System.out.println("Publication date: " + bs.getPublicationDate());
        System.out.println("Series title: " + bs.getSeriesTitle());
        
    }
    
}
