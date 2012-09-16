package ru.spb.aboutweb2.life.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 17.09.2012
 * Time: 3:30:18
 * To change this template use File | Settings | File Templates.
 */
public class ShowOriginListener implements ActionListener {

    private LifePanel lifePanel;

    public ShowOriginListener(LifePanel lifePanel) {
        this.lifePanel = lifePanel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        System.out.println(selected);
        lifePanel.setShowOriginBorder(selected);                
        lifePanel.repaint();
    }



}
