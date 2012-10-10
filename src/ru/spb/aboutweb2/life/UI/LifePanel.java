package ru.spb.aboutweb2.life.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 07.09.2012
 * Time: 14:55:27
 * To change this template use File | Settings | File Templates.
 */
public class LifePanel extends JPanel {
    final private int MIN_CELL_SIZE = 10;
    final private int MAX_CELL_SIZE = 50;

    UIState uiState = new UIState();
    private Integer fieldWidth;
    private Integer fieldHeight;    

    private int residue;

    private Map<Coords, Color> squares = null;
    private OriginBorder originBorder = new OriginBorder();    

    private boolean showOriginBorder = true;

    public LifePanel(Integer fieldWidth, Integer fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void paintComponent(Graphics g) {
        int focusX = uiState.getFocusX();
        int focusY = uiState.getFocusY();
        int cellSize = uiState.getCellSize();        

        g.setColor (new Color(240, 255, 255));
        g.fillRect( 0, 0, fieldWidth, fieldHeight );
        drawGrid(g, cellSize);
        drawOrigin(g, cellSize);

          if (squares != null) {
            for(Coords cell : squares.keySet()) {
                g.setColor(squares.get(cell));
                g.fillRect((cell.getCoordX() + focusX) * cellSize + 1 , (fieldHeight - residue) - ((cell.getCoordY() + focusY) * cellSize) + 1 , cellSize-1, cellSize-1);
            }

          }
    }

    private void drawOrigin(Graphics g, int gridSpace) {
        int focusX = uiState.getFocusX();
        int focusY = uiState.getFocusY();

        g.setColor(Color.RED);
        if(originBorder == null || originBorder.isEmpty() || !showOriginBorder  ) {return;}
        for(Segment segment : originBorder.getSegments()) {
            g.drawLine((segment.getP1().getCoordX() + focusX) * gridSpace, (fieldHeight - residue) - (segment.getP1().getCoordY() + focusY - 1) * gridSpace,
                    (segment.getP2().getCoordX() + focusX) * gridSpace, (fieldHeight - residue) - (segment.getP2().getCoordY() + focusY - 1) * gridSpace);
        }

    }

    private void drawGrid(Graphics g, int gridSpace) {
        g.setColor(Color.GRAY);
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = getWidth() - insets.right;
        int lastY = getHeight() - insets.bottom;

        // Draw vertical lines.
        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += gridSpace;
        }

        // Draw horizontal lines.
        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += gridSpace;
        }
    }

    



    public int getFocusX() {
        return uiState.getFocusX();
    }

    public void setFocusX(int focusX) {
        uiState.setFocusX(focusX);
    }

    public int getFocusY() {
        return uiState.getFocusY();
    }

    public void setFocusY(int focusY) {
        uiState.setFocusY(focusY);
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }

    public int getFirstClickX() {
        return uiState.getFirstClickX();
    }

    public void setFirstClickX(int firstClickX) {
        uiState.setFirstClickX(firstClickX);
    }

    public int getFirstClickY() {
        return uiState.getFirstClickY();
    }

    public void setFirstClickY(int firstClickY) {
        uiState.setFirstClickY(firstClickY);
    }

    public int getCellSize() {
        return uiState.getCellSize();
    }

    public void setCellSize(int cellSize) {
        uiState.setCellSize(cellSize);
    }

    public void incCellSize() {
        if(uiState.getCellSize() <= MAX_CELL_SIZE) {
            uiState.setCellSize(uiState.getCellSize() + 1);
        }
    }

    public void decCellSize() {
        if(uiState.getCellSize()  >= MIN_CELL_SIZE) {
            uiState.setCellSize(uiState.getCellSize() - 1);
        }
    }

    public Map<Coords, Color> getSquares() {
        return squares;
    }

    public Map<Coords, Color> setSquares(Map<Coords, Color> squares) {
        this.squares = squares;
        return squares;
    }

    public Integer getFieldWidth() {
        return fieldWidth;
    }

    public Integer getFieldHeight() {
        return fieldHeight;
    }

    public OriginBorder getOriginBorder() {
        return originBorder;
    }

    public void setShowOriginBorder(boolean showOriginBorder) {
        this.showOriginBorder = showOriginBorder;
    }

    public boolean isShowOriginBorder() {
        return showOriginBorder;
    }

    public int getZoomCenterX() {
        return uiState.getZoomCenterX();
    }

    public void setZoomCenterX(int zoomCenterX) {
        uiState.setZoomCenterX(zoomCenterX);
    }

    public int getZoomCenterY() {
        return uiState.getZoomCenterY();
    }

    public void setZoomCenterY(int zoomCenterY) {
        uiState.setZoomCenterY(zoomCenterY);
    }

    public void initSettings(int initX, int initY) {
        setFirstClickX(1 + getWidth()/2 - (getWidth()/2) % getCellSize());
        setFirstClickY(1 + getHeight()/2 - (getHeight()/2) % getCellSize());

        setFocusX((1 + initX - initX % getCellSize()) / getCellSize());
        setResidue( getFieldHeight() %  getCellSize());


        setFocusY(((getFieldHeight() -  getResidue())
            - (1 + initY - initY % getCellSize())) / getCellSize() + 1);

        setZoomCenterX(getFocusX() - getFirstClickX()/getCellSize());
        setZoomCenterY(getFocusY() - getFirstClickY()/getCellSize());
    }

    public UIState getUiState() {
        return uiState;
    }

    public void setUiState(UIState state) {
        this.uiState = state;
        setResidue( getFieldHeight() %  getCellSize());
        initSettings(uiState.getFirstClickX(), uiState.getFirstClickY());
    }
}



