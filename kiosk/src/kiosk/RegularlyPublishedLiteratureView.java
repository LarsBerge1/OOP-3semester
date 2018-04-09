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
public class RegularlyPublishedLiteratureView  extends LiteratureView{
    private RegularlyPublishedLiterature rpl;
    public RegularlyPublishedLiteratureView(RegularlyPublishedLiterature rpl)
    {
        super(rpl);
        this.rpl = rpl;
    }
    public void display()
    {
        super.display();
        System.out.println("Yearly releases: " + rpl.getNumberOfYearlyReleases());
        System.out.println("Subject: " + rpl.getSubject());
    }
}
