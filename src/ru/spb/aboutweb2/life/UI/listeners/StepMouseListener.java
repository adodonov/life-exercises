package ru.spb.aboutweb2.life.UI.listeners;

import ru.spb.aboutweb2.life.Life;
import ru.spb.aboutweb2.life.UI.LifePanel;
import ru.spb.aboutweb2.life.UI.UIMode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.09.2012
 * Time: 3:34:01
 * To change this template use File | Settings | File Templates.
 */
public class StepMouseListener extends MouseAdapter {

    private Life lifeController;

    public StepMouseListener(Life lifeController) {
        this.lifeController = lifeController;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            new Thread(new Runnable() {
                public void run() {
                        lifeController.executeCommand("step");
                }
            }).start();
        }



    }

}
