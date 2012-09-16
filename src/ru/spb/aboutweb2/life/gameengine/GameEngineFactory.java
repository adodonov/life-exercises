package ru.spb.aboutweb2.life.gameengine;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:15:32
 * To change this template use File | Settings | File Templates.
 */
public class GameEngineFactory {

    public static GameEngine getGameEngine() {
        return new GameEngineImpl();
    }

}
