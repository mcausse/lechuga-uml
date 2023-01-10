package org.homs.houmls.shape.impl;

import org.homs.houmls.GridControl;
import org.homs.houmls.Turtle;
import org.homs.houmls.shape.Draggable;
import org.homs.houmls.shape.Shape;

import java.awt.*;
import java.util.Collection;

import static org.homs.houmls.LookAndFeel.basicStroke;

public class Moneco extends Box {

    static final int MONECO_WIDTH = GridControl.GRID_SIZE * 5;
    static final int MONECO_HEIGTH = GridControl.GRID_SIZE * 8;

    public Moneco(int x, int y, String attributes) {
        super(x, y, MONECO_WIDTH, MONECO_HEIGTH, attributes);
    }

    @Override
    public Shape duplicate() {
        return new Moneco((int) x + DUPLICATE_OFFSET_PX, (int) y + DUPLICATE_OFFSET_PX, attributesText);
    }

    @Override
    public Draggable findDraggableByPos(Collection<Shape> connectors, double mousex, double mousey) {
        if (this.x <= mousex && mousex <= this.x + this.width && this.y <= mousey && mousey <= this.y + this.height) {
            return this;
        }
        return null;
    }

    protected void drawTheBox(Graphics2D g2) {

        int ix = (int) x;
        int iy = (int) y;
        int iwidth = (int) width;
        int iheight = (int) height;

        int headRadius = 5;

        g2.setStroke(basicStroke);

        g2.setColor(backgroundColor);
        g2.fillRect(ix, iy, iwidth, iheight);

        g2.setColor(Color.BLACK);
        var t = new Turtle(ix + iwidth / 2, iy, 0);
        t.rotate(90);
        t.jump(GridControl.GRID_SIZE * 3);

        // cap
        t.jump(headRadius);
        t.drawCircle(g2, headRadius);
        t.jump(headRadius);

        //tronc
        t.walk(20);

        // cames
        t.rotate(-30);
        t.walk(20);
        t.walk(-20);
        t.rotate(60);
        t.walk(20);
        t.walk(-20);
        t.rotate(-30);

        // braços
        t.walk(-15);
        t.rotate(90);
        t.walk(15);
        t.walk(-30);

        t.drawPolyline(g2);
    }
}
