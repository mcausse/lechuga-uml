package org.homs.houmls.shape;

import org.homs.houmls.GridControl;

import java.awt.*;

public interface Shape extends Draggable {

    int DUPLICATE_OFFSET_PX = GridControl.GRID_SIZE * 2;

    String getAttributesText();

    void setAttributesText(String attributesText);

    Draggable findTranslatableByPos(double mousex, double mousey);

    void draw(Graphics g);

    void drawSelection(Graphics g);

    Shape duplicate();
}