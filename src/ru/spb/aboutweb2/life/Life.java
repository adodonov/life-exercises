package ru.spb.aboutweb2.life;

import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.UI.LifeUI;
import ru.spb.aboutweb2.life.UI.LifeUIFactory;
import ru.spb.aboutweb2.life.gameengine.CellStatus;
import ru.spb.aboutweb2.life.gameengine.GameEngine;
import ru.spb.aboutweb2.life.gameengine.GameEngineFactory;
import ru.spb.aboutweb2.life.gameengine.LifeState;

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

        LifeUI lifeUI = LifeUIFactory.getLifeUI();
        lifeController.setLifeUI(lifeUI);
        lifeUI.setController(lifeController);

        //initLifeState(Set< Coords > coords)
        lifeUI.showUI();

    }

    public void executeCommand(String command) {
        gameEngine.executeCommand(command);
    }

    public void initLifeState(Map<Coords, Color> initMap) {
        lifeUI.calculateOriginBorder();
        gameEngine.initLifeState(lifeUI.getSquares());
    }

    public void start() {
        gameEngine.run();
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
        if(gameEngine.getLifeState() == null) {
            gameEngine.initLifeState(lifeUI.getSquares());
        }
        LifeState lifeState = gameEngine.getLifeState();
        try
        {
            Writer wr = new FileWriter(pathToFile);
            wr.write(String.valueOf(lifeState.getTurn()));
            wr.write("\r\n");
            String focus = lifeUI.getFocus() == null ? "0 0" : lifeUI.getFocus().toString();
            wr.write(focus);
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
        gameEngine.executeCommand("stop");
        HashMap<Coords, CellStatus> cells = new HashMap<Coords, CellStatus>();

        try
        {
            BufferedReader rd = new BufferedReader(new FileReader(pathToFile));
            String line = rd.readLine();
            int turn = Integer.parseInt(line);
            line = rd.readLine();
            String[] focus = line.split(" ");
            lifeUI.setFocus(new Coords(focus[0], focus[1]));

            for ( line = rd.readLine(); line != null; line = rd.readLine()) {
                String[] records = line.split(" ");
                cells.put(new Coords(records[0], records[1]), CellStatus.LIVING);
               
            }

            LifeState lifeState = new LifeState(turn, cells);
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
