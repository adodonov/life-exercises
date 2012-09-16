package ru.spb.aboutweb2.life.gameengine.transformers;

import ru.spb.aboutweb2.life.gameengine.Phase;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 23:12:17
 * To change this template use File | Settings | File Templates.
 */
public class TransformerFactory {

    public static Transformer getTransformer(int phaseNum) {
        Phase phase = Phase.values()[phaseNum%3];
        phaseNum++;
        if(Phase.MARK_DYING.equals(phase)) {
            return new MarkDiedTransformer();
        } else if(Phase.MARK_NEWBORN.equals(phase)) { 
            return new MarkNewbornTransformer();
        } else {
            return new NewTurnTransformer();
        }
    }



}
