package org.homs.lechugauml;

import org.homs.lechugauml.shape.Shape;
import org.homs.lechugauml.shape.impl.box.Box;
import org.homs.lechugauml.shape.impl.box.*;
import org.homs.lechugauml.shape.impl.connector.BocadilloConnector;
import org.homs.lechugauml.shape.impl.connector.Connector;
import org.homs.lechugauml.shape.impl.connector.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

import static org.homs.lechugauml.LookAndFeel.DEFAULT_SHADOW_WIDTH;
import static org.homs.lechugauml.shape.impl.connector.Connector.SELECTION_BOX_SIZE;

/**
 * Lechuga UML - Powered with LechugaScript and with bocadillos
 *
 * @author mohms
 */
public class CanvasPopupMenuBuilder {

    final Canvas canvas;

    public CanvasPopupMenuBuilder(Canvas canvas) {
        this.canvas = canvas;
    }

    protected JPopupMenu buildPopupMenu(List<Shape> selectedShapes, Shape shapeUnderMouse, Point2D mousePos) {
        JPopupMenu pm = new JPopupMenu();

        if (shapeUnderMouse == null) {
            JMenuItem createConnector = new JMenuItem("+ connector");
            createConnector.setIcon(LookAndFeel.loadIcon("actions/connector.png"));
            pm.add(createConnector);
            createConnector.addActionListener(e -> {
                canvas.getDiagram().addShape(new Connector(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(mousePos.getX()) + 18 * GridControl.GRID_SIZE,
                        GridControl.engrid(mousePos.getY()),
                        "lt=->\n" +
                                "strokewidth=1\n" +
                                "color=black\n" +
                                "m1=\n" +
                                "m2=\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            pm.addSeparator();

            JMenuItem createClass = new JMenuItem("+ class");
            createClass.setIcon(LookAndFeel.loadIcon("actions/cclass.png"));
            pm.add(createClass);
            createClass.addActionListener(e -> {
                canvas.getDiagram().addShape(new Box(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(8 * GridControl.GRID_SIZE),
                        "._<<>>\n" +
                                ".*C\n" +
                                "--\n" +
                                "--\n" +
                                "bg=orange\n" +
                                "lighter=2\n" +
                                "shadow=" + DEFAULT_SHADOW_WIDTH + "\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createComment = new JMenuItem("+ comment");
            createComment.setIcon(LookAndFeel.loadIcon("actions/ccomment.png"));
            pm.add(createComment);
            createComment.addActionListener(e -> {
                canvas.getDiagram().addShape(new Comment(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(14 * GridControl.GRID_SIZE),
                        "Note...\n" +
                                "bg=yellow\n" +
                                "lighter=2\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createRoundedBox = new JMenuItem("+ rounded");
            createRoundedBox.setIcon(LookAndFeel.loadIcon("actions/crounded.png"));
            pm.add(createRoundedBox);
            createRoundedBox.addActionListener(e -> {
                canvas.getDiagram().addShape(new RoundedBox(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(14 * GridControl.GRID_SIZE),
                        ".*Title\n--\nfontsize=24\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createEllipse = new JMenuItem("+ ellipse");
            createEllipse.setIcon(LookAndFeel.loadIcon("actions/cellipse.png"));
            pm.add(createEllipse);
            createEllipse.addActionListener(e -> {
                canvas.getDiagram().addShape(new Ellipse(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(9 * GridControl.GRID_SIZE),
                        "\n" +
                                "\n" +
                                ".Title\n" +
                                "bg=red\n" +
                                "lighter=3\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createTextBox = new JMenuItem("+ text");
            createTextBox.setIcon(LookAndFeel.loadIcon("actions/text.png"));
            pm.add(createTextBox);
            createTextBox.addActionListener(e -> {
                canvas.getDiagram().addShape(new FloatingText(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(14 * GridControl.GRID_SIZE),
                        ".*Title\nfontsize=24\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createActor = new JMenuItem("+ actor");
            createActor.setIcon(LookAndFeel.loadIcon("actions/actor.png"));
            pm.add(createActor);
            createActor.addActionListener(e -> {
                canvas.getDiagram().addShape(new Actor(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        ".Actor\n"
                ));
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
            JMenuItem createBocadillo = new JMenuItem("+ bocadillo");
            createBocadillo.setIcon(LookAndFeel.loadIcon("actions/cbocadillo.png"));
            pm.add(createBocadillo);
            createBocadillo.addActionListener(e -> {
                final BocadilloConnector bocadillo = new BocadilloConnector(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(mousePos.getX() + 18 * GridControl.GRID_SIZE),
                        GridControl.engrid(mousePos.getY()),
                        "lt=-\n" +
                                "bg=yellow\n" +
                                "lighter=3\n"
                );
                bocadillo.getMiddlePoints().add(new DoublePoint(
                        GridControl.engrid(mousePos.getX() + GridControl.GRID_SIZE * 6),
                        GridControl.engrid(mousePos.getY() + GridControl.GRID_SIZE * 6)
                ));
                canvas.getDiagram().addShape(bocadillo);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            JMenuItem createTurtleBox = new JMenuItem("+ turtle");
            createTurtleBox.setIcon(LookAndFeel.loadIcon("actions/turtle.png"));
            pm.add(createTurtleBox);
            createTurtleBox.addActionListener(e -> {
                final TurtleBox turtleBox = new TurtleBox(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(8 * GridControl.GRID_SIZE),
                        "rotate 90 jump 20 rotate -90\n" +
                                "rotate -45 walk 20 \n" +
                                "rotate 45 walk 50\n" +
                                "rotate 45 walk 20\n" +
                                "rotate 90 walk 55\n" +
                                "rotate 90 walk 55\n" +
                                "rotate 135 walk 76\n" +
                                "draw\n"
                );
                canvas.getDiagram().addShape(turtleBox);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            JMenuItem createLechugaBox = new JMenuItem("+ lechuga");
            createLechugaBox.setIcon(LookAndFeel.loadIcon("actions/cclechuga.png"));
            pm.add(createLechugaBox);
            createLechugaBox.addActionListener(e -> {
                final LechugaScriptBox turtleBox = new LechugaScriptBox(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(8 * GridControl.GRID_SIZE),
                        ";;; \n" +
                                ";;; \n" +
                                ";;; <component description>\n" +
                                ";;; \n" +
                                ";;; \n" +
                                ";\n" +
                                "; variables: *turtle* *graphics* box-x box-y box-width box-height box-bg-color box-font-size\n" +
                                ";" +
                                "((field-static :java.lang.System :out) :print :jou)\n" +
                                "bg=green\n" +
                                "paintbackground=true\n"
                );
                canvas.getDiagram().addShape(turtleBox);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            JMenuItem imageBox = new JMenuItem("+ image");
            imageBox.setIcon(LookAndFeel.loadIcon("actions/cimage.png"));
            pm.add(imageBox);
            imageBox.addActionListener(e -> {
                final ImageBox turtleBox = new ImageBox(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(8 * GridControl.GRID_SIZE),
                        "image=\n" +
                                "paintbackground=true\n" +
                                "bg=green\n"
                );
                canvas.getDiagram().addShape(turtleBox);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            JMenuItem packageBox = new JMenuItem("+ package");
            packageBox.setIcon(LookAndFeel.loadIcon("actions/cpackage.png"));
            pm.add(packageBox);
            packageBox.addActionListener(e -> {
                final PackageBox packageeBox = new PackageBox(
                        GridControl.engrid(mousePos.getX()),
                        GridControl.engrid(mousePos.getY()),
                        GridControl.engrid(18 * GridControl.GRID_SIZE),
                        GridControl.engrid(8 * GridControl.GRID_SIZE),
                        "_<<jou>>\n" +
                                "*Package\n" +
                                "_jou.juas:0.0.2\n" +
                                "--\n" +
                                "bg=orange\n"
                );
                canvas.getDiagram().addShape(packageeBox);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
        } else if (!Connector.class.isAssignableFrom(shapeUnderMouse.getClass())) {
            /*
             * POPUP MENU: BOX
             */

            JMenuItem toFront = new JMenuItem("to front");
            toFront.setIcon(LookAndFeel.loadIcon("actions/cto-front.png"));
            pm.add(toFront);
            toFront.addActionListener(e -> {
                canvas.getDiagram().sendToFront(shapeUnderMouse);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            JMenuItem toBack = new JMenuItem("to back");
            toBack.setIcon(LookAndFeel.loadIcon("actions/cto-back.png"));
            pm.add(toBack);
            toBack.addActionListener(e -> {
                canvas.getDiagram().sendToBack(shapeUnderMouse);
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });

            pm.addSeparator();

            JMenuItem deleteBox = new JMenuItem("remove");
            deleteBox.setIcon(LookAndFeel.loadIcon("actions/cdelete.png"));
            pm.add(deleteBox);
            deleteBox.addActionListener(e -> {
                canvas.getDiagram().getShapes().removeAll(selectedShapes);
                selectedShapes.clear();
                canvas.pushUndoCheckpoint();
                canvas.repaint();
            });

        } else if (Connector.class.isAssignableFrom(shapeUnderMouse.getClass())) {

            /*
             * POPUP MENU: CONNECTOR
             */
            popupMenuForConnector_CreateMiddlePoint(selectedShapes, mousePos, pm);
            popupMenuForConnector_DeleteMiddlePoint(selectedShapes, mousePos, pm);

            pm.addSeparator();

            JMenuItem remove = new JMenuItem("remove");
            remove.setIcon(LookAndFeel.loadIcon("actions/cdelete.png"));
            pm.add(remove);
            remove.addActionListener(e -> {
                canvas.getDiagram().getShapes().removeAll(selectedShapes);
                selectedShapes.clear();
                canvas.repaint();
                canvas.pushUndoCheckpoint();
            });
        }
        return pm;
    }

    private void popupMenuForConnector_CreateMiddlePoint(List<Shape> selectedShapes, Point2D mousePos, JPopupMenu pm) {
        if (selectedShapes.size() != 1) {
            return;
        }
        JMenuItem createPoint = new JMenuItem("add new point");
        createPoint.setIcon(LookAndFeel.loadIcon("actions/ccreate-point.png"));
        pm.add(createPoint);
        createPoint.addActionListener(e -> {
            Connector conn = (Connector) selectedShapes.get(0);

            java.util.List<Point> points = conn.getListOfAbsolutePoints();
            for (var i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                Rectangle pointSelectionBox = new Rectangle(p.x - SELECTION_BOX_SIZE, p.y - SELECTION_BOX_SIZE, SELECTION_BOX_SIZE * 2, SELECTION_BOX_SIZE * 2);
                if (pointSelectionBox.contains(mousePos.getX(), mousePos.getY())) {

                    /*
                     * CREATES A NEW MIDDLE POINT IN THE CONNECTOR
                     */
                    int indexOfClickedPoint = points.indexOf(p);
                    final Point otherPoint;
                    if (indexOfClickedPoint == points.size() - 1) {
                        otherPoint = points.get(points.size() - 2);
                    } else {
                        otherPoint = points.get(indexOfClickedPoint + 1);
                    }
                    var middlePointToCreate = new DoublePoint((p.x + otherPoint.x) / 2.0, (p.y + otherPoint.y) / 2.0);

                    if (indexOfClickedPoint >= conn.getMiddlePoints().size()) {
                        conn.getMiddlePoints().add(middlePointToCreate);
                        canvas.pushUndoCheckpoint();
                    } else {
                        conn.getMiddlePoints().add(indexOfClickedPoint, middlePointToCreate);
                        canvas.pushUndoCheckpoint();
                    }
                    break;
                }
            }
            canvas.repaint();
        });
    }

    private void popupMenuForConnector_DeleteMiddlePoint(List<Shape> selectedShapes, Point2D mousePos, JPopupMenu pm) {

        if (selectedShapes.size() != 1) {
            return;
        }

        JMenuItem deletePoint = new JMenuItem("delete point");
        deletePoint.setIcon(LookAndFeel.loadIcon("actions/cdelete-point.png"));
        pm.add(deletePoint);
        deletePoint.addActionListener(e -> {
            Connector conn = (Connector) selectedShapes.get(0);

            List<Point> points = conn.getListOfAbsolutePoints();
            if (points.size() <= 2) {
                return;
            }
            for (var i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                Rectangle pointSelectionBox = new Rectangle(p.x - SELECTION_BOX_SIZE, p.y - SELECTION_BOX_SIZE, SELECTION_BOX_SIZE * 2, SELECTION_BOX_SIZE * 2);
                if (pointSelectionBox.contains(mousePos.getX(), mousePos.getY())) {

                    if (i == 0) {
                        // remove first point
                        conn.getStartPoint().linkedShape = null;
                        conn.getStartPoint().posx = conn.getMiddlePoints().get(0).x;
                        conn.getStartPoint().posy = conn.getMiddlePoints().get(0).y;
                        conn.getMiddlePoints().remove(0);
                        canvas.pushUndoCheckpoint();
                    } else if (i == points.size() - 1) {
                        // remove last point
                        conn.getEndPoint().linkedShape = null;
                        conn.getEndPoint().posx = conn.getMiddlePoints().get(conn.getMiddlePoints().size() - 1).x;
                        conn.getEndPoint().posy = conn.getMiddlePoints().get(conn.getMiddlePoints().size() - 1).y;
                        conn.getMiddlePoints().remove(conn.getMiddlePoints().size() - 1);
                        canvas.pushUndoCheckpoint();
                    } else {
                        // remove middle point
                        conn.getMiddlePoints().remove(i - 1);
                        canvas.pushUndoCheckpoint();
                    }

                    break;
                }
            }

            canvas.repaint();
        });
    }

}
