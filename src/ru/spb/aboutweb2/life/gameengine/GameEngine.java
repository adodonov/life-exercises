package ru.spb.aboutweb2.life.gameengine;

import ru.spb.aboutweb2.life.Life;
import ru.spb.aboutweb2.life.UI.Coords;
import ru.spb.aboutweb2.life.UI.LifeUI;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:09:28
 * To change this template use File | Settings | File Templates.
 */
public interface GameEngine {
    void setController(Life life);

    void executeCommand(String command);

    void initLifeState(Set<Coords> coords);

    LifeState getNextState(LifeState lifeState);

    LifeState getLifeState();

    void setLifeState(LifeState lifeState); 

    void run();
}
