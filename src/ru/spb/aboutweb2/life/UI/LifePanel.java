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

    private int cellSize = 37;
    private int focusX;
    private int focusY;
    private int residue;
    private int firstClickX;
    private int firstClickY;

    private int zoomCenterX;
    private int zoomCenterY;    

    private Integer fieldWidth;
    private Integer fieldHeight;

    private Map<Coords, Color> squares = null;
    private OriginBorder originBorder = new OriginBorder();    

    private boolean initState = true;
    private boolean showOriginBorder = true;

    public LifePanel(Integer fieldWidth, Integer fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void paintComponent(Graphics g) {
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
        //g.setColor(new Color(255, 228, 225));
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
        return focusX;
    }

    public void setFocusX(int focusX) {
        this.focusX = focusX;
    }

    public int getFocusY() {
        return focusY;
    }

    public void setFocusY(int focusY) {
        this.focusY = focusY;
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }

    public int getFirstClickX() {
        return firstClickX;
    }

    public void setFirstClickX(int firstClickX) {
        this.firstClickX = firstClickX;
    }

    public int getFirstClickY() {
        return firstClickY;
    }

    public void setFirstClickY(int firstClickY) {
        this.firstClickY = firstClickY;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void incCellSize() {
        if(this.cellSize <= MAX_CELL_SIZE) {
            this.cellSize++;
        }
    }

    public void decCellSize() {
        if(this.cellSize >= MIN_CELL_SIZE) {
            this.cellSize--;
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

    public boolean isInitState() {
        return initState;
    }

    public void setInitState(boolean initState) {
        this.initState = initState;
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
        return zoomCenterX;
    }

    public void setZoomCenterX(int zoomCenterX) {
        this.zoomCenterX = zoomCenterX;
    }

    public int getZoomCenterY() {
        return zoomCenterY;
    }

    public void setZoomCenterY(int zoomCenterY) {
        this.zoomCenterY = zoomCenterY;
    }
}



