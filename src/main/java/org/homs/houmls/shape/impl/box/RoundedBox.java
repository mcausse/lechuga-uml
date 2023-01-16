package org.homs.houmls.shape.impl.box;

import org.homs.houmls.GridControl;
import org.homs.houmls.shape.Shape;

import java.awt.*;

import static org.homs.houmls.LookAndFeel.basicStroke;

public class RoundedBox extends Box {

    public RoundedBox(int x, int y, int width, int height, String attributesText) {
        super(x, y, width, height, attributesText);
    }

    @Override
    public Shape duplicate(int translatex, int translatey) {
        var r = new RoundedBox((int) x + translatex, (int) y + translatey, (int) width, (int) height, attributesText);
        r.setAttributesText(attributesText);
        return r;
    }

    protected void drawTheBox(Graphics2D g2) {

        int ix = (int) x;
        int iy = (int) y;
        int iwidth = (int) width;
        int iheight = (int) height;

        g2.setStroke(basicStroke);

        int roundedRadius = GridControl.GRID_SIZE * 4;

        g2.setColor(backgroundColor);
        g2.fillRoundRect(ix, iy, iwidth, iheight, roundedRadius, roundedRadius);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(ix, iy, iwidth, iheight, roundedRadius, roundedRadius);
    }
}
