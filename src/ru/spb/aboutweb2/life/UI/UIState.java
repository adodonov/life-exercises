package ru.spb.aboutweb2.life.UI;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 29.09.2012
 * Time: 20:56:12
 * To change this template use File | Settings | File Templates.
 */
public class UIState {
    private int cellSize = 37;
    private int focusX;
    private int focusY;

    private int firstClickX;
    private int firstClickY;

    private int zoomCenterX;
    private int zoomCenterY;

    public UIState() {
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getZoomCenterY() {
        return zoomCenterY;
    }

    public void setZoomCenterY(int zoomCenterY) {
        this.zoomCenterY = zoomCenterY;
    }

    public int getZoomCenterX() {
        return zoomCenterX;
    }

    public void setZoomCenterX(int zoomCenterX) {
        this.zoomCenterX = zoomCenterX;
    }

    public int getFirstClickY() {
        return firstClickY;
    }

    public void setFirstClickY(int firstClickY) {
        this.firstClickY = firstClickY;
    }

    public int getFirstClickX() {
        return firstClickX;
    }

    public void setFirstClickX(int firstClickX) {
        this.firstClickX = firstClickX;
    }

    public int getFocusY() {
        return focusY;
    }

    public void setFocusY(int focusY) {
        this.focusY = focusY;
    }

    public int getFocusX() {
        return focusX;
    }

    public void setFocusX(int focusX) {
        this.focusX = focusX;
    }

    public Coords getFocus() {
        return new Coords(getFocusX(), getFocusY()) ;        
    }

    public Coords getFirstClick() {
        return new Coords(getFirstClickX(), getFirstClickY()) ;
    }

    public Coords getZoomCenter() {
        return new Coords(getZoomCenterX(), getZoomCenterY()) ;
    }

    public void setFocus(Coords coordinate) {
        if(coordinate != null) {
            setFocusX(coordinate.getCoordX());
            setFocusY(coordinate.getCoordY());
        }
    }

    public void setFirstClick(Coords coordinate) {
        if(coordinate != null) {
            setFirstClickX(coordinate.getCoordX());
            setFirstClickY(coordinate.getCoordY());
        }
    }

    public void setZoomCenter(Coords coordinate) {
        if(coordinate != null) {
            setZoomCenterX(coordinate.getCoordX());
            setZoomCenterY(coordinate.getCoordY());
        }
    }

}
