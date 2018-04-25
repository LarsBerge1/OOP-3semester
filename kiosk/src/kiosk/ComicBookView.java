/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Presents information about the RegularlyPublishedLiterature 
 */
public class ComicBookView  extends BookView {
    private ComicBook cb;
    public ComicBookView(ComicBook cb)
    {
        super(cb);
        this.cb = cb;
    }
    
    /**
     * Displays the information associated with a ComicBook object
     */
    @Override
    public void display(VBox vBox)
    {
        super.display(vBox);
        TextField t = new TextField("Genre " + cb.getGenre());
        vBox.getChildren().add(t);
    }
}
