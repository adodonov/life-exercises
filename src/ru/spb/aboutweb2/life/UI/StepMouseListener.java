package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;

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

    private LifePanel lifePanel;
    private Life lifeController;

    public StepMouseListener(Life lifeController, LifePanel lifePanel) {
        this.lifePanel = lifePanel;
        this.lifeController = lifeController;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            if(lifePanel.isInitState()) {
                lifePanel.setInitState(false);

                new Thread(new Runnable() {
                    public void run() {
                        lifeController.initLifeState(lifePanel.getSquares());
                        lifeController.executeCommand("step");
                    }
                }).start();
            }
            else {

            new Thread(new Runnable() {
                public void run() {
                        lifeController.executeCommand("step");
                }
            }).start();
            }

        }

    }

}
