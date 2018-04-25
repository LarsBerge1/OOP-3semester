/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    
    public void display(VBox vBox)
    {
        super.display(vBox);
        TextField author = new TextField("Author: " + book.getAuthor());
        TextField  publicationDate = new TextField("Publication date: " + book.getPublicationDate());
        vBox.getChildren().addAll(author, publicationDate);
    }
    
}
