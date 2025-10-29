/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * The tool to create a rounded rectangle.
 * 
 * @author Ryan Wilkerson
 * @version 3/8/2024
 */
public class RoundRectangleTool extends AbstractTool {
    
    /**
     * Initializes the start and end point of our round rectangle.
     * 
     * @param theStartPoint of the shape.
     * @param theEndPoint of the shape.
     */
    public RoundRectangleTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    @Override
    public Shape getShape() {
        
        final RoundRectangle2D.Double roundRect = 
                new RoundRectangle2D.Double(0, 0, 1, 1, 50, 50);

        roundRect.setFrameFromDiagonal(getStartPoint(), getEndPoint());

        return roundRect;
    }
    
}
