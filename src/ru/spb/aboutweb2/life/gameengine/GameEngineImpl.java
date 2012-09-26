package ru.spb.aboutweb2.life.gameengine;

import ru.spb.aboutweb2.life.Life;
import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.UI.LifeUI;
import ru.spb.aboutweb2.life.gameengine.transformers.Transformer;
import ru.spb.aboutweb2.life.gameengine.transformers.TransformerFactory;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 18:34:25
 * To change this template use File | Settings | File Templates.
 */
public class GameEngineImpl implements GameEngine {

    private Life lifeController;
    private LifeState lifeState;
    private EngineStatus engineStatus;

    public void setController(Life lifeController) {
        this.lifeController = lifeController;
    }

    private boolean isLifeStatePermanent(LifeState lifeState, LifeState prevLastState) {
        if(lifeState != null) {
            if(lifeState.equals(prevLastState)) {
                return true;
            }
        }
        return false;
    }

    private int phaseNum = 0;
    private LifeState prevLastState = null;    

    private boolean isNewTurn(int phaseNum) {
        return phaseNum%3 == 0;
    }

    private boolean isStopped() {
        return EngineStatus.STOPPED.equals(engineStatus);
    }

    private boolean canContinue() {
        return lifeState!= null && lifeState.getCellCount() != 0 && !(isNewTurn(phaseNum) && isStopped());
    }

    public void run() {
        new Thread(new Runnable() {
            public void run() {
                doRun();
            }
        }).start();
    }

    private synchronized void doRun() {
        engineStatus = EngineStatus.RUN;
        while(canContinue()) {
            System.out.println("Phase " + phaseNum);
            if(isNewTurn(phaseNum)) {
                //lifeState.getCells().put(new Coords((int)(Math.random()*10), (int)(Math.random()*10)), CellStatus.LIVING);
                if(isLifeStatePermanent(lifeState, prevLastState)) {
                    break;
                }
                System.out.println("Turn " + phaseNum/3  + "  Cell count = " + lifeState.getCellCount());
            }
            prevLastState = lifeState;
            lifeState = getNextState(lifeState);

            lifeController.updateUI();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            phaseNum++;
        }
        engineStatus = EngineStatus.STOPPED;
    }

    public void step() {
        engineStatus = EngineStatus.STOPPED;
        doStep();
    }

    private synchronized void doStep() {
        if(lifeState == null || lifeState.getCellCount() == 0) {
            return;
        }
        do {
            if(isNewTurn(phaseNum)) {
                System.out.println("Turn " + phaseNum/3  + "  Cell count = " + lifeState.getCellCount());
            }
            prevLastState = lifeState;
            lifeState = getNextState(lifeState);

            lifeController.updateUI();
            phaseNum++;
        }  while(!isNewTurn(phaseNum));
    }

    public void stop() {
        engineStatus = EngineStatus.STOPPED;
        doStop();
        System.out.println("interrupted!");
    }

    private synchronized void doStop() {
        phaseNum = 0;
        lifeState = null;
        prevLastState = null;
        lifeController.updateUI();
    }

    public LifeState getNextState(LifeState lifeState) {
        Transformer transformer = TransformerFactory.getTransformer(phaseNum);
        return transformer.getNextState(lifeState);
    }


    public void pause() {
            engineStatus = EngineStatus.STOPPED;
            Toolkit.getDefaultToolkit().beep();
    }

    public Life getLifeController() {
        return lifeController;
    }

    public void setLifeController(Life lifeController) {
        this.lifeController = lifeController;
    }

    public LifeState getLifeState() {
        return lifeState;
    }

    public void initLifeState(Set<Coords> coords) {
        lifeState = new LifeState(0, null);
        for(Coords coord : coords) {
            lifeState.addCell(coord, CellStatus.LIVING);    
        }

    }

    public void setLifeState(LifeState lifeState) {
        this.lifeState = lifeState;
    }

    public boolean isRun() {
        return EngineStatus.RUN.equals(engineStatus);
    }
}
