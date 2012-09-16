package ru.spb.aboutweb2.life.gameengine;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 23:02:51
 * To change this template use File | Settings | File Templates.
 */
public enum Phase {

    MARK_DYING (0),
    MARK_NEWBORN (1),
    NEW_TURN (2);    

    private int phaseNum; 

    Phase(int phaseNum) {
        this.phaseNum = phaseNum;
    }

    public int getPhaseNum() {
        return phaseNum;
    }

}
