package org.homs.lechugauml;

import org.homs.lechugauml.xml.HoumlsFileFormatManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Lechuga UML - Powered with LechugaScript and with bocadillos
 *
 * @author mohms
 */
public class ExportAsPng {

    public static void main(String[] args) throws Exception {

        final CmdArgumentsProcessor argsProcessor = new CmdArgumentsProcessor(args);
        argsProcessor.processArgs();

        String inputFileName = argsProcessor.files.get(0);
        double zoom = Double.parseDouble(argsProcessor.modifiers.getOrDefault("zoom", "3.0"));
        String outputFileFormat = argsProcessor.modifiers.getOrDefault("format", "png");
        String outputFileName = argsProcessor.modifiers.getOrDefault("output", inputFileName + "." + outputFileFormat);

        GridControl.drawGrid = Boolean.parseBoolean(argsProcessor.modifiers.getOrDefault("grid", "true"));

        final Diagram diagram = HoumlsFileFormatManager.loadFile_v3(inputFileName);

        //
        System.out.print("Exporting: " + String.join(" ", args) + "...");

        exportAsPng(zoom, outputFileFormat, outputFileName, diagram);
    }

    public static void exportAsPng(double zoom, String outputFileFormat, String outputFileName, Diagram diagram) throws IOException {

        Canvas canvas = new Canvas(new JTextArea(), Collections.emptyList());
        canvas.setDiagram(diagram);
        Rectangle diagramBounds = canvas.getDiagram().getDiagramBounds();
        diagramBounds.grow(100, 100);

        canvas.setSize((int) (diagramBounds.width * zoom), (int) (diagramBounds.height * zoom));
        canvas.centerDiagram();
        canvas.getDiagram().zoom = zoom;

        BufferedImage bi = new BufferedImage((int) (diagramBounds.width * zoom), (int) (diagramBounds.height * zoom), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();

        canvas.paint(g2);

        ImageIO.write(bi, outputFileFormat.toUpperCase(), new File(outputFileName));

        System.out.println(" OK");
    }
}


