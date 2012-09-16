package ru.spb.aboutweb2.life.gameengine;

import ru.spb.aboutweb2.life.UI.Coords;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 21:59:26
 * To change this template use File | Settings | File Templates.
 */
public class LifeState {
    private int turn;
    private Map<Coords, CellStatus> cells;
    private Map<Coords, CellStatus> existCells;

    private void init() {
        cells = new HashMap<Coords, CellStatus>();
        existCells = new HashMap<Coords, CellStatus>();
    }

    public void addCell(Coords coords, CellStatus cellStatus) {
        if(CellStatus.LIVING.equals(cellStatus) || CellStatus.DYING.equals(cellStatus) ) {
            existCells.put(coords, cellStatus);
        }
        cells.put(coords, cellStatus);        
    }

    public void removeCell(Coords coords) {
        existCells.remove(coords);
        cells.remove(coords);
    }


    public LifeState(int turn, HashMap<Coords, CellStatus> cells) {
        this.turn = turn;
        this.cells = cells;
        if(cells == null) {
            init();
        }
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Map<Coords, CellStatus> getCells() {
        return cells;
    }

    public void setCells(HashMap<Coords, CellStatus> cells) {
        this.cells = cells;
    }

    public Map<Coords, CellStatus> getExistCells() {
        return existCells;
    }

    public void setExistCells(Map<Coords, CellStatus> existCells) {
        this.existCells = existCells;
    }

    public int getCellCount() {
        return (existCells != null ? existCells.keySet().size() : 0);    
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LifeState)) return false;

        LifeState lifeState = (LifeState) o;

        if (existCells != null ? !existCells.equals(lifeState.existCells) : lifeState.existCells != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return existCells != null ? existCells.hashCode() : 0;
    }
}
