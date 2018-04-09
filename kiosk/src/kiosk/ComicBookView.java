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
public class ComicBookView  extends LiteratureView {
    private ComicBook cb;
    public ComicBookView(ComicBook cb)
    {
        super(cb);
        this.cb = cb;
    }
    public void display()
    {
        super.display();
        System.out.println("Author: " + cb.getAuthor());
        System.out.println("Publication date: " + cb.getPublicationDate());
        System.out.println("Genre: " + cb.getGenre());
    }
}
