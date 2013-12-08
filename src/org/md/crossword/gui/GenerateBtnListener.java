package org.md.crossword.gui;

import org.md.crossword.app.Progr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Auto-generated Javadoc
/**
 * Created with IntelliJ IDEA.
 * User: Grzegorz
 * Date: 08.12.13
 * Time: 00:39
 * To change this template use File | Settings | File Templates.
 *
 * @see GenerateBtnEvent
 */
public class GenerateBtnListener implements ActionListener {
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        Progr.setCurrentCrossword(Progr.browser.newCrosswordFromFile(15, 15, Progr.pathToDictionary));
        System.out.println("You clicked the button");
    }
}
