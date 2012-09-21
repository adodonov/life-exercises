package ru.spb.aboutweb2.life.UI;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 15:05:50
 * To change this template use File | Settings | File Templates.
 */
public class Coords {
    private int coordX;
    private int coordY;

    public Coords(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Coords(String coordX, String coordY) {
        this.coordX = Integer.parseInt(coordX);
        this.coordY = Integer.parseInt(coordY);;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coords)) return false;

        Coords coords = (Coords) o;

        if (coordX != coords.coordX) return false;
        if (coordY != coords.coordY) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coordX;
        result = 31 * result + coordY;
        return result;
    }

    @Override
    public String toString() {
        return coordX + " " + coordY;
    }
}