package ru.spb.aboutweb2.life.gameengine.transformers;

import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.gameengine.CellStatus;
import ru.spb.aboutweb2.life.gameengine.LifeState;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 23:48:01
 * To change this template use File | Settings | File Templates.
 */
public class NewTurnTransformer implements Transformer {

    public LifeState getNextState(LifeState oldState) {
        LifeState newLifeState = new LifeState(oldState.getTurn()+1, null);        
        Set<Coords> coords = oldState.getCells().keySet(); 
        for(Coords coord : coords) {
            CellStatus oldStatus = oldState.getCells().get(coord);
            if(CellStatus.LIVING.equals(oldStatus) || CellStatus.NEWBORN.equals(oldStatus)) {
                newLifeState.addCell(coord, CellStatus.LIVING);    
            }
        }
        return newLifeState;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
