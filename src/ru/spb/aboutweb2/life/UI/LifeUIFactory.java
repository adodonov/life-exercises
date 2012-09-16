package ru.spb.aboutweb2.life.UI;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 12:12:27
 * To change this template use File | Settings | File Templates.
 */
public class LifeUIFactory {

    public static LifeUI getLifeUI() {
        LifeUI lifeUI = new LifeUIImpl();
        return lifeUI;
    }


}
