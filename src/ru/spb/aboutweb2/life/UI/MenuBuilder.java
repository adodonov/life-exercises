package ru.spb.aboutweb2.life.UI;

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

    public MenuBuilder(LifeFrame lifeGUI) {
        this.lifeGUI = lifeGUI;
    }

    public void build() {

        mb = new MenuBar();
        mFile = new Menu("File");

        miOpen = new MenuItem("Open...");
        mFile.add(miOpen);

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
            if (fn == null)
              System.out.println("You cancelled the choice");
            else {
                System.out.println("You chose " + fn);
                ObjectOutputStream oos;

                try
                {
                  oos = new ObjectOutputStream(
                    new FileOutputStream(
                      fc.getDirectory() +
                      fc.getFile()));

                  oos.writeObject(lifeGUI);    
                  oos.flush();
                  oos.close();
                }
                catch (IOException ex)
                {
                  System.out.println(ex.toString());
                }

            }
        }
    }



}
