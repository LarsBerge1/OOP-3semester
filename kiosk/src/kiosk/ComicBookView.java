/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.TextArea;

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
     * @param infoArea the area to present information
     */
    @Override
    public void display(TextArea infoArea)
    {
        super.display(infoArea);
        infoArea.appendText("\nGenre " + cb.getGenre());
    }
    
}
