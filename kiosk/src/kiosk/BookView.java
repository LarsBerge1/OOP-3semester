/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.Label;
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
        Label author = new Label("Author: " + book.getAuthor());
        Label  publicationDate = new Label("Publication date: " + book.getPublicationDate());
        vBox.getChildren().addAll(author, publicationDate);
    }
    
}
