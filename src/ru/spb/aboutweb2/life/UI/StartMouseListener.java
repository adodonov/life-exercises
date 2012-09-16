package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.09.2012
 * Time: 1:26:49
 * To change this template use File | Settings | File Templates.
 */
public class StartMouseListener extends MouseAdapter {

    private LifePanel lifePanel;
    private Life lifeController;


    public StartMouseListener(Life lifeController, LifePanel lifePanel) {
        this.lifePanel = lifePanel;
        this.lifeController = lifeController;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1  &&
                (lifePanel.getSquares() != null && lifePanel.getSquares().size() != 0)) {
            //lifeController.execute(command);
            if(lifePanel.isZeroTurn()) {
                lifePanel.setZeroTurn(false);

                new Thread(new Runnable() {
                    public void run() {
                        lifeController.initLifeState(lifePanel.getSquares());
                        lifeController.start();
                    }
                }).start();
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        lifeController.start();
                    }    
                }).start();
            }

        }
    }


}
