package ru.spb.aboutweb2.life.gameengine.transformers;

import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.gameengine.CellStatus;
import ru.spb.aboutweb2.life.gameengine.LifeState;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 23:18:32
 * To change this template use File | Settings | File Templates.
 */
public class MarkNewbornTransformer implements Transformer {

    private Set<Coords> alreadyChecked;

    public LifeState getNextState(LifeState oldState) {
        alreadyChecked = new HashSet<Coords>();
        LifeState newLifeState = new LifeState(oldState.getTurn(), null);
        Set<Coords> existsCoords = oldState.getExistCells().keySet();
        for(Coords coord : existsCoords) {
            int i = -1;
            while(i <= 1) {
                int j = -1;
                while(j <= 1) {
                    Coords testedCoord = new Coords(coord.getCoordX() + i, coord.getCoordY() + j);
                    if(!alreadyChecked.contains(testedCoord)) {
                        if(!existsCoords.contains(testedCoord)) {
                            if(checkIfGenerative(testedCoord, existsCoords)) {
                                newLifeState.addCell(testedCoord, CellStatus.NEWBORN);
                            }
                        } else {
                            newLifeState.addCell(testedCoord, oldState.getExistCells().get(testedCoord));
                        }
                        alreadyChecked.add(testedCoord);
                    }
                    j++;
                }
                i++;
            }
        }


        return newLifeState;  
    }

    private boolean checkIfGenerative(Coords testedCoord, Set<Coords> existsCoords) {
//        if(testedCoord.getCoordX() >=0 && testedCoord.getCoordX() <= 2 &&
//                testedCoord.getCoordY() >=0 && testedCoord.getCoordY() <= 2 ) return true;  

        //return true; // really cool generic condition
        boolean generative = false;
        int neighbourNum = 0;
        int i = -1;
        while(i <= 1) {
            int j = -1;
            while(j <= 1) {
                if(!(i==0 && j==0)) {
                    Coords neighbourCoord = new Coords(testedCoord.getCoordX() + i, testedCoord.getCoordY() + j);
                    if(existsCoords.contains(neighbourCoord)) {
                        neighbourNum++;
                    }
                }
                j++;
            }
            i++;
        }
        if(neighbourNum == 3) {
            generative = true;
        }
        return generative;    
    }

}
