package org.homs.lechugauml.shape;

import org.homs.lechugauml.Diagram;
import org.homs.lechugauml.shape.impl.connector.Connector;

import java.awt.*;

/**
 * Lechuga UML - Powered with LechugaScript and with bocadillos
 *
 * @author mohms
 */
public interface Draggable {

    Cursor getTranslationCursor();

    Rectangle getRectangle();

    void translate(Diagram diagram, double dx, double dy);

    /**
     * Es crida en acabar d'arrossegar el component.
     * Necessari per a engridar les coordenades que han canviat, linkar/deslinkar connectors, etc.
     *
     * @param diagram necessari per als {@link Connector}s, per tal
     *                de poder-se linkar amb altres {@link Shape}s.
     */
    void dragHasFinished(Diagram diagram);

}