package org.homs.houmls.shape;

import org.homs.houmls.Diagram;
import org.homs.houmls.shape.impl.Connector;

import java.awt.*;

public interface Draggable {

    Cursor getTranslationCursor();

    Rectangle getRectangle();

    void translate(Diagram diagram, double dx, double dy);

    /**
     * Es crida en acabar d'arrossegar el component.
     * Necessari per a engidar les coordenades que han canviat, linkar/deslinkar connectors, etc.
     *
     * @param diagram necessari per als {@link Connector}s, per tal
     *                 de poder-se linkar amb altres {@link Shape}s.
     * @param diagram
     */
    void dragHasFinished(Diagram diagram);

}