/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * The tool to create a line.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class LineTool extends AbstractTool {

    /**
     * Initializes the line tool.
     * 
     * @param theStartPoint of the line drawn.
     * @param theEndPoint of the line drawn.
     */
    public LineTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    

    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
    
}
