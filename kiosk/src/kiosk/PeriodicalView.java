/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiosk;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Presents information about the Periodical
 * 
 */
public class PeriodicalView  extends LiteratureView{
    private Periodical p;
    public PeriodicalView(Periodical rpl)
    {
        super(rpl);
        this.p = rpl;
    }
    /**
     * Displays the information associated with a Periodical
 object
     */
    @Override
    public void display(VBox vBox)
    {
        super.display(vBox);
        TextArea t = new TextArea("Yearly releases: " + p.getNumberOfYearlyReleases() + "\nSubject: " + p.getSubject());
        vBox.getChildren().add(t);        
    }
}
