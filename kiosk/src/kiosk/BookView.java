/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.TextArea;

/**
 *
 * @author amhat
 */
public class BookView extends LiteratureView{
    private Book book;
    public BookView(Book book)
    {
        super(book);
        this.book = book;
    }
    
    /**
     * Adds information about the book to the informaitonArea
     * @param infoArea the area to present information
     */
    @Override
    public void display(TextArea infoArea)
    {
        super.display(infoArea);
        infoArea.appendText("\nAuthor: " + book.getAuthor());
        infoArea.appendText("\nPublication date: " + book.getPublicationDate());
    }
    
}
