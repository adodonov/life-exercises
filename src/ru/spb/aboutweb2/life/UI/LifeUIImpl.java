package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;
import ru.spb.aboutweb2.life.gameengine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:10:48
 * To change this template use File | Settings | File Templates.
 */
public class LifeUIImpl implements LifeUI {

    private final static Integer WINDOW_WIDTH = 800;
    private final static Integer WINDOW_HEIGHT = 750;

    private LifePanel lifePanel;

    private Life lifeController;

    public void setController(Life lifeController) {
        this.lifeController = lifeController;
    }

    public void update(Map<Coords, Color> cells) {
        lifePanel.setSquares(cells);
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

    public void showUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame lifeGUI = initLifeGUI();
                lifeGUI.setVisible(true);
            }
        });
    }

    private JFrame initLifeGUI() {
        LifeFrame lifeGUI = new LifeFrame();
        lifeGUI.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        lifeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lifeGUI.setLocationRelativeTo(null);
        lifeGUI.setResizable(false);

        Integer fieldWidth = WINDOW_WIDTH;
        Integer fieldHeight = WINDOW_HEIGHT - 50;        
        LifePanel lifePanel = new LifePanel(fieldWidth, fieldHeight);
        lifePanel.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        LifePanelMouseWheelListener wheelListener = new LifePanelMouseWheelListener(lifePanel);
        lifePanel.addMouseWheelListener(wheelListener);
        LifePanelMouseListener mouseListener =  new LifePanelMouseListener(lifeController, lifePanel);

        lifePanel.addMouseListener(mouseListener);


        lifeGUI.setLayout(new BoxLayout(lifeGUI.getContentPane(), BoxLayout.PAGE_AXIS));

        lifeGUI.getContentPane().add(lifePanel);
        lifeGUI.setLifePanel(lifePanel);        

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setPreferredSize(new Dimension(fieldWidth, 50));


        JButton saveButton = new JButton("Save");
        JButton startButton = new JButton(">>");
        JButton stepButton = new JButton(">");
        JButton pauseButton = new JButton("||");
        JButton stopButton = new JButton("s");

        saveButton.setBounds(20, 10, 70, 30);
        startButton.setBounds(270, 10, 50, 30);
        stepButton.setBounds(330, 10, 50, 30);
        pauseButton.setBounds(390, 10, 50, 30);
        stopButton.setBounds(450, 10, 50, 30);

        StartMouseListener startMouseListener = new StartMouseListener(lifeController, lifePanel);
        startButton.addMouseListener(startMouseListener);

        PauseMouseListener pauseMouseListener = new PauseMouseListener(lifeController, lifePanel);
        pauseButton.addMouseListener(pauseMouseListener);

        StepMouseListener stepMouseListener = new StepMouseListener(lifeController, lifePanel);
        stepButton.addMouseListener(stepMouseListener);

        StopMouseListener stopMouseListener = new StopMouseListener(lifeController, lifePanel);
        stopButton.addMouseListener(stopMouseListener);

        controlPanel.add(startButton);
        controlPanel.add(saveButton);
        controlPanel.add(stepButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);

        JCheckBox showOrigin = new JCheckBox("Show origin border");
        showOrigin.setBounds(510, 10, 150, 30);
        showOrigin.setSelected(lifePanel.isShowOriginBorder());
        controlPanel.add(showOrigin);
        ShowOriginListener showListener = new ShowOriginListener(lifePanel);
        showOrigin.addActionListener(showListener);


        lifeGUI.getContentPane().add(controlPanel);

        setLifePanel(lifePanel);

        return lifeGUI; 
    }

    public LifePanel getLifePanel() {
        return lifePanel;
    }

    public void setLifePanel(LifePanel lifePanel) {
        this.lifePanel = lifePanel;
    }


    public void calculateOriginBorder() {
        Set<Coords> squares = lifePanel.getSquares().keySet();
        for(Coords square : squares) {
            if(!squares.contains(new Coords(square.getCoordX()-1, square.getCoordY()))) {
                lifePanel.getOriginBorder().addSegment(new Coords(square.getCoordX(), square.getCoordY()), 
                        new Coords(square.getCoordX(), square.getCoordY()+1));
            }
            if(!squares.contains(new Coords(square.getCoordX()+1, square.getCoordY()))) {
                lifePanel.getOriginBorder().addSegment(new Coords(square.getCoordX()+1, square.getCoordY()),
                        new Coords(square.getCoordX()+1, square.getCoordY()+1));
            }
            if(!squares.contains(new Coords(square.getCoordX(), square.getCoordY()-1))) {
                lifePanel.getOriginBorder().addSegment(new Coords(square.getCoordX(), square.getCoordY()),
                        new Coords(square.getCoordX() + 1, square.getCoordY()));
            }
            if(!squares.contains(new Coords(square.getCoordX(), square.getCoordY()+1))) {
                lifePanel.getOriginBorder().addSegment(new Coords(square.getCoordX(), square.getCoordY()+1),
                        new Coords(square.getCoordX() + 1, square.getCoordY()+1));
            }
        }


    }

}
