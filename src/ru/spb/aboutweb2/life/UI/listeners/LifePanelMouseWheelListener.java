package ru.spb.aboutweb2.life.UI.listeners;

import ru.spb.aboutweb2.life.UI.LifePanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 15:37:39
 * To change this template use File | Settings | File Templates.
 */
public class LifePanelMouseWheelListener implements MouseWheelListener {

    private LifePanel lifePanel;

    public LifePanelMouseWheelListener(LifePanel lifePanel) {
        this.lifePanel = lifePanel;
    }

    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int notches = mouseWheelEvent.getWheelRotation();
        if (notches < 0) {
           lifePanel.decCellSize();
        } else {
           lifePanel.incCellSize();
        }
        lifePanel.setResidue(lifePanel.getFieldHeight() %  lifePanel.getCellSize());
        lifePanel.setFocusX(lifePanel.getZoomCenterX() + lifePanel.getFirstClickX() / lifePanel.getCellSize());
        lifePanel.setFocusY(lifePanel.getZoomCenterY() + ((lifePanel.getFieldHeight() - lifePanel.getResidue()) - lifePanel.getFirstClickY()) / lifePanel.getCellSize());

        lifePanel.repaint();
    }


}
