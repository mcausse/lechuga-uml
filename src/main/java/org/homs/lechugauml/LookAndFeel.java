package org.homs.lechugauml;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Lechuga UML - Powered with LechugaScript and with bocadillos
 *
 * @author mohms
 */
public class LookAndFeel {

    public static final Color yellowMartin = new Color(0xff, 0xff, 0xda);

    protected static final String regularFontName = Font.SANS_SERIF;
    protected static final String monospaceFontName = Font.MONOSPACED;
    public static final int regularFontSize = 12;

    public static Font regularFont() {
        return new Font(regularFontName, Font.PLAIN, regularFontSize);
    }

    public static Font regularFont(int size) {
        return new Font(regularFontName, Font.PLAIN, size);
    }

    public static Font regularFontBold(int size) {
        return new Font(regularFontName, Font.BOLD, size);
    }

    public static Font regularFontItalic(int size) {
        return new Font(regularFontName, Font.ITALIC, size);
    }

    public static Font monospaceFont(int size) {
//        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
//        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//        return new Font(monospaceFontName, Font.BOLD, size).deriveFont(fontAttributes);
        return new Font(monospaceFontName, Font.PLAIN, size);
    }

    public static final Stroke basicStroke = new BasicStroke(1);
//    public static final Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);

    public static final Color DEFAULT_SHADOW_COLOR = Color.GRAY;
    public static final String DEFAULT_SHADOW_WIDTH = "2";

    public static boolean turbo = false;

    public static void setRenderingHints(Graphics2D g2) {
        if (turbo) {
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        } else {
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        }
    }

    public static final Color SHAPE_SELECTED_COLOR = new Color(0, 142, 255);
    public static final BasicStroke MULTI_SELECTION_STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{5}, 0);

    public static Image loadImage(String name) {
        final String baseURL = "org/homs/lechugauml/";
        final URL resource = MainC2.class.getClassLoader().getResource(baseURL + name);
        if (resource == null) {
            throw new RuntimeException("error loading resource: " + name);
        }

        return Toolkit.getDefaultToolkit().getImage(resource);
    }

    public static Icon loadIcon(String name) {
        return new ImageIcon(loadImage(name));
    }
}


