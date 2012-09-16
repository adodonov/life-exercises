package ru.spb.aboutweb2.life.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.2012
 * Time: 3:25:42
 * To change this template use File | Settings | File Templates.
 */
public class LifeFrame  extends JFrame {

    private LifePanel lifePanel;

    private class MyDispatcher implements KeyEventDispatcher {
         @Override
         public boolean dispatchKeyEvent(KeyEvent e) {
             if (e.getID() == KeyEvent.KEY_PRESSED) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    lifePanel.setFocusX(lifePanel.getFocusX() + 1);
                    repaintLifePanel();
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    lifePanel.setFocusX(lifePanel.getFocusX() - 1);
                    repaintLifePanel();
                }
                else if (keyCode == KeyEvent.VK_UP) {
                    lifePanel.setFocusY(lifePanel.getFocusY() - 1);
                    repaintLifePanel();
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    lifePanel.setFocusY(lifePanel.getFocusY() + 1);
                    repaintLifePanel();
                }    
             }
             System.out.println("FocusX " + lifePanel.getFocusX() + "FocusY " + lifePanel.getFocusY());
             return false;
         }
     }

    private void repaintLifePanel() {
        new Thread(new Runnable() {
            public void run() {
                    lifePanel.repaint();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
            }
        }).start();
    }

    public LifeFrame() {
        System.out.println("test");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }

    public void setLifePanel(LifePanel lifePanel) {
        this.lifePanel = lifePanel;
    }
}
