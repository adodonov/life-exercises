package ru.spb.aboutweb2.life.UI;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.09.2012
 * Time: 1:54:18
 * To change this template use File | Settings | File Templates.
 */
public class Segment {
    Coords p1;
    Coords p2;

    public Segment(Coords p1, Coords p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean isVertical() {
        return p1.getCoordX() == p2.getCoordX();
    }

    public boolean isHorizontal() {
        return p1.getCoordY() == p2.getCoordY();
    }

    public Coords getP1() {
        return p1;
    }

    public Coords getP2() {
        return p2;
    }
}
