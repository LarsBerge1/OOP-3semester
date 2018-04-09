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
public class SingleBookView  extends LiteratureView {
    private SingleBook sb;
    public SingleBookView(SingleBook sb)
    {
     super(sb);   
     this.sb = sb;
    }
    public void display()
    {
        super.display();
        System.out.println("Author: " + sb.getAuthor());
        System.out.println("Publication date: " + sb.getPublicationDate());
        System.out.println("Edition: " + sb.getEdition());
    }
}
