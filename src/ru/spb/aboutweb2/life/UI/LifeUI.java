package ru.spb.aboutweb2.life.UI;

import ru.spb.aboutweb2.life.Life;
import ru.spb.aboutweb2.life.gameengine.GameEngine;

import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:08:45
 * To change this template use File | Settings | File Templates.
 */
public interface LifeUI {
    void update(Map<Coords, Color> cells);

    void setController(Life life);

    void showUI();

    void calculateOriginBorder();

    Set<Coords> getSquares();
    
    UIState getUIState(); 

    void setUIState(UIState uiState);

    void clear();

    UIMode getMode();

    void setMode(UIMode mode);

}
