package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 17:20:13
 * To change this template use File | Settings | File Templates.
 */
public class LifePanelMouseListener extends MouseAdapter {

    private LifePanel lifePanel;

    private Color defaultColor = new Color(51, 153, 51);

    public LifePanelMouseListener(Life lifeController, LifePanel lifePanel) {
        this.lifeController = lifeController;
        this.lifePanel = lifePanel;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && lifePanel.isInitState()) {
            if(e.getY() < lifePanel.getFieldHeight()) {
                if (lifePanel.getSquares() == null) {

                    Map<Coords, Color> cells = lifePanel.setSquares(new HashMap<Coords, Color>());
                    cells.put(new Coords(0, 0), defaultColor);
                    lifePanel.setFirstClickX(1 + lifePanel.getWidth()/2 - (lifePanel.getWidth()/2) % lifePanel.getCellSize());
                    lifePanel.setFirstClickY(1 + lifePanel.getHeight()/2 - (lifePanel.getHeight()/2) % lifePanel.getCellSize());

                    lifePanel.setFocusX((1 + e.getX() - e.getX() % lifePanel.getCellSize()) / lifePanel.getCellSize());
                    lifePanel.setResidue( lifePanel.getFieldHeight() %  lifePanel.getCellSize());


                    lifePanel.setFocusY(((lifePanel.getFieldHeight() -  lifePanel.getResidue())
                        - (1 + e.getY() - e.getY() % lifePanel.getCellSize())) / lifePanel.getCellSize() + 1);

                    lifePanel.setZoomCenterX(lifePanel.getFocusX() - lifePanel.getFirstClickX()/lifePanel.getCellSize());
                    lifePanel.setZoomCenterY(lifePanel.getFocusY() - lifePanel.getFirstClickY()/lifePanel.getCellSize());

                } else {
                    int absX = e.getX() / lifePanel.getCellSize();
                    int absY = (( lifePanel.getFieldHeight() - lifePanel.getResidue()) - e.getY()) / lifePanel.getCellSize() + 1;
                    Coords cell = new Coords(absX - lifePanel.getFocusX(), absY - lifePanel.getFocusY());
                    if(lifePanel.getSquares().keySet().contains(cell)) {
                        lifePanel.getSquares().remove(cell);
                    } else {
                        lifePanel.getSquares().put(cell, defaultColor);
                    }
                }

                lifePanel.repaint();
            }
        } else if(e.getButton() == MouseEvent.BUTTON1 && !lifePanel.isInitState()) {
            new Thread(new Runnable() {
                public void run() {
                        lifeController.executeCommand("pauseOrRun");
                }
            }).start();
        }


    }

    private Life lifeController;


}
