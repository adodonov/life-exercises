package ru.spb.aboutweb2.life;

import ru.spb.aboutweb2.life.UI.*;
import ru.spb.aboutweb2.life.gameengine.CellStatus;
import ru.spb.aboutweb2.life.gameengine.GameEngine;
import ru.spb.aboutweb2.life.gameengine.GameEngineFactory;
import ru.spb.aboutweb2.life.gameengine.LifeState;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:02:58
 * To change this template use File | Settings | File Templates.
 */
public class Life {

    private GameEngine gameEngine;
    private LifeUI lifeUI;

    public static void main(String[] args) {

        Life lifeController = new Life();

        GameEngine gameEngine = GameEngineFactory.getGameEngine();
        lifeController.setGameEngine(gameEngine);
        gameEngine.setController(lifeController);

        LookAndFeel old = UIManager.getLookAndFeel();
        System.out.println(old);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(old);
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace(); 
            }
        }


        LifeUI lifeUI = LifeUIFactory.getLifeUI();
        lifeController.setLifeUI(lifeUI);
        lifeUI.setController(lifeController);

        lifeUI.showUI();

    }

    private boolean isInitState() {
        return UIMode.INIT.equals(lifeUI.getMode());
    }

    private boolean isRunning() {
        return UIMode.RUNNING.equals(lifeUI.getMode());
    }

    public void executeCommand(String command) {
        if ("pause".equals(command)) {
            gameEngine.pause();
            lifeUI.setMode(UIMode.PAUSE);            
        } else if ("step".equals(command)) {
            if(isInitState()) {
                initLifeState();
            }            
            gameEngine.step();
            lifeUI.setMode(UIMode.PAUSE);
        } else if ("stop".equals(command)) {
            gameEngine.stop();
            lifeUI.clear();
            lifeUI.setMode(UIMode.INIT);
        } else if ("pauseOrRun".equals(command)) {
            if(gameEngine.isRun()) {
                gameEngine.pause();
                lifeUI.setMode(UIMode.PAUSE);
            } else {
                if(isInitState()) {
                    initLifeState();
                }
                gameEngine.run();
                lifeUI.setMode(UIMode.RUNNING);
            }
        } else if ("run".equals(command)) {
            if(lifeUI.getSquares() == null || lifeUI.getSquares().size() == 0) {return;}
            if(isInitState()) {
                initLifeState();    
            }
            gameEngine.run();
            lifeUI.setMode(UIMode.RUNNING);
        } else if ("showSave".equals(command)) {
            if(isInitState()) {
                gameEngine.initLifeState(lifeUI.getSquares());
            }                
            if(isRunning()) {
                executeCommand("pause");
            }
        } else if ("showOpen".equals(command)) {
            if(isRunning()) {
                executeCommand("pause");
            }
        }

    }


    public void initLifeState() {
        lifeUI.calculateOriginBorder();
        gameEngine.initLifeState(lifeUI.getSquares());
    }


    private EnumMap<CellStatus, Color> cellToColor;

    public Life()
    {
        cellToColor = new EnumMap<CellStatus, Color>(CellStatus.class);
        cellToColor.put(CellStatus.LIVING, new Color(51, 153, 51));
        cellToColor.put(CellStatus.DYING, new Color(255, 204, 255));
        cellToColor.put(CellStatus.NEWBORN, new Color(153, 255, 153));
    }

    public void updateUI() {
        LifeState lifeState = gameEngine.getLifeState();
        Map<Coords, Color> squares = new HashMap<Coords, Color>();        
        if(lifeState != null) {
            Map<Coords, CellStatus> cells = lifeState.getCells();

            for( Coords coords : cells.keySet()  ) {
                squares.put(coords, cellToColor.get(cells.get(coords)));
            }
        }

        lifeUI.update(squares);

    }

    public int getTurn() {
        return gameEngine.getLifeState() == null ? 0 : gameEngine.getLifeState().getTurn();
    }

    public void save(String pathToFile) {
        LifeState lifeState = gameEngine.getLifeState();
        UIState uiState = lifeUI.getUIState();
        try
        {
            Writer wr = new FileWriter(pathToFile);
            int cellSize = uiState.getCellSize();
            wr.write(Integer.toString(cellSize));
            wr.write("\r\n");
            String focus = uiState.getFocus() == null ? "0 0" : uiState.getFocus().toString();
            wr.write(focus);
            wr.write("\r\n");
            String firstClick = uiState.getFirstClick() == null ? "0 0" : uiState.getFirstClick().toString();
            wr.write(firstClick);
            wr.write("\r\n");
            String zoomCenter = uiState.getZoomCenter() == null ? "0 0" : uiState.getZoomCenter().toString();
            wr.write(zoomCenter);
            wr.write("\r\n");
            wr.write(String.valueOf(lifeState.getTurn()));
            wr.write("\r\n");            
            if( lifeState.getExistCells() != null ) {
                for(Coords coords : lifeState.getExistCells().keySet()) {
                    wr.write(coords.toString());
                    wr.write("\r\n");
                }
            }
            wr.flush();
            wr.close();

        }
        catch (IOException ex)
        {
          System.out.println(ex.toString());
        }


    }

    public void load(String pathToFile) {
        HashMap<Coords, CellStatus> cells = new HashMap<Coords, CellStatus>();
        try
        {
            UIState uiState = new UIState();
            BufferedReader rd = new BufferedReader(new FileReader(pathToFile));
            String line = rd.readLine();
            uiState.setCellSize(Integer.parseInt(line));
            line = rd.readLine();
            String[] focus = line.split(" ");
            uiState.setFocus(new Coords(focus[0], focus[1]));
            line = rd.readLine();
            String[] firstClick = line.split(" ");
            uiState.setFirstClick(new Coords(firstClick[0], firstClick[1]));
            line = rd.readLine();
            String[] zoomCenter = line.split(" ");
            uiState.setZoomCenter(new Coords(zoomCenter[0], zoomCenter[1]));

            line = rd.readLine();
            int turn = Integer.parseInt(line);


            for ( line = rd.readLine(); line != null; line = rd.readLine()) {
                String[] records = line.split(" ");
                cells.put(new Coords(records[0], records[1]), CellStatus.LIVING);
               
            }

            LifeState lifeState = new LifeState(turn, cells);

            executeCommand("stop");
            lifeUI.setUIState(uiState);
            gameEngine.setLifeState(lifeState);

        }
        catch (IOException ex)
        {
          System.out.println(ex.toString());
        }

        updateUI();


        
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public LifeUI getLifeUI() {
        return lifeUI;
    }

    public void setLifeUI(LifeUI lifeUI) {
        this.lifeUI = lifeUI;
    }
}
