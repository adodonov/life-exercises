package ru.spb.aboutweb2.life.UI.listeners;

import ru.spb.aboutweb2.life.UI.LifePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.2012
 * Time: 2:46:17
 * To change this template use File | Settings | File Templates.
 */
public class LifePanelKeyListener  extends KeyAdapter {

    private LifePanel lifePanel;

    public LifePanelKeyListener(LifePanel lifePanel) {
        this.lifePanel = lifePanel;
    }

    public void keyTyped(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
    }

    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT)
            lifePanel.setFocusX(lifePanel.getFocusX() - 1);
        else if (keyCode == KeyEvent.VK_RIGHT)
            lifePanel.setFocusX(lifePanel.getFocusX() + 1);
        else if (keyCode == KeyEvent.VK_UP)
            lifePanel.setFocusY(lifePanel.getFocusX() - 1);
        else if (keyCode == KeyEvent.VK_DOWN)
            lifePanel.setFocusX(lifePanel.getFocusY() + 1);
    }

    public void keyReleased(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
    }
}
