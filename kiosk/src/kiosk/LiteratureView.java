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
public abstract class LiteratureView {
    private Literature literature;
    public LiteratureView(Literature literature)
    {
        this.literature = literature;
    }
    
    public void display()
    {
        System.out.println();
        System.out.println("Title: " + literature.getTitle());
        System.out.println("Publisher: " + literature.getPublisher());
    }
}
