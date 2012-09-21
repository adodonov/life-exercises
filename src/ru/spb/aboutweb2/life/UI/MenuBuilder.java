package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 17.09.2012
 * Time: 15:14:12
 * To change this template use File | Settings | File Templates.
 */
public class MenuBuilder {
    private MenuBar mb;
    private Menu mFile;
    private MenuItem miOpen;
    private MenuItem miSave;
    private MenuItem miExit;
    private LifeFrame lifeGUI;
    private Life lifeController;

    public MenuBuilder(LifeFrame lifeGUI, Life lifeController) {
        this.lifeGUI = lifeGUI;
        this.lifeController = lifeController;
    }

    public void build() {

        mb = new MenuBar();
        mFile = new Menu("File");

        miOpen = new MenuItem("Open...");
        mFile.add(miOpen);
        OpenListener openListener = new OpenListener();
        miOpen.addActionListener(openListener);

        miSave = new MenuItem("Save As...");
        mFile.add(miSave);
        SaveListener saveListener = new SaveListener();
        miSave.addActionListener(saveListener);

        mFile.add("-");

        miExit = new MenuItem("Exit");
        mFile.add(miExit);

        miExit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Exit performed");
                        lifeGUI.dispose();
                        System.exit(0);
                    }
                }
        );

        mb.add(mFile);

        lifeGUI.setMenuBar(mb);
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            FileDialog fc = new FileDialog(lifeGUI,
                "Save cells as...", FileDialog.SAVE);
                   fc.setVisible(true);
            String fn = fc.getFile();
            String dir = fc.getDirectory();
            if (fn == null)
              System.out.println("You cancelled the choice");
            else {
                System.out.println("You chose " + fn);                
                lifeController.save(dir + fn);


            }
        }
    }

    private class OpenListener  implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FileDialog fc = new FileDialog(lifeGUI,
                "Open cells...", FileDialog.LOAD);
                   fc.setVisible(true);
            String fn = fc.getFile();
            String dir = fc.getDirectory();
            if (fn == null)
              System.out.println("You cancelled the choice");
            else {
                System.out.println("You chose " + fn);
                lifeController.load(dir + fn);


            }
        }
    }



}
