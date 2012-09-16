package ru.spb.aboutweb2.life.gameengine.transformers;

import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.gameengine.CellStatus;
import ru.spb.aboutweb2.life.gameengine.LifeState;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 23:15:52
 * To change this template use File | Settings | File Templates.
 */
public class MarkDiedTransformer implements Transformer {

    public LifeState getNextState(LifeState oldState) {
        LifeState newLifeState = new LifeState(oldState.getTurn(), null);
        Set<Coords> coords =  oldState.getCells().keySet();
        Set<Coords> existsCoords =  oldState.getExistCells().keySet();

        for(Coords coord : coords) {
            if(oldState.getCells().get(coord) != CellStatus.LIVING) {
                newLifeState.addCell(coord, oldState.getCells().get(coord));    
            }
            boolean dying = false;
            int neighbourNum = 0;
            int i = -1;
            while(i <= 1 && !dying ) {
                int j = -1;
                while(j <= 1 && !dying ) {
                    if(existsCoords.contains(new Coords(coord.getCoordX() + i, coord.getCoordY() + j))
                            && !(i==0 && j==0) ) {
                        neighbourNum++;
                        if(neighbourNum > 3) {
                            dying = true;
                        }
                    }
                    j++;
                }
                i++;
            }
            if(neighbourNum < 2) {
                dying = true;
            }
            if(dying) {
                newLifeState.addCell(coord, CellStatus.DYING);
            } else {
                newLifeState.addCell(coord, CellStatus.LIVING);
            }

        }
        return newLifeState;
    }
}
